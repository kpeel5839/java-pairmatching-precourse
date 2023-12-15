package pairmatching.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputView {

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



}
