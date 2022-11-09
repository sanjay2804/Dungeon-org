import java.io.FileNotFoundException;
import java.util.Arrays;
//import java.util.Map;
import java.util.Scanner;

public class GameLogic {
    private Map board ;
    private int currentGold = 0;
    private HumanPlayer humanPlayer = new HumanPlayer("P");
    private ComputerPlayer computerPlayer = new ComputerPlayer("B");


    public static void main(String[] args) {

        GameLogic g = new GameLogic();
        g.playGame();
    }


//Prints the board
    private String printBoard(int x, int y) {
        String toReturn = "";

        for(int i = x - 2; i <= x + 2; ++i) {
            for(int j = y - 2; j <= y + 2; ++j) {
                if (i >= 0 && j >= 0 && i <= this.board.getHeight() - 1 && j <= this.board.getWidth() - 1) {
                    toReturn = toReturn + this.board.getToken(i, j) + " ";
                } else {
                    toReturn = toReturn + "# ";
                }
            }

            toReturn = toReturn + "\n";
        }

        return toReturn;
    }

    public GameLogic() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose the board");
        System.out.println("1.Large Map");
        System.out.println("2.Medium Map");
        System.out.println("3.Small Map");
        int number = scanner.nextByte();
        if (number == 1) {
            System.out.println("Activating map " + number);
            try {
                this.board = new Map("/Users/sanjayanvinaitheerthan/untitled1/src/large_example_map.txt", this.humanPlayer.getToken(), this.computerPlayer.getToken());
            } catch (FileNotFoundException var7) {
                var7.printStackTrace();
            }
        } else if (number == 2) {
            System.out.println("Activating map " + number);
            try {
                this.board = new Map("/Users/sanjayanvinaitheerthan/untitled1/src/medium_example_map.txt", this.humanPlayer.getToken(), this.computerPlayer.getToken());
            } catch (FileNotFoundException var6) {
                var6.printStackTrace();
            }
        } else if (number == 3) {
            System.out.println("Activating map " + number);
            try {
                this.board = new Map("/Users/sanjayanvinaitheerthan/untitled1/src/small_example_map.txt", this.humanPlayer.getToken(), this.computerPlayer.getToken());
            } catch (FileNotFoundException var5) {
                var5.printStackTrace();
            }
        } else {
            System.out.println("Sorry you have entered a wrong value,so you will be directed to large(Default) map");

            try {
                this.board = new Map("/Users/sanjayanvinaitheerthan/untitled1/src/large_example_map.txt", this.humanPlayer.getToken(), this.computerPlayer.getToken());
            } catch (FileNotFoundException var4) {
                var4.printStackTrace();
            }
        }

    }

    private void setPosition() {
        for(int i = 0; i < this.board.getHeight(); ++i) {
            for(int j = 0; j < this.board.getWidth(); ++j) {
                if (this.board.getToken(i, j).equals("P")) {
                    this.humanPlayer.setXPosition(i);
                    this.humanPlayer.setYPosition(j);
                    i = this.board.getHeight();
                    break;
                }
            }
        }

    }

    private void setComputerPosition() {
        for(int i = this.board.getHeight() - 1; i >= 0; --i) {
            for(int j = this.board.getWidth() - 1; j >= 0; --j) {
                if (this.board.getToken(i, j).equals("B")) {
                    this.computerPlayer.setXPosition(i);
                    this.computerPlayer.setYPosition(j);
                    i = 0;
                    break;
                }
            }
        }

    }

    public void playGame() {
        System.out.println(this.board);
        this.setPosition();
        this.setComputerPosition();
        boolean gameOver = false;
        boolean isCurrentPlayerHuman = true;

        while(!gameOver) {
            if (isCurrentPlayerHuman) {
                gameOver = this.humanMoves();
                isCurrentPlayerHuman = false;
            } else {
                gameOver = this.computerMoves();
                isCurrentPlayerHuman = true;
            }

            if (this.humanPlayer.getXPosition() == this.computerPlayer.getXPosition() && this.humanPlayer.getYPosition() == this.computerPlayer.getYPosition()) {
                System.out.println("Game Over");
                System.out.println("You were eaten by the Bot!");
                gameOver = true;
            }
        }

    }

    private boolean humanMoves() {
        boolean isInValidCommand = false;
        boolean isGameOver = false;

        do {
            isInValidCommand = false;
            int x = this.humanPlayer.getXPosition();
            int y = this.humanPlayer.getYPosition();
            String direction = this.humanPlayer.getMove();
            direction = direction.toUpperCase();
            Map var10000 = this.board;
            if (Arrays.asList(Map.validCommands).indexOf(direction) < 0) {
                isInValidCommand = true;
                System.out.println("Enter valid command");
            } else if (direction.equals(InputCommands.HELLO.getValue())) {
                System.out.println("Gold to win: " + this.board.getWins());
            } else if (direction.equals(InputCommands.GOLD.getValue())) {
                System.out.println("Gold owned: " + this.currentGold);
            }else if (direction.equals(InputCommands.MOVE_NORTH.getValue())) {
                if (this.board.getToken(x - 1, y).equals("#")) {
                    System.out.println("Fail");
                } else {
                    --x;
                    if (this.board.getToken(x, y).equals("G")) {
                        this.board.placeToken(x, y, "PG");
                    } else if (this.board.getToken(x, y).equals("E")) {
                        this.board.placeToken(x, y, "PE");
                    } else {
                        this.board.placeToken(x, y, "P");
                    }

                    if (this.board.getToken(x + 1, y).equals("PG")) {
                        this.board.placeToken(x + 1, y, "G");
                    } else if (this.board.getToken(x + 1, y).equals("PE")) {
                        this.board.placeToken(x + 1, y, "E");
                    } else {
                        this.board.placeToken(x + 1, y, ".");
                    }

                    System.out.println("Success");
                }
            } else if (direction.equals(InputCommands.MOVE_SOUTH.getValue())) {
                if (this.board.getToken(x + 1, y).equals("#")) {
                    System.out.println("Fail");
                } else {
                    ++x;
                    if (this.board.getToken(x, y).equals("G")) {
                        this.board.placeToken(x, y, "PG");
                    } else if (this.board.getToken(x, y).equals("E")) {
                        this.board.placeToken(x, y, "PE");
                    } else {
                        this.board.placeToken(x, y, "P");
                    }

                    if (this.board.getToken(x - 1, y).equals("PG")) {
                        this.board.placeToken(x - 1, y, "G");
                    } else if (this.board.getToken(x - 1, y).equals("PE")) {
                        this.board.placeToken(x - 1, y, "E");
                    } else {
                        this.board.placeToken(x - 1, y, ".");
                    }

                    System.out.println("Success");
                }
            } else if (direction.equals(InputCommands.MOVE_EAST.getValue())) {
                if (this.board.getToken(x, y + 1).equals("#")) {
                    System.out.println("Fail");
                } else {
                    ++y;
                    if (this.board.getToken(x, y).equals("G")) {
                        this.board.placeToken(x, y, "PG");
                    } else if (this.board.getToken(x, y).equals("E")) {
                        this.board.placeToken(x, y, "PE");
                    } else {
                        this.board.placeToken(x, y, "P");
                    }

                    if (this.board.getToken(x, y - 1).equals("PG")) {
                        this.board.placeToken(x, y - 1, "G");
                    } else if (this.board.getToken(x, y - 1).equals("PE")) {
                        this.board.placeToken(x, y - 1, "E");
                    } else {
                        this.board.placeToken(x, y - 1, ".");
                    }

                    System.out.println("Success");
                }
            } else if (direction.equals(InputCommands.MOVE_WEST.getValue())) {
                if (this.board.getToken(x, y - 1).equals("#")) {
                    System.out.println("Fail");
                } else {
                    --y;
                    if (this.board.getToken(x, y).equals("G")) {
                        this.board.placeToken(x, y, "PG");
                    } else if (this.board.getToken(x, y).equals("E")) {
                        this.board.placeToken(x, y, "PE");
                    } else {
                        this.board.placeToken(x, y, "P");
                    }

                    if (this.board.getToken(x, y + 1).equals("PG")) {
                        this.board.placeToken(x, y + 1, "G");
                    } else if (this.board.getToken(x, y + 1).equals("PE")) {
                        this.board.placeToken(x, y + 1, "E");
                    } else {
                        this.board.placeToken(x, y + 1, ".");
                    }

                    System.out.println("Success");
                }
            } else if (direction.equals(InputCommands.PICKUP.getValue())) {
                if (this.board.getToken(x, y).equals("PG")) {
                    this.board.placeToken(x, y, "P");
                    ++this.currentGold;
                    System.out.println("Success. Gold owned: " + this.currentGold);
                } else {
                    System.out.println("Fail");
                }
            } else if (direction.equals(InputCommands.LOOK.getValue())) {
                System.out.println(this.printBoard(x, y));
            } else if (direction.equals(InputCommands.QUIT.getValue())) {
                if (this.board.getToken(x, y).equals("PE")) {
                    if (this.currentGold >= this.board.goldToWin) {
                        System.out.println("WIN");
                    } else {
                        System.out.println("LOSE");
                    }
                } else {
                    System.out.println("You have quitted the game");
                    System.out.println("LOSE");
                }

                isGameOver = true;
            }

            this.humanPlayer.setXPosition(x);
            this.humanPlayer.setYPosition(y);
        } while(isInValidCommand);

        return isGameOver;
    }

    private boolean computerMoves() {
        boolean isGameOver = false;
        int x = this.computerPlayer.getXPosition();
        int y = this.computerPlayer.getYPosition();
        String direction = this.computerPlayer.getMove();
        direction = direction.toUpperCase();
        System.out.println("Computer Move: " + direction);
        if (direction.equals(InputCommands.MOVE_NORTH.getValue())) {
            if (this.board.getToken(x - 1, y).equals("#")) {
                System.out.println("Fail");
            } else {
                --x;
                if (this.board.getToken(x, y).equals("G")) {
                    this.board.placeToken(x, y, "BG");
                } else if (this.board.getToken(x, y).equals("E")) {
                    this.board.placeToken(x, y, "BE");
                } else {
                    this.board.placeToken(x, y, "B");
                }

                if (this.board.getToken(x + 1, y).equals("BG")) {
                    this.board.placeToken(x + 1, y, "G");
                } else if (this.board.getToken(x + 1, y).equals("BE")) {
                    this.board.placeToken(x + 1, y, "E");
                } else {
                    this.board.placeToken(x + 1, y, ".");
                }

                System.out.println("Success");
            }
        } else if (direction.equals(InputCommands.MOVE_SOUTH.getValue())) {
            if (this.board.getToken(x + 1, y).equals("#")) {
                System.out.println("Fail");
            } else {
                ++x;
                if (this.board.getToken(x, y).equals("G")) {
                    this.board.placeToken(x, y, "BG");
                } else if (this.board.getToken(x, y).equals("E")) {
                    this.board.placeToken(x, y, "BE");
                } else {
                    this.board.placeToken(x, y, "B");
                }

                if (this.board.getToken(x - 1, y).equals("BG")) {
                    this.board.placeToken(x - 1, y, "G");
                } else if (this.board.getToken(x - 1, y).equals("BE")) {
                    this.board.placeToken(x - 1, y, "E");
                } else {
                    this.board.placeToken(x - 1, y, ".");
                }

                System.out.println("Success");
            }
        } else if (direction.equals(InputCommands.MOVE_EAST.getValue())) {
            if (this.board.getToken(x, y + 1).equals("#")) {
                System.out.println("Fail");
            } else {
                ++y;
                if (this.board.getToken(x, y).equals("G")) {
                    this.board.placeToken(x, y, "BG");
                } else if (this.board.getToken(x, y).equals("E")) {
                    this.board.placeToken(x, y, "BE");
                } else {
                    this.board.placeToken(x, y, "B");
                }

                if (this.board.getToken(x, y - 1).equals("BG")) {
                    this.board.placeToken(x, y - 1, "G");
                } else if (this.board.getToken(x, y - 1).equals("BE")) {
                    this.board.placeToken(x, y - 1, "E");
                } else {
                    this.board.placeToken(x, y - 1, ".");
                }

                System.out.println("Success");
            }
        } else if (direction.equals(InputCommands.MOVE_WEST.getValue())) {
            if (this.board.getToken(x, y - 1).equals("#")) {
                System.out.println("Fail");
            } else {
                --y;
                if (this.board.getToken(x, y).equals("G")) {
                    this.board.placeToken(x, y, "BG");
                } else if (this.board.getToken(x, y).equals("E")) {
                    this.board.placeToken(x, y, "BE");
                } else {
                    this.board.placeToken(x, y, "B");
                }

                if (this.board.getToken(x, y + 1).equals("BG")) {
                    this.board.placeToken(x, y + 1, "G");
                } else if (this.board.getToken(x, y + 1).equals("BE")) {
                    this.board.placeToken(x, y + 1, "E");
                } else {
                    this.board.placeToken(x, y + 1, ".");
                }

                System.out.println("Success");
            }
        } else if (direction.equals(InputCommands.LOOK.getValue())) {
            System.out.println(this.printBoard(x, y));
        }

        this.computerPlayer.setXPosition(x);
        this.computerPlayer.setYPosition(y);
        return isGameOver;
    }
}
