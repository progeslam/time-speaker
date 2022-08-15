package com.smartbear.timespeaker.service;

public interface SpokenTimeService {

    /**
     * Convert time from number format 'hh:mm' to spoken format ,like '2:05' covert to 'five past two'
     *
     * @param time a string represents the input time as number format 'hh:mm'
     * @return Spoken time form as a string after converting
     */
    String speakTime(String time);

}
