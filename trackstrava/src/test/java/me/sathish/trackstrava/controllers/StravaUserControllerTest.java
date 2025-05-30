import static me.sathish.trackstrava.utils.StravaMSAppConstants.PROFILE_TEST;

import org.springframework.test.context.ActiveProfiles;

// @WebMvcTest(controllers = StravaRunController.class)
@ActiveProfiles(PROFILE_TEST)
class StravaUserControllerTest {

    //    @Autowired private MockMvc mockMvc;
    //
    //    @MockBean private StravaUserService stravaUserService;
    //
    //    @Autowired private ObjectMapper objectMapper;
    //
    //    private List<StravaUser> stravaUserList;
    //
    //    @BeforeEach
    //    void setUp() {
    //        this.stravaUserList = new ArrayList<>();
    //        this.stravaUserList.add(new StravaUser(1L, "text 1"));
    //        this.stravaUserList.add(new StravaUser(2L, "text 2"));
    //        this.stravaUserList.add(new StravaUser(3L, "text 3"));
    //    }
    //
    //    @Test
    //    void shouldReturn404WhenFetchingNonExistingStravaUser() throws Exception {
    //        Long stravaUserId = 1L;
    //
    // given(stravaUserService.findStravaUserById(stravaUserId)).willReturn(Optional.empty());
    //
    //        this.mockMvc
    //                .perform(get("/api/stravauser/{id}", stravaUserId))
    //                .andExpect(status().isNotFound())
    //                .andExpect(
    //                        header().string(
    //                                        "Content-Type",
    //                                        is(MediaType.APPLICATION_PROBLEM_JSON_VALUE)))
    //                .andExpect(jsonPath("$.type",
    // is("http://api.trackstrava.com/errors/not-found")))
    //                .andExpect(jsonPath("$.title", is("Not Found")))
    //                .andExpect(jsonPath("$.status", is(404)))
    //                .andExpect(
    //                        jsonPath("$.detail")
    //                                .value(
    //                                        "StravaUser with Id '%d' not found"
    //                                                .formatted(stravaUserId)));
    //    }
    //
    //    @Test
    //    void shouldCreateNewStravaUser() throws Exception {
    //
    //        StravaUserResponse stravaUser = new StravaUserResponse(1L, "some text");
    //        StravaUserRequest stravaUserRequest = new StravaUserRequest("some text");
    //        given(stravaUserService.saveStravaUser(any(StravaUserRequest.class)))
    //                .willReturn(stravaUser);
    //
    //        this.mockMvc
    //                .perform(
    //                        post("/api/stravauser")
    //                                .contentType(MediaType.APPLICATION_JSON)
    //                                .content(objectMapper.writeValueAsString(stravaUserRequest)))
    //                .andExpect(status().isCreated())
    //                .andExpect(header().exists(HttpHeaders.LOCATION))
    //                .andExpect(jsonPath("$.id", notNullValue()))
    //                .andExpect(jsonPath("$.text", is(stravaUser.text())));
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
    //        Long stravaUserId = 1L;
    //        StravaUserResponse stravaUser = new StravaUserResponse(stravaUserId, "Updated text");
    //        StravaUserRequest stravaUserRequest = new StravaUserRequest("Updated text");
    //        given(stravaUserService.updateStravaUser(eq(stravaUserId),
    // any(StravaUserRequest.class)))
    //                .willReturn(stravaUser);
    //
    //        this.mockMvc
    //                .perform(
    //                        put("/api/stravauser/{id}", stravaUserId)
    //                                .contentType(MediaType.APPLICATION_JSON)
    //                                .content(objectMapper.writeValueAsString(stravaUserRequest)))
    //                .andExpect(status().isOk())
    //                .andExpect(jsonPath("$.id", is(stravaUserId), Long.class))
    //                .andExpect(jsonPath("$.text", is(stravaUser.text())));
    //    }
    //
    //    @Test
    //    void shouldReturn404WhenUpdatingNonExistingStravaUser() throws Exception {
    //        Long stravaUserId = 1L;
    //        StravaUserRequest stravaUserRequest = new StravaUserRequest("Updated text");
    //        given(stravaUserService.updateStravaUser(eq(stravaUserId),
    // any(StravaUserRequest.class)))
    //                .willThrow(new StravaUserNotFoundException(stravaUserId));
    //
    //        this.mockMvc
    //                .perform(
    //                        put("/api/stravauser/{id}", stravaUserId)
    //                                .contentType(MediaType.APPLICATION_JSON)
    //                                .content(objectMapper.writeValueAsString(stravaUserRequest)))
    //                .andExpect(status().isNotFound())
    //                .andExpect(
    //                        header().string(
    //                                        "Content-Type",
    //                                        is(MediaType.APPLICATION_PROBLEM_JSON_VALUE)))
    //                .andExpect(jsonPath("$.type",
    // is("http://api.trackstrava.com/errors/not-found")))
    //                .andExpect(jsonPath("$.title", is("Not Found")))
    //                .andExpect(jsonPath("$.status", is(404)))
    //                .andExpect(
    //                        jsonPath("$.detail")
    //                                .value(
    //                                        "StravaUser with Id '%d' not found"
    //                                                .formatted(stravaUserId)));
    //    }
    //
    //    @Test
    //    void shouldDeleteStravaUser() throws Exception {
    //        Long stravaUserId = 1L;
    //        StravaUserResponse stravaUser = new StravaUserResponse(stravaUserId, "Some text");
    //        given(stravaUserService.findStravaUserById(stravaUserId))
    //                .willReturn(Optional.of(stravaUser));
    //        doNothing().when(stravaUserService).deleteStravaUserById(stravaUserId);
    //
    //        this.mockMvc
    //                .perform(delete("/api/stravauser/{id}", stravaUserId))
    //                .andExpect(status().isOk())
    //                .andExpect(jsonPath("$.text", is(stravaUser.text())));
    //    }
    //
    //    @Test
    //    void shouldReturn404WhenDeletingNonExistingStravaUser() throws Exception {
    //        Long stravaUserId = 1L;
    //
    // given(stravaUserService.findStravaUserById(stravaUserId)).willReturn(Optional.empty());
    //
    //        this.mockMvc
    //                .perform(delete("/api/stravauser/{id}", stravaUserId))
    //                .andExpect(
    //                        header().string(
    //                                        "Content-Type",
    //                                        is(MediaType.APPLICATION_PROBLEM_JSON_VALUE)))
    //                .andExpect(jsonPath("$.type",
    // is("http://api.trackstrava.com/errors/not-found")))
    //                .andExpect(jsonPath("$.title", is("Not Found")))
    //                .andExpect(jsonPath("$.status", is(404)))
    //                .andExpect(
    //                        jsonPath("$.detail")
    //                                .value(
    //                                        "StravaUser with Id '%d' not found"
    //                                                .formatted(stravaUserId)));
    //    }
    //
    //    List<StravaUserResponse> getStravaUserResponseList() {
    //        return stravaUserList.stream()
    //                .map(stravaUser -> new StravaUserResponse(stravaUser.getId(),
    // stravaUser.getText()))
    //                .toList();
    //    }
}
