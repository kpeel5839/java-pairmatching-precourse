package pairmatching;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class CrewRepository {
    private List<Crew> crews;
    private Map<Level, EachLevel> levels; // 각 레벨당 맞는 Each Level 이 주어져있는걸로

    public CrewRepository() {
        crews = new ArrayList<>();
        readCrews();
        initLevels();
    }

    public void clear() {
        for (Level level : levels.keySet()) {
            EachLevel eachLevel = levels.get(level);
            eachLevel.clear(this);
        }
    }

    private void initLevels() {
        levels = new HashMap<>();
        Arrays.stream(Level.values())
                .forEach(level -> levels.put(level, new EachLevel(level, this)));
    }

    private void readCrews() {
        readAndAddAll(Course.BACKEND, FileHandler.readBackendCrew());
        readAndAddAll(Course.FRONTEND, FileHandler.readFrontendCrew());
    }

    private void readAndAddAll(Course course, String nameOfAll) {
        StringTokenizer separateByLine = new StringTokenizer(nameOfAll);

        while (separateByLine.hasMoreTokens()) {
            crews.add(new Crew(course, separateByLine.nextToken()));
        }
    }

    public List<Crew> getCrewBy(Course course) {
        return crews.stream()
                .filter(crew -> crew.isSameCourse(course))
                .collect(Collectors.toList());
    }

    public boolean isMatchPossible(Course course, Level level, String missionName) {
        EachLevel eachLevel = levels.get(level);
        return eachLevel.isMatchingPossible(course, missionName);
    }

    public void match(Course course, Level level, String missionName) {
        EachLevel eachLevel = levels.get(level);
        eachLevel.matching(course, missionName, this);
    }

    public List<List<String>> inquiry(Course course, Level level, String missionName) {
        EachLevel eachLevel = levels.get(level);
        return eachLevel.getPair(course, missionName);
    }
}
