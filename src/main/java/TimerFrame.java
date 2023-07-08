import javax.swing.*;
import java.awt.*;
import java.util.TimerTask;
import java.util.Timer;

public class TimerFrame extends CommonFrame {
    public TimerFrame() {
        super("타이머");

        MainFrame.titleClip.close();
        audio("./image/click.wav");
        audio("./image/count.wav");

        // 3, 2, 1 카운트 gif 넣기
        JLabel label = new JLabel(new ImageIcon("./image/321.gif"));
        label.setBounds(0, 0, 900, 900);
        add(label);

        //https://ridd-coding.tistory.com/74
        Timer time = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                audio("./image/bell.wav");
                audio("./image/gameBgm.wav");
                dispose();
                new GameStartFrame().setVisible(true);
            }
        };

        time.schedule(task, 2400);

    }
}
