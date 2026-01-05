package pairmatching.repo;

import pairmatching.domain.Course;
import pairmatching.domain.Level;
import pairmatching.domain.Mission;
import pairmatching.domain.PairMatching;

import java.util.ArrayList;
import java.util.List;

public class PairMatchingRepository {

    private static final List<PairMatching> PAIR_MATCHINGS = new ArrayList<>();

    public boolean isExistByCourseAndLevelAndMission(Course course, Level level, Mission mission) {
        for (PairMatching pairMatching : PAIR_MATCHINGS) {
            if (pairMatching.getCourse().equals(course) &&
                    pairMatching.getLevel().equals(level) &&
                    pairMatching.getMission().equals(mission)) {
                return true;
            }
        }
        return false;
    }

    public void save(PairMatching pairMatching) {
        PAIR_MATCHINGS.add(pairMatching);
    }

    public void deleteByCourseAndLevelAndMission(Course course, Level level, Mission mission) {
        PAIR_MATCHINGS.removeIf(pairMatching -> pairMatching.getCourse().equals(course) &&
                pairMatching.getLevel().equals(level) &&
                pairMatching.getMission().equals(mission));
    }
}
