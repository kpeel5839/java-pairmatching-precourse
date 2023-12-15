package pairmatching.repository.impl;

import java.util.HashMap;
import java.util.Map;
import pairmatching.repository.PairRepository;
import pairmatching.service.Project;
import pairmatching.service.pair.Pairs;

public class PairRepositoryImpl implements PairRepository {

    private final Map<Project, Pairs> matchingResult;

    public PairRepositoryImpl() {
        matchingResult = new HashMap<>();
    }

    @Override
    public void save(Project project, Pairs pairs) {
        matchingResult.put(project, pairs);
    }

    @Override
    public boolean isExistsPairOfProject(Project project) {
        return matchingResult.containsKey(project);
    }

    @Override
    public void removePairs(Project project) {
        if (!matchingResult.containsKey(project)) {
            return;
        }

        matchingResult.remove(project);
    }

    @Override
    public Pairs inquiry(Project project) {
        return matchingResult.get(project);
    }

    @Override
    public void clear() {
        matchingResult.clear();
    }



}
