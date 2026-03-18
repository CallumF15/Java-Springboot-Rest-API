package com.company.business.controllers;



import com.company.business.Application;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
class BusinessControllerIntegrationTest {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;


    @Test
    void testCreateBusiness() throws Exception {
        String json = """
        {
            "title": "My Business",
            "description": "Sample",
            "sectorId": 1,
            "industryId": 2,
            "email": "contact@example.com",
            "landline": "01417745433",
            "phoneNumber": "123456789",
            "website": "https://example.com",
            "logoURL": "https://example.com"
        }
        """;

        mockMvc.perform(post("/api/business")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
            .andExpect(status().isOk());
    }
}
