package ru.volginvs.springbootmealsmanager.repos;

import ru.volginvs.springbootmealsmanager.domain.Food;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;


public interface FoodRepo extends CrudRepository<Food, Long> {


    public Food findByName(String name); 
    public List<Food> findAll(Sort sort); 
    public List<Food> findAllByName(String name);  
    public Food findByNameAndSpecies(String name, String species);
    public void deleteByNameAndSpecies(String name, String species);  
    public boolean existsByNameAndSpecies(String name, String species);
    
}



