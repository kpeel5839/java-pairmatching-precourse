package pairmatching.view;

import java.util.Arrays;

public enum MatchingStatus {

    MATCHING("네"),
    NO_MATCHING("아니오");

    private final String status;

    MatchingStatus(String status) {
        this.status = status;
    }

    public static MatchingStatus from(String input) {
        return Arrays.stream(values())
                .filter(value -> value.status.equalsIgnoreCase(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 MatchingStatus 가 존재하지 않습니다."));
    }

    public String getStatus() {
        return status;
    }

}
