package pairmatching;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Level {
    LEVEL1("레벨1", Arrays.asList("자동차경주", "로또", "숫자야구게임")),
    LEVEL2("레벨2", Arrays.asList("장바구니", "결제", "지하철노선")),
    LEVEL3("레벨3", Collections.emptyList()),
    LEVEL4("레벨4", Arrays.asList("성능개선", "배포")),
    LEVEL5("레벨5", Collections.emptyList());

    private final String name;
    private final List<String> missions;

    Level(String name, List<String> missions) {
        this.name = name;
        this.missions = missions;
    }

    public static Level getByName(String byName) {
        return Arrays.stream(values())
                .filter(name -> name.getName().equals(byName))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public static Level get(String byName, String mission) {
        return Arrays.stream(values())
                .filter(name -> name.getName().equals(byName))
                .filter(missions -> missions.getMissions().contains(mission))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public String getName() {
        return this.name;
    }

    public List<String> getMissions() {
        return this.missions;
    }

    @Override
    public String toString() {
        return name + " " + missions;
    }
}
