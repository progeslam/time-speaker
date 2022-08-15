package com.smartbear.timespeaker.validator;

import com.smartbear.timespeaker.exception.ServiceUnprocessableException;
import java.util.Objects;
import lombok.AllArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
@AllArgsConstructor
public class TimeValidatorInterceptor {

    final static String TIME_REGEX = "([01]?[0-9]|2[0-3]):[0-5][0-9]";
    final static String ERROR_MESSAGE = "Invalid format for input time ";


    /**
     * Validate the input time using regex '([01]?[0-9]|2[0-3]):[0-5][0-9]' to accept only valid time, it accepts 'hh:mm' & 'h:mm' formats,
     * hours accept 0-23 and minutes accepts 0-59
     *
     * @param proceedingJoinPoint cross-cutting object holding the input time to be validated
     * @return the execution to the target method when operation is completed
     * @throws Throwable throw ServiceUnprocessableException when validation fails or input is null
     */
    @Around("execution(* *(..)) && @annotation(TimeValidator)")
    public Object timeValidator(final ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        final String inputTime = (String) proceedingJoinPoint.getArgs()[0];

        if (Objects.isNull(inputTime) || !inputTime.matches(TIME_REGEX)) {
            throw new ServiceUnprocessableException(ERROR_MESSAGE + inputTime);
        }

        return proceedingJoinPoint.proceed();
    }

}
