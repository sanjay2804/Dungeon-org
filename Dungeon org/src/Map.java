import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Map {
    private String[][] map;
    public int goldToWin;
    public static String[] validCommands = new String[]{"HELLO", "GOLD", "QUIT", "MOVE N", "MOVE S", "MOVE W", "MOVE E", "PICKUP", "LOOK"};

    public Map(String path, String humanToken, String computerToken) throws FileNotFoundException {
        ArrayList<String> listLines = new ArrayList();
        Scanner givenMaps = new Scanner(new File(path));
        givenMaps.nextLine();
        String[] t = givenMaps.nextLine().split(" ");
        this.goldToWin = Integer.parseInt(t[1]);

        while(givenMaps.hasNext()) {
            String line = givenMaps.nextLine();
            listLines.add(line);
        }

        boolean assignedP1 = false;
        this.map = new String[listLines.size()][];

        int k;

        for(int i = 0; i < listLines.size(); ++i) {
            this.map[i] = new String[((String)listLines.get(i)).length()];

            for(k = 0; k < ((String)listLines.get(i)).length(); ++k) {
                String line = (String)listLines.get(i);
                char c = line.charAt(k);
                this.map[i][k] = Character.toString(c);
                //assigned P1 is just to make sure the players position is assigned
                if (this.map[i][k].equals(".") && !assignedP1) {//If the element is  "." and the player is not assigned then assign the player
                    this.map[i][k] = humanToken;
                    assignedP1 = true;
                }
            }
        }

        boolean assignedComputer = false;

        for(int i = this.map.length - 1; i >= 0; --i) {
            for(int j = this.map[i].length - 1; j >= 0; --j) {
                if (this.map[i][j].equals(".") && !assignedComputer) {
                    this.map[i][j] = computerToken;
                    assignedComputer = true;
                }
            }
        }

    }

    public void placeToken(int x, int y, String token) {
        this.map[x][y] = token;
    }

    public String getToken(int x, int y) {
        return this.map[x][y];
    }

    public int getWidth() {
        return this.map[0].length;
    }

    public int getHeight() {
        return this.map.length;
    }

    public String toString() {
        String toReturn = "";

        for(int i = 0; i < this.map.length; ++i) {
            for(int j = 0; j < this.map[i].length; ++j) {
                if (this.map[i][j] == "\u0000") {
                    toReturn = toReturn + ".";
                } else {
                    toReturn = toReturn + this.map[i][j] + " ";
                }
            }

            toReturn = toReturn + "\n";
        }

        return toReturn;
    }

    public int getWins() {
        return this.goldToWin;
    }

//    public int getNine(){
//        return 9;
//    }
}
