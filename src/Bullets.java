import javax.swing.*;
import java.awt.*;

public class Bullets
{
     static Setting Setting=new Setting();
     Image img;
     int x;
     int y;
    private int dir_set;

    static int  speed=Setting.BULLET_SPEED;

    public Bullets(int x ,int y,int dir_set)
    {
        this.x=x;
        this.y=y;
        this.dir_set=dir_set;
        this.img=new ImageIcon("img/bullet.gif").getImage();
    }//构造方法

    public void draw_bullets(Graphics g)
    {
        g.drawImage(img,x+15,y+15,null);
    }//画子弹

    public  void bullet_move()
    {
        switch (dir_set) {
            case 4 -> y += speed;
            case 1 -> x -= speed;
            case 2 -> x += speed;
            default -> y -= speed;
        }
    }//子弹移动

    public boolean  bullet_map_collision(Bullets bullet,Map map)
    {
        boolean result=false;
        for(int i=1;i<15;i++)
        {
            for (int i1 = 1; i1 <15; i1++)
            {
                switch (map.map[i].charAt(i1))
                {
                    case ('1'):
                    {
                        Rectangle rect_bullet=new Rectangle(bullet.x,bullet.y,8,8);
                        Rectangle rect_map=new Rectangle(10+i1* Map.interval,25+i* Map.interval, Map.interval, Map.interval);
                        if(rect_bullet.intersects(rect_map))
                        {
                            StringBuilder sb=new StringBuilder(map.map[i]);
                            sb.setCharAt(i1,'0');
                            map.map[i]=sb.toString();//移除碰撞的墙
                            BeginFrame.bgm.wall();
                            result=true;
                        }
                    }//子弹与墙碰撞
                    case ('3'):
                    {
                        Rectangle rect_bullet=new Rectangle(bullet.x,bullet.y,8,8);
                        Rectangle rect_map=new Rectangle(10+i1* Map.interval,25+i* Map.interval, Map.interval, Map.interval);
                        if(rect_bullet.intersects(rect_map))
                        {
                            result = true;
                            BeginFrame.bgm.iron();
                        }
                    }//子弹与铁碰撞
                    default:break;
                }
            }
        }
        return result;
    }//子弹与地图碰撞

    public  static boolean enemybullet_tank_collision(Bullets bullet,Tank tank)
    {
        boolean result=false;
        Rectangle rect_bullet=new Rectangle(bullet.x,bullet.y,8,8);
        Rectangle rect_tank=new Rectangle(tank.x,tank.y,40,40);
        if(rect_bullet.intersects(rect_tank))
        {
            result=true;
        }
        return result;
    }

    public static boolean bullet_enemytank_collision(Bullets bullet,EnemyTank tank)
    {
        boolean result=false;
        Rectangle rect_bullet=new Rectangle(bullet.x,bullet.y,8,8);
        Rectangle rect_tank=new Rectangle(tank.x,tank.y,40,40);
        if(rect_bullet.intersects(rect_tank))
        {
            result=true;
        }
        return result;
    }
}
