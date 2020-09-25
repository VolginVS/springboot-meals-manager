/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.volginvs.springbootmealsmanager.service;

import ru.volginvs.springbootmealsmanager.domain.DayMeal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.volginvs.springbootmealsmanager.repos.DayMealRepo;

@Service
public class DayMealService {
    
    @Autowired
    DayMealRepo dayMealRepo;
    
    private boolean existsById(Long id){
        return dayMealRepo.existsById(id);
    }
    
    private boolean existsByDate(LocalDate date){
        return dayMealRepo.existsByDate(date);
    }
    
    public DayMeal findById(Long id){
        return dayMealRepo.findById(id).orElse(null);
    }
    
    public List<DayMeal> findAll() {
        List<DayMeal> mealList = new ArrayList<DayMeal>();
        mealList=dayMealRepo.findAll(new Sort(Sort.Direction.ASC, "date"));
        return mealList;
    }
    
    public DayMeal save(DayMeal meal) throws Exception  {
        if (StringUtils.isEmpty(meal.getBreakfast())) {
            throw new Exception("Breakfast is required");
        } 
        if (StringUtils.isEmpty(meal.getDinner())) {
            throw new Exception("Dinner is required");
        } 
        if (StringUtils.isEmpty(meal.getSupper())) {
            throw new Exception("Supper is required");
        }   
        if (StringUtils.isEmpty(meal.getDate())) {
            throw new Exception("Date is required");
        }           
        
        if (meal.getId() != null && existsById(meal.getId())) { 
            throw new Exception("Meal with id: " + meal.getId() + " is already exists");
        }
        if (meal.getDate() != null && existsByDate(meal.getDate())) { 
            throw new Exception("Meal with date: " + meal.getDate() + " is already exists. Please, set the enother date.");
        }       
        
        return dayMealRepo.save(meal);
    }  
    
    public void update(DayMeal meal) {
        
        //?????????????? Надо ли делать эксепшионы
        dayMealRepo.save(meal);
    }    
    
    public void deleteByDate(LocalDate date)  {
         dayMealRepo.deleteByDate(date);
        
    }    
    public DayMeal findByDate(LocalDate date){
        return dayMealRepo.findByDate(date);
    }

    public void delete(DayMeal meal)  {
         dayMealRepo.delete(meal);
    } 

}
