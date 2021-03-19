package com.kingjakeu.lolesport.api.config.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "COMMON_CONFIG")
public class Configuration {
    @Id
    @Column(name = "CONFIG_ID", length = 100)
    private String id;

    @Lob
    @Column(name = "CONFIG_VALUE")
    private String value;

    @CreationTimestamp
    @Column(name = "CREATE_DTM", nullable = false, updatable = false,  columnDefinition = "timestamp")
    private LocalDateTime createDateTime;

    @UpdateTimestamp
    @Column(name = "UPDATE_DTM", nullable = false, columnDefinition = "timestamp")
    private LocalDateTime updateDateTime;
}
