package task;

public class IncorrectArgumentException extends TaskNotFoundException {
    private String argument;

    public IncorrectArgumentException(String message) {
        super(message);
    }

    public String getArgument() {
        return argument;
    }

    @Override
    public String toString() {
        return "IncorrectArgumentException{" +
                "argument='" + argument + '\'' +
                '}';
    }
}
