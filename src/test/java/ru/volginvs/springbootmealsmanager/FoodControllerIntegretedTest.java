/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.volginvs.springbootmealsmanager;



import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import ru.volginvs.springbootmealsmanager.cotroller.FoodController;

@RunWith(SpringRunner.class)
@SpringBootTest    
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value={"/create-food-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value=("/create-food-after.sql"), executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class FoodControllerIntegretedTest {
  
    
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private FoodController controller;
    
    //Проверка наличия 
    @Test
    public void testController() throws Exception{
	assertThat(controller).isNotNull();
    }
    
    @Test
    public void foodPageTest( ) throws Exception{
        this.mockMvc.perform(get("/food"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Foods")));
    }
    @Test
    public void showFoodListTest() throws Exception{
        mockMvc.perform(get("/food"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Foods")));
        
    }
    
}
