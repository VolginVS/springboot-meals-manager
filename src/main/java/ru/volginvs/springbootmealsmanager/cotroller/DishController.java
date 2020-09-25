package ru.volginvs.springbootmealsmanager.cotroller;


import ru.volginvs.springbootmealsmanager.domain.Food;
import ru.volginvs.springbootmealsmanager.domain.Dish;
import ru.volginvs.springbootmealsmanager.domain.DishIngredient;
import ru.volginvs.springbootmealsmanager.service.DishIngredientService;
import ru.volginvs.springbootmealsmanager.service.DishService;
import ru.volginvs.springbootmealsmanager.service.FoodService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Map;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DishController {

    @Autowired
    private FoodService foodService;
    @Autowired
    private DishService dishService;
    @Autowired
    private DishIngredientService dishIngredientService;  

    @GetMapping("/dishes")
    public String showDishList(Map<String, Object> model) {
        List<Dish> dishList  = dishService.findAll();
        model.put("dishList", dishList);
        return "dishes";
    }
    @PostMapping("/dishes")
    public String addDish(         
        @RequestParam String dishName, Map<String, Object> model) {
        
      try{  
        Dish dish = new Dish(dishName);
        dishService.save(dish);
        return "redirect:/dishes";
      } catch(Exception ex){
        model.put("errorMessage", ex.getMessage());
        
        List<Dish> dishList = dishService.findAll();
        model.put("dishList", dishList);
        return "dish";
      }
    }
    @GetMapping("/dish-{id}-delete")
    public String deleteDish(
        @PathVariable Long id, Map<String, Object> model){
        
        Dish dish= dishService.findById(id);
        dishService.delete(dish);    
        return "redirect:/dishes";
    }       
    @GetMapping(value = {"/dish-{id}-edit"})
    public String showDishEdit(
            @PathVariable Long id,
            Map<String, Object> model) {
        
        List<DishIngredient> dishIngredientList = dishIngredientService.findAllByDish(dishService.findById(id));
        List<Food> foodList = foodService.findAll();
        model.put("dish", dishService.findById(id));
        model.put("dishIngredientList", dishIngredientList);
        model.put("foodList", foodList);        
        return "dish-edit";
    } 
    @PostMapping(value = {"/dish-{id}-edit"})
    public String addDishIngredient(
        @PathVariable Long id, 
        @RequestParam Long foodId,  
        @RequestParam Long servingWeight, Map<String, Object> model){
        
        Dish dish = dishService.findById(id);
        try{
        DishIngredient dishIngredient = new DishIngredient(dish, foodService.findById(foodId), servingWeight);  
        dishIngredientService.save(dishIngredient);
        dish.addDishIngredient(dishIngredient);
        return "redirect:/dish-{id}-edit";
        }catch(Exception ex){
            model.put("errorMessage", ex.getMessage());
            List<DishIngredient> dishIngredientList = dishIngredientService.findAllByDish(dish);
            List<Food> foodList = foodService.findAll();
            model.put("dish", dish);            
            model.put("dishIngredientList", dishIngredientList);
            model.put("foodList", foodList); 
            return "dish-edit";
        }
    }    
    @PostMapping(value = {"/dish-{id}-edit/name"})
    public String updateDishName(
        @PathVariable Long id, 
        @RequestParam String dishName, Map<String, Object> model){
        
        Dish dish = dishService.findById(id);   
        try{ 
            dish.setName(dishName);
            dishService.update(dish);
            return "redirect:/dish-{id}-edit";
        } catch (Exception ex){
            model.put("errorMessage", ex.getMessage());
            List<DishIngredient> dishIngredientList = dishIngredientService.findAllByDish(dish);
            List<Food> foodList = foodService.findAll();
            model.put("dish", dish);            
            model.put("dishIngredientList", dishIngredientList);
            model.put("foodList", foodList);        
            return "dish-edit";
       }
    }      
    
    @PostMapping(value = {"/dish-{id}-edit/ingredient-{dishIngredientId}"})
    public String updateDishIngredient(
        @PathVariable Long id, 
        @PathVariable Long dishIngredientId,
        @RequestParam Long foodId,       
        @RequestParam Long servingWeight, Map<String, Object> model){

        Dish dish = dishService.findById(id);        
        try{ 
            DishIngredient dishIngredient =dishIngredientService.findById(dishIngredientId);
            dishIngredient.setServingWeight(servingWeight);
            dishIngredient.setFood(foodService.findById(foodId));
            dishIngredientService.update(dishIngredient);
            dish.addDishIngredient(dishIngredient);
            return "redirect:/dish-{id}-edit";
        } catch (Exception ex){
            model.put("errorMessage", ex.getMessage());
            List<DishIngredient> dishIngredientList = dishIngredientService.findAllByDish(dish);
            List<Food> foodList = foodService.findAll();
            model.put("dish", dish);            
            model.put("dishIngredientList", dishIngredientList);
            model.put("foodList", foodList);        
            return "dish-edit";
       }
    }  
    @GetMapping(value = {"/dish-{id}-edit/ingredient-{dishIngredientId}-delete"})
    public String deleteDishIngredient(
        @PathVariable Long id,            
        @PathVariable Long dishIngredientId, Map<String, Object> model){
               
        dishIngredientService.delete(dishIngredientService.findById(dishIngredientId));    
        return "redirect:/dish-{id}-edit";
    }           
}