package pairmatching.repo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pairmatching.domain.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PairMatchingRepositoryTest {

    private final PairMatchingRepository pairMatchingRepository = new PairMatchingRepository();
    @ParameterizedTest
    @CsvSource(value = {
            "BACKEND,LEVEL1,LOTTO,true",
            "FRONTEND,LEVEL1,LOTTO,false",
    })
    @DisplayName("해당 Course, Level, Mission 조합의 페어 매칭이 존재하는지 여부를 반환한다.")
    public void isExistByCourseAndLevelAndMission(Course course, Level level, Mission mission, boolean result) throws Exception {
        // given
        initPairMatch(Course.BACKEND, Level.LEVEL1, Mission.LOTTO);

        // when
        boolean isExist = pairMatchingRepository.isExistByCourseAndLevelAndMission(course, level, mission);

        // then
        assertThat(isExist).isEqualTo(result);
    }

    private void initPairMatch(Course course, Level level, Mission mission) {
        Crew crew1 = new Crew(course, "crew1");
        Crew crew2 = new Crew(course, "crew2");
        List<Crew> crews = Arrays.asList(crew1, crew2);
        Pair pair = new Pair(crews);
        ;
        PairMatching pairMatching =
                new PairMatching(Collections.singletonList(pair), course, level, mission);
        pairMatchingRepository.save(pairMatching);
    }

}