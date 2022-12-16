package pairmatching;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Mission {
    private List<List<String>> pair;

    public Mission() {
        pair = new ArrayList<>();
    }

    public void match(Course course, Map<Course, Map<String, HashSet<String>>> matchingBoard, CrewRepository crewRepository) {
        List<String> nameOfCrews = extractName(crewRepository.getCrewBy(course));
//        System.out.println(nameOfCrews);
        int shuffleCount = 0;
        boolean matchingSuccess = false;
        while (!matchingSuccess) {
            nameOfCrews = Randoms.shuffle(nameOfCrews); // 섞어버렷
            int numberOfPair = (int) Math.floor(nameOfCrews.size() / 2d);
            pair = new ArrayList<>();
            for (int count = 1; count <= numberOfPair; count++) {
                addSubList(count, numberOfPair, nameOfCrews);
            }
            shuffleCount++;
            shuffleValidate(shuffleCount);
            matchingSuccess = isMatchingSuccess(matchingBoard.get(course)); // pair 괜찮으면 true 반환
        }
    }

    private void addSubList(int count, int numberOfPair, List<String> nameOfCrews) {
        if (count == numberOfPair) {
            pair.add(nameOfCrews.subList(count * 2 - 2, nameOfCrews.size()));
        }

        if (count != numberOfPair) {
            System.out.println(count * 2 - 2 + " " + count * 2);
            pair.add(nameOfCrews.subList(count * 2 - 2, count * 2));
        }
    }

    private void shuffleValidate(int shuffleCount) {
        if (3 <= shuffleCount) {
            System.out.print(Const.ERROR);
            throw new IllegalArgumentException(" 3번 이상 섞어보렷..");
        }
    }

    private boolean isMatchingSuccess(Map<String, HashSet<String>> matchingBoard) {
        boolean matchingSuccess = true;

        for (List<String> partial : pair) {
            if (!parsingPartial(partial, matchingBoard)) { // 한번이라도 false 이면 안됨
                matchingSuccess = false;
                break;
            }
        }

        return matchingSuccess;
    }

    private boolean parsingPartial(List<String> partial, Map<String, HashSet<String>> matchingBoard) {
        boolean matchingSuccess = true;

        for (int me = 0; me < partial.size(); me++) {
            if (isContains(me, partial, matchingBoard)) {
                matchingSuccess = false;
                break;
            }
        }

        return matchingSuccess;
    }

    private boolean isContains(int me, List<String> partial, Map<String, HashSet<String>> matchingBoard) {
        boolean isContains = false;

        for (int you = 0; you < partial.size(); you++) {
            if (me != you && matchingBoard.get(partial.get(me)).contains(partial.get(you))) {
                isContains = true;
            }
        }

        return isContains;
    }

    private List<String> extractName(List<Crew> crews) {
        if (crews.size() < 2) {
            System.out.print(Const.ERROR);
            throw new IllegalArgumentException(" matching 이 불가능 합니다.");
        }

        return crews.stream()
                .map(Crew::getName)
                .collect(Collectors.toList());
    }

    public List<List<String>> getPair() {
        return this.pair;
    }

    public boolean isPairExist() {
        return pair.size() != 0;
    }
}
