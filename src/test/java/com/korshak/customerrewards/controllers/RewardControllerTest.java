package com.korshak.customerrewards.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.korshak.customerrewards.services.RewardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RewardController.class)
public class RewardControllerTest {
    @MockBean
    private RewardService rewardService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void computeRewards_success() throws Exception {
        CustomerTransaction customerTransaction = new CustomerTransaction("trID", "custID", 12, 123456L);
        when(rewardService.computeAndSaveReward(any())).thenReturn(1);
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String jsonCustTran = ow.writeValueAsString(customerTransaction);
        System.out.println(jsonCustTran);
        mockMvc.perform(post("/reward")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonCustTran))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void computeRewards_error() throws Exception {
        CustomerTransaction customerTransaction = new CustomerTransaction("trID", "custID", 12, 123456L);
        when(rewardService.computeAndSaveReward(any())).thenReturn(-1);
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String jsonCustTran = ow.writeValueAsString(customerTransaction);
        mockMvc.perform(post("/reward")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonCustTran))
                .andExpect(status().is(500));
    }

}
