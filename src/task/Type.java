package task;

public enum Type {

    WORK("рабочие задачи"),
    PERSONAL("личные задачи"),
    ONE("однократная"),
    DAILY("ежедневная"),
    WEEKLY("еженедельная"),
    MONTHLY("ежемесячная"),
    YEARLY("ежегодная");
    private String type;

    Type(String type) {
        this.type = type;
    }

    public void  values(Task task) {
        for (Type t : Type.values()) {
            System.out.println(t);
            Type.valueOf("рабочие задачи");
        }}


}
