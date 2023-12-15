package pairmatching.service.pair;

import java.util.List;

public class Pair {

    private final List<String> crews;

    private Pair(List<String> crews) {
        this.crews = crews;
    }

    public static Pair from(List<String> crews) {
        validateCrewsSize(crews);

        return new Pair(crews);
    }

    private static void validateCrewsSize(List<String> crews) {
        if (crews.size() == 2 || crews.size() == 3) {
            return;
        }

        throw new IllegalArgumentException("Pair 는 2명 혹은 3명이어야 합니다.");
    }

    public List<String> getCrews() {
        return crews;
    }

}
