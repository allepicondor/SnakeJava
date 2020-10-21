import java.awt.*;
import java.util.ArrayList;

public class Board {
    int x;
    int y;
    int segment;
    int[][][] board;
    int rows;
    int columns;
    public Board(int widthX, int heightY, int segment) {
        this.x = widthX;
        this.y = heightY;
        this.segment = segment;
        this.rows = heightY/segment;
        this.columns = widthX/segment;
        this.board = new int[this.rows][this.columns][2];
        for (int x = 0; x < this.columns; x++) {
            for (int y = 0; y < this.rows; y++) {

                this.board[x][y] = new int[]{x * segment,y*segment};
            }
        }
    }
    public int[] grabPix(int x , int y) {
        return this.board[x][y];
    }
    public void draw(Graphics g, ArrayList<int[]> snakecords){
        for (int x = 0; x < this.columns; x++) {
            for (int y = 0; y < this.rows; y++) {
                if (!snakecords.contains(new int[]{x,y})) {
                    int[] pos = this.grabPix(x, y);
                    g.setColor(Color.darkGray);
                    g.fillRect(pos[0] + 2, pos[1] + 2, this.segment - 2, this.segment - 2);
                }
            }
        }

    }
    public void drawSquare(Graphics g,int[] pos,Color color){
        g.setColor(color);
        g.fillRect(pos[0] + 2, pos[1] + 2, this.segment - 2, this.segment - 2);
    }


}
