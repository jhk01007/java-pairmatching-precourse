package pairmatching.util;

public enum ErrorMessage {
    MISSION_INFO_FORMAT_ERROR("미션 정보 입력 형식이 올바르지 않습니다. 다시 입력해주세요."),
    COURSE_FORMAT_ERROR("존재하지 않는 Course 타입 입니다."),
    LEVEL_FORMAT_ERROR("존재하지 않는 Level 타입 입니다."),
    MISSION_FORMAT_ERROR("존재하지 않는 Mission 타입 입니다."),
    REMATCH_FLAG_FORMAT_ERROR("네, 아니오로만 입력해주세요."),
    NO_MATCHING_NUMBER_OF_CASE_ERROR("매칭할 수 있는 경우의 수가 없습니다."),
    PAIR_MATCHING_NOT_FOUND_ERROR("매칭 이력이 없습니다."),
    CREW_NOT_FOUND_ERROR("존재하지 않는 Crew입니다."),

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

