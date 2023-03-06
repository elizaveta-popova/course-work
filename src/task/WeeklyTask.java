package task;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class WeeklyTask extends Task {
    public WeeklyTask(String title, Type type, LocalDateTime dateTime, String description) {
        super(title, type, dateTime, description);
    }

    public boolean appearsIn(LocalDate localDate) {
        LocalDate dateTime = this.getDateTime().toLocalDate();
        return localDate.equals(dateTime) ||
                (localDate.isAfter(getDateTime().toLocalDate()) & localDate.getDayOfWeek().equals(dateTime.getDayOfWeek()));
    }

}
