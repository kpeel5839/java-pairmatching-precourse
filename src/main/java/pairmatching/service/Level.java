package pairmatching.service;

import java.util.Arrays;

public enum Level {

    LEVEL1("레벨1"),
    LEVEL2("레벨2"),
    LEVEL3("레벨3"),
    LEVEL4("레벨4"),
    LEVEL5("레벨5"),
    ;

    private final String name;

    Level(String name) {
        this.name = name;
    }

    public static Level from(String input) {
        return Arrays.stream(values())
                .filter(value -> value.name.equalsIgnoreCase(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 Level 은 존재하지 않습니다."));
    }

    public String getName() {
        return name;
    }

}
