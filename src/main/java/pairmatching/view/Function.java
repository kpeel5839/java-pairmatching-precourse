package pairmatching.view;

import java.util.Arrays;

public enum Function {

    MATCHING("1"),
    INQUIRY("2"),
    CLEAR("3"),
    EXIT("Q"),
    ;

    private final String input;

    Function(String input) {
        this.input = input;
    }

    public static Function from(String input) {
        return Arrays.stream(values())
                .filter(value -> value.input.equalsIgnoreCase(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 입력입니다."));
    }

}
