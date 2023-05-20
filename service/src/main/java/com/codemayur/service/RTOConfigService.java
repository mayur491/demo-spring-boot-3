package com.codemayur.service;

import lombok.NonNull;

import java.util.Optional;

public interface RTOConfigService {

    Optional<String> getConfigValue(@NonNull String key);

}
