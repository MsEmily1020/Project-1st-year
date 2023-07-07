import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * 플레이어 설정하는 클래스입니다.
 * @version : 1.1
 * @author  : MsEmily1020
 */
public class PlayerFrame extends CommonFrame {
    JTextField playerNameTextField = new JTextField();

    public PlayerFrame() {
        super("플레이어 설정");

        //플레이어 입력창
        playerNameTextField.setHorizontalAlignment(JTextField.CENTER);
        add(this.setBounds(playerNameTextField, 200, 230, 500, 300, 255, 255, 255));
        playerNameTextField.setFont(new Font("굴림", Font.PLAIN, 50));

        // 플레이어 추가, 삭제, 확인 버튼
        JButton playerAddBtn = new JButton("추가");
        JButton playerCheckBtn = new JButton("확인");
        JButton playerRemoveBtn = new JButton("삭제");

        // 이전 화면으로 돌아가는 버튼
        JButton beforeBtn = new JButton("이전");

        // 게임 시작 화면으로 넘어가는 버튼
        JButton gameStartBtn = new JButton("게임 시작");

        add(this.setBounds(playerAddBtn, 185, 550, 150, 50, 255, 0, 0));
        add(this.setBounds(playerCheckBtn, 367, 550, 150, 50, 255, 0, 0));
        add(this.setBounds(playerRemoveBtn, 550, 550, 150, 50, 255, 0, 0));

        // 플레이어가 모두 지정되었을 때만 게임이 시작 되어야함. 따라서 초반에는 게임 시작 버튼을 안 보이게 지정해둠.
        add(this.setBounds(gameStartBtn, 730, 800, 150, 50, 255, 255, 255)).setVisible(false);
        add(this.setBounds(beforeBtn, 20, 800, 150, 50, 255, 255, 255));

        // 추가 버튼을 눌렀을 때 addBtnListener 실행하기
        playerAddBtn.addActionListener(addBtnListener);

        //이미지 넣기
        ImagePanel img = new ImagePanel(new ImageIcon("./image/player.jpg").getImage());
        add(img);

    }

    /**
     * 추가 버튼을 눌렀을 때 실행하는 리스너입니다.
     */
    ActionListener addBtnListener = e -> {
        audio("./image/click.wav"); // 클릭했을 때 효과음

        String playerNameText = playerNameTextField.getText().trim();

        // 입력한 이름이 공백일 경우 공백은 불가능하다는 메세지 띄우기
        if(playerNameText.length() == 0) {
            JOptionPane.showMessageDialog(null, "공백은 입력불가합니다.");
            return;
        }
    };
}
