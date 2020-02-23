package com.assignment.discount.service;

import com.assignment.discount.domain.Campaign;
import com.assignment.discount.repository.CampaignRepository;
import com.assignment.discount.service.impl.CampaignServiceImpl;
import com.assignment.discount.testdata.CampaignTestData;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class CampaignServiceTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @MockBean
    private CampaignRepository campaignRepository;

    @Autowired
    private CampaignServiceImpl campaignService;

    @Test
    public void testCreate_SendValidCampaign_ReturnCampaign() {
        Campaign campaign = CampaignTestData.getCampaign();
        when(campaignRepository.save(any(Campaign.class))).thenReturn(campaign);

        Campaign created = campaignService.create(campaign);

        verify(campaignRepository).save(any(Campaign.class));
        Assert.assertEquals(campaign.getId(), created.getId());
        Assert.assertEquals(campaign.getCategoryId(), created.getCategoryId());
        Assert.assertEquals(campaign.getDiscount(), created.getDiscount());
    }

    @Test
    public void testGetByCampaignId_SendCampaignId_ReturnCampaignList() {
        String categoryId = "CAMPAIGN-001";
        List<Campaign> campaigns = CampaignTestData.getCampaignList();
        when(campaignRepository.findByCategoryId(anyString())).thenReturn(campaigns);

        List<Campaign> found = campaignService.getCampaignsByCategoryId(categoryId);

        verify(campaignRepository).findByCategoryId(anyString());
        Assert.assertEquals(3, found.size());
    }

}
