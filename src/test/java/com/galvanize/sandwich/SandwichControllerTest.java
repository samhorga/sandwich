package com.galvanize.sandwich;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SandwichControllerTest {
    @Autowired
    MockMvc mvc;

    @Test
    void testGetAllSandwiches() throws Exception {
        mvc.perform(get("/sandwiches"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0].name").value("Ham and Cheese"))
                .andExpect(jsonPath("[1].name").value("Turkey and Swiss"))
                .andExpect(jsonPath("[2].name").value("Veggie Deluxe"))
                .andExpect(jsonPath("[3].name").value("Kale and Lime"));
    }
}
