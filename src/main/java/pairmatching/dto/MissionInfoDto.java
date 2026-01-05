package pairmatching.dto;

public class MissionInfoDto {
    private String course;
    private String level;
    private String mission;

    public MissionInfoDto(String course, String level, String mission) {
        this.course = course;
        this.level = level;
        this.mission = mission;
    }

    public String getCourse() {
        return course;
    }

    public String getLevel() {
        return level;
    }

    public String getMission() {
        return mission;
    }
}
