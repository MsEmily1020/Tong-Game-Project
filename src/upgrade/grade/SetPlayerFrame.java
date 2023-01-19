package upgrade.grade;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class SetPlayerFrame extends CommonFrame {
	JTextField textField = new JTextField();
	
	public static ArrayList<String> playerNameAdd = new ArrayList<String>();
	public static int playersNum;
	
	JButton playerAdd = new JButton("추가");
	JButton playerCheck = new JButton("확인");
	JButton playerRemove = new JButton("삭제");
	JButton beforeButton = new JButton("이전");
	JButton gameStartButton = new JButton("게임 시작");
	
	String[] sameName = {"A", "B", "C"};
	
	Clip clickClip;        //https://pgtd.tistory.com/269
	Clip collectClickClip; //https://www.sellbuymusic.com/search/soundeffect 
	Clip errorClickClip;   //https://www.sellbuymusic.com/search/soundeffect
	Clip beforeClickClip;  //https://www.soundgator.com/

	public SetPlayerFrame() {
		super(900, 900, "플레이어설정");
		playerNameAdd.clear();
		playersNum = 0;
		
		//플레이어 입력창
		textField.setHorizontalAlignment(JTextField.CENTER);
		add(this.setBounds(textField, 200, 230, 500, 300, 255, 255, 255));
		textField.setFont(new Font("굴림", Font.PLAIN, 50));

		//플레이어 추가
		add(this.setBounds(playerAdd, 185, 550, 150, 50, 255, 0, 0));
		playerAdd.addActionListener(addBt);

		//플레이어 체크(2~4명인지)
		add(this.setBounds(playerCheck, 367, 550, 150, 50, 255, 0, 0));
		playerCheck.addActionListener(checkBt);

		//플레이어 삭제
		add(this.setBounds(playerRemove, 550, 550, 150, 50, 255, 0, 0));
		playerRemove.addActionListener(removeBt);

		//게임시작
		add(this.setBounds(gameStartButton, 730, 800, 150, 50, 255, 255, 255)).setVisible(false);
		gameStartButton.addActionListener(gameStart);
		
		//메인으로
		add(this.setBounds(beforeButton, 20, 800, 150, 50, 255, 255, 255));
		beforeButton.addActionListener(beforeBt);

		//이미지 넣기
		ImagePanel img = new ImagePanel(new ImageIcon("./image/player.jpg").getImage());
		add(img);
	}

	//플레이어 추가
	ActionListener addBt = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			clickClip = Audio("./image/click.wav", clickClip);
			gameStartButton.setVisible(false); //반드시 확인버튼을 눌러야 함

			//빈칸일 경우
			if(textField.getText().trim().length() == 0) JOptionPane.showMessageDialog(null, "공백은 입력불가합니다.");

			else {
				playerNameAdd.add(textField.getText().trim());

				if(Collections.frequency(playerNameAdd, textField.getText().trim()) >= 2) { //중복 값이 2개 이상인 경우
					int yesOrNo = JOptionPane.showConfirmDialog(null, "동명이인이 추가되었습니다.", "정말로 추가하시겠습니까?", JOptionPane.YES_NO_OPTION);

					if(yesOrNo == JOptionPane.YES_OPTION) {
						String equals = textField.getText().trim();
						for(int i = 0; i < sameName.length; i++) {
							equals = textField.getText().trim() + sameName[i];
							if(Collections.frequency(playerNameAdd, equals) == 0) { 
								playerNameAdd.add(equals);
								playerNameAdd.remove(textField.getText().trim());
								break;
							}
						}
						JOptionPane.showMessageDialog(null, "지금 추가된 값은 " + equals + "으로 저장됩니다."); //동명이인 추가 저장
					}
				}

				else JOptionPane.showMessageDialog(null, textField.getText().trim() + "님이 추가되었습니다."); //동명이인x

				if(playerNameAdd.size() == 4) { //리스트 총 길이 4명이하인지
					JOptionPane.showMessageDialog(null, "최대 4명까지입니다. 혹시 변경하고 싶다면 삭제 후 추가 눌러주세요.");
					playerAdd.setVisible(false);
				}

			}
		}
	};

	//플레이어 체크(2~4명)
	ActionListener checkBt = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(playerNameAdd.size() < 2) {//리스트 총 길이 2명이상인지
				JOptionPane.showMessageDialog(null, "최소 2명 입력해주세요.");
				errorClickClip = Audio("./image/error.wav", errorClickClip);
			}
			//프로그램에서 원하는 값으로 플레이어 수가 정해졌을 때(2~4명 사이로)
			else {
				collectClickClip = Audio("./image/correct.wav", collectClickClip);
				gameStartButton.setVisible(true); //인원 수가 조건에 맞을 경우에만 게임 시작 버튼 보이게
				JOptionPane.showMessageDialog(null, "확인되었습니다.");
				Collections.shuffle(playerNameAdd); //배열 순서 무작위 돌리기
				playersNum = playerNameAdd.size();
			}
		}
	};

	//플레이어 삭제
	ActionListener removeBt = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			//list안에 입력한 값이 있는지 없는지 확인
			if(playerNameAdd.contains(textField.getText().trim())) {
				collectClickClip = Audio("./image/correct.wav", collectClickClip);
				playerNameAdd.remove(textField.getText().trim());
				JOptionPane.showMessageDialog(null, textField.getText().trim() + "삭제되었습니다.");
				gameStartButton.setVisible(false); //반드시 확인버튼을 눌러야 함
			}

			else {
				errorClickClip = Audio("./image/error.wav", errorClickClip);
				JOptionPane.showMessageDialog(null, textField.getText().trim() + "는 존재하지 않습니다.");
			}

			if(playerNameAdd.size() == 3)
				playerAdd.setVisible(true);
		}
	};

	//이전 버튼
	ActionListener beforeBt = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(playerNameAdd.size() >= 1) { 
				int before = JOptionPane.showConfirmDialog(null, "예를 누르시면 리셋된 채로 메인화면으로 나가집니다", "경고 ) 추가된 사람이 있습니다.", JOptionPane.YES_NO_OPTION);
				if(before == JOptionPane.NO_OPTION) return;
			}
			
			beforeClickClip = Audio("./image/beforeClick.wav", beforeClickClip);
			dispose();
			new MainFrame().setVisible(true);
		}
	};
	
	//게임 시작
	ActionListener gameStart = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			new TimerFrame().setVisible(true);
		}
	};
}