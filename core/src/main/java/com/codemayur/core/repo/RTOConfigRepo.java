package com.codemayur.core.repo;

import com.codemayur.core.entity.RTOConfigEntity;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RTOConfigRepo extends CassandraRepository<RTOConfigEntity, String> {
}
