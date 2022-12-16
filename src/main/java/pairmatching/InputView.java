package pairmatching;

import camp.nextstep.edu.missionutils.Console;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputView {

    public String menu() {
        String select = Console.readLine();

        if (!(select.equals("1") || select.equals("2") || select.equals("3") || select.equals("Q"))) {
            System.out.print(Const.ERROR);
            throw new IllegalArgumentException();
        }

        return select;
    }

    public String rematch() {
        String opinion = Console.readLine();

        if (!(opinion.equals("네") || opinion.equals("아니오"))) {
            System.out.print(Const.ERROR);
            throw new IllegalArgumentException();
        }

        return opinion;
    }

    public List<String> select() {
        List<String> userWant = Arrays.stream(Console.readLine().split(","))
                .map(String::trim)
                .collect(Collectors.toList());

        try {
            Course.get(userWant.get(0));
            Level.get(userWant.get(1), userWant.get(2));
        } catch (IllegalArgumentException exception) {
            System.out.print(Const.ERROR);
            select();
//            throw new IllegalArgumentException();
        }

        return userWant;
    }
}
