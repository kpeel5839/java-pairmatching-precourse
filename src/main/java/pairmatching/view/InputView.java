package pairmatching.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import pairmatching.service.Project;

public class InputView {

    private static final int CHOICE_COURSE = 0;
    private static final int CHOICE_LEVEL = 1;
    private static final int CHOICE_MISSION = 2;

    private final BufferedReader reader;

    private InputView(BufferedReader reader) {
        this.reader = reader;
    }

    public static InputView from(Source source) {
        return new InputView(
                new BufferedReader(new InputStreamReader(source.getInputStream()))
        );
    }

    public Function chooseFunction() {
        System.out.println("기능을 선택하세요.");
        System.out.println("1. 페어 매칭");
        System.out.println("2. 페어 조회");
        System.out.println("3. 페어 초기화");
        System.out.println("Q. 종료");

        try {
            return Function.from(reader.readLine());
        } catch (IOException exception) {
            throw new RuntimeException("기능 선택 입력을 받는 도중 IOException 이 발생하였습니다.");
        }
    }

    public Project chooseProject() {
        System.out.println("과정, 레벨, 미션을 선택하세요");
        System.out.println("ex) 백엔드, 레벨1, 자동차경주");

        try {
            List<String> componentOfProjects = Arrays.stream(reader.readLine().split(","))
                    .map(String::trim)
                    .collect(Collectors.toList());

            return Project.of(
                    componentOfProjects.get(CHOICE_COURSE),
                    componentOfProjects.get(CHOICE_LEVEL),
                    componentOfProjects.get(CHOICE_MISSION)
            );
        } catch (IOException exception) {
            throw new RuntimeException("프로젝트 선택 입력을 받는 도중 IOException 이 발생하였습니다.");
        }
    }

    public MatchingStatus chooseMatchingStatus() {
        System.out.println("매칭 정보가 있습니다. 다시 매칭하시겠습니까?");
        System.out.println("네 | 아니오");

        try {
            return MatchingStatus.from(reader.readLine());
        } catch (IOException exception) {
            throw new RuntimeException("기능 선택 입력을 받는 도중 IOException 이 발생하였습니다.");
        }
    }

}
