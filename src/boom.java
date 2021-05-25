import javax.swing.*;
import java.awt.*;

public class boom {
    static int x, y,w,h;
    static Map map;
    static Image img = new ImageIcon("img/boom.jpg").getImage();;
    public boom(int x,int y,Map map)
    {
        this.x=x;
        this.y=y;
        this.map=map;
    }

    public static void draw(Graphics g) {
        g.drawImage(img, x, y,40,40, null);
    }//画爆炸
}
