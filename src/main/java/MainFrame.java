import com.sun.tools.javac.Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * 메인화면을 구성하는 클래스입니다.
 * @version : 1.1
 * @author  : MsEmily1020
 */
public class MainFrame extends CommonFrame {
    public MainFrame() {
        super("해적룰렛");

        // 메인 화면을 구성하는 버튼 3가지 (게임 시작, 설명, 종료)
        JButton startBtn = new JButton("게임 시작");
        JButton explainBtn = new JButton("게임 설명");
        JButton endBtn = new JButton("게임 종료");

        add(this.setBounds(startBtn, 125, 570, 200, 100, 0, 255, 0));
        add(this.setBounds(explainBtn, 550, 570, 200, 100, 255, 0, 0));
        add(this.setBounds(endBtn, 338, 700, 200, 100, 255, 255, 255));

        // 게임 시작 버튼을 눌렀을 경우 플레이어를 정하는 화면으로 이동
        startBtn.addActionListener(e -> {
            // TODO : 플레이어를 정하는 화면으로 이동하기
        });

        // 게임 설명 버튼을 눌렀을 경우 게임 설명이 있는 화면으로 이동하기
        explainBtn.addActionListener(e -> {
            // TODO : 게임 설명하는 화면으로 이동하기
        });

        // 게임 종료 버튼을 눌렀을 경우 게임 종료하기
        endBtn.addActionListener(e -> {
            System.exit(0);
        });

        // 메인 화면 이미지
        ImagePanel img = new ImagePanel(new ImageIcon("./image/title.jpg").getImage());
        add(img);
    }

    public static void main(String[] args) {
        new MainFrame().setVisible(true);
    }
}
