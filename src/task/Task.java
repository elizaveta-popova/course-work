package task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public abstract class Task {
    private static int idGenerator = 0;
    private String title;
    private Type type;
    private int id;
    private LocalDateTime dateTime;
    private String description;


    public Task(String title, Type type, LocalDateTime dateTime, String description) {
        this.title = title;
        this.type = type;
        this.id = idGenerator++;
        this.dateTime = dateTime;
        this.description = description;
    }


    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                ", type=" + type +
                ", id=" + id +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task that = (Task) o;
        return id == that.id && title.equals(that.title) && type.equals(that.type) && dateTime.equals(that.dateTime) && description.equals(that.description);
    }

    public int hashCode() {
        return Objects.hash(title, type, id, dateTime, description);
    }

    public abstract boolean appearsIn(LocalDate dateTime);

    public int getIdGenerator() {
        return idGenerator;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) throws IncorrectArgumentException {
        if (title == null || title.isEmpty()) {
            throw new IncorrectArgumentException("Error");
        } else {
            this.title = title;
        }
    }

    public Type getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) throws IncorrectArgumentException {
        if (description == null || description.isEmpty()) {
            throw new IncorrectArgumentException("Error");
        } else {
            this.description = description;
        }
    }
}