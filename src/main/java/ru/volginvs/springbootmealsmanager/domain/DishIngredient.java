package ru.volginvs.springbootmealsmanager.domain;

import javax.persistence.*;

import ru.volginvs.springbootmealsmanager.domain.Dish;


@Entity
public class DishIngredient {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name="dish_id")
    private Dish dish;
    
    @ManyToOne
    @JoinColumn(name="food_id")
    private Food food;
    
    private Long servingWeight;

    
    public DishIngredient(){
        
    }
    public DishIngredient(Dish dish, Food food,Long servingWeight) {
        this.dish=dish;
        this.food=food;
        this.servingWeight=servingWeight;    
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Dish getDish() {
        return dish;
    }
    public void setDish(Dish dish) {
        this.dish = dish;
        if (!dish.getDishIngredients().contains(this)) { 
            dish.getDishIngredients().add(this);
        }
    }
    
    public Food getFood() {
        return food;
    }
    public void setFood(Food food) {
        this.food = food;
    }   
    public Long getServingWeight() {
        return servingWeight;
    }
    public void setServingWeight(Long servingWeight) {
        this.servingWeight = servingWeight;
    }
    public String toString(){
        return this.getFood().toString()+"("+this.getServingWeight()+"г)"+"\n";
    }
             

}