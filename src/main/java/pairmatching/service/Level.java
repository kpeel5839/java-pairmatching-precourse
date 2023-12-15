package pairmatching.service;

import static pairmatching.service.Mission.BASE_BALL;
import static pairmatching.service.Mission.DEPLOY;
import static pairmatching.service.Mission.LOTTO;
import static pairmatching.service.Mission.PAYMENT;
import static pairmatching.service.Mission.PERFORMANCE_IMPROVMENT;
import static pairmatching.service.Mission.RACING_CAR;
import static pairmatching.service.Mission.SHOPPING;
import static pairmatching.service.Mission.SUBWAY_PATH;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Level {

    LEVEL1("레벨1", Arrays.asList(RACING_CAR, LOTTO, BASE_BALL)),
    LEVEL2("레벨2", Arrays.asList(SHOPPING, PAYMENT, SUBWAY_PATH)),
    LEVEL3("레벨3", Collections.emptyList()),
    LEVEL4("레벨4", Arrays.asList(PERFORMANCE_IMPROVMENT, DEPLOY)),
    LEVEL5("레벨5", Collections.emptyList()),
    ;

    private final String name;
    private final List<Mission> missions;

    Level(final String name, final List<Mission> missions) {
        this.name = name;
        this.missions = missions;
    }

    public String getName() {
        return name;
    }

    public List<Mission> getMissions() {
        return missions;
    }

}
