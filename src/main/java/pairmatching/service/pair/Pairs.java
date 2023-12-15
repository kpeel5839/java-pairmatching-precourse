package pairmatching.service.pair;

import java.util.ArrayList;
import java.util.List;

public class Pairs {

    private List<Pair> pairs;

    private Pairs(List<Pair> pairs) {
        this.pairs = pairs;
    }

    public static Pairs from(List<String> crewNames) {
        validateSize(crewNames);
        return new Pairs(matching(crewNames));
    }

    private static void validateSize(List<String> crewNames) {
        if (2 <= crewNames.size()) {
            return;
        }

        throw new IllegalArgumentException("크루 목록은 2명 이상이어야 합니다.");
    }

    private static List<Pair> matching(List<String> crewNames) {
        if (isCrewsSizeOdd(crewNames)) {
            createPairsWhenCrewsSizeOdd(crewNames);
        }

        return createPairsWhenCrewsSizeEven(crewNames);
    }

    private static boolean isCrewsSizeOdd(List<String> crewNames) {
        return crewNames.size() % 2 == 1;
    }

    private static List<Pair> createPairsWhenCrewsSizeOdd(List<String> crewNames) {
        List<Pair> pairs = new ArrayList<>();

        for (int startIndex = 0; startIndex < crewNames.size() / 2 - 1; startIndex++) {
            pairs.add(extractPair(crewNames, startIndex * 2, 2));
        }

        pairs.add(extractPair(crewNames, crewNames.size() - 3, 3));
        return pairs;
    }

    private static List<Pair> createPairsWhenCrewsSizeEven(List<String> crewNames) {
        List<Pair> pairs = new ArrayList<>();

        for (int startIndex = 0; startIndex < crewNames.size() / 2; startIndex++) {
            pairs.add(extractPair(crewNames, startIndex * 2, 2));
        }

        return pairs;
    }

    private static Pair extractPair(
            List<String> crewNames,
            int startIndex,
            int pairSize
    ) {
        List<String> pairCrews = new ArrayList<>();

        for (int j = 0; j < pairSize; j++) {
            pairCrews.add(crewNames.get(startIndex + j));
        }

        return Pair.from(pairCrews);
    }

    public List<Pair> getPairs() {
        return pairs;
    }

}
