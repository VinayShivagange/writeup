package com.writeup.web;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.writeup.service.ArticleService;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.inject.Inject;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@WebMvcTest(ArticleControllerTest.class)
public class ArticleControllerTest {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    @Inject
    private MockMvc mockMvc;
    @Inject
    private WebApplicationContext context;
    @MockBean
    private ArticleService service;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .defaultRequest(get("/").with(user("user").password("user").roles("USER"))).build();
    }

}
