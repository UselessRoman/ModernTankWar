import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Map
{
    /*0是空地
      1是墙  可用子弹击穿
      2是草  可进入子弹可通过
      3是铁  不可进入也不可击穿
      4是水  不可进入但子弹可通过
    */
    static int interval=50;
    String[] map=new String[100];

    private final Image wall = new ImageIcon("img/walls.gif").getImage();
    private final Image grass = new ImageIcon("img/grass.gif").getImage();
    private final Image iron = new ImageIcon("img/iron.gif").getImage();
    private final Image water = new ImageIcon("img/water.gif").getImage();

    public Map()
    {
        String mapFileName;
        switch (Setting.level)
        {
            case 2->mapFileName="map/map2.txt";
            case 3->mapFileName="map/map3.txt";
            default -> mapFileName="map/map.txt";
        }
        try {
            FileReader map_reader=new FileReader(mapFileName);
            BufferedReader buffer_reader=new BufferedReader(map_reader);
            for(int i=0;i<18;i++)
            {
                try {
                    map[i]=buffer_reader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }//构造方法

    public void draw_map(Graphics g)
    {
        for (int i = 0; i < 16; i++)
        {
            for (int j=0;j<16;j++)
            {
                switch (map[i].charAt(j)) {
                    case ('1') -> g.drawImage(wall, 10 + j * interval, 25 + i * interval, interval, interval, null);
                    case ('2') -> g.drawImage(grass, 10 + j * interval, 25 + i * interval, interval, interval, null);
                    case ('3') -> g.drawImage(iron, 10 + j * interval, 25 + i * interval, interval, interval, null);
                    case ('4') -> g.drawImage(water, 10 + j * interval, 25 + i * interval, interval, interval, null);
                    default -> {}
                }
            }
        }
    }
}

