package com.assignment.discount.testdata;

import com.assignment.discount.domain.Campaign;
import com.assignment.discount.enums.DiscountType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CampaignTestData {

    public static List<Campaign> getCampaignList() {
        List<Campaign> campaigns = new ArrayList<>();

        Campaign campaign = new Campaign();
        campaign.setId("CAMPAIGN-001");
        campaign.setCategoryId("CATEGORY-001");
        campaign.setDiscount(new BigDecimal(50));
        campaign.setMinQuantity(3);
        campaign.setDiscountType(DiscountType.RATE);
        campaigns.add(campaign);

        Campaign campaign2 = new Campaign();
        campaign2.setId("CAMPAIGN-002");
        campaign2.setCategoryId("CATEGORY-001");
        campaign2.setDiscount(new BigDecimal(200));
        campaign2.setMinQuantity(5);
        campaign2.setDiscountType(DiscountType.AMOUNT);
        campaigns.add(campaign2);

        Campaign campaign3 = new Campaign();
        campaign3.setId("CAMPAIGN-003");
        campaign3.setCategoryId("CATEGORY-001");
        campaign3.setDiscount(new BigDecimal(50));
        campaign3.setMinQuantity(3);
        campaign3.setDiscountType(DiscountType.AMOUNT);
        campaigns.add(campaign3);

        return campaigns;
    }

    public static Campaign getCampaign() {
        Campaign campaign = new Campaign();
        campaign.setId("CAMPAIGN-001");
        campaign.setCategoryId("CATEGORY-001");
        campaign.setDiscount(new BigDecimal(50));
        campaign.setMinQuantity(3);
        campaign.setDiscountType(DiscountType.AMOUNT);
        return campaign;
    }
}
