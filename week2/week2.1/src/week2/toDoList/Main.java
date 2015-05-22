package week2.toDoList;

public class Main {

    public static void main(String[] args) {
        ToDoLIst list = new ToDoLIst(10);
        list.addTask(new SleepTask(9)); // prio of sleep task is 10
        list.addTask(new Task());
        list.addTask(new Task(5));

        if (list.canFinish()) {
            System.out.println("List is doable!");
        } else {
            System.out.println("You gota cut sleeping or you will not finish slacker");
        }

        System.out.println(list.getTop());
        System.out.println(list.getTotalTimeNeeded()); 
        list.markCancelled(list.getTop());   
        System.out.println(list.getTop());
        System.out.println(list.getTotalTimeNeeded()); 
    }
}
