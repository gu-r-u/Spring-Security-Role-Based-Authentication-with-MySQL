package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc
class SecurityIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void publicEndpoint_ShouldReturnOk() throws Exception {
        mockMvc.perform(get("/public"))
                .andExpect(status().isOk());
    }

    @Test
    void helloEndpoint_AsUser_ShouldReturnOk() throws Exception {
        mockMvc.perform(get("/hello")
                        .with(httpBasic("ram", "password")))
                .andExpect(status().isOk());
    }

    @Test
    void adminEndpoint_AsUser_ShouldReturnForbidden() throws Exception {
        mockMvc.perform(get("/admin")
                        .with(httpBasic("ram", "password")))
                .andExpect(status().isForbidden());
    }

    @Test
    void adminEndpoint_AsAdmin_ShouldReturnOk() throws Exception {
        mockMvc.perform(get("/admin")
                        .with(httpBasic("admin", "admin")))
                .andExpect(status().isOk());
    }

    @Test
    void helloEndpoint_NoAuth_ShouldReturnUnauthorized() throws Exception {
        mockMvc.perform(get("/hello"))
                .andExpect(status().isUnauthorized());
    }
}
