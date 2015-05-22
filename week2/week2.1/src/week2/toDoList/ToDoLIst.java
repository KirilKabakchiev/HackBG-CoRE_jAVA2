package week2.toDoList;

import java.util.PriorityQueue;

public class ToDoLIst {

    private float hoursAvailable;
    private PriorityQueue<Task> tasks;

    public ToDoLIst(float hoursAvailable) {
        setHoursAvailable(hoursAvailable);
        tasks = new PriorityQueue<Task>();
    }

    public float getHoursAvailable() {
        return hoursAvailable;
    }

    public void setHoursAvailable(float hoursAvailable) {
        this.hoursAvailable = hoursAvailable;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void markFinished(Task task) {
        task.setFinished(true);
    }

    public void markCancelled(Task task) {
        task.setActive(false);
        tasks.remove(task);
    }

    public Task getTop() {
        return tasks.peek();
    }

    public boolean canFinish() {

        return getTotalTimeNeeded() <= hoursAvailable;
    }

    public float getRemainingTime() {
        return hoursAvailable - getTotalTimeNeeded();
    }

    public float getTotalTimeNeeded() {
        float totalTimeNeeded = 0;
        for (Task task : tasks) {
            totalTimeNeeded += task.getTimeRequired();
        }
        return totalTimeNeeded;
    }
}