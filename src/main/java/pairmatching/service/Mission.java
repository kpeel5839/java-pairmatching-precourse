package pairmatching.service;

import static pairmatching.service.Level.LEVEL1;
import static pairmatching.service.Level.LEVEL2;
import static pairmatching.service.Level.LEVEL4;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Mission {

    RACING_CAR(LEVEL1, "자동차경주"),
    LOTTO(LEVEL1, "로또"),
    BASE_BALL(LEVEL1, "숫자야구게임"),
    SHOPPING(LEVEL2, "장바구니"),
    PAYMENT(LEVEL2, "결제"),
    SUBWAY_PATH(LEVEL2, "지하철노선도"),
    PERFORMANCE_IMPROVEMENT(LEVEL4, "성능개선"),
    DEPLOY(LEVEL4, "배포"),
    ;

    private final Level level;
    private final String name;

    Mission(Level level, String name) {
        this.level = level;
        this.name = name;
    }

    public static Mission fromMissionNameAndLevelName(String levelName, String missionName) {
        return Arrays.stream(values())
                .filter(value -> value.level.getName().equalsIgnoreCase(levelName))
                .filter(value -> value.name.equalsIgnoreCase(missionName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당하는 Mission 이 존재하지 않습니다."));
    }

    public static List<Mission> fromLevel(String input) {
        return Arrays.stream(values())
                .filter(value -> value.level.getName().equalsIgnoreCase(input))
                .collect(Collectors.toList());
    }

    public Level getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }

}
