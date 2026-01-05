package pairmatching.repo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pairmatching.domain.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class PairMatchingRepositoryTest {

    private final PairMatchingRepository pairMatchingRepository = new PairMatchingRepository();
    @ParameterizedTest
    @CsvSource(value = {
            "BACKEND,LEVEL1,LOTTO,true",
            "FRONTEND,LEVEL1,LOTTO,false",
    })
    @DisplayName("해당 Course, Level, Mission 조합의 페어 매칭이 존재하는지 여부를 반환한다.")
    public void existByCourseAndLevelAndMission(Course course, Level level, Mission mission, boolean result) throws Exception {
        // given
        initPairMatch(Course.BACKEND, Level.LEVEL1, Mission.LOTTO);

        // when
        boolean isExist = pairMatchingRepository.existByCourseAndLevelAndMission(course, level, mission);

        // then
        assertThat(isExist).isEqualTo(result);
    }

    @Test
    @DisplayName("특정 Course, Level, Mission 조합의 페어 매칭을 삭제한다.")
    public void deleteByCourseAndLevelAndMission() throws Exception {
        // given
        Course course = Course.BACKEND;
        Level level = Level.LEVEL1;
        Mission mission = Mission.LOTTO;
        initPairMatch(course, level, mission);
        boolean beforeDelete = pairMatchingRepository.existByCourseAndLevelAndMission(course, level, mission);

        // when
        pairMatchingRepository.deleteByCourseAndLevelAndMission(course, level, mission);

        // then
        boolean afterDelete = pairMatchingRepository.existByCourseAndLevelAndMission(course, level, mission);
        assertThat(beforeDelete).isTrue();
        assertThat(afterDelete).isFalse();
    }

    @ParameterizedTest
    @CsvSource(value = {
                    "LEVEL1,true",
                    "LEVEL2,false"
            })
    @DisplayName("같은 레벨 내에서 짝이 된적이 있는지 여부를 반환한다.")
    public void existByCrewsAndLevel(Level level, boolean result) throws Exception {
        // given
        String crew1Name = "crew1";
        String crew2Name = "crew2";
        initPairMatch(Course.BACKEND, Level.LEVEL1, Mission.LOTTO, crew1Name, crew2Name);

        Crew crew1 = new Crew(Course.BACKEND, crew1Name);
        Crew crew2 = new Crew(Course.BACKEND, crew2Name);
        List<Crew> crews = Arrays.asList(crew1, crew2);

        // when
        boolean isExist = pairMatchingRepository.existByCrewsAndLevel(crews, level);

        // then
        assertThat(isExist).isEqualTo(result);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "BACKEND,LEVEL1,LOTTO,true",
            "FRONTEND,LEVEL1,LOTTO,false",
            "BACKEND,LEVEL2,LOTTO,false",
            "BACKEND,LEVEL2,PERFORMANCE_IMPROVEMENT,false",
    })
    @DisplayName("특정 Course, Level, Mission 조합의 페어 매칭을 조회한다.")
    public void findByCourseAndLevelAndMission(Course course, Level level, Mission mission, boolean result) throws Exception {
        // given
        initPairMatch(Course.BACKEND, Level.LEVEL1, Mission.LOTTO);

        // when
        Optional<PairMatching> find = pairMatchingRepository.findByCourseAndLevelAndMission(course, level, mission);

        // then
        assertThat(find.isPresent()).isEqualTo(result);
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

    private void initPairMatch(Course course, Level level, Mission mission, String crew1Name, String crew2Name) {
        Crew crew1 = new Crew(course, crew1Name);
        Crew crew2 = new Crew(course, crew2Name);
        List<Crew> crews = Arrays.asList(crew1, crew2);
        Pair pair = new Pair(crews);

        PairMatching pairMatching =
                new PairMatching(Collections.singletonList(pair), course, level, mission);
        pairMatchingRepository.save(pairMatching);
    }

}