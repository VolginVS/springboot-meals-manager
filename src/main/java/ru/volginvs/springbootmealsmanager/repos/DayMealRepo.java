
package ru.volginvs.springbootmealsmanager.repos;

import ru.volginvs.springbootmealsmanager.domain.DayMeal;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;


public interface DayMealRepo extends CrudRepository<DayMeal,Long> {

    public DayMeal findByDate(LocalDate date);
    public void deleteByDate(LocalDate date);
    public void delete(DayMeal meal);
    public List<DayMeal> findAll(Sort sort);
    public boolean existsByDate(LocalDate date);

}
