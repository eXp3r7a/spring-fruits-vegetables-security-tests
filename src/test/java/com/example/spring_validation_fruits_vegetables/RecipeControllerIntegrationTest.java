package com.example.spring_validation_fruits_vegetables;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class RecipeControllerIntegrationTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    void testGetRecipeAddForm() throws Exception {
        mockMvc.perform(get("/recipes/add"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("<label class=\"form-label\">Description</label>")))
                .andExpect(content().string(containsString("<p>Select Products:</p>")))
                .andExpect(content().string(containsString("<button type=\"submit\" class=\"btn btn-primary\">Submit</button>")))
                .andExpect(content().string(containsString("<input class=\"form-check-input\" type=\"radio\" name=\"category\" value=\"Main\" id=\"category1\">")));
    }

    @Test
    void testGetRecipeList() throws Exception {
        mockMvc.perform(get("/recipes/get"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("<li class=\"list-group-item\" style=\"width: 10rem;\">Recipe ID</li>")))
                .andExpect(content().string(containsString("<li class=\"list-group-item\" style=\"width: 10rem;\">Description</li>")))
                .andExpect(content().string(containsString("<button class=\"btn btn-warning\" type=\"submit\">Update</button>")))
                .andExpect(content().string(containsString("<button class=\"btn btn-danger\" type=\"submit\">Delete</button>")));
    }

    @Test
    void testGetRecipeEditPage() throws Exception {
        mockMvc.perform(get("/recipes/edit?recipe_id=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("<label class=\"form-label\">Recipe name</label>")))
                .andExpect(content().string(containsString("<input type=\"text\" class=\"form-control\" id=\"description\" name=\"description\" value=\"Chicken soup with vegetables\">")))
                .andExpect(content().string(containsString("<button type=\"submit\" class=\"btn btn-primary\">Update</button>")));

    }
}
