import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

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
    Game(Canvas canvas,int width,int height,int segment)
    {
        // create a empty canvas
        c = canvas;
        board = new Board(width,height,segment);
        snake = new Snake(board);
        food = new Food(board);
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
        int TICKS_PER_SECOND = 10;
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
            while (System.currentTimeMillis() > next_game_tick && loops < MAX_FRAMESKIP) {
                snake.move(vel);
                if (food.currentPos[0] == snake.currentPos[0] && food.currentPos[1] == snake.currentPos[1] ) {
                    TICKS_PER_SECOND += 2;
                    food.SpawnFood(snake);
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
                if (snake.currentPos[0] >= board.columns || snake.currentPos[0] < 0 || snake.currentPos[1] >= board.rows || snake.currentPos[1] < 0){
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
}
