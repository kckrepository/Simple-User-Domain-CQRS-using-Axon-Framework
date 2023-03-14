package org.dani.command.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(profiles = "localtest")
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void createUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"first_name\": \"FIRST_NAME\", \"last_name\": \"LAST_NAME\", \"birth\": \"1990-08-08\" }"))
                .andExpect(status().isOk());
    }

    @Test
    public void activateUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/active")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"id\": \"22c9e230-c187-11ed-afa1-0242ac120002\" }"))
                .andExpect(status().isOk());
    }

    @Test
    public void deactivateUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/deactive")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"id\": \"22c9e230-c187-11ed-afa1-0242ac120002\" }"))
                .andExpect(status().isOk());
    }

}
