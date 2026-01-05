package pairmatching.domain;

import static pairmatching.util.ErrorMessage.MISSION_FORMAT_ERROR;

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

    public String getName() {
        return name;
    }

    public Level getLevel() {
        return level;
    }

    Mission(String name, Level level) {
        this.name = name;
        this.level = level;
    }

    public static Mission of(String name, Level level) {
        // 레벨 1
        Mission level1 = checkLevel1(name, level);
        if (level1 != null) return level1;
        // 레벨 2
        Mission level2 = checkLevel2(name, level);
        if (level2 != null) return level2;
        // 레벨 3
        Mission level3 = checkLevel3(name, level);
        if (level3 != null) return level3;

        throw new IllegalArgumentException(MISSION_FORMAT_ERROR.getMessage());
    }

    private static Mission checkLevel1(String name, Level level) {
        if(CAR_RACING.getName().equals(name) && CAR_RACING.getLevel().equals(level))
            return CAR_RACING;
        if(LOTTO.getName().equals(name) && LOTTO.getLevel().equals(level))
            return LOTTO;
        if(NUMBER_BASEBALL.getName().equals(name) && NUMBER_BASEBALL.getLevel().equals(level))
            return NUMBER_BASEBALL;
        return null;
    }

    private static Mission checkLevel2(String name, Level level) {
        if(SHOPPING_BASKET.getName().equals(name) && SHOPPING_BASKET.getLevel().equals(level))
            return SHOPPING_BASKET;
        if(PAYMENT.getName().equals(name) && PAYMENT.getLevel().equals(level))
            return PAYMENT;
        if(SUBWAY_MAP.getName().equals(name) && SUBWAY_MAP.getLevel().equals(level))
            return SUBWAY_MAP;
        return null;
    }

    private static Mission checkLevel3(String name, Level level) {
        if(PERFORMANCE_IMPROVEMENT.getName().equals(name) && PERFORMANCE_IMPROVEMENT.getLevel().equals(level))
            return PERFORMANCE_IMPROVEMENT;
        if(DEPLOYMENT.getName().equals(name) && DEPLOYMENT.getLevel().equals(level))
            return DEPLOYMENT;
        return null;
    }
}
