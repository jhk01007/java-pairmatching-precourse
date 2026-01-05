package pairmatching.domain;

public enum Mission {
    CAR_RACING("자동차경주", Level.LEVEL1),
    LOTTO("로또", Level.LEVEL1),
    NUMBER_BASEBALL("숫자야구게임", Level.LEVEL1),

    SHOPPING_BASKET("장바구니", Level.LEVEL2),
    PAYMENT("결제", Level.LEVEL2),
    SUBWAY_MAP("지하철노선도", Level.LEVEL2),

    PERFORMANCE_IMPROVEMENT("성능개선", Level.LEVEL3),
    DEPLOYMENT("배포", Level.LEVEL4);


    private final String name;
    private final Level level;

    Mission(String name, Level level) {
        this.name = name;
        this.level = level;
    }
}
