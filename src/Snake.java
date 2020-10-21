import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
public class Snake {
    int[] currentPos;
    Board board2;
    ArrayList<int[]> SnakeCords = new ArrayList<int[]> ();
    ArrayList<int[]> SnakeBodyCords = new ArrayList<int[]> ();
    Random random = new Random();
    public Snake(Board board){
        board2 = board;
        this.currentPos = new int[]{random.nextInt(board.columns-1),random.nextInt(board.rows-1)};
        SnakeCords.add(currentPos);
        SnakeBodyCords.add(new int[]{currentPos[0]+1,currentPos[1]});
        SnakeCords.add(new int[]{currentPos[0]+1,currentPos[1]});
    }
    public void move(int[] velocity){
        currentPos = add(velocity,currentPos);
        ArrayList<int[]> NewSnakeCords = new ArrayList<int[]> ();
        ArrayList<int[]> newSnakeBodyCords = new ArrayList<int[]> ();
        NewSnakeCords.add(currentPos);
        SnakeCords.remove(SnakeCords.size()-1);
        for (int i = 0;i < SnakeCords.size();i++){
            newSnakeBodyCords.add(SnakeCords.get(i));
            NewSnakeCords.add(SnakeCords.get(i));
        }
        SnakeBodyCords = newSnakeBodyCords;
        SnakeCords = NewSnakeCords;
        //System.out.println(Arrays.toString(currentPos));
    }
    public void draw(Graphics g){
        for (int[] cord: SnakeCords){
            int[] pos = board2.grabPix(cord[0],cord[1]);
            board2.drawSquare(g,pos,Color.RED);

        }

    }
    public void EatFood(){
        SnakeCords.add(SnakeCords.get(SnakeCords.size()-1));
    }
    public static int[] add(int[] first, int[] second) {
        int length = first.length < second.length ? first.length
                : second.length;
        int[] result = new int[length];

        for (int i = 0; i < length; i++) {
            result[i] = first[i] + second[i];
        }

        return result;
    }
}
