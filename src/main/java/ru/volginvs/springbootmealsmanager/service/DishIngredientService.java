/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.volginvs.springbootmealsmanager.service;

import ru.volginvs.springbootmealsmanager.domain.Dish;
import ru.volginvs.springbootmealsmanager.domain.DishIngredient;
import ru.volginvs.springbootmealsmanager.domain.Food;
import ru.volginvs.springbootmealsmanager.repos.DishIngredientRepo;
import ru.volginvs.springbootmealsmanager.repos.FoodRepo;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class DishIngredientService {
    
    @Autowired
    DishIngredientRepo dishIngredientRepo;
    
    @Autowired
    FoodService foodService; 
    
    @Autowired
    DishService dishService;
    
    
    public DishIngredient findById(Long id){
        return dishIngredientRepo.findById(id).orElse(null);
    }
    public boolean existsByDishAndFood(Dish dish, Food food){
        return dishIngredientRepo.existsByDishAndFood(dish, food);
    }
    public DishIngredient findByDishAndFood(Food food){
        return dishIngredientRepo.findByFood(food);
    }
       
    public DishIngredient findByDishAndFood(Dish dish,Food food){
        return dishIngredientRepo.findByDishAndFood(dish,food);
    }
    
    public List<DishIngredient> findAllByDishId(Long id){
        List<DishIngredient> dishIngredientList = new ArrayList<>();
        dishIngredientList=dishIngredientRepo.findAllByDish(dishService.findById(id), new Sort(Sort.Direction.ASC, "food"));
        return dishIngredientList;
    }    
     
    public DishIngredient save(DishIngredient dishIngredient) throws Exception{

        if (StringUtils.isEmpty(dishIngredient.getFood())) {
            throw new Exception("'Food' for ingredient is required");
        } 
        if (StringUtils.isEmpty(dishIngredient.getServingWeight())) {
            throw new Exception("Set 'Serving Weight' for ingredient");
        } 
        if ( dishIngredient.getServingWeight()<1 ) {
            throw new Exception(" Use  numbers above zero for 'Serving Weight'");
        } 
        
        if (existsByDishAndFood(dishIngredient.getDish(),dishIngredient.getFood())) { 
            throw new Exception("'" + dishIngredient.getFood().getName()+"' is already uses as an ingredient in '"+dishIngredient.getDish().getName()+"'");
        }   
        return dishIngredientRepo.save(dishIngredient);
    }
    
    public DishIngredient update(Long dishIngredientId, Long foodId, Long servingWeight) throws Exception{
        
        DishIngredient dishIngredient = findById(dishIngredientId);
        Dish dish = dishService.findById(dishIngredient.getDish().getId());
        
        if (foodId==null || foodService.findById(foodId)==null) {
            throw new Exception("For the ingredient, a 'Food' existing in database is required");
        } 
        if (servingWeight==null) {
            throw new Exception("Set 'Serving Weight' for ingredient");
        } 
        if (servingWeight<1 ) {
            throw new Exception(" Use  numbers above zero for 'Serving Weight'");
        } 
        
        if (existsByDishAndFood(dish,foodService.findById(foodId))&&findByDishAndFood(dish,foodService.findById(foodId)).getId()!=dishIngredient.getId()) { 
            throw new Exception("'" + dishIngredient.getFood().getName()+"' is already uses as an ingredient in '"+dishIngredient.getDish().getName()+"'");
        }          
        dishIngredient.setFood(foodService.findById(foodId));
        dishIngredient.setServingWeight(servingWeight);  
        dish.addDishIngredient(dishIngredient);        
        return dishIngredientRepo.save(dishIngredient);
    }
    
    public DishIngredient delete(Long id){
        DishIngredient dishIngredient = findById(id);
        dishIngredientRepo.delete(dishIngredient);
        return dishIngredient;
    }

    public DishIngredient findByFoodAndServingWeight(Food food, Long servingWeight){
        return  dishIngredientRepo.findByFoodAndServingWeight(food, servingWeight);
    };   
}
