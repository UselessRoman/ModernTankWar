import javax.swing.*;

public class Run
{
    public  static void main(String[] args)
    {   BeginFrame bf=new BeginFrame();
        ImageIcon background =new ImageIcon("img/background.jpg");
        JLabel imgLabel=new JLabel(background);
        imgLabel.setBounds(0,0,Setting.frame_width,Setting.frame_height);
        bf.getLayeredPane().add(imgLabel, Integer.MIN_VALUE);



        while (Setting.begin_on)
        {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            bf.repaint();
            if(Setting.client_on) {
                Cilent.run();

            }
            if(Setting.server_on){
                Server.run();
                }
            if (Setting.choose_on)
            {
                System.out.println("Choose True");
                ChooseFrame cf=new ChooseFrame();
                while (Setting.choose_on)
                {
                    try {
                        Thread.sleep(1);
                    }catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    cf.repaint();

                    if(Setting.game_on)
                    {
                        cf.dispose();
                        TankFrame tf = new TankFrame();
                        while (Setting.game_on)
                        {
                            try {
                                Thread.sleep(1);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            tf.repaint();
                            if (!Setting.game_on)
                            {
                                tf.dispose();

                                break;
                            }
                        }
                    }

                    if (!Setting.choose_on)
                    {
                        break;
                    }
                }
            }
            if (Setting.set_on)
            {
                System.out.println("Set true");
                SetFrame setFrame=new SetFrame();
                while(true){
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    setFrame.repaint();
                    if (!Setting.set_on)
                    {
                        setFrame.dispose();
                        break;
                    }
                }
            }
        }
    }
}