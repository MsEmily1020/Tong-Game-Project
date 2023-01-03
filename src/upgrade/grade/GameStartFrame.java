package upgrade.grade;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class GameStartFrame extends CommonFrame {
	JButton mainButton = new JButton("메인으로");
	JButton[] holeButtons = new JButton[24];
	JButton[] playerButton;

	JLabel titleLabel = new JLabel();
	JLabel penaltyLabel = new JLabel();

	int holePenalty;
	int start = 1;
	
	JPanel buttonPanel = new JPanel(new GridLayout(4, 6, 20, 20));
	
	public GameStartFrame() {
		super(900, 900, "게임 시작");
		int playersNum = SetPlayerFrame.playersNum;
		
		this.setBounds(mainButton, 350, 500, 200, 100, 255, 255, 255);
		this.setBounds(penaltyLabel, 0, 300, 900, 100, 255, 255, 255);
		penaltyLabel.setHorizontalAlignment(JLabel.CENTER);
		penaltyLabel.setFont(new Font("굴림", Font.BOLD, 25));
		penaltyLabel.setForeground(Color.white);
		add(mainButton).setVisible(false); //안보이게 했다가 끝났을 경우에만 보이게
		add(penaltyLabel).setVisible(false);

		playerButton = new JButton[playersNum];

		for(int i = 0; i < playersNum; i++)
			add(this.setBounds(playerButton[i] = new JButton(SetPlayerFrame.playerNameAdd.get(i)), 100 + (i + 1) * 180, 7, 150, 50, 255, 255, 255));

		playerButton[0].setBackground(new Color(255, 0, 0));
		playerButton[1].setBackground(new Color(80, 188, 223));
		if(playersNum == 3) {
			playerButton[2].setBackground(new Color(255, 255, 0));
			for(int i = 0; i < playersNum; i++)
				playerButton[i].setLocation((i + 1) * 180, 7);
		}
		
		if(playersNum == 4) {
			playerButton[2].setBackground(new Color(255, 255, 0));
			playerButton[3].setBackground(new Color(0, 255, 0));
			for(int i = 0; i < playersNum; i++)
				playerButton[i].setLocation(100 + i * 180, 7);
		}

		playerButton[0].setBorder(new LineBorder(Color.black, 10)); //첫번째 순서

		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		titleLabel.setForeground(new Color(255, 255, 255));
		add(this.setBounds(titleLabel, 0, 0, 900, 60, 255, 255, 255));
		titleLabel.setIcon(new ImageIcon("./image/titleLabel.jpg"));

		add(this.setBounds(buttonPanel, 0, 60, 900, 800, 0, 102, 0));

		for(int i = 0; i < 24; i++) {
			holeButtons[i] = new JButton(""); //이미 선택한 구멍과 선택하지 않은 구멍 차이점 ('-' 유무)
			buttonPanel.add(holeButtons[i]);
			holeButtons[i].setFont(new Font("굴림", Font.BOLD, 100));
		}

		add(buttonPanel);
	}
}
