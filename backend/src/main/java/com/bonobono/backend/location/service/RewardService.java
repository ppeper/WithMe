package com.bonobono.backend.location.service;

import com.bonobono.backend.location.repository.RewardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RewardService {

    private final RewardRepository rewardRepository;
}
