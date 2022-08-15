package com.smartbear.timespeaker.service.impl;

import com.smartbear.timespeaker.model.Time;

public class BaseTimeService {

    private static final String SPLIT_REGEX = ":";
    protected static final String O_CLOCK = " o'clock";

    /**
     * Splitting the time input by colon delimiter and build the 'Time' object , if hours sent as 24h format it would be converted to 12h
     * format by subtracting 12 from hours value
     *
     * @param time a string represents input time in numbers 'hh:mm'
     * @return Time Object after splitting hours and minutes
     */
    protected Time buildTimeModel(final String time) {

        String[] inputTime = time.split(SPLIT_REGEX);
        Integer hours = Integer.parseInt(inputTime[0]);

        // convert input time format from 24h to 12h
        hours = hours > 12 ? hours - 12 : hours;

        return Time.builder()
            .hour(hours)
            .minute(Integer.parseInt(inputTime[1]))
            .build();
    }

}
