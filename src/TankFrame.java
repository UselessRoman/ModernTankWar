import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class TankFrame extends JFrame implements FrameMethod
{
    Map thismap = new Map();
    Tank MyTank=new Tank((Setting.frame_width)/2-170,Setting.frame_height-100,Direction.S,thismap);
    public ArrayList<EnemyTank> enemyTanks=new ArrayList<>();
    public ArrayList<boom> booms=new ArrayList<>();

    public TankFrame()
    {
        this.setVisible(true);
        this.setResizable(false);
        this.setSize(Setting.frame_width, Setting.frame_height);
        this.addKeyListener(new TankKeyListener());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("TankWar");
        this.setLocationRelativeTo(null);

        this.setVisible(true);
        BeginFrame.bgm.playBGM();



        for(int i=0;i<Setting.ENEMY_NUMBER;i++)
        {
            enemyTanks.add(new EnemyTank((i+1)*200,150,Direction.R,thismap));
        }//创建一系列敌方坦克

    }//构造方法
    public void run()
    {
        for (int i = enemyTanks.size()-1; i >=0 ; i--) {
            for (int j = MyTank.bullets.size() - 1; j >= 0; j--) {
                if (Bullets.bullet_enemytank_collision(MyTank.bullets.get(j), enemyTanks.get(i))) {
                    MyTank.bullets.remove(j);

                    booms.add( new boom(enemyTanks.get(i).x,enemyTanks.get(i).y,thismap));
                    enemyTanks.remove(i);
                    BeginFrame.bgm.broke();
                    break;
                }
            }
        }//我方子弹击中敌方坦克

        for (int i = enemyTanks.size()-1; i >=0 ; i--)
        {    if(MyTank.live==0){break;}
            for(int j=enemyTanks.get(i).enemybullets.size()-1;j>=0;j--)
            {
                if(Bullets.enemybullet_tank_collision(enemyTanks.get(i).enemybullets.get(j),MyTank))
                {
                    enemyTanks.get(i).enemybullets.remove(j);
                    MyTank.live--;
                    if(MyTank.live==0)
                    {     BeginFrame.bgm.gameover();
                        Setting.mytank_is_alive=false;
                        booms.remove(0);
                        break;
                    }
                }
            }
        }//敌方子弹击中我方坦克

        if(!Setting.mytank_is_alive)
        {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.dispose();
            Setting.game_on=false;
            Setting.begin_on=true;
            Setting.choose_on=false;
            Setting.mytank_is_alive=true;
            BeginFrame.bgm.playMenuBGM();
        }
        if(enemyTanks.size()==0)
        {
            switch (Setting.mode)
            {
                case 1->
                        {
                            Setting.level++;
                            thismap=new Map();
                            MyTank.map=thismap;
                            MyTank.x=Setting.frame_width/2-170;
                            MyTank.y=Setting.frame_height-100;

                            for(int i=0;i<Setting.ENEMY_NUMBER;i++)
                            {
                                enemyTanks.add(new EnemyTank((i+1)*100,100,Direction.R,thismap));
                            }//创建一系列敌方坦克
                        }
                case 2->
                        {
                            for(int i=0;i<Setting.ENEMY_NUMBER;i++)
                            enemyTanks.add(new EnemyTank((i+1)*50,50,Direction.R,thismap));
                        }//创建一系列敌方坦克
            }

        }
    }


    public void paint (Graphics g)
    {
        run();
        Image image =createImage(800,800);//创建g2画布
        Graphics g2 = image.getGraphics();
        if(Setting.mytank_is_alive)
        {
            MyTank.move();//坦克移动
        }
        MyTank.draw(g2);//g2画我方坦克

        for (EnemyTank tank : enemyTanks)
        {
            tank.move();
            tank.draw(g2);
        }//画出一系列敌方坦克


        thismap.draw_map(g2);//g2画地图

        for(int i=MyTank.bullets.size()-1;i>=0;i--)
        {
            MyTank.bullets.get(i).draw_bullets(g2);
        }//画己方子弹

        for (EnemyTank tank : enemyTanks)
        {
            for (int i = tank.enemybullets.size()-1; i >=0 ; i--)
            {
                tank.enemybullets.get(i).draw_bullets(g2);
            }
        }//画敌方子弹
        for(boom boom:booms){
            boom.draw(g2);
        }
        g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), null);//g画
    }//屏幕绘制


    //键盘响应
    private  class TankKeyListener extends KeyAdapter
    {
        public void keyPressed(KeyEvent e)
        {
            MyTank.keyPressed(e);
        }

        public void keyReleased(KeyEvent e)
        {
            MyTank.keyReleased(e);
        }
    }
}
