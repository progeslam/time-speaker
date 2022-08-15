package com.smartbear.timespeaker.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.smartbear.timespeaker.exception.ServiceUnprocessableException;
import com.smartbear.timespeaker.service.impl.BritishSpokenTimeServiceImpl;
import com.smartbear.timespeaker.validator.TimeValidatorInterceptor;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@Import(BritishSpokenTimeServiceImpl.class)
@SpringBootTest(classes = {TimeValidatorInterceptor.class, AnnotationAwareAspectJAutoProxyCreator.class})
public class BritishSpokenTimeServiceTest {

    @Autowired
    private SpokenTimeService spokenTimeService;

    @Test
    public void speak_inValidRequest_throwException() {
        // GIVEN
        final String time = "1130";
        final String expectedMessage = "Invalid format for input time 1130";

        // WHEN
        final Exception exception = assertThrows(ServiceUnprocessableException.class,
            () -> spokenTimeService.speakTime(time));

        // THEN
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    public void speak_emptyRequest_throwException() {
        // GIVEN
        final String time = "";
        final String expectedMessage = "Invalid format for input time ";

        // WHEN
        final Exception exception = assertThrows(ServiceUnprocessableException.class,
            () -> spokenTimeService.speakTime(time));

        // THEN
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    public void speak_nullRequest_throwException() {
        // GIVEN
        final String time = null;
        final String expectedMessage = "Invalid format for input time null";

        // WHEN
        final Exception exception = assertThrows(ServiceUnprocessableException.class,
            () -> spokenTimeService.speakTime(time));

        // THEN
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void speak_validSamples_returnsSuccess() {

        //GIVEN
        final Map<String, String> samples = new HashMap<>();
        samples.put("1:00", "One o'clock");
        samples.put("02:05", "Five past two");
        samples.put("03:10", "Ten past three");
        samples.put("04:15", "Quarter past four");
        samples.put("05:20", "Twenty past five");
        samples.put("06:25", "Twenty five past six");
        samples.put("06:32", "Six thirty two");
        samples.put("07:30", "Half past seven");
        samples.put("07:35", "Twenty five to eight");
        samples.put("08:40", "Twenty to nine");
        samples.put("09:45", "Quarter to ten");
        samples.put("10:50", "Ten to eleven");
        samples.put("11:55", "Five to twelve");
        samples.put("00:00", "Midnight");
        samples.put("12:00", "Noon");

        samples.forEach((inputTime, expectedOutput) -> {
            //When
            final String actualOutput = spokenTimeService.speakTime(inputTime);

            //Then
            assertEquals(expectedOutput, actualOutput);
        });
    }

    @Test
    public void speak_validRequest_returnsSuccess() {
        // GIVEN
        final String time = "10:30";
        final String expectedResult = "Half past ten";

        // WHEN
        String result = spokenTimeService.speakTime(time);

        // THEN
        assertEquals(expectedResult, result);
    }

    @Test
    void speak_24HoursFormat_returnsSuccess() {

        //Given
        final String time = "15:00";

        //When
        final String expectedOutput = spokenTimeService.speakTime(time);

        //Then
        Assertions.assertEquals("Three o'clock", expectedOutput);
    }

    @Test
    void speak_beforeQuarter_returnsSuccess() {

        //Given
        final String time = "07:13";

        //When
        final String expectedOutput = spokenTimeService.speakTime(time);

        //Then
        Assertions.assertEquals("Thirteen past seven", expectedOutput);
    }

    @Test
    void speak_hourOneDigitWithinThirty_returnsSuccess() {

        //Given
        final String time = "0:33";

        //When
        final String expectedOutput = spokenTimeService.speakTime(time);

        //Then
        Assertions.assertEquals("Thirty three past Midnight", expectedOutput);
    }

    @Test
    void speak_afterThirsty_returnsSuccess() {

        //Given
        final String time = "20:42";

        //When
        final String expectedOutput = spokenTimeService.speakTime(time);

        //Then
        Assertions.assertEquals("Eighteen to nine", expectedOutput);
    }

}
