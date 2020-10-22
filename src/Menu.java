import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame {
    Canvas c;
    Menu(){
        c = new Canvas();
        c.setBackground(Color.WHITE);
        int width = 600;
        int height = 600;
        int segment = 30;
        // add mouse listener
        Button b=new Button("Snake");
        add(c);
        setSize(width+17, height+40);
        b.setBounds(50,100,80,30);
        add(b);
        show();
        b.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Game a = new Game(c,width,height,segment);
            }
        });
       //Game a = new Game(c,width,height,segment);

    }
    public static void main(String[] args){
        new Menu();
    }
}
