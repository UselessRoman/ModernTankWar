import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class BeginFrame extends JFrame
{
    private JButton button_play;
    private JButton button_set;
    private JButton button_quit;
    private JButton button_server;
    private JButton button_client;
    static Bgm bgm=Bgm.getInstance();

    public BeginFrame()
    {
        this.setResizable(false);
        this.setSize(Setting.frame_width, Setting.frame_height);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("TankWar");
        this.setLayout(null);

        this.button_play=new JButton("Play");
        this.button_set=new JButton("Set");
        this.button_quit=new JButton("Quit");
        this.button_server=new JButton("Server");
        this.button_client=new JButton("Client");

        button_play.setBounds(325,300,150,75);
        button_play.setBackground(Color.GRAY);
        button_play.setFocusPainted(false);
        button_play.setForeground(Color.cyan);
        button_set.setBounds(325,375,150,75);
        button_set.setBackground(Color.GRAY);
        button_set.setFocusPainted(false);
        button_set.setForeground(Color.cyan);
        button_quit.setBounds(325,450,150,75);
        button_quit.setBackground(Color.GRAY);
        button_quit.setFocusPainted(false);
        button_quit.setForeground(Color.cyan);
        button_server.setBounds(325,525,150,75);
        button_server.setBackground(Color.GRAY);
        button_server.setFocusPainted(false);
        button_server.setForeground(Color.cyan);
        button_client.setBounds(325,600,150,75);
        button_client.setBackground(Color.GRAY);
        button_client.setFocusPainted(false);
        button_client.setForeground(Color.cyan);

        this.getLayeredPane().add(button_play);
        this.getLayeredPane().add(button_server);
        this.getLayeredPane().add(button_client);
        this.getLayeredPane().add(button_set);
        this.getLayeredPane().add(button_quit);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        try {
            bgm.loadBGM();
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
        bgm.playMenuBGM();
        button_play.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent e)
            {
                Setting.choose_on=true;
            }
            public void mouseReleased(MouseEvent e)
            {
                Setting.begin_on=false;

            }
        });
        button_server.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent e)
            {
                Setting.server_on=true;
            }
            public void mouseReleased(MouseEvent e)
            {
                Setting.server_on=false;

            }
        });
        button_client.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent e)
            {
                Setting.client_on=true;
            }
            public void mouseReleased(MouseEvent e)
            {
                Setting.client_on=false;

            }
        });

        button_set.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent e)
            {
                Setting.set_on=true;
            }
            public void mouseReleased(MouseEvent e)
            {
                Setting.begin_on=false;
            }
        });

        button_quit.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent e)
            {
                System.exit(0);
            }
            public void mouseReleased(MouseEvent e) {}
        });
    }

    public void paint(Graphics g)
    {
        button_play.requestFocus();
        button_set.requestFocus();
        button_quit.requestFocus();
    }

}