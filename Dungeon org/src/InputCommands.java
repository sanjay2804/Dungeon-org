
public enum InputCommands {
    HELLO("HELLO"),
    GOLD("GOLD"),
    QUIT("QUIT"),
    MOVE_NORTH("MOVE N"),
    MOVE_SOUTH("MOVE S"),
    MOVE_WEST("MOVE W"),
    MOVE_EAST("MOVE E"),
    PICKUP("PICKUP"),
    LOOK("LOOK");

    private String inputValue;

    private InputCommands(String value) {
        this.inputValue = value;
    }

    public String getValue() {
        return this.inputValue;
    }
}
