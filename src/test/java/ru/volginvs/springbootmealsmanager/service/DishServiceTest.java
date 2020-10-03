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
import ru.volginvs.springbootmealsmanager.domain.Food;
import ru.volginvs.springbootmealsmanager.repos.DishRepo;
import ru.volginvs.springbootmealsmanager.repos.FoodRepo;

@RunWith(SpringRunner.class)
@SpringBootTest   
public class DishServiceTest {
    
    @Autowired 
    private DishService dishService;
    
    @MockBean
    private DishRepo dishRepo;
     
    @Test
    public void testSaveDish() throws Exception {
        Dish dish = new Dish("Italian pasta");
        
        when(dishRepo.save(dish)).thenReturn(dish);
        
        Dish dishSaved = dishService.save(dish);
        
        assertEquals(dish,dishSaved);
        
        verify(dishRepo, times(1)).save(dish);
    }
    
    @Test
    public void testUpdateDish() throws Exception {
        Dish dish = new Dish("Italian pasta");
        Long id = (long)1;
 
        when(dishRepo.findById(id)).thenReturn(Optional.of(dish));
        
        dish.setName("Russian pasta");
        when(dishRepo.save(dish)).thenReturn(dish);
        
        Dish dishUpdated = dishService.update(id, "Russian pasta");
        
        assertEquals(dish, dishUpdated);
        
        verify(dishRepo, times(1)).save(dish);
        verify(dishRepo, times(1)).findById(id);
    }

    @Test
    public void testDeleteDish() {
        Dish dish = new Dish("Italian pasta");
        Long id = (long)1;
        
        when(dishRepo.findById(id)).thenReturn(Optional.of(dish));
        
        Dish dishDeleted = dishService.delete(id);
        
        assertEquals(dish, dishDeleted);
        
        verify(dishRepo, times(1)).delete(dish);
        verify(dishRepo, times(1)).findById(id);
    }        

    @Test
    public void testFindDishById() { 
        Dish dish = new Dish("Italian pasta");
        Long id = (long)1;
        
        when(dishRepo.findById(id)).thenReturn(Optional.of(dish));
        
        Dish dishFinded = dishService.findById(id);
        
        assertEquals(dish, dishFinded);
        
        verify(dishRepo, times(1)).findById(id);        
    }
    
    @Test
    public void testFindAllDishes() {
        List<Dish> dishList= new ArrayList<>();
        
        dishList.add(new Dish("Italian pasta"));
        dishList.add(new Dish("Fried chicken"));
	
        when(dishRepo.findAll(new Sort(Sort.Direction.ASC, "name"))).thenReturn(dishList);
        
        List<Dish> dishListFinded= dishService.findAll();
        
        assertEquals(dishList, dishListFinded);        
        
        verify(dishRepo, times(1)).findAll(new Sort(Sort.Direction.ASC, "name"));
  
    }   
}    
    
    
 
 /* @Service
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
    
    public Dish delete(Long id){
        Dish dish= findById(id);
        dishRepo.delete(dish);
        return dish;
    }
    public void deleteByName(String name)  {
                  
        dishRepo.deleteByName(name);
    } 
    
    public Dish save(String name) throws Exception {
        if (name==null) {
            throw new Exception("Dish's name is required");
        } 
    
        if (existsByName(name)) { 
            throw new Exception("Dish with name: '" + name+"' is already exists");
        }             
        return dishRepo.save(new Dish(name));
    }  
    
    public Dish update(Long id, String name) throws Exception{
        Dish dish = findById(id);   
        dish.setName(name);
        
        if (StringUtils.isEmpty(dish.getName())) {
            throw new Exception("Dish's name is required");
        } 
    
        if (existsByName(dish.getName()) && findByName(dish.getName()).getId()!=dish.getId()) { 
            throw new Exception("Dish with name: '" + dish.getName()+"' is already exists");
        }                     
        return dishRepo.save(dish);
    }        
}
 */