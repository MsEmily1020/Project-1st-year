import com.sun.tools.javac.Main;

import javax.swing.*;

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
