import java.util.Random;

public class ComputerPlayer {
    private String token;
    private int xPosition;
    private int yPosition;

    public ComputerPlayer(String token) {
        this.token = token;
    }

    public void setXPosition(int x) {
        this.xPosition = x;
    }

    public void setYPosition(int y) {
        this.yPosition = y;
    }

    public int getXPosition() {
        return this.xPosition;
    }

    public int getYPosition() {
        return this.yPosition;
    }

    private int generateRandomNumber() {
        Random random = new Random();
        boolean var2 = false;

        int rand;
        do {
            do {
                rand = random.nextInt(6);
            } while(rand <= 0);
        } while(rand >= 6);

        return rand;
    }

    public String getMove() {
        switch(this.generateRandomNumber()) {
            case 1:
                return InputCommands.MOVE_NORTH.getValue();
            case 2:
                return InputCommands.MOVE_SOUTH.getValue();
            case 3:
                return InputCommands.MOVE_EAST.getValue();
            case 4:
                return InputCommands.MOVE_WEST.getValue();
            case 5:
                return InputCommands.LOOK.getValue();
            default:
                return "Invalid command";
        }
    }

    public String getToken() {
        return this.token;
    }
}
