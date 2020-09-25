package ru.volginvs.springbootmealsmanager.service;

import ru.volginvs.springbootmealsmanager.domain.Food;
import ru.volginvs.springbootmealsmanager.repos.FoodRepo;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


@Service
public class FoodService {
    
    @Autowired
    private FoodRepo foodRepo;
    
    
    public List<Food> findAll() {
        List<Food> foodList = new ArrayList<>();
        foodList=foodRepo.findAll(new Sort(Sort.Direction.ASC, "name"));
        return foodList;
    }
    
    public boolean existsByNameAndSpecies(String name, String species){
        return foodRepo.existsByNameAndSpecies(name, species);
    }  
    
    //??????????
    public Food findByNameAndSpecies(String name, String species){
        return foodRepo.findByNameAndSpecies(name,species);
    }
    public List<Food> findAllByName(String name){
        return foodRepo.findAllByName(name);
    }  
    public Food save(Food food) throws Exception{
        
        if (StringUtils.isEmpty(food.getName())) {
            throw new Exception("Food's name is required");
        } 
        if (StringUtils.isEmpty(food.getSpecies())) {
            throw new Exception("The species  is requered");
        }           
        if (existsByNameAndSpecies(food.getName(),food.getSpecies())) { 
            throw new Exception("Food with name: '" + food.getName() +"' and species: '" + food.getSpecies()+"' is already exists");
        }                 
        return foodRepo.save(food);
    }
    
    public Food findById(Long id){
        return foodRepo.findById(id).orElse(null);
    }    

    public void update(Food food) throws Exception {
        if (StringUtils.isEmpty(food.getName())) {
            throw new Exception("Food's name is required");
        } 
        if (StringUtils.isEmpty(food.getSpecies())) {
            throw new Exception("The species of Food is requered");
        }           
        if (existsByNameAndSpecies(food.getName(),food.getSpecies()) && findByNameAndSpecies(food.getName(),food.getSpecies()).getId()!=food.getId()) { 
            throw new Exception("Food with name: '" + food.getName() +"' and species: '" + food.getSpecies()+"' is already exists");
        }              
        foodRepo.save(food);
    }
    public void delete(Food food){
        foodRepo.delete(food);
    }
    
    
}
