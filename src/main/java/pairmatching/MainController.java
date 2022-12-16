package pairmatching;

public class MainController {
    private final CrewRepository crewRepository;

    public MainController() {
        crewRepository = new CrewRepository(); // 여기서 다 넘겨주면서 해야함
    }
}
