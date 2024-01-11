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
import me.sathish.trackstrava.entities.StravaRun;
import me.sathish.trackstrava.model.request.StravaRunRequest;
import me.sathish.trackstrava.repositories.StravaRunRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

class StravaRunControllerIT extends AbstractIntegrationTest {

//    @Autowired private StravaRunRepository stravaRunRepository;
//
//    private List<StravaRun> stravaRunList = null;
//
//    @BeforeEach
//    void setUp() {
//        stravaRunRepository.deleteAllInBatch();
//
//        stravaRunList = new ArrayList<>();
//        stravaRunList.add(new StravaRun(null, "First StravaRun"));
//        stravaRunList.add(new StravaRun(null, "Second StravaRun"));
//        stravaRunList.add(new StravaRun(null, "Third StravaRun"));
//        stravaRunList = stravaRunRepository.saveAll(stravaRunList);
//    }
//
//    @Test
//    void shouldFetchAllStravaRuns() throws Exception {
//        this.mockMvc
//                .perform(get("/api/stravarun"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.data.size()", is(stravaRunList.size())))
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
//    void shouldFindStravaRunById() throws Exception {
//        StravaRun stravaRun = stravaRunList.get(0);
//        Long stravaRunId = stravaRun.getId();
//
//        this.mockMvc
//                .perform(get("/api/stravarun/{id}", stravaRunId))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id", is(stravaRun.getId()), Long.class))
//                .andExpect(jsonPath("$.text", is(stravaRun.getText())));
//    }
//
//    @Test
//    void shouldCreateNewStravaRun() throws Exception {
//        StravaRunRequest stravaRunRequest = new StravaRunRequest("New StravaRun");
//        this.mockMvc
//                .perform(
//                        post("/api/stravarun")
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(objectMapper.writeValueAsString(stravaRunRequest)))
//                .andExpect(status().isCreated())
//                .andExpect(header().exists(HttpHeaders.LOCATION))
//                .andExpect(jsonPath("$.id", notNullValue()))
//                .andExpect(jsonPath("$.text", is(stravaRunRequest.text())));
//    }
//
//    @Test
//    void shouldReturn400WhenCreateNewStravaRunWithoutText() throws Exception {
//        StravaRunRequest stravaRunRequest = new StravaRunRequest(null);
//
//        this.mockMvc
//                .perform(
//                        post("/api/stravarun")
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(objectMapper.writeValueAsString(stravaRunRequest)))
//                .andExpect(status().isBadRequest())
//                .andExpect(header().string("Content-Type", is("application/problem+json")))
//                .andExpect(jsonPath("$.type", is("about:blank")))
//                .andExpect(jsonPath("$.title", is("Constraint Violation")))
//                .andExpect(jsonPath("$.status", is(400)))
//                .andExpect(jsonPath("$.detail", is("Invalid request content.")))
//                .andExpect(jsonPath("$.instance", is("/api/stravarun")))
//                .andExpect(jsonPath("$.violations", hasSize(1)))
//                .andExpect(jsonPath("$.violations[0].field", is("text")))
//                .andExpect(jsonPath("$.violations[0].message", is("Text cannot be empty")))
//                .andReturn();
//    }
//
//    @Test
//    void shouldUpdateStravaRun() throws Exception {
//        Long stravaRunId = stravaRunList.get(0).getId();
//        StravaRunRequest stravaRunRequest = new StravaRunRequest("Updated StravaRun");
//
//        this.mockMvc
//                .perform(
//                        put("/api/stravarun/{id}", stravaRunId)
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(objectMapper.writeValueAsString(stravaRunRequest)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id", is(stravaRunId), Long.class))
//                .andExpect(jsonPath("$.text", is(stravaRunRequest.text())));
//    }
//
//    @Test
//    void shouldDeleteStravaRun() throws Exception {
//        StravaRun stravaRun = stravaRunList.get(0);
//
//        this.mockMvc
//                .perform(delete("/api/stravarun/{id}", stravaRun.getId()))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id", is(stravaRun.getId()), Long.class))
//                .andExpect(jsonPath("$.text", is(stravaRun.getText())));
//    }
}
