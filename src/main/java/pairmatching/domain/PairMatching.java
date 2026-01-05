package pairmatching.domain;

import java.util.Collections;
import java.util.List;

public class PairMatching {
    private List<Pair> pairs;
    private Course course;
    private Level level;
    private Mission mission;

    public PairMatching(List<Pair> pairs, Course course, Level level, Mission mission) {
        this.pairs = pairs;
        this.course = course;
        this.level = level;
        this.mission = mission;
    }

    public List<Pair> getPairs() {
        return Collections.unmodifiableList(pairs);
    }

    public Course getCourse() {
        return course;
    }

    public Level getLevel() {
        return level;
    }

    public Mission getMission() {
        return mission;
    }
}
