package v3;

public enum OtherCommand {
    EXIT("exit"),
    HISTORY("his"),
    BIG_HISTORY("bighis")
    ;

    String command;

    OtherCommand(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
}
