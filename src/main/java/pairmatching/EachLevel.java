package pairmatching;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class EachLevel {
    private Level level; // 여기서 레벨을 얻으면, mission 도 안에 들어있긴함
    private Map<Course, Map<String, HashSet<String>>> matchingRecord; // 매칭 기록
    private Map<Course, Map<String, Mission>> missions; // mission 안에 실제 pair 정보가 들어가있을 것임

    public EachLevel(Level level, CrewRepository crewRepository) {
        this.level = level;
        initMatchingRecord(crewRepository);
        initMissions();
    }

    private void initMatchingRecord(CrewRepository crewRepository) {
        matchingRecord = new HashMap<>();
        matchingRecord.put(Course.BACKEND, new HashMap<>());
        matchingRecord.put(Course.FRONTEND, new HashMap<>());
        crewRepository.getBackendCrew()
                .forEach(crew -> matchingRecord.get(Course.BACKEND).put(crew.getName(), new HashSet<>()));
        crewRepository.getBackendCrew()
                .forEach(crew -> matchingRecord.get(Course.BACKEND).put(crew.getName(), new HashSet<>()));
    }

    private void initMissions() {
        missions = new HashMap<>();
        List<String> mission = level.getMissions();
        missions.put(Course.BACKEND, new HashMap<>());
        missions.put(Course.FRONTEND, new HashMap<>());
        mission.forEach(name -> missions.get(Course.BACKEND).put(name, new Mission()));
        mission.forEach(name -> missions.get(Course.FRONTEND).put(name, new Mission()));
    }

    @Override
    public String toString() {
        return level.toString();
    }

    public boolean isMatchingPossible(Course course, String missionName, CrewRepository crewRepository) { // match 가능한지
        // Course, mission 들어올겨 그러면 그거 가지고 mission 에 pair 사이즈 있나 보면 됨
        boolean isMatchingPossible = !missions.get(course).get(missionName).isPairExist();

        if (isMatchingPossible) { // Pair 있으면 바로
            matching(course, missionName, crewRepository);
        }

        return isMatchingPossible;
    }

    public void matching(Course course, String missionName, CrewRepository crewRepository) { // matching 실제로 들어가는겨, rematching 하면 바로 이걸로 들어가면 됨
        missions.get(course).get(missionName).match(course, matchingRecord, crewRepository); // (매칭 보드, CrewRepository)
        matchingRecordUpdate(course);
    }

    public List<List<String>> getPair(Course course, String missionName) {
        List<List<String>> pair = missions.get(course).get(missionName).getPair();

        if (pair.size() == 0) {
            System.out.print(Const.ERROR);
            throw new IllegalArgumentException(" 조회 결과가 없습니다.");
        }

        return pair;
    }

    private void matchingRecordUpdate(Course course) { // matchingRecordUpdate 하면 해줌
        Map<String, HashSet<String>> matchingRecordByCourse = matchingRecord.get(course);

        for (String name : matchingRecordByCourse.keySet()) {
            matchingRecordByCourse.put(name, new HashSet<>()); // 초기화
        }

        Map<String, Mission> missionsByCourse = missions.get(course);

        for (String name : missionsByCourse.keySet()) {
            addRecord(matchingRecordByCourse, missionsByCourse.get(name).getPair());
        }
    }

    private void addRecord(Map<String, HashSet<String>> matchingRecordByCourse, List<List<String>> pair) {
        for (List<String> partial : pair) {
            parsingPartial(matchingRecordByCourse, partial);
        }
    }

    private void parsingPartial(Map<String, HashSet<String>> matchingRecordByCourse, List<String> partial) {
        for (int me = 0; me < partial.size(); me++) {
            for (int you = 0; you < partial.size(); you++) {
                addPartial(me, you, matchingRecordByCourse ,partial);
            }
        }
    }

    private void addPartial(int me, int you, Map<String, HashSet<String>> matchingRecordByCourse, List<String> partial) {
        if (me != you) {
            matchingRecordByCourse.get(partial.get(me)).add(partial.get(you));
        }
    }
}

