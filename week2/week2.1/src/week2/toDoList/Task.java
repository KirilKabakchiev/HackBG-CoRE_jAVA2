package week2.toDoList;

public class Task implements Comparable<Task> {
    private static final int DEFAULT_PRIORITY = 1;
    private static final float DEFAULT_TIME_REQUIRED = 1;
    private static final boolean DEFAUL_ACTIVE_STATUS = true;
    private static final boolean DEFAUL_FINISH_STATUS = false;

    private boolean isFinished;
    private boolean isActive;
    private int priority;
    private float timeRequired;

    public Task(int priority, float timeRequired) {
        setPriority(priority);
        setTimeRequired(timeRequired);
        setActive(DEFAUL_ACTIVE_STATUS);
        setFinished(DEFAUL_FINISH_STATUS);
    }

    public Task(int priority) {
        this(priority, DEFAULT_TIME_REQUIRED);
    }

    public Task(float timeRequired) {
        this(DEFAULT_PRIORITY, timeRequired);
    }

    public Task() {
        this(DEFAULT_PRIORITY, DEFAULT_TIME_REQUIRED);
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean isFinished) {
        this.isFinished = isFinished;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        if (priority < 1 || priority > 10) {
            throw new IllegalArgumentException("Priority has to be between 1 and 10");
        }

        this.priority = priority;
    }

    public float getTimeRequired() {
        return timeRequired;
    }

    public void setTimeRequired(float timeRequired) {
        if (timeRequired < 0) {
            throw new IllegalArgumentException("Time required has to be more than 0");
        }
        this.timeRequired = timeRequired;
    }

    @Override
    public int compareTo(Task task) {
        return -(getPriority() - task.getPriority());
    }
}
