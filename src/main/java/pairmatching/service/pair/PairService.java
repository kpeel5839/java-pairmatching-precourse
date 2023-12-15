package pairmatching.service.pair;

import static pairmatching.service.Course.BACKEND;
import static pairmatching.service.Course.FRONTEND;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import pairmatching.repository.CrewRepository;
import pairmatching.repository.PairRepository;
import pairmatching.repository.impl.BackendCrewRepositoryImpl;
import pairmatching.repository.impl.FrontendCrewRepositoryImpl;
import pairmatching.repository.impl.PairRepositoryImpl;
import pairmatching.service.Course;
import pairmatching.service.Mission;
import pairmatching.service.Project;

public class PairService {

    private final PairRepository pairRepository;
    private final Map<Course, CrewRepository> crewRepository;

    public PairService() {
        this.pairRepository = new PairRepositoryImpl();
        this.crewRepository = new HashMap<>();
        initializeCrewRepository();
    }

    private void initializeCrewRepository() {
        crewRepository.put(BACKEND, new BackendCrewRepositoryImpl());
        crewRepository.put(FRONTEND, new FrontendCrewRepositoryImpl());
    }

    public boolean isExistsMatchingResultOfProject(Project project) {
        return pairRepository.isExistsPairOfProject(project);
    }

    public Pairs matching(Project project) {
        pairRepository.removePairs(project);
        List<String> crewNames = findCrewNamesOfCourse(project.getCourse());
        Pairs pairs = createPairs(project, crewNames);
        pairRepository.save(project, pairs);
        return pairs;
    }

    private Pairs createPairs(final Project project, final List<String> crewNames) {
        int count = 0;
        Pairs pairs;

        do {
            pairs = Pairs.from(Randoms.shuffle(crewNames));
        } while (isImpossiblePairs(project, pairs) && ++count < 3);

        validateMatchingCount(count);
        return pairs;
    }

    private void validateMatchingCount(int count) {
        if (3 <= count) {
            throw new IllegalArgumentException("페어 매칭에 실패하였습니다.");
        }
    }

    private List<String> findCrewNamesOfCourse(Course course) {
        return crewRepository.get(course)
                .findAllCrewNames();
    }

    private boolean isImpossiblePairs(Project project, Pairs pairs) {
        List<Mission> missions = Mission.fromLevel(project.getLevel().getName());
        List<Project> projects = missions.stream()
                .map(mission -> Project.of(
                        project.getCourse().getName(),
                        project.getLevel().getName(),
                        mission.getName())
                )
                .collect(Collectors.toList());

        return pairs.getPairs()
                .stream()
                .anyMatch(pair -> isAlreadyExistsPair(pair, projects));
    }

    private boolean isAlreadyExistsPair(Pair pair, List<Project> projects) {
        return projects.stream()
                .filter(pairRepository::isExistsPairOfProject)
                .map(pairRepository::inquiry)
                .anyMatch(pairs -> pairs.isContains(pair));
    }

    public Pairs inquiryPairs(Project project) {
        validateExistsPairs(project);
        return pairRepository.inquiry(project);
    }

    private void validateExistsPairs(final Project project) {
        if (pairRepository.isExistsPairOfProject(project)) {
            return;
        }

        throw new IllegalArgumentException("페어 매칭 결과가 존재하지 않습니다.");
    }

    public void clear() {
        pairRepository.clear();
    }

}
