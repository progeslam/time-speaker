package com.smartbear.timespeaker.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class SpokenTimeResponse {

    private final String spokenTime;

}
