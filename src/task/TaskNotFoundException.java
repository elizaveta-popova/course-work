package task;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(Integer message){
        super(String.valueOf(message));
    }
}

