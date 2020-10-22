import java.awt.*;
public class Button {
    int X;
    int Y;
    int Height;
    int Width;
    Color color;
    String title;
    public boolean contains(Point p) {
        if( p != null) {
            return (this.X < p.getX() && this.Y < p.getY() &&
                    this.X + this.Width > p.getX() &&
                    this.Y + this.Height > p.getY());
        }
        return false;
    }
    Button(int posx, int posy,int height,int width,Color c, String Title){
        X = posx;
        Y = posy;
        Height = height;
        Width = width;
        color = c;
        title = Title;
    }

    void draw(Graphics g){
        g.setColor(color);
        g.fillRect(X,Y,Width,Height);
        g.setColor(Color.RED);
        g.drawString(title,X+(Width/3),Y+(Height/2));
    }
}
