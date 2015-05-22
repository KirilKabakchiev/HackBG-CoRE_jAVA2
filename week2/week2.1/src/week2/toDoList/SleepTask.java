package week2.toDoList;


public class SleepTask extends Task{
    public static final float DEFAULT_TIME_REQUIRED = 8f;
    
    public SleepTask(int priority, float timeRequired) {
        super(priority, timeRequired);        
    }
    
    public SleepTask(int priority) {
        super(priority, DEFAULT_TIME_REQUIRED);        
    }
    
    public SleepTask(float timeRequired) {
        super(timeRequired);        
    }
    
    public SleepTask() {
        super(DEFAULT_TIME_REQUIRED);        
    }
}
