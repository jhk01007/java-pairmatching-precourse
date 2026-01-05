package pairmatching.util;

public enum ErrorMessage {
    MISSION_INFO_FORMAT_ERROR("미션 정보 입력 형식이 올바르지 않습니다. 다시 입력해주세요."),
    COURSE_FORMAT_ERROR("존재하지 않는 Course 타입 입니다."),
    LEVEL_FORMAT_ERROR("존재하지 않는 Level 타입 입니다."),
    MISSION_FORMAT_ERROR("존재하지 않는 Mission 타입 입니다."),

    ;


    private static final String ERROR_PREFIX = "[ERROR]";


    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return ERROR_PREFIX + " " + message;
    }
}

