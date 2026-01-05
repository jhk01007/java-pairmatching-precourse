package pairmatching.domain;

import static pairmatching.util.ErrorMessage.LEVEL_FORMAT_ERROR;

public enum Level {
    LEVEL1("레벨1"),
    LEVEL2("레벨2"),
    LEVEL3("레벨3"),
    LEVEL4("레벨4"),
    LEVEL5("레벨5");

    private String name;

    public String getName() {
        return name;
    }

    Level(String name) {
        this.name = name;
    }

    public static Level of(String level) {
        if(LEVEL1.getName().equals(level))
            return LEVEL1;
        if(LEVEL2.getName().equals(level))
            return LEVEL2;
        if(LEVEL3.getName().equals(level))
            return LEVEL3;
        if(LEVEL4.getName().equals(level))
            return LEVEL4;
        if(LEVEL5.getName().equals(level))
            return LEVEL5;

        throw new IllegalArgumentException(LEVEL_FORMAT_ERROR.getMessage());
    }
}

