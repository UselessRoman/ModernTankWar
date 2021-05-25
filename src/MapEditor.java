import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class MapEditor extends JFrame
{
    MyCursor cursor= new MyCursor();
    String[] text=new String[16];
    char[][]map=new char[16][16];
    String map_name="map/map.txt";
    Image wall,water,grass,iron;

    public MapEditor()
    {
        this.setSize(Setting.frame_width,Setting.frame_height);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("MapEditor");
        this.addKeyListener(new map_keylistener());
        this.setVisible(true);

        for(int i=0;i<16;i++)
        {
            for(int j=0;j<16;j++)
            {
                map[i][j]='0';
            }
        }

        wall=new ImageIcon("img/walls.gif").getImage();
        grass=new ImageIcon("img/grass.gif").getImage();
        iron=new ImageIcon("img/iron.gif").getImage();
        water=new ImageIcon("img/water.gif").getImage();
    }

    public static void main(String[] args)
    {
        new MapEditor();
    }

    @Override
    public void paint(Graphics g)
    {
        Image img=this.createImage(Setting.frame_width,Setting.frame_height);
        Graphics g2=img.getGraphics();

        for(int i=0;i<16;i++)
        {
            for (int j=0;j<16;j++)
            {
                char a=map[i][j];
                int x=10+j*Map.interval;
                int y=25+i*Map.interval;
                switch (a) {
                    case '1' -> g2.drawImage(wall, x, y, Map.interval, Map.interval, null);
                    case '2' -> g2.drawImage(grass, x, y, Map.interval, Map.interval, null);
                    case '3' -> g2.drawImage(iron, x, y, Map.interval, Map.interval, null);
                    case '4' -> g2.drawImage(water, x, y, Map.interval, Map.interval, null);
                }
            }
        }
        cursor.draw(g2);
        g.drawImage(img,0,0,null);
    }

    public void save() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(map_name));
        for (int i = 0; i < 16; i++) {
            text[i] = "";
        }
        for (int i = 0; i < 16; i++) {
            for (int i1 = 0; i1 < 16; i1++) {
                text[i] += "" + map[i][i1] ;
            }
            text[i] += "" + map[i][15] + "\n";
        }
        for (int i = 0; i < 16; i++) {
            bw.write(text[i]);
        }
        bw.close();
    }

    public static class MyCursor
    {
        Image img;
        int x,y;

        public MyCursor()
        {
            img = new ImageIcon("img/cursor.png").getImage();
            x = 10;
            y = 25;
        }

        public void draw(Graphics g2)
        {
            g2.drawImage(img, x, y, Map.interval, Map.interval, null);
        }
    }

    public class map_keylistener implements KeyListener
    {

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e)
        {
            int key=e.getKeyCode();
            int i=cursor.y/50;
            int j=cursor.x/50;
            switch (key)
            {
                case KeyEvent.VK_1,KeyEvent.VK_NUMPAD1 -> map[i][j] = '1';
                case KeyEvent.VK_2,KeyEvent.VK_NUMPAD2 -> map[i][j] = '2';
                case KeyEvent.VK_3,KeyEvent.VK_NUMPAD3 -> map[i][j] = '3';
                case KeyEvent.VK_4,KeyEvent.VK_NUMPAD4 -> map[i][j] = '4';
                case KeyEvent.VK_UP -> cursor.y -= 50;
                case KeyEvent.VK_DOWN -> cursor.y += 50;
                case KeyEvent.VK_LEFT -> cursor.x -= 50;
                case KeyEvent.VK_RIGHT -> cursor.x += 50;
                case KeyEvent.VK_DELETE,KeyEvent.VK_SPACE ->map[i][j]='0';
                case KeyEvent.VK_BACK_SPACE -> {
                    for (i=0;i<16;i++)
                    {
                        for (j=0;j<16;j++)
                        {
                            map[i][j]='0';
                        }
                    }
                }
            }
            repaint();
            try {
                save();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }

        @Override
        public void keyReleased(KeyEvent e)
        {}
    }
}
