package ru.volginvs.springbootmealsmanager.repos;

import ru.volginvs.springbootmealsmanager.domain.Dish;
import ru.volginvs.springbootmealsmanager.domain.DishIngredient;
import ru.volginvs.springbootmealsmanager.domain.Food;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;


public interface DishIngredientRepo extends CrudRepository<DishIngredient, Long> {
   
    public void delete(DishIngredient dishIngredient);  
    public boolean existsByDishAndFood(Dish dish, Food food);    
    public boolean existsByFood(Food food);
    public List<DishIngredient> findAll(Sort sort);
    public List<DishIngredient> findAllByDish(Dish dish, Sort sort); 
    public DishIngredient findByDishAndFood(Dish dish, Food food);    
    public DishIngredient findByFood(Food food);
    public DishIngredient findByFoodAndServingWeight(Food food, Long servingWeight);    






 
 
    
    
}
