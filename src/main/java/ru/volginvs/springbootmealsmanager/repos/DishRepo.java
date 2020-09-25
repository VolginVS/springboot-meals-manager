package ru.volginvs.springbootmealsmanager.repos;



import ru.volginvs.springbootmealsmanager.domain.Dish;
import ru.volginvs.springbootmealsmanager.domain.Food;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;


public interface DishRepo extends CrudRepository<Dish, Long> {
    
    public List<Dish> findAll(Sort sort);
    public List<Dish> findAllByName(String name);
    public Dish findByName(String name);
    public boolean existsByName(String name);
    public void deleteByName(String name);
    public void delete(Dish dish);

}