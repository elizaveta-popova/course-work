package task;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class YearlyTask extends Task {

    public YearlyTask(String title, Type type, LocalDateTime dateTime, String description) {
        super(title, type, dateTime, description);
    }

    public boolean appearsIn(LocalDate localDate) {
        return localDate.isAfter(getDateTime().toLocalDate())
                || localDate.isEqual(getDateTime().toLocalDate())
                && localDate.getDayOfYear() == getDateTime().getDayOfYear();
    }

}
