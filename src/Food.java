import java.awt.*;
import java.util.Random;

public class Food {
    Board board2;
    int[] currentPos;
    Random random = new Random();
    public Food(Board board){
        board2 = board;
        this.currentPos = new int[]{random.nextInt(board.columns),random.nextInt(board.rows)};
    }
    public void SpawnFood(){
        this.currentPos = new int[]{random.nextInt(board2.columns),random.nextInt(board2.rows)};
    }
    public void draw(Graphics g){
        int[] pos = board2.grabPix(currentPos[0],currentPos[1]);
        board2.drawSquare(g,pos,Color.GREEN);
    }
}
