package com.assignment.discount.service;

import com.assignment.discount.domain.Campaign;

import java.math.BigDecimal;

public interface CampaignService {

    Campaign create(Campaign campaign);

    Campaign findBestCampaign(String categoryId, int quantity, BigDecimal amount);
}
