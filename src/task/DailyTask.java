package task;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DailyTask extends Task {
    public DailyTask(String title, Type type, LocalDateTime dateTime, String description) {
        super(title, type, dateTime, description);
    }

    public boolean appearsIn(LocalDate localDate) {
        return localDate.isAfter(getDateTime().toLocalDate())
                || localDate.isEqual(getDateTime().toLocalDate());
    }

//    public TaskType getTaskType() {
//        return TaskType.DAILY;
//    }
}
