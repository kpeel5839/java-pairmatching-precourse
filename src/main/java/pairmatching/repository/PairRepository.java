package pairmatching.repository;

import pairmatching.service.Project;
import pairmatching.service.pair.Pairs;

public interface PairRepository {

    void save(Project project, Pairs pairs);
    boolean isExistsPairOfProject(Project project);
    void removePairs(Project project);
    Pairs inquiry(Project project);
    void clear();

}
