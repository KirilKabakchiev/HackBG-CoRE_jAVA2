package week2.studentcomparator;

import java.util.ArrayList;
import java.util.List;

public class CompareStudents {

    public static void main(String[] args) {
        Student stud1 = new Student("Ivan ivanov", 5);
        Student stud2 = new Student("Yordan Yordanov", 4);
        Student stud3 = new Student("byy byy", 6);
        List<Student> list = new ArrayList<>();
        list.add(stud1);
        list.add(stud2);
        list.add(stud3);
        list.sort((o1, o2) -> {
            if (o1.getGrade() == o2.getGrade()) {
                return o1.getName().compareTo(o2.getName());
            } else {
                return o1.getGrade() - o2.getGrade();
            }
        });
        System.out.println(list);

    }
}
