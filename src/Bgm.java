import javax.sound.sampled.*;
import java.io.IOException;
import java.io.InputStream;

public class Bgm {
    static Bgm bgmInstance = new Bgm();

    Clip bgm;
    InputStream bgFile;
    AudioInputStream bgAis;

    Clip menuBgm;
    InputStream menuBgFile;
    AudioInputStream menuBgAis;

    Clip iron;
    InputStream ironFile;
    AudioInputStream ironAis;

    Clip wall1;
    InputStream wall1File;
    AudioInputStream wall1Ais;

    Clip attack;
    InputStream attackFile;
    AudioInputStream attackAis;

    Clip gameover;
    InputStream gameoverFile;
    AudioInputStream gameoverAis;

    Clip button;
    InputStream buttonFile;
    AudioInputStream buttonAis;

    Clip broke;
    InputStream brokeFile;
    AudioInputStream brokeAis;
    private Bgm(){
    }
    public static Bgm getInstance(){
        return bgmInstance;
    }
    //加载BGM
    public void loadBGM() throws LineUnavailableException, UnsupportedAudioFileException, IOException {
        //加载BGM
        bgm = AudioSystem.getClip();
        bgFile = this.getClass().getClassLoader().getResourceAsStream("bgm.wav");
        assert bgFile != null;
        bgAis = AudioSystem.getAudioInputStream(bgFile);
        bgm.open(bgAis);
        //加载主菜单bgm
        menuBgm = AudioSystem.getClip();
        menuBgFile = this.getClass().getClassLoader().getResourceAsStream("menuBgm.wav");
        assert menuBgFile != null;
        menuBgAis = AudioSystem.getAudioInputStream(menuBgFile);
        menuBgm.open(menuBgAis);
        //攻击
        attack = AudioSystem.getClip();
        attackFile = this.getClass().getClassLoader().getResourceAsStream("attack.wav");
        assert attackFile != null;
        attackAis = AudioSystem.getAudioInputStream(attackFile);
        attack.open(attackAis);
        //gameover
       gameover = AudioSystem.getClip();
        gameoverFile = this.getClass().getClassLoader().getResourceAsStream("over.wav");
        assert gameoverFile != null;
        gameoverAis = AudioSystem.getAudioInputStream(gameoverFile);
       gameover.open(gameoverAis);
        //按钮
        button = AudioSystem.getClip();
        buttonFile = this.getClass().getClassLoader().getResourceAsStream("button.wav");
        assert buttonFile != null;
        buttonAis = AudioSystem.getAudioInputStream(buttonFile);
        button.open(buttonAis);
        //毁坏
        broke = AudioSystem.getClip();
        brokeFile = this.getClass().getClassLoader().getResourceAsStream("broke.wav");
        assert brokeFile != null;
        brokeAis = AudioSystem.getAudioInputStream(brokeFile);
        broke.open(brokeAis);
        //打铁
        iron = AudioSystem.getClip();
        ironFile = this.getClass().getClassLoader().getResourceAsStream("iron.wav");
        assert ironFile != null;
        ironAis = AudioSystem.getAudioInputStream(ironFile);
        iron.open(ironAis);
        //打墙
       wall1 = AudioSystem.getClip();
       wall1File = this.getClass().getClassLoader().getResourceAsStream("wall1.wav");
       assert wall1File != null;
       wall1Ais = AudioSystem.getAudioInputStream(wall1File);
       wall1.open(wall1Ais);
    }
    //游戏内部的BGM的播放
    public void playBGM(){
        bgm.loop(Clip.LOOP_CONTINUOUSLY);
        menuBgm.stop();
    }
    //菜单界面的BGM的播放
    public void playMenuBGM(){
        menuBgm.loop(Clip.LOOP_CONTINUOUSLY);
        bgm.stop();
    }
    //攻击
    public void attack(){ attack.loop(1); }
    //gameover
    public void gameover(){gameover.loop(1); }
    //打铁
    public void iron(){ iron.loop(1); }
    //    打墙
    public void wall(){ wall1.loop(1); }
    //按钮
    public void button(){
        button.loop(1);
    }
    //毁坏
    public void broke(){
       broke.loop(1);
    }
}
