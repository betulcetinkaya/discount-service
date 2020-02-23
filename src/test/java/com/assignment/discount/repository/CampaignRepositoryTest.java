package com.assignment.discount.repository;

import com.assignment.discount.DiscountConstants;
import com.assignment.discount.RepositoryBaseTest;
import com.assignment.discount.domain.Campaign;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.fail;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class CampaignRepositoryTest extends RepositoryBaseTest {

    @Autowired
    CampaignRepository campaignRepository;

    @Before
    public void onSetup() throws Exception {
        this.objectMapper = new ObjectMapper();
        if (!mongoTemplate.collectionExists(DiscountConstants.CAMPAIGN_DOCUMENT_NAME))
        mongoTestDataLoader.loadTestDataIntoDb(DiscountConstants.CAMPAIGN_DOCUMENT_NAME);

        System.out.println("");
    }

    @Test
    public void testFindByCategoryId_SendCategoryId_ReturnCampaignList() {
        String categoryId = "Category1";

        List<Campaign> campaigns = campaignRepository.findByCategoryId(categoryId);

        Assert.assertEquals(2, campaigns.size());
        Assert.assertEquals(categoryId, campaigns.get(0).getCategoryId());
        Assert.assertEquals(categoryId, campaigns.get(1).getCategoryId());
    }

    @After
    public void clearDb() {
        try {
            mongoTemplate.dropCollection(DiscountConstants.CAMPAIGN_DOCUMENT_NAME);
        } catch (Exception e) {
            fail("error on After", e);
        }
    }

}
