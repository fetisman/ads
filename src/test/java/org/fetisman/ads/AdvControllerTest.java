package org.fetisman.ads;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@WithUserDetails("ff101")
@Sql(value = {"/create-user-before.sql", "/adv-list-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/adv-list-after.sql", "/create-user-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class AdvControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void advsPageTest() throws Exception {
        this.mockMvc.perform(get("/main/0"))
        .andDo(print())
        .andExpect(authenticated())
        .andExpect(xpath("//div[@id=\"navbarNav\"]/ul/div").string("ff101"));
    }

    @Test
    public void advListTest() throws Exception {
        this.mockMvc.perform(get("/main/0"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("//*[@id=\"adv-list\"]/tr").nodeCount(3));
    }

    @Test
    public void filterAdvTest() throws Exception {
        this.mockMvc.perform(get("/main/1").param("filter", "root 1.1"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("//*[@id=\"adv-list\"]/tr").nodeCount(2))
                .andExpect(xpath("//*[@id=\"adv-list\"]/tr[@data-id='1']").exists())
                .andExpect(xpath("//*[@id=\"adv-list\"]/tr[@data-id='2']").exists());

    }

    @Test
    public void addAdvToListTest() throws  Exception {
        MockHttpServletRequestBuilder multipart = multipart("/main/0")
                .file("file", "123".getBytes())
                .param("shortDesc", "d")
                .param("longDesc", "ddd")
                .param("phone", "444 44 44")
                .param("type", "root 2.2")
                .with(csrf());

        this.mockMvc.perform(multipart)
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("//*[@id=\"adv-list\"]/tr").nodeCount(4))
                .andExpect(xpath("//*[@id=\"adv-list\"]/tr[@data-id='10']").exists())
                .andExpect(xpath("//*[@id=\"adv-list\"]/tr[@data-id='10']/td/i").string("root 2.2"))
                .andExpect(xpath("//*[@id=\"adv-list\"]/tr[@data-id='10']/td/span").string("d"));

    }
}
