package com.kingjakeu.lolesport.api.info.dto.matchHistory;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class TimelineDetailDto {
    @JsonProperty(value = "10-20")
    private Double tenToTwenty;

    @JsonProperty(value = "0-10")
    private Double zeroToTen;
}
