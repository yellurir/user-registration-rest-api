package com.gamesys.codingtest.userregistrationrestapi.controller;

import com.gamesys.codingtest.userregistrationrestapi.model.User;
import com.gamesys.codingtest.userregistrationrestapi.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserRegistrationControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;
    private MvcResult mvcResult;
    private User user;

    @BeforeEach
    //delete the data after each test
    public void setUp(){
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("Validate that the API creates user successfully into database and returns 201 isCreated")
    public void postUser_withValidRequestData_returnsCreated() throws Exception {

        //mock the user data that we have to save
        User user = new User();
        user.setUsername("thomas3dison");
        user.setPassword("ligHt6u18");
        user.setDateOfBirth("1847-02-11");
        user.setSsn("18081931");
        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapToJson(user))).andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("thomas3dison"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password").value("ligHt6u18"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dateOfBirth").value("1847-02-11"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.ssn").value("18081931"));
    }

    @Test
    @DisplayName("Validate that the API doesn't create user into database when user in exclusion list and returns 400 badRequest")
    public void postUser_presentInExclusionList_returnsBadRequest() throws Exception {

        //mock the user data that we have to save
        User user = new User();
        user.setUsername("alanTuring");
        user.setPassword("eniGmA123");
        user.setDateOfBirth("1912-06-23");
        user.setSsn("123456789");
        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapToJson(user))).andExpect(status().isBadRequest()).andReturn();
    }

    @Test
    @DisplayName("Validate that the API doesn't create already existing user into database and returns 400 badRequest User already exist")
    public void postUser_AlreadyPresentInDatabase_returnsIsFound() throws Exception {

        //mock the user data that we have to save
        User user = new User();
        user.setUsername("thomas3dison");
        user.setPassword("ligHt6u18");
        user.setDateOfBirth("1847-02-11");
        user.setSsn("18081931");
        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapToJson(user))).andExpect(status().isCreated()).andReturn();

        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapToJson(user))).andExpect(status().isBadRequest()).andReturn();
    }

    @Test
    @DisplayName("Validate that the API doesn't create user into database with invalid username and returns 400 badRequest")
    public void postUser_withInvalidUsername_returnsBadRequest() throws Exception {

        //mock the user data that we have to save
        User user = new User();
        user.setUsername("thomas@dison");
        user.setPassword("ligHt6u18");
        user.setDateOfBirth("1847-02-11");
        user.setSsn("18081931");
        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapToJson(user))).andExpect(status().isBadRequest()).andReturn();
    }

    @Test
    @DisplayName("All lowercase- validate that the API doesn't create user into database with invalid password and returns 400 badRequest")
    public void postUser_withInvalidPasswordAllLowercase_returnsBadRequest() throws Exception {

        //mock the user data that we have to save
        User user = new User();
        user.setUsername("thomas3dison");
        user.setPassword("lightbulb");
        user.setDateOfBirth("1847-02-11");
        user.setSsn("18081931");
        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapToJson(user))).andExpect(status().isBadRequest()).andReturn();
    }

    @Test
    @DisplayName("All uppercase- validate that the API doesn't create user into database with invalid password and returns 400 badRequest")
    public void postUser_withInvalidPasswordAllUppercase_returnsBadRequest() throws Exception {

        //mock the user data that we have to save
        User user = new User();
        user.setUsername("thomas3dison");
        user.setPassword("LIGHTBULB");
        user.setDateOfBirth("1847-02-11");
        user.setSsn("18081931");
        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapToJson(user))).andExpect(status().isBadRequest()).andReturn();
    }

    @Test
    @DisplayName("No digit - validate that the API doesn't create user into database with invalid password and returns 400 badRequest")
    public void postUser_withInvalidPasswordNoDigit_returnsBadRequest() throws Exception {

        //mock the user data that we have to save
        User user = new User();
        user.setUsername("thomas3dison");
        user.setPassword("ligHt");
        user.setDateOfBirth("1847-02-11");
        user.setSsn("18081931");
        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapToJson(user))).andExpect(status().isBadRequest()).andReturn();
    }


    @Test
    @DisplayName("Less than 4 characters - validate that the API doesn't create user into database with invalid password and returns 400 badRequest")
    public void postUser_withInvalidPassword_returnsBadRequest() throws Exception {

        //mock the user data that we have to save
        User user = new User();
        user.setUsername("thomas3dison");
        user.setPassword("lit");
        user.setDateOfBirth("1847-02-11");
        user.setSsn("18081931");
        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapToJson(user))).andExpect(status().isBadRequest()).andReturn();
    }

    @Test
    @DisplayName("Validate that the API doesn't create user into database with invalid dateOfBirth and returns 400 badRequest")
    public void postUser_withInvalidDateOfBirth_returnsBadRequest() throws Exception {

        //mock the user data that we have to save
        User user = new User();
        user.setUsername("thomas3dison");
        user.setPassword("ligHt1");
        user.setDateOfBirth("1992-May-12");
        user.setSsn("18081931");
        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapToJson(user))).andExpect(status().isBadRequest()).andReturn();
    }

    private String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
}

