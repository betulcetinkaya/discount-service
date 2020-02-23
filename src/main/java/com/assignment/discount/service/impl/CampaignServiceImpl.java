package com.assignment.discount.service.impl;

import com.assignment.discount.domain.Campaign;
import com.assignment.discount.repository.CampaignRepository;
import com.assignment.discount.service.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<Campaign> getCampaignsByCategoryId(String categoryId) {
        return campaignRepository.findByCategoryId(categoryId);
    }
}
