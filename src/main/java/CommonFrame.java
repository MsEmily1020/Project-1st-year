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

//패널에 그림을 올려주는 클래스
//https://eunbc-2020.tistory.com/54
class ImagePanel extends JPanel {
    private Image img;

    public ImagePanel(Image img) {
        this.img = img;
        setSize(new Dimension(img.getWidth(null), img.getHeight(null)));
        setPreferredSize(new Dimension(img.getWidth(null), img.getHeight(null)));
        setLayout(null);
    }

    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, null);
    }
}
