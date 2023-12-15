package pairmatching.controller;

import java.util.HashMap;
import java.util.Map;
import pairmatching.service.Project;
import pairmatching.service.pair.PairService;
import pairmatching.view.Function;
import pairmatching.view.InputView;
import pairmatching.view.MatchingStatus;
import pairmatching.view.OutputView;
import pairmatching.view.Source;

public class PairMatchingController {

    private final InputView inputView;
    private final OutputView outputView;
    private final PairService pairService;
    private final Map<Function, Runnable> mapper;

    public PairMatchingController() {
        this.inputView = InputView.from(Source.from(System.in));
        this.outputView = new OutputView();
        this.pairService = new PairService();
        this.mapper = new HashMap<>();
        initializeMapper();
    }

    public void initializeMapper() {
        mapper.put(Function.MATCHING, this::match);
        mapper.put(Function.INQUIRY, this::inquiry);
        mapper.put(Function.CLEAR, this::clear);
    }

    public void start() {
        while (true) {
            Function function = inputView.chooseFunction();

            if (function == Function.QUIT) {
                return;
            }

            mapper.get(function)
                    .run();
        }
    }

    public void match() {
        outputView.printProject();
        Project project = inputView.chooseProject();

        if (doNotMatchWhenRematching(project)) {
            return;
        }

        outputView.printMatchingResult(pairService.matching(project));
    }

    private boolean doNotMatchWhenRematching(Project project) {
        return pairService.isExistsMatchingResultOfProject(project)
                && inputView.chooseMatchingStatus() == MatchingStatus.NO_MATCHING;
    }

    public void inquiry() {
        outputView.printProject();
        Project project = inputView.chooseProject();
        outputView.printMatchingResult(pairService.inquiryPairs(project));
    }

    public void clear() {
        pairService.clear();
    }

}
