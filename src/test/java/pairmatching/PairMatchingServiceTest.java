package pairmatching;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pairmatching.domain.Course;
import pairmatching.domain.Level;
import pairmatching.domain.Mission;
import pairmatching.dto.PairMatchingDto;
import pairmatching.repo.CrewRepository;
import pairmatching.repo.PairMatchingRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static pairmatching.util.ErrorMessage.NO_MATCHING_NUMBER_OF_CASE_ERROR;

class PairMatchingServiceTest {

    private final PairMatchingService pairMatchingService = new PairMatchingService(
            new PairMatchingRepository(), new CrewRepository()
    );

    @ParameterizedTest
    @CsvSource(value = {
           "FRONTEND,LEVEL1,LOTTO,7",
           "BACKEND,LEVEL1,LOTTO,10"
    })
    @DisplayName("새로운 페어 매칭을 생성한다.")
    public void createPairMatching_success(Course course, Level level, Mission mission, int size) throws Exception {
        // when
        PairMatchingDto pairMatchingDto =
                pairMatchingService.createPairMatching(course, level, mission, false);

        // then
        assertThat(pairMatchingDto.getPairMatchingResults()).hasSize(size);
    }

//    @Test
//    @DisplayName("새로운 페어 매칭을 생성할 때 더 이상 생성할 수 있는 경우의 수가 없으면 에러 발생")
//    public void createPairMatching_fail() throws Exception {
//
//        // given
//        Course course = Course.BACKEND;
//        Level level = Level.LEVEL1;
//        Mission mission = Mission.LOTTO;
//
//        // when // then
//        assertThatThrownBy(() -> {
//            for (int i = 0; i < 10; i++) {
//                pairMatchingService.createPairMatching(course, level, mission, false);
//            }
//        })
//                .isInstanceOf(IllegalArgumentException.class)
//                .hasMessage(NO_MATCHING_NUMBER_OF_CASE_ERROR.getMessage());
//    }
}