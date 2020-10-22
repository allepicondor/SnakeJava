import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.MissingResourceException;

public class Menu extends JFrame {
    Canvas c;
    Point mousePos;
    Menu(){
        c = new Canvas();
        c.setBackground(Color.BLACK);
        int width = 600;
        int height = 600;
        int segment = 30;
        // add mouse listener
        c.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mousePos = e.getLocationOnScreen();
            }
            @Override
            public void mousePressed(MouseEvent e) {

            }
            @Override
            public void mouseReleased(MouseEvent e) {

            }
            @Override
            public void mouseEntered(MouseEvent e) {

            }
            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        add(c);
        String os = System.getProperty("os.name");
        if (os.startsWith("Mac")){
            setSize(width + 1, height + 23);//mac
        }else if (os.startsWith("Windows")){
            setSize(width+17, height+40);//windows
        }else{
            throw new MissingResourceException("ERROR CANNOT DETECT SYSTEM","os","OS");
        }
        show();
        //System.out.println("WIDTH:"+c.getWidth());
        //System.out.println("HEIGHT:"+c.getHeight());
        int TICKS_PER_SECOND = 10;
        final int SKIP_TICKS = 1000 / TICKS_PER_SECOND;
        final int MAX_FRAMESKIP = 5;
        double next_game_tick = System.currentTimeMillis();
        int loops;
        Button b = new Button(100,100,50,100,Color.darkGray,"Vanilla");
        while (true) {
            loops = 0;
            if (b.contains(mousePos)){
                System.out.println("CLICKED");
                break;
            }
            while (System.currentTimeMillis() > next_game_tick && loops < MAX_FRAMESKIP) {
                b.draw(c.getGraphics());
                next_game_tick += SKIP_TICKS;
                loops++;
            }

        }
        c.repaint();
        Game game = new Game(c,width,height,segment);
    }
    public static void main(String[] args){
        new Menu();
    }
}
