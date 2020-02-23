package com.assignment.discount.service;

import com.assignment.discount.domain.Campaign;

import java.util.List;

public interface CampaignService {

    Campaign create(Campaign campaign);

    List<Campaign> getCampaignsByCategoryId(String categoryId);
}
