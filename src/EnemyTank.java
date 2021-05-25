import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.KeyEvent;

public class EnemyTank
{
    Map map;
    Image img;
    private int changeDirInternal=100;
    public ArrayList<Bullets> enemybullets=new ArrayList<>();
    private  Image enemy_up ;
    private  Image enemy_down ;
    private  Image enemy_left ;
    private  Image enemy_right  ;
    //四幅图片

    private int fire_internal=0;
    private final int speed=Setting.ENEMY_SPEED*(Setting.difficulty+3)/3;
    int x, y,w,h;
    public Direction Dir;

    int dir_set=0;
    public EnemyTank(int x,int y,Direction Dir,Map map)
    {
        this.x=x;
        this.y=y;
        this.Dir=Dir;
        this.map=map;

        img = new ImageIcon("img/enemy1.gif").getImage();
        this.enemy_up=new ImageIcon("img/enemy1.gif").getImage();
        this.enemy_down=new ImageIcon("img/enemy2.gif").getImage();
        this.enemy_left=new ImageIcon("img/enemy3.gif").getImage();
        this.enemy_right=new ImageIcon("img/enemy4.gif").getImage();


        this.w=enemy_up.getWidth(null);
        this.h=enemy_up.getHeight(null);
    }//构建方法
    public void draw(Graphics g)
    {
        g.drawImage(img, x, y,40,40, null);
    }//画敌人坦克


    public void enemyfire()
    {
        if(fire_internal>0)
        {
            fire_internal--;
        }
        if(fire_internal==0)
        {
            enemybullets.add(new Bullets(x,y,dir_set));
            fire_internal=40*(4-Setting.difficulty);
        }//连续射出子弹

        for(int i=enemybullets.size()-1;i>=0;i--)
        {
            if(is_bullet_border(enemybullets.get(i).x,enemybullets.get(i).y)/*子弹在边界错误*/&&
                    !enemybullets.get(i).bullet_map_collision(enemybullets.get(i),map)/*子弹未与地图碰撞*/)
            {
                enemybullets.get(i).bullet_move();
            }
            else
            {
                enemybullets.remove(i);
            }
        }//判断子弹与地图的碰撞
    }
    //子弹射出子弹

    private boolean is_bullet_border(int x, int y)
    {
        return x>0&&y>0&&x+8< Setting.frame_width &&y+8<Setting.frame_height;
    }
    //判断子弹是否出边界


    public void move()
    {
        if(changeDirInternal>0)
        {
            changeDirInternal--;
        }
        else if(changeDirInternal==0)
        {
            Dir=getRandomDir(Dir);
            changeDirInternal=400*(4-Setting.difficulty);//根据难度确定改变方向的时间间隔
        }//隔段时间改变方向

        switch (this.Dir) {
            case L -> {
                if (x != 0&&tank_map_collision(this,map)[0]==0) {
                    img=enemy_left;//显示图片改变方向
                    dir_set=1;//子弹方向
                    x -= speed;
                }
                else
                {
                    Dir=getRandomDir(Direction.L);
                }
            }
            case R -> {
                if (x != Setting.frame_width - 45&&tank_map_collision(this,map)[1]==0) {
                    img=enemy_right;//显示图片改变方向
                    x += speed;
                    dir_set=2;//子弹方向
                }
                else
                {
                    Dir=getRandomDir(Direction.R);
                }
            }
            case U -> {
                if (y != 25&&tank_map_collision(this,map)[2]==0) {
                    img=enemy_up;//显示图片改变方向
                    y -= speed;
                    dir_set=3;//子弹方向
                }
                else
                {
                    Dir=getRandomDir(Direction.U);
                }
            }
            case D -> {
                if (y != Setting.frame_height - 45&&tank_map_collision(this,map)[3]==0) {
                    img=enemy_down;//显示图片改变方向
                    y += speed;
                    dir_set=4;//子弹方向
                }
                else
                {
                    Dir=getRandomDir(Direction.D);
                }
            }
        }//运动
        enemyfire();
    }//坦克移动

    private int[]  tank_map_collision(EnemyTank tank, Map map)
    {
        int[] result={0,0,0,0};
        for (int i = 0; i < 16; i++)
        {
            for (int i1 = 0; i1 < 16; i1++)
            {
                if(map.map[i].charAt(i1)=='1'||map.map[i].charAt(i1)=='3'||map.map[i].charAt(i1)=='4')
                {
                    Rectangle rect_tank_up=new Rectangle(tank.x,tank.y-1,40,1);
                    Rectangle rect_tank_down=new Rectangle(tank.x,tank.y+40,40,1);
                    Rectangle rect_tank_left=new Rectangle(tank.x-1,tank.y,1,40);
                    Rectangle rect_tank_right=new Rectangle(tank.x+40,tank.y,1,40);
                    Rectangle rect_map=new Rectangle(10+i1* Map.interval,25+i* Map.interval, Map.interval, Map.interval);
                    if (rect_tank_left.intersects(rect_map)) {
                        result[0] = 1;//坦克左边与地图右边碰撞，不能向左
                    }
                    if (rect_tank_right.intersects(rect_map)) {
                        result[1] = 1;//坦克右边与地图左边碰撞，不能向右
                    }
                    if (rect_tank_up.intersects(rect_map)) {
                        result[2] = 1;//坦克上边与地图下边碰撞，不能向上
                    }
                    if (rect_tank_down.intersects(rect_map)) {
                        result[3] = 1;//坦克下边与地图上边碰撞，不能向下
                    }
                }
            }
        }
        return result;
    }//坦克与地图的碰撞

    private Direction getRandomDir(Direction nowDir)
    {
        Direction result=nowDir;

        while (result==nowDir)
        {
            int  num=(int)(Math.random()*100);
            num=num%4;

            switch (num) {
                case 0 -> result = Direction.L;
                case 1 -> result = Direction.R;
                case 2 -> result = Direction.U;
                case 3 -> result = Direction.D;
            }
        }
        return result;
    }//返回敌方坦克的随机方向
}
