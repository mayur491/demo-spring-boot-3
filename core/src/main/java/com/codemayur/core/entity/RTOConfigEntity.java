package com.codemayur.core.entity;

import lombok.*;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table(value = "rto_config")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class RTOConfigEntity {

    @PrimaryKey(value = "key")
    private String key;

    @Column(value = "value")
    private String value;

}
