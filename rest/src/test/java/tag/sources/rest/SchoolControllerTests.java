package tag.sources.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import tag.sources.rest.models.School;
import tag.sources.rest.repositories.SchoolRepository;
import tag.sources.rest.utils.TestStringUtils;

import java.io.IOException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SchoolControllerTests {
    private static final String INIT_SCRIPT = "src/test/resources/init.sql";
    private static final String CLEAN_SCRIPT = "src/test/resources/clean.sql";
    private final MockMvc mockMvc;
    private final JdbcTemplate jdbcTemplate;
    private final SchoolRepository schoolRepository;

    @Autowired
    public SchoolControllerTests(MockMvc mockMvc, JdbcTemplate jdbcTemplate, SchoolRepository schoolRepository1) {
        this.mockMvc = mockMvc;
        this.jdbcTemplate = jdbcTemplate;
        this.schoolRepository = schoolRepository1;
    }

    @BeforeEach
    void setUp() throws IOException {
        String initScript = TestStringUtils.readFile(INIT_SCRIPT);
        jdbcTemplate.execute(initScript);
    }

    @AfterEach
    void cleanUp() throws IOException {
        String initScript = TestStringUtils.readFile(CLEAN_SCRIPT);
        jdbcTemplate.execute(initScript);
    }

    @Test
    void getAllTest() throws Exception {
        this.mockMvc.perform(get("/schools")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void getByIdTest() throws Exception {
        School school = schoolRepository.findAll().stream().findAny().orElseThrow();
        this.mockMvc.perform(get(String.format("/schools/%s", school.getId()))).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void updateTest() throws Exception {
        School school = schoolRepository.findAll().stream().findAny().orElseThrow();
        school.setYear(2020);
        MultiValueMap<String, School> params = new LinkedMultiValueMap<>();
        this.mockMvc.perform(patch(String.format("/schools/%s", school.getId()), school).contentType(MediaType.APPLICATION_JSON)
                .content( new ObjectMapper().writer().writeValueAsString(school))).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void deleteTest() throws Exception {
        School school = schoolRepository.findAll().stream().findAny().orElseThrow();
        this.mockMvc.perform(delete(String.format("/schools/%s", school.getId()))).andDo(print()).andExpect(status().isAccepted());
    }
}
