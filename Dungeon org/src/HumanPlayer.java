//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.util.Scanner;

public class HumanPlayer {
    private String token;//name of the character
    private int xPosition;
    private int yPosition;

    public HumanPlayer(String token) {
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

    public String getMove() {
        try {
            Scanner getDirection = new Scanner(System.in);
            String direction = getDirection.nextLine();
            direction = direction.toUpperCase();
            return direction;
        } catch (Exception var3) {
            throw var3;
        }
    }

    public String getToken() {
        return this.token;
    }
}
