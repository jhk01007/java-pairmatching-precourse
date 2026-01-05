package pairmatching.util;

import pairmatching.dto.MissionInfoDto;

import static pairmatching.util.ErrorMessage.MISSION_INFO_FORMAT_ERROR;

public class InputParser {

    public static MissionInfoDto parseMissionInfo(String missionInfo) {
        String[] split = missionInfo.split(",", -1);
        if(split.length != 3) {
            throw new IllegalArgumentException(MISSION_INFO_FORMAT_ERROR.getMessage());
        }
        String course = split[0].trim();
        String level = split[1].trim();
        String mission = split[2].trim();
        return new MissionInfoDto(course, level, mission);
    }
}
