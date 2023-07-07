import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.io.File;


public class CommonFrame extends JFrame {
    public CommonFrame(String title) {
        setTitle(title);
        setSize(900, 900);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
    }

    JComponent setBounds(JComponent comp, int x, int y, int width, int height, int r, int g, int b) {
        comp.setBounds(x, y, width, height);
        comp.setFont(new Font("굴림", Font.PLAIN, 20));
        comp.setBackground(new Color(r, g, b));
        return comp;
    }

    public Clip audio(String filePath) {
        try {
            File file = new File(filePath);
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(file));
            clip.loop(0);
            return clip;
        } catch (Exception e) {
            System.err.println("파일을 찾을 수 없습니다.");
            return null;
        }
    }
}