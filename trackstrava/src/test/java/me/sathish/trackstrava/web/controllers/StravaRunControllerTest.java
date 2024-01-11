package me.sathish.trackstrava.web.controllers;

import static me.sathish.trackstrava.utils.StravaMSAppConstants.PROFILE_TEST;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import me.sathish.trackstrava.controllers.StravaRunController;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;

@WebMvcTest(controllers = StravaRunController.class)
@ActiveProfiles(PROFILE_TEST)
class StravaRunControllerTest {

//    @Autowired private MockMvc mockMvc;
//
//    @MockBean private StravaRunService stravaRunService;
//
//    @Autowired private ObjectMapper objectMapper;
//
//    private List<StravaRun> stravaRunList;
//
//    @BeforeEach
//    void setUp() {
//        this.stravaRunList = new ArrayList<>();
//        this.stravaRunList.add(new StravaRun(1L, "text 1"));
//        this.stravaRunList.add(new StravaRun(2L, "text 2"));
//        this.stravaRunList.add(new StravaRun(3L, "text 3"));
//    }
//
//    @Test
//    void shouldFetchAllStravaRuns() throws Exception {
//
//        Page<StravaRun> page = new PageImpl<>(stravaRunList);
//        PagedResult<StravaRunResponse> stravaRunPagedResult =
//                new PagedResult<>(page, getStravaRunResponseList());
//        FindStravaRunsQuery findStravaRunsQuery = new FindStravaRunsQuery(0, 10, "id", "asc");
//        given(stravaRunService.findAllStravaRuns(findStravaRunsQuery))
//                .willReturn(stravaRunPagedResult);
//
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
//        Long stravaRunId = 1L;
//        StravaRunResponse stravaRun = new StravaRunResponse(stravaRunId, "text 1");
//        given(stravaRunService.findStravaRunById(stravaRunId)).willReturn(Optional.of(stravaRun));
//
//        this.mockMvc
//                .perform(get("/api/stravarun/{id}", stravaRunId))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.text", is(stravaRun.text())));
//    }
//
//    @Test
//    void shouldReturn404WhenFetchingNonExistingStravaRun() throws Exception {
//        Long stravaRunId = 1L;
//        given(stravaRunService.findStravaRunById(stravaRunId)).willReturn(Optional.empty());
//
//        this.mockMvc
//                .perform(get("/api/stravarun/{id}", stravaRunId))
//                .andExpect(status().isNotFound())
//                .andExpect(
//                        header().string(
//                                        "Content-Type",
//                                        is(MediaType.APPLICATION_PROBLEM_JSON_VALUE)))
//                .andExpect(jsonPath("$.type", is("http://api.trackstrava.com/errors/not-found")))
//                .andExpect(jsonPath("$.title", is("Not Found")))
//                .andExpect(jsonPath("$.status", is(404)))
//                .andExpect(
//                        jsonPath("$.detail")
//                                .value("StravaRun with Id '%d' not found".formatted(stravaRunId)));
//    }
//
//    @Test
//    void shouldCreateNewStravaRun() throws Exception {
//
//        StravaRunResponse stravaRun = new StravaRunResponse(1L, "some text");
//        StravaRunRequest stravaRunRequest = new StravaRunRequest("some text");
//        given(stravaRunService.saveStravaRun(any(StravaRunRequest.class))).willReturn(stravaRun);
//
//        this.mockMvc
//                .perform(
//                        post("/api/stravarun")
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(objectMapper.writeValueAsString(stravaRunRequest)))
//                .andExpect(status().isCreated())
//                .andExpect(header().exists(HttpHeaders.LOCATION))
//                .andExpect(jsonPath("$.id", notNullValue()))
//                .andExpect(jsonPath("$.text", is(stravaRun.text())));
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
//        Long stravaRunId = 1L;
//        StravaRunResponse stravaRun = new StravaRunResponse(stravaRunId, "Updated text");
//        StravaRunRequest stravaRunRequest = new StravaRunRequest("Updated text");
//        given(stravaRunService.updateStravaRun(eq(stravaRunId), any(StravaRunRequest.class)))
//                .willReturn(stravaRun);
//
//        this.mockMvc
//                .perform(
//                        put("/api/stravarun/{id}", stravaRunId)
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(objectMapper.writeValueAsString(stravaRunRequest)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id", is(stravaRunId), Long.class))
//                .andExpect(jsonPath("$.text", is(stravaRun.text())));
//    }
//
//    @Test
//    void shouldReturn404WhenUpdatingNonExistingStravaRun() throws Exception {
//        Long stravaRunId = 1L;
//        StravaRunRequest stravaRunRequest = new StravaRunRequest("Updated text");
//        given(stravaRunService.updateStravaRun(eq(stravaRunId), any(StravaRunRequest.class)))
//                .willThrow(new StravaRunNotFoundException(stravaRunId));
//
//        this.mockMvc
//                .perform(
//                        put("/api/stravarun/{id}", stravaRunId)
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(objectMapper.writeValueAsString(stravaRunRequest)))
//                .andExpect(status().isNotFound())
//                .andExpect(
//                        header().string(
//                                        "Content-Type",
//                                        is(MediaType.APPLICATION_PROBLEM_JSON_VALUE)))
//                .andExpect(jsonPath("$.type", is("http://api.trackstrava.com/errors/not-found")))
//                .andExpect(jsonPath("$.title", is("Not Found")))
//                .andExpect(jsonPath("$.status", is(404)))
//                .andExpect(
//                        jsonPath("$.detail")
//                                .value("StravaRun with Id '%d' not found".formatted(stravaRunId)));
//    }
//
//    @Test
//    void shouldDeleteStravaRun() throws Exception {
//        Long stravaRunId = 1L;
//        StravaRunResponse stravaRun = new StravaRunResponse(stravaRunId, "Some text");
//        given(stravaRunService.findStravaRunById(stravaRunId)).willReturn(Optional.of(stravaRun));
//        doNothing().when(stravaRunService).deleteStravaRunById(stravaRunId);
//
//        this.mockMvc
//                .perform(delete("/api/stravarun/{id}", stravaRunId))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.text", is(stravaRun.text())));
//    }
//
//    @Test
//    void shouldReturn404WhenDeletingNonExistingStravaRun() throws Exception {
//        Long stravaRunId = 1L;
//        given(stravaRunService.findStravaRunById(stravaRunId)).willReturn(Optional.empty());
//
//        this.mockMvc
//                .perform(delete("/api/stravarun/{id}", stravaRunId))
//                .andExpect(
//                        header().string(
//                                        "Content-Type",
//                                        is(MediaType.APPLICATION_PROBLEM_JSON_VALUE)))
//                .andExpect(jsonPath("$.type", is("http://api.trackstrava.com/errors/not-found")))
//                .andExpect(jsonPath("$.title", is("Not Found")))
//                .andExpect(jsonPath("$.status", is(404)))
//                .andExpect(
//                        jsonPath("$.detail")
//                                .value("StravaRun with Id '%d' not found".formatted(stravaRunId)));
//    }
//
//    List<StravaRunResponse> getStravaRunResponseList() {
//        return stravaRunList.stream()
//                .map(stravaRun -> new StravaRunResponse(stravaRun.getId(), stravaRun.getText()))
//                .toList();
//    }
}
