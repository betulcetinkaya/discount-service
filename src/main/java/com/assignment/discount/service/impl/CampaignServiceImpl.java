package com.assignment.discount.service.impl;

import com.assignment.discount.domain.Campaign;
import com.assignment.discount.enums.DiscountType;
import com.assignment.discount.repository.CampaignRepository;
import com.assignment.discount.service.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class CampaignServiceImpl implements CampaignService {

    @Autowired
    private CampaignRepository campaignRepository;

    @Override
    public Campaign create(Campaign campaign) {
        return campaignRepository.save(campaign);
    }

    @Override
    public Campaign findBestCampaign(String categoryId, int quantity, BigDecimal amount) {
        List<Campaign> campaigns = campaignRepository.findByCategoryIdAndQuantity(categoryId, quantity);
        BigDecimal maxDiscount = BigDecimal.ZERO;
        Campaign bestCampaign = null;
        for (Campaign campaign : campaigns) {
            BigDecimal totalDiscount;
            if (DiscountType.RATE.equals(campaign.getDiscountType())) {
                totalDiscount = amount.multiply(campaign.getDiscount()).divide(new BigDecimal(100), 10, RoundingMode.UP);
            } else {
                totalDiscount = campaign.getDiscount();
            }
            if (totalDiscount.compareTo(maxDiscount) > 0) {
                maxDiscount = totalDiscount;
                bestCampaign = campaign;
            }
        }
        return bestCampaign;
    }
}
