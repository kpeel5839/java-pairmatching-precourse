package pairmatching.service;

import java.util.Arrays;

public enum Course {

    BACKEND("백엔드"),
    FRONTEND("프론트엔드"),
    ;

    private final String name;

    Course(String name) {
        this.name = name;
    }

    public static Course from(String input) {
        return Arrays.stream(values())
                .filter(value -> value.name.equalsIgnoreCase(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당하는 Course 가 존재하지 않습니다."));
    }

    public String getName() {
        return name;
    }

}
