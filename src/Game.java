import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;

class Game extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 388656052825580701L;
	// create a canvas
    Canvas c;
    Board board;
    Snake snake;
    Food food;
    int e = KeyEvent.VK_DOWN;
    Game()
    {
        super("Snake");

        // create a empty canvas
        c = new Canvas();
        // set background
        c.setBackground(Color.black);
        int width = 900;
        int height = 900;
        // add mouse listener
        add(c);
        setSize(width+17, height+40);
        board = new Board(900,900,30);
        snake = new Snake(board);
        food = new Food(board);
        System.out.println(board.grabPix(29,29)[1]);
        c.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent a) {
            }
            public void keyPressed(KeyEvent a) {
                e = a.getKeyCode();
            }
            public void keyReleased(KeyEvent a) {
            }
        });
        int TICKS_PER_SECOND = 20;
        final int SKIP_TICKS = 1000 / TICKS_PER_SECOND;
        final int MAX_FRAMESKIP = 5;
        int[] vel = new int[]{0, 0};
        double next_game_tick = System.currentTimeMillis();
        int loops;
        show();
        while (true){
            loops = 0;
            if (e == KeyEvent.VK_LEFT){
                vel = new int[]{-1, 0};
            }
            if (e == KeyEvent.VK_UP){
                vel = new int[]{0, -1};
            }
            if (e == KeyEvent.VK_RIGHT){
                vel = new int[]{1, 0};
            }
            if (e == KeyEvent.VK_DOWN){
                vel = new int[]{0, 1};
            }
            while (System.currentTimeMillis() > next_game_tick
                    && loops < MAX_FRAMESKIP) {

                snake.move(vel);
                if (food.currentPos[0] == snake.currentPos[0] && food.currentPos[1] == snake.currentPos[1] ) {
                    TICKS_PER_SECOND += 2;
                    food.SpawnFood();
                    snake.EatFood();
                }
                for (int[] cord: snake.SnakeBodyCords){
                    if (cord[0] == snake.currentPos[0]){
                        if (cord[1] == snake.currentPos[1]){
                            snake = new Snake(board);
                            break;
                        }
                    }
                    
                }
                if (snake.currentPos[0] >= 30 || snake.currentPos[0] < 0 || snake.currentPos[1] >= 30 || snake.currentPos[1] < 0){
                    snake = new Snake(board);
                }else {
                    drawFrame(c.getGraphics(), snake, board);
                }

                next_game_tick += SKIP_TICKS;
                loops++;
            }
            snake.draw(c.getGraphics());
            food.draw(c.getGraphics());
        }
    }
    public void drawFrame(Graphics g,Snake snake,Board board){
        board.draw(g,snake.SnakeCords);
        snake.draw(g);
        food.draw(g);
    }
    // main class
    public static void main(String args[])
    {
        Game a = new Game();
    }
}
