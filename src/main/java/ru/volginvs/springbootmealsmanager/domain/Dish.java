package ru.volginvs.springbootmealsmanager.domain;

import javax.persistence.*;

import ru.volginvs.springbootmealsmanager.domain.DishIngredient;
import java.util.ArrayList;


import java.util.List;
import org.springframework.data.domain.Sort;



@Entity
public class Dish {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    
    @OneToMany(mappedBy="dish")
    private List<DishIngredient> dishIngredients = new ArrayList<DishIngredient>();

    
    public Dish(){
        
    }
    public Dish(String name) {
        this.name=name;
    }
  
    public String getIngredientInfo(){
        return this.getDishIngredients().toString();
    }
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }   
    
    public List<DishIngredient> getDishIngredients(){
        return this.dishIngredients;
    }
    
            
    public void addDishIngredient(DishIngredient dishIngredient){
        this.dishIngredients.add(dishIngredient) ;
        if(dishIngredient.getDish()!=this ) dishIngredient.setDish(this);
    }    
    
    public String toString(){
        return this.getName();
    }
    

}