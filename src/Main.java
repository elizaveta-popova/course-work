import task.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Scanner;
import java.util.regex.Pattern;


public class Main {
    private static final TaskService TASK_SERVICE = new TaskService();
    private static final Pattern DATE_TIME_PATTERN = Pattern.compile("\\d{2}\\.\\d{2}\\.\\d{4} \\d{2}\\:\\d{2}");
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
    private static final Pattern DATE_PATTERN = Pattern.compile("\\d{2}\\.\\d{2}\\.\\d{4}");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {
            label:
            while (true) {
                printMenu();
                System.out.println("Выберите пункт меню: ");
                if (scanner.hasNextInt()) {
                    int menu = scanner.nextInt();
                    switch (menu) {
                        case 1:
                            addTask(scanner);
                            break;
                        case 2:
                            removeTask(scanner);
                        case 3:
                            printTaskByDate(scanner);
                            break;
                        case 0:
                            break label;
                    }
                } else {
                    scanner.next();
                    System.out.println("Выберите пункт меню из списка!");
                }
            }
        }

    }
    private static void removeTask (Scanner scanner){
        System.out.println("Введите ID задачи для удаления");
        int id = scanner.nextInt();
        try {
            TASK_SERVICE.remove(id);
        } catch (TaskNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void printTaskByDate(Scanner scanner) {
        System.out.println("Введите дату в формате dd.MM.yyyy");

        if (scanner.hasNext(DATE_PATTERN)) {
            String dateTime = scanner.next(DATE_PATTERN);

            LocalDate inputDate = LocalDate.parse(dateTime, DATE_FORMATTER);
            Collection<Task> tasks = TASK_SERVICE.getAllByDate(inputDate);
            for (Task task : tasks) {
                System.out.println(task);
            }
        } else {
            System.out.println("Введите дату в формате dd.MM.yyyy");
        }
    }


    private static void printMenu() {
        System.out.println("1. Добавить задачу\n2. Удалить задачу\n3. Получить задачу за определённую дату\n0. Выход");
    }

    private static void addTask (Scanner scanner) {
        scanner.useDelimiter("\n");
        String title = addTaskTitle(scanner);
        String description = addTaskDescription(scanner);
        Type type = addTaskType(scanner);
        LocalDateTime taskTime = addTaskTime(scanner);
        int repetition = addRepetition(scanner);
        createTask(title, description, type, taskTime, repetition);
    }

    private static void createTask(String title, String description, Type type, LocalDateTime taskTime, int repetition) {
        Task task = null;
        try {
            switch (repetition) {
                case 1 -> task = new OneTimeTask(title, type, taskTime, description);
                case 2 -> task = new DailyTask(title, type, taskTime, description);
                case 3 -> task = new WeeklyTask(title, type, taskTime, description);
                case 4 -> task = new MonthlyTask(title, type, taskTime, description);
                case 5 -> task = new YearlyTask(title, type, taskTime, description);
                default -> System.out.println("Повторяемость задачи введена некорректно");
            }
        } catch (IncorrectArgumentException e) {
            System.out.println(e.getMessage());
        }
        if (task != null) {
            TASK_SERVICE.add(task);
            System.out.println("Задача добавлена");
        } else {
            System.out.println("Введены некорректные данные по задаче");
        }
    }

    private static String addTaskTitle(Scanner scanner) {
        System.out.println("Введите название задачи: ");
        String title = scanner.next();
        if (title.isBlank()) {
            System.out.println("Вы не ввели название задачи, попробуйте ещё раз: ");
        }
        return title;
    }

    private static String addTaskDescription(Scanner scanner) {
        System.out.println("Введите описание задачи: ");
        String description = scanner.next();
        if (description.isBlank()) {
            System.out.println("Вы не ввели описание задачи, попробуйте ещё раз:");
        }
        return description;
    }
    private static Type addTaskType(Scanner scanner) {
        System.out.println("Задача личная (1) или рабочая (2)? ");
        Type type = null;
        int typeChoice = scanner.nextInt();
        switch (typeChoice) {
            case 1 -> type = Type.PERSONAL;
            case 2 -> type = Type.WORK;
            default -> {
                System.out.println("Данные введены некорректно, попробуйте ещё раз: ");
            }
        }
        return type;
    }

    private static LocalDateTime addTaskTime(Scanner scanner) {
        System.out.println("Введите дату и время задачи в формате dd.MM.yyyy HH:mm");
        if (scanner.hasNext(DATE_TIME_PATTERN)) {
            String dateTime = scanner.next(DATE_TIME_PATTERN);
            return LocalDateTime.parse(dateTime, DATE_TIME_FORMATTER);
        } else {
            System.out.println("Введите дату и время задачи в формате dd.MM.yyyy HH:mm");
            return null;
        }
    }

    private static int addRepetition (Scanner scanner) {
        System.out.println("Выберите соответствующую цифру, если задача повторяется: 1 - однократно, 2 - ежедневно, 3 - еженедельно, 4 - ежемесячно, 5 - ежегодно");
        if (scanner.hasNextInt()) {
            return scanner.nextInt();
        } else {
            System.out.println("Введите числом повторяемость задачи");
        }
        return -1;
    }
}