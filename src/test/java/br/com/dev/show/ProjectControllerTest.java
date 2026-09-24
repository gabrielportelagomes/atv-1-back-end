package br.com.dev.show;

import br.com.dev.show.model.Project;
import br.com.dev.show.repository.ProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProjectRepository projectRepository;

    private Long testProjectId;

    @BeforeEach
    void setUp() {
        projectRepository.deleteAll();
        Project project = projectRepository.save(new Project("Plataforma Eventos", "Java"));
        testProjectId = project.getId();
    }

    @Test
    void shouldAddFeedbackAndCalculateAverageRating() throws Exception {
        String json = """
            {
                "rating": 5,
                "comment": "Excelente arquitetura!"
            }
        """;

        mockMvc.perform(post("/api/projects/" + testProjectId + "/feedbacks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.rating").value(5))
                .andExpect(jsonPath("$.comment").value("Excelente arquitetura!"));
    }

    @Test
    void shouldIncrementUpvote() throws Exception {
        mockMvc.perform(put("/api/projects/" + testProjectId + "/upvote"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.upvotes").value(1));
    }

    @Test
    void shouldListProjectsWithPaginationAndFilter() throws Exception {
        mockMvc.perform(get("/api/projects")
                        .param("technology", "Java")
                        .param("page", "0")
                        .param("size", "5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].name").value("Plataforma Eventos"))
                .andExpect(jsonPath("$.content[0].technology").value("Java"));
    }
}
