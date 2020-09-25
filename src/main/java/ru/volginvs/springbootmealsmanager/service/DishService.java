package ru.volginvs.springbootmealsmanager.service;

import ru.volginvs.springbootmealsmanager.domain.Dish;
import ru.volginvs.springbootmealsmanager.domain.Food;
import ru.volginvs.springbootmealsmanager.repos.DishRepo;
import ru.volginvs.springbootmealsmanager.repos.FoodRepo;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;


@Service
public class DishService {
    
    @Autowired
    private DishRepo dishRepo;

    private boolean existsById(Long id) {
        return dishRepo.existsById(id);
    }
    private boolean existsByName(String name ) {
        return dishRepo.existsByName(name);
    }    
    public Dish findById(Long id){
        return dishRepo.findById(id).orElse(null);
    }
    public Dish findByName(String name) {
        //return dishRepo.findByName(name);
        return name!=null? dishRepo.findByName(name): null;
    }

    public List<Dish> findAll() {
        List<Dish> dishList = new ArrayList<>();
        dishList=dishRepo.findAll(new Sort(Sort.Direction.ASC, "name"));
        return dishList;
    }
    
    //public List<Dish> findAllByName(String name) {
    //    List<Dish> dishList = new ArrayList<>();
    //    dishList=dishRepo.findAllByName(name);
    //    return dishList;
    //}
    
    public void delete(Dish dish){
        dishRepo.delete(dish);
    }
    public void deleteByName(String name)  {
                  
        dishRepo.deleteByName(name);
    }        
    public Dish save(Dish dish) throws Exception {
        if (StringUtils.isEmpty(dish.getName())) {
            throw new Exception("Dish's name is required");
        } 
    
        if (existsByName(dish.getName())) { 
            throw new Exception("Dish with name: '" + dish.getName()+"' is already exists");
        }             
        return dishRepo.save(dish);
    }  
    
    public void update(Dish dish) throws Exception{
        if (StringUtils.isEmpty(dish.getName())) {
            throw new Exception("Dish's name is required");
        } 
    
        if (existsByName(dish.getName()) && findByName(dish.getName()).getId()!=dish.getId()) { 
            throw new Exception("Dish with name: '" + dish.getName()+"' is already exists");
        }                     
        dishRepo.save(dish);
    }    
    



    
}
