package pairmatching.view;

import java.util.stream.Collectors;
import pairmatching.service.pair.Pair;
import pairmatching.service.pair.Pairs;

public class OutputView {
    public void printProject() {
        System.out.println("#############################################");
        System.out.println("과정: 백엔드 | 프론트엔드");
        System.out.println("미션:");
        System.out.println(" - 레벨1: 자동차경주 | 로또 | 숫자야구게임");
        System.out.println(" - 레벨2: 장바구니 | 결제 | 지하철노선도");
        System.out.println(" - 레벨3: ");
        System.out.println(" - 레벨4: 성능개선 | 배포");
        System.out.println(" - 레벨5:");
        System.out.println("############################################");
    }

    public void printMatchingResult(Pairs pairs) {
        System.out.println("페어 매칭 결과입니다.");

        pairs.getPairs()
                .stream()
                .map(this::pairsToString)
                .forEach(System.out::println);
    }

    private String pairsToString(Pair pair) {
        return pair.getCrews()
                .stream()
                .collect(Collectors.joining(" : "));
    }

}
