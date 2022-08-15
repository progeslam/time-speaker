package com.smartbear.timespeaker.model;

import java.util.Objects;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ClockNumbersEnum {

    ZERO(0, "Midnight"),
    ONE(1, "one"),
    TWO(2, "two"),
    THREE(3, "three"),
    FOUR(4, "four"),
    FIVE(5, "five"),
    SIX(6, "six"),
    SEVEN(7, "seven"),
    EIGHT(8, "eight"),
    NINE(9, "nine"),
    TEN(10, "ten"),
    ELEVEN(11, "eleven"),
    TWELVE(12, "twelve"),
    THIRTEEN(13, "thirteen"),
    FOURTEEN(14, "fourteen"),
    QUARTER(15, "quarter"),
    SIXTEEN(16, "sixteen"),
    SEVENTEEN(17, "seventeen"),
    EIGHTEEN(18, "eighteen"),
    NINETEEN(19, "nineteen"),
    TWENTY(20, "twenty"),
    THIRTY(30, "thirty"),
    FORTY(40, "forty"),
    FIFTY(50, "fifty");


    private final Integer value;
    private final String name;

    /**
     * Retrieve the corresponding time spoken form of an input time number
     *
     * @param key is the time in numbers format
     * @return a String corresponds to spoken form of time number
     */
    public static String findNameByValue(final Integer key) {

        return Stream.of(values())
            .filter(clockNumbers -> Objects.equals(clockNumbers.value, key))
            .findFirst().get().name;
    }
}
