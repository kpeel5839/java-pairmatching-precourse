package pairmatching;

public class Crew {
    private Course course;
    private String name;

    public Crew(Course course, String name) {
        this.course = course;
        this.name = name;
    }

    public boolean isSameCourse(Course course) {
        return this.course.equals(course);
    }

    @Override
    public String toString() {
        return "Course : " + course + ", name : " + name;
    }

    public Course getCourse() {
        return this.course;
    }

    public String getName() {
        return this.name;
    }

}
