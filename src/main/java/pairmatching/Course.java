package pairmatching;

import java.util.Arrays;

public enum Course {
    BACKEND("백엔드"),
    FRONTEND("프론트엔드");

    private final String courseName;

    Course(String courseName) {
        this.courseName = courseName;
    }

    public static Course get(String courseName) {
        return Arrays.stream(values())
                .filter(course -> course.getCourseName().equals(courseName))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    private String getCourseName() {
        return this.courseName;
    }

    public boolean equals(Course course) {
        return this.courseName.equals(course.getCourseName());
    }

    @Override
    public String toString() {
        return this.courseName;
    }
}
