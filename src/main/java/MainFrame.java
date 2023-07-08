import javax.sound.sampled.Clip;
import javax.swing.*;

/**
 * 메인화면을 구성하는 클래스입니다.
 * @version : 1.1
 * @author  : MsEmily1020
 */
public class MainFrame extends CommonFrame {

    // titleClip은 플레이어를 설정하는 화면에서도 나와야 하는 음성 파일이기 때문에 전역변수로 설정함.
    public static Clip titleClip;		   //https://pixabay.com/ko/music/search/genre/%EB%B9%84%EB%94%94%EC%98%A4%20%EA%B2%8C%EC%9E%84/

    public MainFrame() {
        super("해적룰렛");

        audio("./image/open.wav"); // 게임 프로그램을 열었을 때 효과음

        // 메인 오디오 (무한 반복으로 설정)
        titleClip = audio("./image/titleClip.wav");
        titleClip.loop(Clip.LOOP_CONTINUOUSLY);

        // 메인 화면을 구성하는 버튼 3가지 (게임 시작, 설명, 종료)
        JButton startBtn = new JButton("게임 시작");
        JButton explainBtn = new JButton("게임 설명");
        JButton endBtn = new JButton("게임 종료");

        add(this.setBounds(startBtn, 125, 570, 200, 100, 0, 255, 0));
        add(this.setBounds(explainBtn, 550, 570, 200, 100, 255, 0, 0));
        add(this.setBounds(endBtn, 338, 700, 200, 100, 255, 255, 255));

        // 게임 시작 버튼을 눌렀을 경우 플레이어를 정하는 화면으로 이동
        startBtn.addActionListener(e -> {
            dispose();
            new PlayerFrame().setVisible(true);
        });

        // 게임 설명 버튼을 눌렀을 경우 게임 설명이 있는 화면으로 이동하기
        explainBtn.addActionListener(e -> {
            dispose();
            new ExplanFrame().setVisible(true);
        });

        // 게임 종료 버튼을 눌렀을 경우 게임 종료하기
        endBtn.addActionListener(e -> {
            System.exit(0);
        });

        // 메인 화면 이미지
        ImagePanel mainFrameBackgroundImage = new ImagePanel(new ImageIcon("./image/title.jpg").getImage());
        add(mainFrameBackgroundImage);
    }

    public static void main(String[] args) {
        new MainFrame().setVisible(true);
    }
}
