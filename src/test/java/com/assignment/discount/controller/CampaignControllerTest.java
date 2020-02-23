package com.assignment.discount.controller;

import com.assignment.discount.ControllerBaseTest;
import com.assignment.discount.domain.Campaign;
import com.assignment.discount.service.CampaignService;
import com.assignment.discount.testdata.CampaignTestData;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CampaignController.class)
public class CampaignControllerTest extends ControllerBaseTest {

    @MockBean
    private CampaignService campaignService;

    @BeforeClass
    public static void init() {
        baseAddress = "/campaigns";
    }

    @Test
    public void testCreateCampaign_SendValidRequest_ReturnCreated() throws Exception {
        Campaign campaign = CampaignTestData.getCampaign();
        when(campaignService.create(any(Campaign.class))).thenReturn(campaign);

        ResultActions perform = mockMvc.perform(post(baseAddress)
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(campaign)));


        perform.andExpect(status().isCreated());
    }

    @Test
    public void testGetCampaign_SendId_GetACampaign() throws Exception {
        String campaignId = "CATEGORY-001";
        List<Campaign> campaigns = CampaignTestData.getCampaignList();
        when(campaignService.getCampaignsByCategoryId(campaignId)).thenReturn(campaigns);

        ResultActions resultActions = mockMvc.perform(get(baseAddress + "?categoryId=" + campaignId)
                .contentType(mediaType));

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(3)));

        verify(campaignService).getCampaignsByCategoryId(anyString());
    }


}