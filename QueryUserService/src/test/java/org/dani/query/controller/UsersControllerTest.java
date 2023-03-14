package org.dani.query.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(profiles = "localtest")
public class UsersControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void listUser() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk());
    }

    @Test
    public void listActiveUser() throws Exception {
        mockMvc.perform(get("/active-users"))
                .andExpect(status().isOk());
    }

    @Test
    public void listNonActiveUser() throws Exception {
        mockMvc.perform(get("/nonactive-users"))
                .andExpect(status().isOk());
    }

}
