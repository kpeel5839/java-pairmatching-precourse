package pairmatching.service;

public enum Mission {

    RACING_CAR("자동차경주"),
    LOTTO("로또"),
    BASE_BALL("숫자야구게임"),
    SHOPPING("장바구니"),
    PAYMENT("결제"),
    SUBWAY_PATH("지하철노선도"),
    PERFORMANCE_IMPROVMENT("성능개선"),
    DEPLOY("배포"),
    ;

    private final String name;

    Mission(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
