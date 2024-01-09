package me.sathish.trackstrava.web.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import me.sathish.trackstrava.common.AbstractIntegrationTest;
import me.sathish.trackstrava.entities.StravaUser;
import me.sathish.trackstrava.model.request.StravaUserRequest;
import me.sathish.trackstrava.repositories.StravaUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

class StravaUserControllerIT extends AbstractIntegrationTest {

//    @Autowired private StravaUserRepository stravaUserRepository;
//
//    private List<StravaUser> stravaUserList = null;
//
//    @BeforeEach
//    void setUp() {
//        stravaUserRepository.deleteAllInBatch();
//
//        stravaUserList = new ArrayList<>();
//        stravaUserList.add(new StravaUser(null, "First StravaUser"));
//        stravaUserList.add(new StravaUser(null, "Second StravaUser"));
//        stravaUserList.add(new StravaUser(null, "Third StravaUser"));
//        stravaUserList = stravaUserRepository.saveAll(stravaUserList);
//    }
//
//    @Test
//    void shouldFetchAllStravaUsers() throws Exception {
//        this.mockMvc
//                .perform(get("/api/stravauser"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.data.size()", is(stravaUserList.size())))
//                .andExpect(jsonPath("$.totalElements", is(3)))
//                .andExpect(jsonPath("$.pageNumber", is(1)))
//                .andExpect(jsonPath("$.totalPages", is(1)))
//                .andExpect(jsonPath("$.isFirst", is(true)))
//                .andExpect(jsonPath("$.isLast", is(true)))
//                .andExpect(jsonPath("$.hasNext", is(false)))
//                .andExpect(jsonPath("$.hasPrevious", is(false)));
//    }
//
//    @Test
//    void shouldFindStravaUserById() throws Exception {
//        StravaUser stravaUser = stravaUserList.get(0);
//        Long stravaUserId = stravaUser.getId();
//
//        this.mockMvc
//                .perform(get("/api/stravauser/{id}", stravaUserId))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id", is(stravaUser.getId()), Long.class))
//                .andExpect(jsonPath("$.text", is(stravaUser.getText())));
//    }
//
//    @Test
//    void shouldCreateNewStravaUser() throws Exception {
//        StravaUserRequest stravaUserRequest = new StravaUserRequest("New StravaUser");
//        this.mockMvc
//                .perform(
//                        post("/api/stravauser")
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(objectMapper.writeValueAsString(stravaUserRequest)))
//                .andExpect(status().isCreated())
//                .andExpect(header().exists(HttpHeaders.LOCATION))
//                .andExpect(jsonPath("$.id", notNullValue()))
//                .andExpect(jsonPath("$.text", is(stravaUserRequest.text())));
//    }
//
//    @Test
//    void shouldReturn400WhenCreateNewStravaUserWithoutText() throws Exception {
//        StravaUserRequest stravaUserRequest = new StravaUserRequest(null);
//
//        this.mockMvc
//                .perform(
//                        post("/api/stravauser")
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(objectMapper.writeValueAsString(stravaUserRequest)))
//                .andExpect(status().isBadRequest())
//                .andExpect(header().string("Content-Type", is("application/problem+json")))
//                .andExpect(jsonPath("$.type", is("about:blank")))
//                .andExpect(jsonPath("$.title", is("Constraint Violation")))
//                .andExpect(jsonPath("$.status", is(400)))
//                .andExpect(jsonPath("$.detail", is("Invalid request content.")))
//                .andExpect(jsonPath("$.instance", is("/api/stravauser")))
//                .andExpect(jsonPath("$.violations", hasSize(1)))
//                .andExpect(jsonPath("$.violations[0].field", is("text")))
//                .andExpect(jsonPath("$.violations[0].message", is("Text cannot be empty")))
//                .andReturn();
//    }
//
//    @Test
//    void shouldUpdateStravaUser() throws Exception {
//        Long stravaUserId = stravaUserList.get(0).getId();
//        StravaUserRequest stravaUserRequest = new StravaUserRequest("Updated StravaUser");
//
//        this.mockMvc
//                .perform(
//                        put("/api/stravauser/{id}", stravaUserId)
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(objectMapper.writeValueAsString(stravaUserRequest)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id", is(stravaUserId), Long.class))
//                .andExpect(jsonPath("$.text", is(stravaUserRequest.text())));
//    }
//
//    @Test
//    void shouldDeleteStravaUser() throws Exception {
//        StravaUser stravaUser = stravaUserList.get(0);
//
//        this.mockMvc
//                .perform(delete("/api/stravauser/{id}", stravaUser.getId()))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id", is(stravaUser.getId()), Long.class))
//                .andExpect(jsonPath("$.text", is(stravaUser.getText())));
//    }
}
