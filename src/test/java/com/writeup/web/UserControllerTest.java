package com.writeup.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.writeup.domain.User;
import com.writeup.service.UserService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.inject.Inject;
import java.util.Arrays;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@WebMvcTest(UserController.class)
public class UserControllerTest {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    @Inject
    private MockMvc mockMvc;
    @Inject
    private WebApplicationContext context;
    @MockBean
    private UserService service;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .defaultRequest(get("/").with(user("user").password("user").roles("USER"))).build();
    }

    @Test
    public void findObjects_StorageIsNotEmpty_OneObjectIsReturned() throws Exception {
        given(service.findAllUsers()).willReturn(Arrays.asList(new User()));
        mockMvc
                .perform(get("/api/v1/user"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", Matchers.hasSize(1)));
    }

    @Test
    public void saveUser_validUser_UserIsReturned() throws Exception {
        User user = new User();
        user.setUserName("vsn@apptio.com");
        user.setDisplayName("vinay");
        mockMvc
                .perform(post("/api/v1/user")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content(MAPPER.writeValueAsString(user)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.title", Matchers.equalTo("Java 8")));

    }

    @Test
    public void saveUser_NotValidUser_BadRequest() throws Exception {
        User user = new User();
        user.setUserName("vsn@apptio.com");
        mockMvc
                .perform(post("/api/v1/user")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                //.content(MAPPER.writeValueAsString(user)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updateUser_validUser_UserIsReturned() throws Exception {
        User user = new User(1, "vinay", "vsn@apptio.com");
        mockMvc
                .perform(post("/api/v1/user")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content(MAPPER.writeValueAsString(user)));

        User user2 = new User(2, "sumana", "spb@apptio.com");
        given(service.findUserById(2)).willReturn(user2);
        mockMvc
                .perform(put("/api/v1/objects/2").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content(MAPPER.writeValueAsString(user2))).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.title", Matchers.equalTo("Java 9")));

    }

    @Test
    public void findUserByID_ValidId_OneObjectIsReturned() throws Exception {
        User user = new User(2, "vinay", "vsn@apptio.com");
        given(service.findUserById(2)).willReturn(user);
        given(service.isUserExist(user)).willReturn(true);
        mockMvc
                .perform(get("/api/v1/user/2"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.id", Matchers.equalTo(2)));
    }

    @Test
    public void findUserByID_NotValidId_NotFound() throws Exception {
        given(service.findUserById(1)).willReturn(null);
        mockMvc
                .perform(get("/api/v1/user/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteObjectById_NotValidId_NotFound() throws Exception {
        User user = new User(1, "vinay", "vsn@apptio.com");
        given(service.isUserExist(user)).willReturn(false);
        mockMvc
                .perform(delete("/api/v1/user/1"))
                .andExpect(status().isNotFound());
    }
}
