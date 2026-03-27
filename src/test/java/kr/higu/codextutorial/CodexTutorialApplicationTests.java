package kr.higu.codextutorial;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CodexTutorialApplicationTests {

    private final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new SimpleController()).build();

    @Test
    void displayName() throws Exception {
        //given
        String name = "codex";

        //when
        var resultActions = mockMvc.perform(get("/simple/{name}", name));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string("hi " + name));
    }

    @Test
    void displayAge() throws Exception {
        //given
        int age = 20;

        //when
        var resultActions = mockMvc.perform(get("/simple/age/{age}", age));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string("hi " + age));
    }
}
