package com.galvanize.sandwich;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SandwichControllerTest {
    @Autowired
    MockMvc mvc;

    @Test
    void getAllSandwiches() throws Exception {
        mvc.perform(get("/sandwiches"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("[0].name").value("Ham and Cheese"))
            .andExpect(jsonPath("[1].name").value("Turkey and Swiss"))
            .andExpect(jsonPath("[2].name").value("Veggie Deluxe"))
            .andExpect(jsonPath("[3].name").value("Kale and Lime"));
    }

    @Test
    void AddSandwich() throws Exception {
        Sandwich chickenClub = new Sandwich();
        chickenClub.setName("Chicken Club");

        mvc.perform(
            post("/sandwiches")
                .content(new ObjectMapper().writeValueAsString(chickenClub))
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isCreated());

        mvc.perform(get("/sandwiches"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(5)))
            .andExpect(jsonPath("[4].name").value("Chicken Club"));
    }
}
