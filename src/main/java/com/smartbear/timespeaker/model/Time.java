package com.smartbear.timespeaker.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Getter
public class Time {

    private Integer hour;
    private Integer minute;
}
