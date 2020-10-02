package ru.volginvs.springbootmealsmanager.cotroller;


import java.util.List;
import ru.volginvs.springbootmealsmanager.domain.Food;
import ru.volginvs.springbootmealsmanager.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Map;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FoodController {

    @Autowired
    private FoodService foodService;
    
    @GetMapping("/foods")
    public String showFoodList(
            @RequestParam(required = false, defaultValue = "") String filter ,Map<String,Object> model) {
        
        List<Food> foodList = foodService.findAll();
        model.put("foodList", foodList);
        model.put("filter", filter);
        return "foods";
    }
    @PostMapping("/foods")
    public String addFood(
        @RequestParam String name,
        @RequestParam String species, Map<String, Object> model){
        
        try{
            Food food = new Food(name,  species);
            foodService.save(food);
            return "redirect:/foods";        
        } catch (Exception ex){
            model.put("errorMessage", ex.getMessage());
            List<Food> foodList = foodService.findAll();
            model.put("foodList", foodList);           
            return "foods";         
        }
    }
    @GetMapping(value = {"/food-{id}-edit"})
    public String showFoodEdit(
            @PathVariable Long id,              
            Map<String, Object> model) {
        
        Food food= foodService.findById(id);
        model.put("food", food);  
        return "food-edit";
    }
    
    @PostMapping(value = {"/food-{id}-edit"})
    public String updateFood(
        @PathVariable Long id,      
        @RequestParam String name,    
        @RequestParam String species, Map<String, Object> model){
        
        try{
            foodService.update(id, name, species);    
            return "redirect:/foods";
        } catch (Exception ex){
            model.put("errorMessage", ex.getMessage());
            Food food= foodService.findById(id);
            model.put("food", food);           
            return "food-edit";    
        }
    }        
    @GetMapping(value = {"/food-{id}-delete"})
    public String deleteFood(
        @PathVariable Long id, Map<String, Object> model){      
        
        foodService.delete(id);    
        return "redirect:/foods";
    }      
}