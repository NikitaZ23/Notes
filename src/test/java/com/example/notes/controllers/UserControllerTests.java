package com.example.notes.controllers;

import com.example.notes.common.Role;
import com.example.notes.configuration.ConfigurationTests;
import com.example.notes.domain.User;
import com.example.notes.service.UserServiceImp;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ContextConfiguration(classes = ConfigurationTests.class)
@WithMockUser(username = "admin", roles = {"ADMIN"})
public class UserControllerTests {

    @MockBean
    UserServiceImp service;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext context;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

//    @SneakyThrows
//    @Test
//    @DisplayName("Получение пользователя")
//    public void getUserTest() {
//        User user = new User("user", "user", "user", Role.User);
//
//        Mockito.when(service.findByUuid(Mockito.any())).thenReturn(Optional.of(user));
//
//        mockMvc.perform(
//                        MockMvcRequestBuilders.get("/users/7207d531-0e01-4cd0-ba0a-02f7c0c8fb2d")
//                )
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.login").value("user"))
//                .andExpect(jsonPath("$.password").value("user"))
//                .andExpect(jsonPath("$.userName").value("user"));
//    }
//Как исправить 403 ошибку в данном случае я не знаю, хотелсь бы узнать ваше мнение по этому вопросу
}
