package com.smartbear.timespeaker.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ErrorResponseModel {

    private String message;

}
