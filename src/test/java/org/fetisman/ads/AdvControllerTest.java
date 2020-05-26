package org.fetisman.ads;

import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
}
