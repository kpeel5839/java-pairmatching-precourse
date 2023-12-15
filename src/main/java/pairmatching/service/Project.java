package pairmatching.service;

public class Project {

    private final Course course;
    private final Level level;
    private final Mission mission;

    private Project(
            Course course,
            Level level,
            Mission mission
    ) {
        this.course = course;
        this.level = level;
        this.mission = mission;
    }

    public static Project of(
            String course,
            String level,
            String mission
    ) {
        return new Project(
                Course.from(course),
                Level.from(level),
                Mission.fromMissionName(mission)
        );
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
