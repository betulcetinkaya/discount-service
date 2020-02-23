package com.assignment.discount.controller;

import com.assignment.discount.domain.Campaign;
import com.assignment.discount.service.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;

@RestController
@RequestMapping("/campaigns")
public class CampaignController {

    @Autowired
    private CampaignService campaignService;

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public
    @ResponseBody
    ResponseEntity<Campaign> create(@Valid @RequestBody Campaign campaign) {
        return new ResponseEntity<>(campaignService.create(campaign), HttpStatus.CREATED);
    }

    @GetMapping
    public
    @ResponseBody
    ResponseEntity<Campaign> getBestCampaign(@RequestParam(value = "categoryId") String categoryId,
                                                         @RequestParam(value = "quantity") int quantity,
                                                         @RequestParam(value = "amount") BigDecimal amount) {
        Campaign campaign = campaignService.findBestCampaign(categoryId, quantity, amount);
        return new ResponseEntity<>(campaign, HttpStatus.OK);
    }

}