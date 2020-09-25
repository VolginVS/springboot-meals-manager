/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.volginvs.springbootmealsmanager.domain;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class DayMeal {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
   
    @ManyToOne
    @JoinColumn(name="breakfast_dish_id")
    private Dish breakfast;
    
    @ManyToOne
    @JoinColumn(name="dinner_dish_id")
    private Dish dinner;
    
    @ManyToOne
    @JoinColumn(name="supper_dish_id")
    private Dish supper;
    
    public DayMeal(){   
    }
    
    public DayMeal(LocalDate date, Dish breakfast, Dish dinner, Dish supper){
        this.date=date;
        this.breakfast=breakfast;
        this.dinner=dinner;
        this.supper=supper;
    }   
    
    
    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id=id;
    }
    public LocalDate getDate(){
        return date;
    }
    public void setDate(LocalDate date){
        this.date=date;
    } 
    public Dish getBreakfast(){
        return breakfast;
    }
    public void setBreakfast(Dish breakfast){
        this.breakfast=breakfast;
    } 
    public Dish getDinner(){
        return dinner;
    }
    public void setDinner(Dish dinner){
        this.dinner=dinner;
    } 
    public Dish getSupper(){
        return supper;
    }
    public void setSupper(Dish supper){
        this.supper=supper;
    }     
    
}
