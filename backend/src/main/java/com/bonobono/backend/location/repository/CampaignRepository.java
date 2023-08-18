package com.bonobono.backend.location.repository;

import com.bonobono.backend.location.entity.Campaign;
import com.bonobono.backend.location.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Long> {

    List<Campaign> findAllByOrderByIdDesc();

    List<Campaign> findAllByLocationOrderByIdDesc(Location location);
}
