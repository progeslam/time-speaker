package com.smartbear.timespeaker.service.impl;

import com.smartbear.timespeaker.model.ClockNumbersEnum;
import com.smartbear.timespeaker.model.Time;
import com.smartbear.timespeaker.service.SpokenTimeService;
import com.smartbear.timespeaker.validator.TimeValidator;
import lombok.AllArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service("britishSpokenTimeServiceImpl")
@Primary
@AllArgsConstructor
public class BritishSpokenTimeServiceImpl extends BaseTimeService implements SpokenTimeService {

    private static final int SIXTY = 60;
    private static final int MINUTES_LIMIT = 34;
    public static final String EMPTY_SPACE = " ";
    public static final String HALF_KEY_WORD = "half";
    public static final String MIDNIGHT_KEY_WORD = "Midnight";
    public static final String NOON_KEY_WORD = "Noon";
    private static final String TO_KEY_WORD = " to ";
    private static final String PAST_KEY_WORD = " past ";


    @Override
    @TimeValidator
    public String speakTime(final String time) {

        Time receivedTime = buildTimeModel(time);

        if (receivedTime.getMinute() == 0) {
            return speakHoursOnly(receivedTime.getHour());
        }

        return StringUtils.capitalize(speakMinutesWithHours(receivedTime.getHour(), receivedTime.getMinute()));
    }

    private String speakHoursOnly(Integer hours) {

        switch (hours) {
            case 0:
                return MIDNIGHT_KEY_WORD;
            case 12:
                return NOON_KEY_WORD;
            default:
                return StringUtils.capitalize(ClockNumbersEnum.findNameByValue(hours).concat(O_CLOCK));
        }
    }

    private String speakMinutesWithHours(Integer hours, Integer minutes) {

        // 'past' as a default value
        String beforeOrAfter = PAST_KEY_WORD;

        if (minutes > 30 && minutes < 35) {
            return speakWithinThirty(hours, minutes);
        }

        // if minutes greater than limit, change hours & minutes value and set the keyword 'TO'
        // when hours reach to '12', set it back to '1'
        if (minutes > MINUTES_LIMIT) {
            hours = (hours == 12) ? 1 : hours + 1 ;
            minutes = SIXTY - minutes;
            beforeOrAfter = TO_KEY_WORD;
        }

        String spokenMinutes = speakMinutes(minutes);
        String spokenHours = ClockNumbersEnum.findNameByValue(hours);

        return spokenMinutes.concat(beforeOrAfter).concat(spokenHours);

    }

    private String speakWithinThirty(final Integer hours, final Integer minutes) {

        StringBuilder hoursInString = new StringBuilder(ClockNumbersEnum.findNameByValue(hours));
        StringBuilder minutesInString = new StringBuilder(ClockNumbersEnum.findNameByValue(30))
            .append(EMPTY_SPACE)
            .append(ClockNumbersEnum.findNameByValue(minutes - 30));
        // if it's Midnight
        if (hours == 0) {

            return minutesInString.append(PAST_KEY_WORD).append(hoursInString).toString();
        }

        return hoursInString.append(EMPTY_SPACE).append(minutesInString).toString();

    }

    private String speakMinutes(Integer minutes) {

        if (minutes > 20 && minutes < 30) {

            return ClockNumbersEnum.findNameByValue(20).concat(EMPTY_SPACE).concat(ClockNumbersEnum.findNameByValue(minutes - 20));
        } else if (minutes == 30) {

            return HALF_KEY_WORD;
        } else {

            return ClockNumbersEnum.findNameByValue(minutes);
        }
    }

}
