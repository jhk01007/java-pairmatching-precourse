package pairmatching.util;

public enum ErrorMessage {
    MISSION_INFO_FORMAT_ERROR("미션 정보 입력 형식이 올바르지 않습니다. 다시 입력해주세요."),
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

