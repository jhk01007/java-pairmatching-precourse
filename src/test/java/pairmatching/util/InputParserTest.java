package pairmatching.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pairmatching.dto.MissionInfoDto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InputParserTest {

    @Test
    @DisplayName("문자열 형태의 미션 정보를 MissionInfoDto형태로 변환")
    public void parseMissionInfo_success() throws Exception {
        // given
        String missionInfo = "프론트엔드, 레벨1, 자동차경주";

        // when
        MissionInfoDto missionInfoDto = InputParser.parseMissionInfo(missionInfo);

        // then
        assertThat(missionInfoDto.getCourse()).isEqualTo("프론트엔드");
        assertThat(missionInfoDto.getLevel()).isEqualTo("레벨1");
        assertThat(missionInfoDto.getMission()).isEqualTo("자동차경주");
    }

    @Test
    @DisplayName("문자열 형태의 미션 정보를 MissionInfoDto형태로 변환시 형태가 올바르지 않으면 예외 발생")
    public void parseMissionInfo_fail() throws Exception {
        // given
        String missionInfo = "레벨1, 자동차경주";

        // when // then
        assertThatThrownBy(() -> InputParser.parseMissionInfo(missionInfo))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.MISSION_INFO_FORMAT_ERROR.getMessage());
    }

}