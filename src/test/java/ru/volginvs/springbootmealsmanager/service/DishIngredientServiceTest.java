package ru.volginvs.springbootmealsmanager.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import ru.volginvs.springbootmealsmanager.domain.Dish;
import ru.volginvs.springbootmealsmanager.domain.DishIngredient;
import ru.volginvs.springbootmealsmanager.domain.Food;
import ru.volginvs.springbootmealsmanager.repos.DishIngredientRepo;
import ru.volginvs.springbootmealsmanager.repos.DishRepo;

@RunWith(SpringRunner.class)
@SpringBootTest  
public class DishIngredientServiceTest {
    
    @Autowired 
    private DishIngredientService dishIngredientService;
    
    @MockBean
    private DishIngredientRepo dishIngredientRepo;
    
    @MockBean
    private DishService dishService;
     
    @MockBean
    private FoodService foodService;
     
    @Test
    public void testSaveDishIngredient() throws Exception {
        DishIngredient dishIngredient = new DishIngredient(new Dish("Italian pasta"), new Food("Pasta", "Spagetti"), (long)100);
        
        when(dishIngredientRepo.save(dishIngredient)).thenReturn(dishIngredient);
        
        DishIngredient dishIngredientSaved = dishIngredientService.save(dishIngredient);
        
        assertEquals(dishIngredient,dishIngredientSaved);
        
        verify(dishIngredientRepo, times(1)).save(dishIngredient);
    }
        
    @Test
    public void testUpdateDishIngrdient() throws Exception {
        Dish dish = new Dish("Italian pasta");
        Food food = new Food("Pasta", "Vermiselli");
        DishIngredient dishIngredient = new DishIngredient(dish, new Food("Pasta", "Spagetti"), (long)100); 
        Long id = (long)2;
        Long dishId = (long)1;
        Long foodId= (long)3;
        dish.addDishIngredient(dishIngredient);
        dish.setId(dishId);
        food.setId(foodId);
 
        when(dishIngredientRepo.findById(id)).thenReturn(Optional.of(dishIngredient));
        when(dishService.findById(dishId)).thenReturn(dish);
        when(foodService.findById(foodId)).thenReturn(food);
        
        
        dishIngredient.setFood(food);
        dishIngredient.setServingWeight((long)200);
        dish.addDishIngredient(dishIngredient);        
        when(dishIngredientRepo.save(dishIngredient)).thenReturn(dishIngredient);
        
        DishIngredient dishIngredientUpdated = dishIngredientService.update(id, foodId, (long) 200);
        
        assertEquals(dishIngredient, dishIngredientUpdated);
        
        verify(dishIngredientRepo, times(1)).findById(id);
        verify(dishIngredientRepo, times(1)).save(dishIngredient);
    }

    @Test
    public void testDeleteDishIngredient() {
        Dish dish = new Dish("Italian pasta");
        DishIngredient dishIngredient = new DishIngredient(dish, new Food("Pasta", "Spagetti"), (long)100); 
        Long id = (long)1;
        dish.addDishIngredient(dishIngredient);
        
        when(dishIngredientRepo.findById(id)).thenReturn(Optional.of(dishIngredient));
        
        DishIngredient dishIngredientDeleted = dishIngredientService.delete(id);
        
        assertEquals(dishIngredient, dishIngredientDeleted);
        
        verify(dishIngredientRepo, times(1)).delete(dishIngredient);
        verify(dishIngredientRepo, times(1)).findById(id);
    }        

    @Test
    public void testFindDishIngredientById() { 
        Dish dish = new Dish("Italian pasta");
        DishIngredient dishIngredient = new DishIngredient(dish, new Food("Pasta", "Spagetti"), (long)100); 
        Long id = (long)1;
        dish.addDishIngredient(dishIngredient);
        
        when(dishIngredientRepo.findById(id)).thenReturn(Optional.of(dishIngredient));
        
        DishIngredient dishIngredientFinded = dishIngredientService.findById(id);
        
        assertEquals(dishIngredient, dishIngredientFinded);
        
        verify(dishIngredientRepo, times(1)).findById(id);        
    }
    
    @Test
    public void testFindAllDishIngredientsByDishId() {
        Dish dish = new Dish("Italian pasta");
        Long dishId = (long) 1;
        dish.setId(dishId);
        
        dish.addDishIngredient(new DishIngredient(dish, new Food("Pasta", "Spagetti"), (long)100));
        dish.addDishIngredient(new DishIngredient(dish, new Food("Tomato paste", "Italian"), (long)100));
        
        List<DishIngredient> dishIngredientList = dish.getDishIngredients();
	
        when(dishService.findById(dishId)).thenReturn(dish);
        when(dishIngredientRepo.findAllByDish(dish,new Sort(Sort.Direction.ASC, "food"))).thenReturn(dishIngredientList);
        
        List<DishIngredient> dishIngredientListFinded= dishIngredientService.findAllByDishId(dishId);
        
        assertEquals(dishIngredientList, dishIngredientListFinded);        
        
        verify(dishIngredientRepo, times(1)).findAllByDish(dish, new Sort(Sort.Direction.ASC, "food"));
        
   /*     
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
        
   */     
  
    }   
    
}
