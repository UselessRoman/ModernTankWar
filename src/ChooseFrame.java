import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ChooseFrame extends JFrame
{
    private  ImageIcon img1 =new ImageIcon("img/1.png");
    private  ImageIcon img2 = new ImageIcon("img/2.png");
    private  ImageIcon img3 = new ImageIcon("img/3.png");
    private  JButton OK=new JButton("确定");;
    private  JButton Cancel=new JButton("取消");

    public ChooseFrame()
    {
        this.setTitle("选择");
        this.setSize(Setting.frame_width-200, Setting.frame_height-400);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        OK.setBounds(200,280,100,60);
        Cancel.setBounds(300,280,100,60);
        JLabel label1=new JLabel(img1);
        JLabel label2=new JLabel(img2);
        JLabel label3=new JLabel(img3);
        JLabel label4=new JLabel("选择关卡:");
        label4.setBounds(100,50,60,50);
        label1.setBounds(160,50,80,80);
        label2.setBounds(260,50,80,80);
        label3.setBounds(360,50,80,80);
        this.getLayeredPane().add(label1);
        this.getLayeredPane().add(label2);
        this.getLayeredPane().add(label3);
        this.getLayeredPane().add(label4);
        this.getLayeredPane().add(OK);
        this.getLayeredPane().add(Cancel);
        this.setLayout(null);


        OK.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Setting.game_on=true;
            }
        });

        Cancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Setting.choose_on=false;
            }
        });
        label1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Setting.level=1;
                label1.setBounds(140,30,120,120);
                Image image1=img1.getImage();
                image1=image1.getScaledInstance(120,120,Image.SCALE_DEFAULT);
                img1.setImage(image1);
                label1.setIcon(img1);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                label1.setBounds(140,30,120,120);
                Image image1=img1.getImage();
                image1=image1.getScaledInstance(120,120,Image.SCALE_DEFAULT);
                img1.setImage(image1);
                label1.setIcon(img1);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if(Setting.level!=1)
                {
                    label1.setBounds(160,50,80,80);
                    Image image1=img1.getImage();
                    image1=image1.getScaledInstance(80,80,Image.SCALE_DEFAULT);
                    img1.setImage(image1);
                    label1.setIcon(img1);
                }
            }
        });

        label2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Setting.level=2;
                label2.setBounds(240,30,120,120);
                Image image2=img2.getImage();
                image2=image2.getScaledInstance(120,120,Image.SCALE_DEFAULT);
                img2.setImage(image2);
                label2.setIcon(img2);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                label2.setBounds(240,30,120,120);
                Image image2=img2.getImage();
                image2=image2.getScaledInstance(120,120,Image.SCALE_DEFAULT);
                img2.setImage(image2);
                label2.setIcon(img2);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if(Setting.level!=2)
                {
                    label2.setBounds(260,50,80,80);
                    Image image2=img2.getImage();
                    image2=image2.getScaledInstance(80,80,Image.SCALE_DEFAULT);
                    img2.setImage(image2);
                    label2.setIcon(img2);
                }
            }
        });

        label3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Setting.level=3;
                label3.setBounds(340,30,120,120);
                Image image3=img3.getImage();
                image3=image3.getScaledInstance(120,120,Image.SCALE_DEFAULT);
                img3.setImage(image3);
                label3.setIcon(img3);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                label3.setBounds(340,30,120,120);
                Image image3=img3.getImage();
                image3=image3.getScaledInstance(120,120,Image.SCALE_DEFAULT);
                img3.setImage(image3);
                label3.setIcon(img3);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if(Setting.level!=3)
                {
                    label3.setBounds(360,50,80,80);
                    Image image3=img3.getImage();
                    image3=image3.getScaledInstance(80,80,Image.SCALE_DEFAULT);
                    img3.setImage(image3);
                    label3.setIcon(img3);
                }
            }
        });
        this.setVisible(true);
    }
}
