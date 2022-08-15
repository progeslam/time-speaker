package com.smartbear.timespeaker.controller;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.smartbear.timespeaker.service.SpokenTimeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class TimeSpeakerControllerTest {

    private static final String SPEAK_TIME_PATH = "/v1/api/speak";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SpokenTimeService spokenTimeService;

    @Test
    public void speakTime_whenInputValidTime_returnTimeInWords() throws Exception {

        //Given
        final String time = "10:30";
        final String expectedResult = "Half past ten";
        when(spokenTimeService.speakTime(anyString())).thenReturn(expectedResult);

        //When
        final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post(SPEAK_TIME_PATH)
            .param("time", time)
            .contentType(MediaType.APPLICATION_JSON));

        //Then
        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.spokenTime").value(expectedResult));
    }

}
