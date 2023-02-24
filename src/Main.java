import task.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;


public class Main {
    private static final TaskService TASK_SERVICE = new TaskService();
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("d.MM.yyyy");
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("hh:hh");
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TASK_SERVICE.addTask(new OneTimeTask("Eat", Type.PERSONAL, 1, LocalDateTime.now().plusHours(2), "Important"));
        TASK_SERVICE.addTask(new DailyTask("Sleep", Type.PERSONAL, 2, LocalDateTime.now().plusHours(9), "Very important"));
        TASK_SERVICE.addTask(new WeeklyTask("Shopping", Type.WORK, 3, LocalDateTime.now().plusHours(4), "Waste your money"));
        TASK_SERVICE.addTask(new MonthlyTask("Travelling", Type.WORK, 4, LocalDateTime.now().plusHours(9), "Important"));
        TASK_SERVICE.addTask(new YearlyTask("MedicalCheckUp", Type.PERSONAL, 5, LocalDateTime.now().plusHours(5), "Important"));
        addTask(scanner);
        printTaskForDate(scanner);

    }
    private static void printTaskForDate(Scanner scanner) {
    }

    private static void addTask (Scanner scanner) {
        String title = readString ("введите название задачи:", scanner);
        String description = readString ("введите описание задачи:", scanner);
        LocalDateTime dateTime = readDateTime (scanner);
        Type type = readType (scanner);
        int id = readId (scanner);
        Task task = switch (type) {
            case WORK -> null;
            case PERSONAL -> null;
            case ONE -> new OneTimeTask(title, type, id, dateTime, description);
            case DAILY -> new OneTimeTask(title, type, id, dateTime, description);
            case WEEKLY -> new OneTimeTask(title, type, id, dateTime, description);
            case MONTHLY -> new OneTimeTask(title, type, id, dateTime, description);
            case YEARLY -> new OneTimeTask(title, type, id, dateTime, description);
        };
        TASK_SERVICE.addTask(task);
    }


    private static int readId(Scanner scanner) {
        return 0;
    }


    private static Type readType (Scanner scanner) {
        while (true) {
            try {
                System.out.println("выберите тип задачи: ");
                for (Type taskType : Type.values()) {
                    System.out.println(taskType.ordinal() + ". " + localizeType(taskType));
                }
                System.out.println("введите тип: ");
                String ordinalLine = scanner.nextLine();
                int ordinal = Integer.parseInt(ordinalLine);
                return Type.values()[ordinal];
            } catch (NumberFormatException e) {
                System.out.println("введён неверный тип");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("тип не найден");
            }
        }
    }

    private static int localizeType(Type type) {
        return 0;
    }

    private static LocalDateTime readDateTime (Scanner scanner) {
        LocalDate localDate = readDate(scanner);
        LocalTime localTime = readTime(scanner);
        return localDate.atTime(localTime);
    }
    private static String readString (String message, Scanner scanner) {
        while (true) {
            System.out.println(message);
            String readString = scanner.nextLine();
            if (readString == null || readString.isEmpty() || readString.isBlank()) {
                System.out.println("Значение не введено");
            } else {
                return readString;
            }
        }
    }

    private static void removeTask (Scanner scanner) {
        System.out.println("Введите ID задачи для удаления");
        int id = scanner.nextInt();
        try {
            TASK_SERVICE.removeTask(id);
        } catch (TaskNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    private static String printTaskTitle(Scanner scanner) {
        System.out.println("Введите название задачи: ");
        String title = scanner.next();
        if (title.isBlank()) {
            System.out.println("Необходимо ввести название задачи!");
        }
        return title;
    }
    private static String inputTaskDescription(Scanner scanner) {
        System.out.println("Введите описание задачи: ");
        String description = scanner.next();
        if (description.isBlank()) {
            System.out.println("Необходимо ввести описание задачи!");
        }
        return description;
    }
    public static LocalDate readDate (Scanner scanner) {
        while (true) {
            try {
                System.out.println("введите дату: ");
                String dateLine = scanner.nextLine();
                return LocalDate.parse(dateLine, DATE_FORMAT);
            } catch (DateTimeParseException e) {
                System.out.println("неверный формат даты");
            }
        }
    }
    private static LocalTime readTime(Scanner scanner) {
        while (true) {
            try {
                System.out.println("введите время: ");
                String dateLine = scanner.nextLine();
                return LocalTime.parse(dateLine, TIME_FORMAT);
            } catch (DateTimeParseException e) {
                System.out.println("неверный формат времени");
            }
        }
    }



}