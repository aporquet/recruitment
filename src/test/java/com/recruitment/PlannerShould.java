package com.recruitment;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

public class PlannerShould {

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @Before
    public void setup () {
        DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
        this.mockMvc = builder.build();
    }

    @Test
    public void return_candidate_not_exist_error_if_idCandidate_is_null(){
 /*       MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.post("/schedule")
                .header("headerValue")
                .content()*/
    }

    @Test
    public void return_interview_date_is_prior_than_current_date_error_if_date_is_equal_or_prior_than_current_date(){

    }

    @Test
    public void schedule_interview(){

    }
}
