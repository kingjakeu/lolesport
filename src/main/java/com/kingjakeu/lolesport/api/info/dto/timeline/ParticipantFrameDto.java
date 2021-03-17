package com.kingjakeu.lolesport.api.info.dto.timeline;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ParticipantFrameDto {
    @JsonProperty(value = "1")
    private ParticipantDto participant1;

    @JsonProperty(value = "2")
    private ParticipantDto participant2;

    @JsonProperty(value = "3")
    private ParticipantDto participant3;

    @JsonProperty(value = "4")
    private ParticipantDto participant4;

    @JsonProperty(value = "5")
    private ParticipantDto participant5;

    @JsonProperty(value = "6")
    private ParticipantDto participant6;

    @JsonProperty(value = "7")
    private ParticipantDto participant7;

    @JsonProperty(value = "8")
    private ParticipantDto participant8;

    @JsonProperty(value = "9")
    private ParticipantDto participant9;

    @JsonProperty(value = "10")
    private ParticipantDto participant10;
}
