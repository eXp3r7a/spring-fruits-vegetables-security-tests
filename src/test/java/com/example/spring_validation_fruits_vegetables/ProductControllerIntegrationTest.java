package com.example.spring_validation_fruits_vegetables;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @WithMockUser(username = "user", authorities = {"ROLE_USER"})
    void testGetProductAddForm() throws Exception {
        mockMvc.perform(get("/products/add"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("<button type=\"submit\" class=\"btn btn-primary\">Submit</button>")))
                .andExpect(content().string(containsString("<label class=\"form-label\">Product name</label>")))
                .andExpect(content().string(containsString("<input class=\"form-check-input\" type=\"radio\" name=\"category\" value=\"Spices\" id=\"category3\">")));
    }

    @Test
    @WithMockUser(username = "user", authorities = {"ROLE_USER"})
    void testGetProductEditPage() throws Exception {
        mockMvc.perform(get("/products/edit?product_id=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("<button type=\"submit\" class=\"btn btn-primary\">Update</button>")))
                .andExpect(content().string(containsString("<input class=\"form-check-input\" type=\"radio\" name=\"category\" value=\"Spices\" id=\"category3\">")))
                .andExpect(content().string(containsString("<input type=\"text\" class=\"form-control\" id=\"name\" name=\"name\" value=")));

    }

    @Test
    void testGetProductsList() throws Exception {
        mockMvc.perform(get("/products/get"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("<nav class=\"navbar navbar-expand-lg bg-body-tertiary\">")))
                .andExpect(content().string(containsString("<ul class=\"list-group list-group-horizontal-md\">")))
                .andExpect(content().string(containsString("<button class=\"btn btn-warning\" type=\"submit\">Update</button>")))
                .andExpect(content().string(containsString("<button class=\"btn btn-danger\" type=\"submit\">Delete</button>")));
    }
}
