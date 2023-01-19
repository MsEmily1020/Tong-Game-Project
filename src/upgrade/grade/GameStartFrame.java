package upgrade.grade;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class GameStartFrame extends CommonFrame implements ActionListener {
	Clip penaltyClip;	   //https://drive.google.com/file/d/1lUNLybu6AJB06vplh5KbsvxEZMiubQU3/view
	Clip cardClip;		   //https://drive.google.com/drive/folders/17FSmgq-mto_L5z-oYiyG83jzo8Ksiwt6
	Clip clickClip;        //https://pgtd.tistory.com/269

	JButton mainButton = new JButton("메인으로");
	JButton[] holeButtons = new JButton[24];
	JButton[] playerButton;

	ArrayList<String> penaltyArr = new ArrayList<String>();

	JLabel titleLabel = new JLabel();
	JLabel penaltyLabel = new JLabel();

	int holePenalty;
	int start = 1;

	ImageIcon[] card = new ImageIcon[] { new ImageIcon("./image/red.jpg"), new ImageIcon("./image/blue.jpg"), new ImageIcon("./image/yellow.jpg"), new ImageIcon("./image/green.jpg") };

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

		Random rd = new Random();

		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader("image/penalty.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		String str;
		try {
			while ((str = reader.readLine()) != null) {
				penaltyArr.add(str);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		holePenalty = rd.nextInt(24);

		for(int i = 0; i < 24; i++) {
			holeButtons[i] = new JButton(""); //이미 선택한 구멍과 선택하지 않은 구멍 차이점 ('-' 유무)
			buttonPanel.add(holeButtons[i]);
			holeButtons[i].setFont(new Font("굴림", Font.BOLD, 100));
			holeButtons[i].addActionListener(this); //해당 버튼 동작 이벤트
		}

		add(buttonPanel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for(int i = 0; i < holeButtons.length; i++)
			if(e.getSource() == holeButtons[i]) //클릭한 값 갖고옴
				if(holeButtons[i].getText() == "") {
					cardClip = Audio("./image/card.wav", cardClip);
					holeButtons[i].setText("-");
					holeButtons[i].setIcon(card[start - 1]);
					playerButton[start - 1].setBorder(new LineBorder(null));

					//벌칙구멍과 선택한 구멍이 동일한 경우
					if(holeButtons[i] == holeButtons[holePenalty]) {
						TimerFrame.gameClip.close();
						penaltyClip = Audio("./image/penalty.wav", penaltyClip);
						for(int j = 0; j < playerButton.length; j++) playerButton[j].setVisible(false);
						JOptionPane.showMessageDialog(null, penaltyArr.get(holePenalty) + "을(를) 수행해주세요.");
						for(int j = 0; j < 24; j++) holeButtons[j].setVisible(false);
						//https://heaven0713.tistory.com/28 (라벨 \n 사용하기)
						penaltyLabel.setVisible(true);
						penaltyLabel.setText(("<html><body><center>" + SetPlayerFrame.playerNameAdd.get(start - 1) + "님은 오늘의 주인공! <br><br>" + penaltyArr.get(holePenalty) + "을(를) 수행해주세요.</center></body></html>"));
						titleLabel.setBounds(0, 0, 900, 900);
						mainButton.setVisible(true);
						mainButton.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								clickClip = Audio("./image/click.wav", clickClip);
								dispose();
								MainFrame frame = new MainFrame();
								frame.setAudio();
								frame.setVisible(true);
							}
						});
						titleLabel.setIcon(new ImageIcon("./image/win.jpg"));
						break;
					}

					else
						if(start != SetPlayerFrame.playersNum) { playerButton[start].setBorder(new LineBorder(Color.black, 7)); start++; } //마지막 순서가 아닐 경우
						else { playerButton[0].setBorder(new LineBorder(Color.black, 7)); start = 1; } //마지막 순서일 경우
				} //이미 선택한 구멍과 선택하지 않은 구멍 차이점 ('-' 유무)
	}


}
