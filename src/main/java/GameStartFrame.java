import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * 게임을 진행하는 클래스입니다.
 *
 * @author : MsEmily1020
 * @version : 1.1
 */

@Getter
@AllArgsConstructor
class ColorList {
    private int red;
    private int green;
    private int blue;
}

public class GameStartFrame extends CommonFrame implements ActionListener {

    // 메인으로 돌아가는 버튼
    JButton mainButton = new JButton("메인으로");

    // 게임 판 버튼
    JButton[] holeButtons = new JButton[24];

    // player 버튼
    JButton[] playerButton;

    // 벌칙들의 종류가 들어가 있는 list
    ArrayList<String> penaltyArr = new ArrayList<String>();

    // design적 요소
    JLabel titleLabel = new JLabel();
    JLabel penaltyLabel = new JLabel();

    // 벌칙 구멍 랜덤으로 돌림
    int holePenalty = new Random().nextInt(42);

    // 플레이어 순서
    int start = 1;

    // 플레이어 카드들의 이미지
    ImageIcon[] card = new ImageIcon[]{new ImageIcon("./image/red.jpg"),
            new ImageIcon("./image/blue.jpg"),
            new ImageIcon("./image/yellow.jpg"),
            new ImageIcon("./image/green.jpg")};

    // 플레이어 색
    ColorList[] colorLists = new ColorList[]{new ColorList(255, 0, 0)
            , new ColorList(80, 188, 223)
            , new ColorList(255, 255, 0)
            , new ColorList(0, 255, 0)
    };

    // 플레이어 수
    int playersNum = PlayerFrame.playerNameAddList.size();

    public GameStartFrame() {
        super("해적 룰렛");

        // 메인 이동 버튼 및 패널티 값 저장 label
        this.setBounds(mainButton, 350, 500, 200, 100, 255, 255, 255);
        this.setBounds(penaltyLabel, 0, 300, 900, 100, 255, 255, 255);
        penaltyLabel.setHorizontalAlignment(JLabel.CENTER);
        penaltyLabel.setFont(new Font("굴림", Font.BOLD, 25));
        penaltyLabel.setForeground(Color.white);
        add(mainButton).setVisible(false); // 안보이게 했다가 끝났을 경우에만 보이게
        add(penaltyLabel).setVisible(false);

        // 플레이어 수만큼 playerButton 생성
        playerButton = new JButton[playersNum];

        // playerButton 생성
        for (int i = 0; i < playersNum; i++) {
            add(this.setBounds(playerButton[i] = new JButton(PlayerFrame.playerNameAddList.get(i)), 100 + (i + 1) * 180, 7, 150, 50, 255, 255, 255));
            playerButton[i].setBackground(new Color(colorLists[i].getRed(), colorLists[i].getGreen(), colorLists[i].getBlue()));
            playerButton[i].setLocation(100 + i * 180, 7);
        }

        // 순서 LineBorder 생성. 우선 첫번째 버튼이 첫번째 순서임을 나타냄
        playerButton[0].setBorder(new LineBorder(Color.black, 10));

        // playerButton 들이 보여질 title Design
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setForeground(new Color(255, 255, 255));
        add(this.setBounds(titleLabel, 0, 0, 900, 60, 255, 255, 255));
        titleLabel.setIcon(new ImageIcon("./image/titleLabel.jpg"));

        // txt 파일에 있는 penalty 종류들을 하나씩 받아 list에 추가
        try {
            BufferedReader reader = new BufferedReader(new FileReader("image/penalty.txt"));
            String str = null;
            while ((str = reader.readLine()) != null) {
                penaltyArr.add(str);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 게임판 작성
        JPanel buttonPanel = new JPanel(new GridLayout(4, 6, 20, 20));

        for (int i = 0; i < 24; i++) {
            holeButtons[i] = new JButton(""); //이미 선택한 구멍과 선택하지 않은 구멍 차이점 ('-' 유무)
            buttonPanel.add(holeButtons[i]);
            holeButtons[i].setFont(new Font("굴림", Font.BOLD, 100));
            holeButtons[i].addActionListener(this); //해당 버튼 동작 이벤트
        }

        add(this.setBounds(buttonPanel, 0, 60, 900, 800, 0, 102, 0));
    }

    // 게임 진행 리스너
    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < holeButtons.length; i++)
            // 버튼 클릭 값 가져옴.
            if (e.getSource() == holeButtons[i])
                // "" 라는 것은 아직 버튼 선택 안 한 것이라는 말임.
                // 만약 버튼을 클릭했는데 버튼이 아직 클릭 안된 버튼이라면 이라는 뜻
                if (holeButtons[i].getText() == "") {
                    audio("./image/card.wav");
                    holeButtons[i].setText("-"); // 클릭했다는 뜻
                    holeButtons[i].setIcon(card[start - 1]); // 해당 클릭한 버튼에 이미지 입히기
                    playerButton[start - 1].setBorder(new LineBorder(null)); // 다음 플레이어 순서로 이동

                    // 버튼을 클릭했는데 해당 버튼이 벌칙 구멍이라면
                    if (holeButtons[i] == holeButtons[holePenalty]) {
                        audio("./image/penalty.wav");

                        // 게임 판 삭제하기
                        for (int j = 0; j < playerButton.length; j++) playerButton[j].setVisible(false);
                        JOptionPane.showMessageDialog(null, penaltyArr.get(holePenalty) + "을(를) 수행해주세요.");
                        for (int j = 0; j < 24; j++) holeButtons[j].setVisible(false);

                        // 누가 벌칙인지 띄우기
                        //https://heaven0713.tistory.com/28 (라벨 \n 사용하기)
                        penaltyLabel.setVisible(true);
                        penaltyLabel.setText(("<html><body><center>" + PlayerFrame.playerNameAddList.get(start - 1) + "님은 오늘의 주인공! <br><br>" + penaltyArr.get(holePenalty) + "을(를) 수행해주세요.</center></body></html>"));
                        titleLabel.setBounds(0, 0, 900, 900);

                        // 메인으로 돌아가는 버튼 띄우기
                        mainButton.setVisible(true);

                        /**
                         * 메인으로 돌아가는 리스너 입니다
                         */
                        mainButton.addActionListener(e1 -> {
                            audio("./image/click.wav");
                            dispose();
                            TimerFrame.gameAudio.close();
                            new MainFrame().setVisible(true);
                        });

                        titleLabel.setIcon(new ImageIcon("./image/win.jpg"));
                        break;

                    } else if (start != playersNum) {
                        // 다음 플레이어 표시 ++
                        playerButton[start].setBorder(new LineBorder(Color.black, 7));
                        start++;
                    } // 벌칙 구멍이 아니고 마지막 순서가 아닐 경우

                    else {
                        // 다시 첫번째 플레이어 순서임을 표시 1
                        playerButton[0].setBorder(new LineBorder(Color.black, 7));
                        start = 1;
                    } // 벌칙 구멍이 아니고 마지막 순서일 경우

                }
    }
}
