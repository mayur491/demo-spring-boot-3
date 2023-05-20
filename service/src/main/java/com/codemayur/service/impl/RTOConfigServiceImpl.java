package com.codemayur.service.impl;

import com.codemayur.core.entity.RTOConfigEntity;
import com.codemayur.core.repo.RTOConfigRepo;
import com.codemayur.service.RTOConfigService;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RTOConfigServiceImpl implements RTOConfigService {

    private final RTOConfigRepo rtoConfigRepo;

    public RTOConfigServiceImpl(RTOConfigRepo rtoConfigRepo) {
        this.rtoConfigRepo = rtoConfigRepo;
    }

    @Override
    public Optional<String> getConfigValue(@NonNull String key) {
        Optional<RTOConfigEntity> entityOptional = rtoConfigRepo.findById(key);
        return entityOptional.map(RTOConfigEntity::getValue);
    }

}
