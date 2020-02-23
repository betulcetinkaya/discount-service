package com.assignment.discount.repository;

import com.assignment.discount.domain.Campaign;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampaignRepository extends MongoRepository<Campaign, String> {

    @Query("{categoryId : ?0 })")
    List<Campaign> findByCategoryId(String categoryId);



}
