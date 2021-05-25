import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SetFrame extends JFrame {
    public SetFrame() {
        JLabel j1 = new JLabel("游戏难度:");
        JLabel j2 = new JLabel("游戏模式:");

        JRadioButton bu = new JRadioButton("菜鸡");
        JRadioButton bu1 = new JRadioButton("普通");
        JRadioButton bu2 = new JRadioButton("地狱");
        ButtonGroup bGroup = new ButtonGroup();
        bGroup.add(bu);
        bGroup.add(bu1);
        bGroup.add(bu2);
        JRadioButton bu3 = new JRadioButton("经典");
        JRadioButton bu4 = new JRadioButton("无尽");
        ButtonGroup bGroup1 = new ButtonGroup();
        bGroup1.add(bu3);
        bGroup1.add(bu4);
        j1.setBounds(100, 60, 80, 20);
        j2.setBounds(100, 120, 80, 20);
        bu.setBounds(170, 60, 50, 20);
        bu1.setBounds(220, 60, 50, 20);
        bu2.setBounds(270, 60, 50, 20);
        bu3.setBounds(170, 120, 50, 20);
        bu4.setBounds(220, 120, 50, 20);

        JButton ok = new JButton("确定");
        ok.setBounds(100, 250, 80, 30);
        JButton cancle = new JButton("取消");
        cancle.setBounds(190, 250, 80, 30);

        bu.addActionListener(new CaijiListener());
        bu1.addActionListener(new PutongListener());
        bu2.addActionListener(new DiyuListener());
        bu3.addActionListener(new JingdianListener());
        bu4.addActionListener(new WujinListener());
        ok.addActionListener(new OkListener());
        cancle.addActionListener(new CancelListener());


        this.add(bu2);
        this.add(bu1);
        this.add(bu);
        this.add(bu3);
        this.add(bu4);
        this.add(ok);
        this.add(cancle);
        this.add(j2);
        this.add(j1);

        this.setLayout(null);
        this.setResizable(false);
        this.setSize(Setting.frame_width / 2, Setting.frame_height / 2);
        this.setTitle("Setting");
        this.setLocationRelativeTo(null);


        this.setVisible(true);
    }

    static class CaijiListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Setting.difficulty = 1;
            Setting.live = 8;
            BeginFrame.bgm.button();
        }
    }

    static class PutongListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Setting.difficulty = 2;
            Setting.live = 4;
            BeginFrame.bgm.button();
        }
    }

        static class DiyuListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                Setting.difficulty = 3;
                Setting.live = 2;
                BeginFrame.bgm.button();
            }
        }

        static class JingdianListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                Setting.mode = 1;
            }
        }

        static class WujinListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                Setting.mode = 2;
            }
        }

        static class OkListener implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                Setting.set_on = false;
                Setting.begin_on = true;
            }
        }

        static class CancelListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                Setting.mode = 1;
                Setting.difficulty = 4;
                Setting.set_on = false;
                Setting.begin_on = true;
            }
        }
}
