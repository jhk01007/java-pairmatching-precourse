package pairmatching.repo;

import pairmatching.domain.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class PairMatchingRepository {

    private static final List<PairMatching> PAIR_MATCHINGS = new ArrayList<>();

    public boolean existByCourseAndLevelAndMission(Course course, Level level, Mission mission) {
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

    /**
     * 같은 레벨 내에서 이미 짝이 된적이 있는지 여부
     */
    public boolean existByCrewsAndLevel(List<Crew> newCrews, Level level) {
        for (PairMatching pairMatching : PAIR_MATCHINGS) {
            List<Pair> pairs = pairMatching.getPairs();
            for (Pair pair : pairs) {
                List<Crew> crews = pair.getCrews();
                if (new HashSet<>(crews).containsAll(newCrews) &&
                        pairMatching.getLevel().equals(level)) { // 같은 레벨 내에서 이미 짝이 된적이 있는 경우
                    return true;
                }
            }
        }
        return false;
    }
}
