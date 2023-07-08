import com.sun.tools.javac.Main;

import javax.swing.*;

/**
 * 게임에 대한 설명을 보여주는 클래스입니다.
 * @version : 1.1
 * @author  : MsEmily1020
 */
public class ExplanFrame extends CommonFrame {
    public ExplanFrame() {
        super("게임 설명");

        audio("./image/click.wav");

        JButton beforeButton = new JButton("이전");
        add(this.setBounds(beforeButton, 20, 800, 150, 50, 255, 255, 255));
        beforeButton.addActionListener(e -> {
            audio("./image/beforeClick.wav");
            dispose();
            MainFrame.titleClip.close();
            new MainFrame().setVisible(true);
        });

        ImagePanel img = new ImagePanel(new ImageIcon("./image/exp.jpg").getImage());
        add(img);
    }
}
