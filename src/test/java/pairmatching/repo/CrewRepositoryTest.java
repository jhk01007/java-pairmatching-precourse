package pairmatching.repo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import pairmatching.domain.Course;
import pairmatching.domain.Crew;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;


class CrewRepositoryTest {

    private final CrewRepository crewRepository = new CrewRepository();

    @ParameterizedTest
    @CsvSource(value = {
            "BACKEND,20",
            "FRONTEND,15"
    })
    @DisplayName("특정 Course의 Crew들을 가져온다.")
    public void findByCourse(Course course, int num) throws Exception {
        // when
        List<Crew> crews = crewRepository.findByCourse(course);

        // then
        System.out.printf("---%s 코스 크루원 목록---\n", course);
        System.out.println(crews.stream().map(Crew::getName).collect(Collectors.toList()));
        assertThat(crews).hasSize(num);
    }
}