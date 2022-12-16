package pairmatching;

import java.util.List;

public class MainController {
    private final CrewRepository crewRepository;
    private final OutputView outputView;
    private final InputView inputView;
    private boolean isRunning;
    private final int COURSE_INDEX = 0;
    private final int LEVEL_INDEX = 1;
    private final int MISSION_INDEX = 2;

    public MainController() {
        crewRepository = new CrewRepository(); // 여기서 다 넘겨주면서 해야함
        outputView = new OutputView();
        inputView = new InputView();
        isRunning = true;
    }

    public void start() {
        do {
            outputView.printStart();
            String menu = inputView.menu();
            System.out.println();
            select(menu);
        } while (isRunning);
    }

    private void select(String menu) {
        if (menu.equals("1")) {
            match();
        }

        if (menu.equals("2")) {
            inquiry();
        }

        if (menu.equals("3")) {
            clear();
        }

        if (menu.equals("Q")) {
            isRunning = false;
        }
    }

    private void match() {
        outputView.printMenu();

        while (true) {
            outputView.printSelect();
            List<String> select = inputView.select();
            System.out.println();
            boolean isMatchPossible = crewRepository.isMatchPossible(Course.get(select.get(COURSE_INDEX)), Level.getByName(select.get(LEVEL_INDEX)), select.get(MISSION_INDEX));

            if (isMatchPossible){ // 바로 매치
                crewRepository.match(Course.get(select.get(COURSE_INDEX)), Level.getByName(select.get(LEVEL_INDEX)), select.get(MISSION_INDEX));
                outputView.printResult();
                outputView.printPair(crewRepository.inquiry(Course.get(select.get(COURSE_INDEX)), Level.getByName(select.get(LEVEL_INDEX)), select.get(MISSION_INDEX)));
                System.out.println();
                break;
            }

            outputView.printRematching();
            String opinion = inputView.rematch();
            System.out.println();

            if (opinion.equals("네")) {
                crewRepository.match(Course.get(select.get(COURSE_INDEX)), Level.getByName(select.get(LEVEL_INDEX)), select.get(MISSION_INDEX));
                outputView.printResult();
                outputView.printPair(crewRepository.inquiry(Course.get(select.get(COURSE_INDEX)), Level.getByName(select.get(LEVEL_INDEX)), select.get(MISSION_INDEX)));
                System.out.println();
                break;
            }
        }
    }

    private void inquiry() {
        outputView.printMenu();
        outputView.printSelect();
        List<String> select = inputView.select();
        System.out.println();
        outputView.printResult();
        outputView.printPair(crewRepository.inquiry(Course.get(select.get(COURSE_INDEX)), Level.getByName(select.get(LEVEL_INDEX)), select.get(MISSION_INDEX)));
        System.out.println();
    }

    private void clear() {
        outputView.printClear();
        crewRepository.clear();
        System.out.println();
    }
}
