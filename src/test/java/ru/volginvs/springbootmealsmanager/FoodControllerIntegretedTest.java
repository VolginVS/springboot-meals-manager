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
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;
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
        mockMvc.perform(get("/foods"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Foods")));
    }
    @Test
    public void shouldShowFoodListTest() throws Exception{
        mockMvc.perform(get("/foods"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(xpath("//div[@id='food-list']/table/tr").nodeCount(6+1));  //Проверяеn сколько выводится строк в таблице(должно выводить n+1 строка c pfujkjdrfvb)
    }

    @Test
    public void shouldAddFoodToListTest() throws Exception{
        MockHttpServletRequestBuilder multipart = MockMvcRequestBuilders.multipart("/foods")
                .param("name", "pasta")
                .param("species", "spagetti");

        mockMvc.perform(multipart)
                .andDo(print())
                .andExpect(xpath("//div[@id='food-list']/table/tr").nodeCount(6)) 
                .andExpect(MockMvcResultMatchers.xpath("//div[@id='food-list']/table/tr[@data-id=10]").exists()); 
    }    
    
}
