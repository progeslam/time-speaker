package com.smartbear.timespeaker.controller;

import com.smartbear.timespeaker.dto.SpokenTimeResponse;
import com.smartbear.timespeaker.service.SpokenTimeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(path = TimeSpeakerController.ROOT_PATH)
@CrossOrigin
@AllArgsConstructor()
public class TimeSpeakerController {

    static final String ROOT_PATH = "/";
    private static final String SPEAK_TIME_PATH = "v1/api/speak";

    private final SpokenTimeService spokenTimeService;


    /**
     * The entrypoint to the application where the root path '/' directed to
     *
     * @return ModelAndView object containing index page as the view
     */
    @GetMapping
    public ModelAndView indexPage() {

        return new ModelAndView("index");
    }

    /**
     * Convert time from numbers format to a spoken form
     *
     * @param time String where the input time to be spoken ,and it accepts those formats 'hh:mm' , 'h:mm'
     * @return SpokenTimeResponse object contains spoken time as a String in case of success ,and returns 'ServiceUnprocessableException'
     * with error code 422 in case of failure
     */
    @PostMapping(path = SPEAK_TIME_PATH)
    @ResponseStatus(HttpStatus.OK)
    public SpokenTimeResponse speakTime(final @RequestParam("time") String time) {

        return SpokenTimeResponse.builder()
            .spokenTime(spokenTimeService.speakTime(time))
            .build();
    }

}
