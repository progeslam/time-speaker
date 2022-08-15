package com.smartbear.timespeaker.service.impl;

import com.smartbear.timespeaker.service.SpokenTimeService;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Service;

@Service("germanSpokenTimeServiceImpl")
public class GermanSpokenTimeServiceImpl implements SpokenTimeService {

    @Override
    public String speakTime(final String time) {

        throw new NotImplementedException();
    }

}
