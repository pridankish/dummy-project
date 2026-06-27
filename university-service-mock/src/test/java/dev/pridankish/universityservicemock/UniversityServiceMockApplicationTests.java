package dev.pridankish.universityservicemock;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class UniversityServiceMockApplicationTests {

    @Resource
    private MockMvc mvc;

    @Test
    void contextLoads() throws Exception {

//        mvc.perform(get("/"))
//                .andExpect(status().isOk())
//                .andExpect((ResultMatcher) content().bytes("".getBytes()));
    }

}
