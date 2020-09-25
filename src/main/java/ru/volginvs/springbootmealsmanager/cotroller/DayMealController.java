package ru.volginvs.springbootmealsmanager.cotroller;


import ru.volginvs.springbootmealsmanager.domain.Dish;
import ru.volginvs.springbootmealsmanager.domain.DayMeal;
import ru.volginvs.springbootmealsmanager.service.DishService;
import ru.volginvs.springbootmealsmanager.service.DayMealService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Map;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DayMealController {


    @Autowired
    private DishService dishService;

    @Autowired
    private DayMealService dayMealService; 
    
    @GetMapping("/meal-plan")
    public String showDayMealPlan(
            Map<String, Object> model) {
        
        List<DayMeal> dayMealList  = dayMealService.findAll();
        model.put("dayMealList", dayMealList);
        List<Dish> dishList = dishService.findAll();
        model.put("dishList", dishList);       
        return "meal-plan";
    }     
    @GetMapping(value = {"/meal-plan-{id}-delete"})
    public String deleteDayMeal(
        @PathVariable Long id, Map<String, Object> model){
        
        DayMeal dayMeal= dayMealService.findById(id);
        dayMealService.delete(dayMeal);    
        return "redirect:/meal-plan";
    }     
    
    @PostMapping("/meal-plan")
    public String addDayMeal(           
        @RequestParam String stringDate, 
        @RequestParam String breakfastName,
        @RequestParam String dinnerName,        
        @RequestParam String supperName, Map<String, Object> model) {
        
        try{ 
            LocalDate date = LocalDate.parse(stringDate);
            Dish breakfast=dishService.findByName(breakfastName);
            Dish dinner=dishService.findByName(dinnerName);
            Dish supper=dishService.findByName(supperName);         

            DayMeal dayMeal = new DayMeal(date,breakfast,dinner,supper);
            
            dayMealService.save(dayMeal);
            List<DayMeal> dayMealList = dayMealService.findAll();
            model.put("dayMealList", dayMealList);
            return "redirect:/meal-plan";
        }catch(Exception ex){
            String errorMessage = ex.getMessage();            
            model.put("errorMessage", errorMessage);
            List<DayMeal> dayMealList  = dayMealService.findAll();
            model.put("dayMealList", dayMealList);
            List<Dish> dishList = dishService.findAll();
            model.put("stringDateValueBeforeError",stringDate);           
            model.put("breakfastNameValueBeforeError",breakfastName);
            model.put("dinnerNameValueBeforeError",dinnerName);            
            model.put("supperNameValueBeforeError",supperName);            
            model.put("dishList", dishList);            
            return "meal-plan";
        }
    }    
    @GetMapping(value = {"/day-meal-{stringDate}"})
    public String showDayMealEdit(
            @PathVariable String stringDate, Map<String, Object> model) {
        
        LocalDate dayMealDate = LocalDate.parse(stringDate);
        DayMeal dayMeal=dayMealService.findByDate(dayMealDate);
        List<Dish> dishList = dishService.findAll();
        model.put("dayMeal", dayMeal);
        model.put("dishList", dishList);        
        return "day-meal-edit";
    }         
    @PostMapping(value = {"/day-meal-{stringDate}"})
    public String updateDayMeal(
        @PathVariable String stringDate,      
        @RequestParam String breakfastName,    
        @RequestParam String dinnerName,
        @RequestParam String supperName, Map<String, Object> model){
        
        LocalDate mealDate = LocalDate.parse(stringDate);      
        DayMeal dayMeal = dayMealService.findByDate(mealDate);
        dayMeal.setBreakfast(dishService.findByName(breakfastName));
        dayMeal.setDinner(dishService.findByName(dinnerName));
        dayMeal.setSupper(dishService.findByName(supperName));
        dayMealService.update(dayMeal);      
        return "redirect:meal-plan";
    }     

}