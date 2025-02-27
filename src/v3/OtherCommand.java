package v3;

public enum OtherCommand {
    EXIT("exit"),
    HISTORY("his")
    ;

    String command;

    OtherCommand(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
}
