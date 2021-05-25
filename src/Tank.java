import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Tank
{
    Map map;
    Image img;
    Image death;
    public ArrayList<Bullets> bullets=new ArrayList<>();
    private final Image player_up;
    private final Image player_down;
    private final Image player_left;
    private final Image player_right;
    //四幅图片

    public int live ;


    private int fire_internal=0;
    int x, y,w,h;
    public Direction Dir;
    public boolean bL;
    public boolean bR;
    public boolean bU;
    public boolean bD;//方向布尔值
    public boolean bF;//射击布尔值
    int dir_set=0;
    public Tank(int x,int y,Direction Dir,Map map)
    {
        this.x=x;
        this.y=y;
        this.Dir=Dir;
        this.map=map;
        this.live=Setting.live;
        this.bU=true;
        this.bD=true;
        this.bF=true;
        this.bL=true;
        this.bR=true;

        death=new ImageIcon("img/boom.jpg").getImage();
        img = new ImageIcon("img/player1.gif").getImage();
        this.player_up=new ImageIcon("img/player1.gif").getImage();
        this.player_down=new ImageIcon("img/player2.gif").getImage();
        this.player_left=new ImageIcon("img/player3.gif").getImage();
        this.player_right=new ImageIcon("img/player4.gif").getImage();


        this.w=player_up.getWidth(null);
        this.h=player_up.getHeight(null);
    }//构造方法

    public void draw(Graphics g)
    {
        if(Setting.mytank_is_alive)
        {
            g.drawImage(img, x, y,40,40, null);
        }
        g.setColor(Color.RED);
        g.fillRect(x,y-10,(live*(40/Setting.live)),5);
    }//画坦克的方法

    public void keyPressed(KeyEvent e)
    {
        int key =e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_LEFT, KeyEvent.VK_A -> {
                img = player_left;
                dir_set=1;
                this.bL = true;
            }
            case KeyEvent.VK_RIGHT, KeyEvent.VK_D -> {
                img = player_right;
                dir_set=2;
                this.bR = true;
            }
            case KeyEvent.VK_UP, KeyEvent.VK_W -> {
                img = player_up;
                dir_set=3;
                this.bU = true;
            }
            case KeyEvent.VK_DOWN, KeyEvent.VK_S -> {
                img = player_down;
                dir_set=4;
                this.bD = true;
            }
            case KeyEvent.VK_SPACE -> bF=true;
        }
        SetDir();
    }//读取按键

    public void keyReleased(KeyEvent e)
    {
        int key =e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_LEFT, KeyEvent.VK_A -> this.bL = false;
            case KeyEvent.VK_RIGHT, KeyEvent.VK_D -> this.bR = false;
            case KeyEvent.VK_UP, KeyEvent.VK_W -> this.bU = false;
            case KeyEvent.VK_DOWN, KeyEvent.VK_S -> this.bD = false;
            case KeyEvent.VK_SPACE -> this.bF = false;
        }
        SetDir();
    }
    //键盘响应
    public void fire()
    {
        if(fire_internal>0)
        {
            fire_internal--;
        }
        if(bF&&fire_internal==0)
        {
            bullets.add(new Bullets(x,y,dir_set));
            fire_internal=40*Setting.difficulty;
            BeginFrame.bgm.attack();//依据难度射出子弹
        }//连续射出子弹

        for(int i=bullets.size()-1;i>=0;i--)
        {
            if(is_bullet_border(bullets.get(i).x,bullets.get(i).y)/*子弹在边界内*/
                    &&!bullets.get(i).bullet_map_collision(bullets.get(i),map)/*子弹未与地图碰撞*/)
            {
                bullets.get(i).bullet_move();
            }
            else
            {
                bullets.remove(i);
            }
        }//判断子弹与地图的碰撞
    }
    //射出子弹

    private boolean is_bullet_border(int x, int y)
    {
        return x>0&&y>0&&x+8< Setting.frame_width &&y+8<Setting.frame_height;
    }
    //判断子弹是否出边界

    public void SetDir()
    {
        if(!bR&&!bL&&!bU&&!bD)
        {
            Dir=Direction.S;
        }
        if(bR&&!bL&&!bU&&!bD)
        {
            Dir=Direction.R;
        }
        if(!bR&&bL&&!bU&&!bD)
        {
            Dir=Direction.L;
        }
        if(!bR&&!bL&&bU&&!bD)
        {
            Dir=Direction.U;
        }
        if(!bR&&!bL&&!bU&&bD)
        {
            Dir=Direction.D;
        }
    }
    //方向设置

    public void move()
    {
        int speed = Setting.TANK_SPEED;
        switch (this.Dir) {
            case L -> {
                if (x != 0&&tank_map_collision(this,map)[0]==0) {
                    x -= speed;
                }
            }
            case R -> {
                if (x != Setting.frame_width - 45&&tank_map_collision(this,map)[1]==0) {
                    x += speed;
                }
            }
            case U -> {
                if (y != 25&&tank_map_collision(this,map)[2]==0) {
                    y -= speed;
                }
            }
            case D -> {
                if (y != Setting.frame_height - 45&&tank_map_collision(this,map)[3]==0) {
                    y += speed;
                }
            }
        }//运动
        fire();
    }//坦克移动

    private int[]  tank_map_collision(Tank tank, Map map)
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
}
