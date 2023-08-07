package com.bonobono.backend.location.service;

import com.bonobono.backend.location.repository.CampaignRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CampaignService {

    private final CampaignRepository campaignRepository;
}
