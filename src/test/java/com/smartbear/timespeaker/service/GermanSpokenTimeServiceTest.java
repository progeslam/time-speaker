package com.smartbear.timespeaker.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.smartbear.timespeaker.exception.ServiceUnprocessableException;
import com.smartbear.timespeaker.service.impl.GermanSpokenTimeServiceImpl;
import com.smartbear.timespeaker.validator.TimeValidatorInterceptor;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.NotImplementedException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@Import(GermanSpokenTimeServiceImpl.class)
@SpringBootTest(classes = {TimeValidatorInterceptor.class, AnnotationAwareAspectJAutoProxyCreator.class})
public class GermanSpokenTimeServiceTest {

    @Autowired
    private SpokenTimeService spokenTimeService;

    @Test
    public void speak_validRequest_throwException() {
            // GIVEN
            final String time = "11:30";
            final String expectedMessage = "Code is not implemented";

            // WHEN
            final Exception exception = assertThrows(NotImplementedException.class,
                () -> spokenTimeService.speakTime(time));

            // THEN
            assertEquals(expectedMessage, exception.getMessage());
        }

}
