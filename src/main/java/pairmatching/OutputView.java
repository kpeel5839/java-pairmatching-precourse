package pairmatching;

import java.util.List;

public class OutputView {
    private final String START_MENTION = "기능을 선택하세요.\n" +
            "1. 페어 매칭\n" +
            "2. 페어 조회\n" +
            "3. 페어 초기화\n" +
            "Q. 종료";
    private final String MENU_MENTION = "#############################################\n" +
            "과정: 백엔드 | 프론트엔드\n" +
            "미션:\n" +
            "- 레벨1: 자동차경주 | 로또 | 숫자야구게임\n" +
            "- 레벨2: 장바구니 | 결제 | 지하철노선도\n" +
            "- 레벨3:\n" +
            "- 레벨4: 성능개선 | 배포\n" +
            "- 레벨5:\n" +
            "############################################";
    private final String SELECT_MENTION = "과정, 레벨, 미션을 선택하세요.\n" +
            "ex) 백엔드, 레벨1, 자동차경주";
    private final String RESULT_MENTION = "페어 매칭 결과입니다.";
    private final String REMATCHING_MENTION = "매칭 정보가 있습니다. 다시 매칭하시겠습니까?\n" +
            "네 | 아니오";
    private final String CLEAR_MENTION = "초기화 되었습니다.";
    private final String SEPARATE_CHARACTER = " : ";

    public void printStart() {
        System.out.println(START_MENTION);
    }

    public void printMenu() {
        System.out.println(MENU_MENTION);
    }

    public void printSelect() {
        System.out.println(SELECT_MENTION);
    }

    public void printResult() {
        System.out.println(RESULT_MENTION);
    }

    public void printRematching() {
        System.out.println(REMATCHING_MENTION);
    }

    public void printClear() {
        System.out.println(CLEAR_MENTION);
    }

    public void printPair(List<List<String>> pair) {
        for (List<String> partial : pair) {
            System.out.println(String.join(" : ", partial));
        }
    }
}
