import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * 플레이어 설정하는 클래스입니다.
 * @version : 1.1
 * @author  : MsEmily1020
 */
public class PlayerFrame extends CommonFrame {

    public static ArrayList<String> playerNameAddList = new ArrayList<String>();
    JTextField playerNameTextField = new JTextField();

    // 플레이어 추가, 삭제, 확인 버튼
    JButton playerAddBtn = new JButton("추가");
    JButton playerCheckBtn = new JButton("확인");
    JButton playerRemoveBtn = new JButton("삭제");

    // 이전 화면으로 돌아가는 버튼
    JButton beforeBtn = new JButton("이전");

    // 게임 시작 화면으로 넘어가는 버튼
    JButton gameStartBtn = new JButton("게임 시작");

    public PlayerFrame() {
        super("플레이어 설정");

        //플레이어 입력창
        playerNameTextField.setHorizontalAlignment(JTextField.CENTER);
        add(this.setBounds(playerNameTextField, 200, 230, 500, 300, 255, 255, 255));
        playerNameTextField.setFont(new Font("굴림", Font.PLAIN, 50));

        add(this.setBounds(playerAddBtn, 185, 550, 150, 50, 255, 0, 0));
        add(this.setBounds(playerCheckBtn, 367, 550, 150, 50, 255, 0, 0));
        add(this.setBounds(playerRemoveBtn, 550, 550, 150, 50, 255, 0, 0));

        // 플레이어가 모두 지정되었을 때만 게임이 시작 되어야함. 따라서 초반에는 게임 시작 버튼을 안 보이게 지정해둠.
        add(this.setBounds(gameStartBtn, 730, 800, 150, 50, 255, 255, 255)).setVisible(false);
        add(this.setBounds(beforeBtn, 20, 800, 150, 50, 255, 255, 255));

        // 추가 버튼을 눌렀을 때 addBtnListener 실행하기
        playerAddBtn.addActionListener(addBtnListener);
        
        // 삭제 버튼을 눌렀을 때 removeBtnListener 실행하기
        playerRemoveBtn.addActionListener(removeBtnListener);

        //이미지 넣기
        ImagePanel playerFrameBackgroundImage = new ImagePanel(new ImageIcon("./image/player.jpg").getImage());
        add(playerFrameBackgroundImage);

    }

    /**
     * 플레이어 추가 버튼을 눌렀을 때 실행하는 리스너입니다.
     */
    ActionListener addBtnListener = e -> {
        audio("./image/click.wav"); // 클릭했을 때 효과음

        String playerNameText = playerNameTextField.getText().trim();

        // 입력한 이름이 공백일 경우 공백은 불가능하다는 메세지 띄우기
        if(playerNameText.length() == 0) {
            JOptionPane.showMessageDialog(null, "공백은 입력불가합니다.");
            return;
        }

        playerNameAddList.add(playerNameText);

        playerNameAddList.stream().forEach(System.out::println);
        // 중복값이 있을 경우
        if(Collections.frequency(playerNameAddList, playerNameText) >= 2) {

            // 동명이인이 추가되었다는 메세지와 함께 yes or no를 선택할 수 있는 confirm창 띄우기 -> yes일 경우 동명이인 처리하기
            if(JOptionPane.showConfirmDialog(null,
                    "동명이인이 추가되었습니다.",
                    "정말로 추가하시겠습니까?",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

                String[] sameCheckName = "A,B,C".split(",");

                // 플레이어는 4명이 최대이므로 모두 동명이인으로 처리한다해도 기본, A, B, C 까지만 있으면 되고
                // A를 붙인 사람이 없으면 A를 붙이고, B를 붙인 사람이 없으면 B.. 이런식으로 하나하나씩 대입하며 동명이인이 없도록 로직 구현함.
                for (String name : sameCheckName) {
                    String equalCheckName = playerNameText + name;
                    if (!playerNameAddList.contains(equalCheckName)) {
                        playerNameAddList.add(equalCheckName);
                        playerNameAddList.remove(playerNameText);
                        JOptionPane.showMessageDialog(null, "지금 추가된 값은 " + equalCheckName + "으로 저장됩니다.");
                        break;
                    }
                }

            }
        }

        // 동명이인이 아닐 경우
        else {
            JOptionPane.showMessageDialog(null, "지금 추가된 값은 " + playerNameText + "으로 저장됩니다.");
        }

        // 최대 인원 수는 4명이므로 4명이라면 더는 추가 못하도록 막음.
        if(playerNameAddList.size() == 4) {
            JOptionPane.showMessageDialog(null, "최대 4명까지입니다. 혹시 변경하고 싶다면 삭제 후 추가 눌러주세요.");
            playerAddBtn.setVisible(false);
        }
    };

    /**
     * 플레이어 삭제 버튼을 눌렀을 때 실행하는 리스너입니다.
     */
    ActionListener removeBtnListener = e -> {
        
    };
}
