package week2.studentcomparator;

public class Student {
    private String name;
    private int grade;

    public Student(String name, int grade) {
        setName(name);
        setGrade(grade);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("name is null");
        }
        this.name = name;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        if (grade < 2 || grade > 6) {
            throw new IllegalArgumentException("Grade has to be between 2 and 6");
        }
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Student: " + name + " " + grade;
    }
}