package pairmatching.domain;

import java.util.List;

public class PairMatching {
    private List<Pair> crews;
    private Course course;
    private Level level;
    private Mission mission;

    public PairMatching(List<Pair> crews, Course course, Level level, Mission mission) {
        this.crews = crews;
        this.course = course;
        this.level = level;
        this.mission = mission;
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
