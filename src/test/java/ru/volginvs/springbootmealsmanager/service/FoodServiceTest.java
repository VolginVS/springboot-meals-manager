
package ru.volginvs.springbootmealsmanager.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import ru.volginvs.springbootmealsmanager.domain.Food;
import ru.volginvs.springbootmealsmanager.repos.FoodRepo;


@RunWith(SpringRunner.class)
@SpringBootTest    
public class FoodServiceTest {
     
    @Autowired
    private FoodService foodService;
    
    @MockBean
    private FoodRepo foodRepo;
     
    @Test
    public void testSave() throws Exception {
        Food food = new Food("Pasta", "Spagetti");
        
        when(foodRepo.save(food)).thenReturn(food);
        
        Food foodSaved = foodService.save(food);
        
        assertEquals(food,foodSaved);
        
        verify(foodRepo, times(1)).save(food);
    }
    
    @Test
    public void testUpdateFood() throws Exception {
        Food food = new Food("Pasta", "Spagetti"); 
        Long id = (long)1;
 
        when(foodRepo.findById(id)).thenReturn(Optional.of(food));
        
        food.setSpecies("Vermicelli");
        when(foodRepo.save(food)).thenReturn(food);
        
        Food foodUpdated = foodService.update(id, "Pasta", "Vermicelli");
        
        assertEquals(food, foodUpdated);
        
        verify(foodRepo, times(1)).save(food);
        verify(foodRepo, times(1)).findById(id);
    }

    @Test
    public void testDelete() {
        Food food = new Food("Pasta", "Spagetti"); 
        Long id = (long)1;
        
        when(foodRepo.findById(id)).thenReturn(Optional.of(food));
        
        Food foodDeleted = foodService.delete(id);
        
        assertEquals(food, foodDeleted);
        
        verify(foodRepo, times(1)).delete(food);
        verify(foodRepo, times(1)).findById(id);
    }        

    @Test
    public void testFindById() { 
        Food food = new Food("Pasta", "Spagetti"); 
        Long id = (long)1;
        
        when(foodRepo.findById((long)1)).thenReturn(Optional.of(food));
        
        Food foodFinded = foodService.findById(id);
        
        assertEquals(food, foodFinded);
        
        verify(foodRepo, times(1)).findById(id);        
    }
    
    @Test
    public void testFindAll() {
        List<Food> foodList= new ArrayList<>();
        
        foodList.add(new Food("Pasta", "Spagetti"));
        foodList.add(new Food("Pasta", "Vermicelli"));
	
        when(foodRepo.findAll(new Sort(Sort.Direction.ASC, "name"))).thenReturn(foodList);
        
        List<Food> foodListFinded= foodService.findAll();
        
        assertEquals(foodList, foodListFinded);        
        
        verify(foodRepo, times(1)).findAll(new Sort(Sort.Direction.ASC, "name"));
  
    }   
}


