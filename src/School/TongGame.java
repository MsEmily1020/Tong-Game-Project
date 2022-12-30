package School;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class TongGame extends Main implements ActionListener {
	Clip open;			   //https://pixabay.com/ko/music/search/genre/%EB%B9%84%EB%94%94%EC%98%A4%20%EA%B2%8C%EC%9E%84/
	Clip titleClip;		   //https://pixabay.com/ko/music/search/genre/%EB%B9%84%EB%94%94%EC%98%A4%20%EA%B2%8C%EC%9E%84/
	Clip clickClip;        //https://pgtd.tistory.com/269
	Clip beforeClickClip;  //https://www.soundgator.com/
	Clip collectClickClip; //https://www.sellbuymusic.com/search/soundeffect 
	Clip errorClickClip;   //https://www.sellbuymusic.com/search/soundeffect
	Clip count;			   //https://youtubelab.tistory.com/34
	Clip bell;			   //https://pgtd.tistory.com/225
	Clip cardClip;		   //https://drive.google.com/drive/folders/17FSmgq-mto_L5z-oYiyG83jzo8Ksiwt6
	Clip gameClip;		   //https://pixabay.com/ko/music/search/genre/%EB%B9%84%EB%94%94%EC%98%A4%20%EA%B2%8C%EC%9E%84/
	Clip penaltyClip;	   //https://drive.google.com/file/d/1lUNLybu6AJB06vplh5KbsvxEZMiubQU3/view
	
	JPanel expPanel = new JPanel();
	JPanel mainPanel = new JPanel();
	JPanel startPersonPanel = new JPanel();
	JPanel gamePanel = new JPanel();
	JPanel buttonPanel = new JPanel();

	JTextField textField = new JTextField();
	ArrayList<String> playerNameAdd = new ArrayList<String>();

	JButton playerAdd = new JButton("추가");
	JButton playerCheck = new JButton("확인");
	JButton playerRemove = new JButton("삭제");
	JButton beforeButton = new JButton("이전");
	JButton gameStartButton = new JButton("게임 시작");
	JButton startButton = new JButton("게임 시작");
	JButton endButton = new JButton("게임 종료");
	JButton expButton = new JButton("게임 설명");
	JButton mainButton = new JButton("메인으로");
	JButton[] holeButtons = new JButton[24];
	JButton[] playerButton;

	JLabel titleLabel = new JLabel();
	JLabel penaltyLabel = new JLabel();

	String[] sameName = {"A", "B", "C"};
	String[] penalty = new String[] 
			{ "생각하는 의지로 10초 버티기", 
					"벌칙자:피리불기 왼쪽사람:뱀 역할극",
					"모르는 사람한테 '나 이뻐?' 물어보기",
					"칠판에 만세 따라그리기",
					"눈물셀카찍기",
					"개다리춤추기",
					"곰 세마리 노래로 랩하기",
					"모델걸음+무표정",
					"대걸레잡고 섹시포즈",
					"퉁퉁이 성대모사하기",
					"담임선생님 허그하기",
					"대걸레 잡고 노래부르기",
					"복도에서 인어공주포즈",
					"세번 절하기",
					"플랭크 10초동안 하기",
					"김연아의 트리플악셀",
					"간지럼피기",
					"코끼리코 10바퀴 돌고 앞에 사람보고 고백하기",
					"빗자루로 기타치기",
					"빗자루타고 '아이캔플라이'외치기",
					"세일러문 등장 따라하기",
					"다음 게임 흑기사 해주기",
					"소원 하나씩 이루어주기",
					"가마 태우기"
			};

	ImageIcon[] card = new ImageIcon[] { new ImageIcon("./image/red.jpg"), new ImageIcon("./image/blue.jpg"), new ImageIcon("./image/yellow.jpg"), new ImageIcon("./image/green.jpg") };

	int playersNum;
	int holePenalty;
	int start = 1;

	public TongGame() {
		super(900, 900, "해적룰렛");
		open = Audio("./image/open.wav", open);
		titleClip = Audio("./image/titleClip.wav", titleClip);
		titleClip.loop(Clip.LOOP_CONTINUOUSLY);

		add(mainPanel());
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickClip = Audio("./image/click.wav", clickClip);
				add(setPlayers());

				beforeButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if(playerNameAdd.size() >= 1) { 
							int before = JOptionPane.showConfirmDialog(null, "예를 누르시면 리셋된 채로 메인화면으로 나가집니다", "경고 ) 추가된 사람이 있습니다.", JOptionPane.YES_NO_OPTION);
							if(before != 0) return;
						}
						beforeClickClip = Audio("./image/beforeClick.wav", beforeClickClip);
						titleClip.close();
						dispose();
						new TongGame().setVisible(true);
					}
				});

				gameStartButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						titleClip.close();
						clickClip = Audio("./image/click.wav", clickClip);
						count = Audio("./image/count.wav", count);

						JFrame frame = new JFrame();
						ImageIcon img = new ImageIcon("./image/321.gif");
						JLabel label = new JLabel();
						label.setIcon(img);
						label.setBounds(0, 0, 900, 900);
						frame.add(label);

						frame.setVisible(true);
						frame.setSize(900, 900);
						frame.setLocationRelativeTo(null);
						frame.setDefaultCloseOperation(2);

						//https://ridd-coding.tistory.com/74
						Timer time = new Timer();
						TimerTask task = new TimerTask() {
							@Override
							public void run() {
								frame.dispose();
								bell = Audio("./image/bell.wav", bell);
								gameClip = Audio("./image/gameBgm.wav", gameClip);
							}
						};

						time.schedule(task, 2400);

						add(gameFrameBoard());

						Random rd = new Random();
						holePenalty = rd.nextInt(24);
						mainButton.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								clickClip = Audio("./image/click.wav", clickClip);
								dispose();
								new TongGame().setVisible(true);
							}
						});
					}
				});
			}
		});

		expButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clickClip = Audio("./image/click.wav", clickClip);
				add(explanationFrame());

				beforeButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						titleClip.close();
						beforeClickClip = Audio("./image/beforeClick.wav", beforeClickClip);
						dispose();
						new TongGame().setVisible(true);
					}
				});
			}
		});

		endButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}

	public Component mainPanel() {
		mainPanel.add(this.setBounds(startButton, 125, 570, 200, 100, 0, 255, 0));
		mainPanel.add(this.setBounds(endButton, 550, 570, 200, 100, 255, 0, 0));
		mainPanel.add(this.setBounds(expButton, 338, 700, 200, 100, 255, 255, 255));

		ImagePanel img = new ImagePanel(new ImageIcon("./image/title.jpg").getImage());
		mainPanel.add(img);

		this.setVisible(mainPanel);
		return mainPanel;
	}

	//플레이어 설정
	public Component setPlayers() {
		mainPanel.setVisible(false); 
		//플레이어 입력창
		textField.setHorizontalAlignment(JTextField.CENTER);
		startPersonPanel.add(this.setBounds(textField, 200, 230, 500, 300, 255, 255, 255));
		textField.setFont(new Font("굴림", Font.PLAIN, 50));

		startPersonPanel.add(this.setBounds(playerAdd, 185, 550, 150, 50, 255, 0, 0));
		startPersonPanel.add(this.setBounds(playerCheck, 367, 550, 150, 50, 255, 0, 0));
		startPersonPanel.add(this.setBounds(playerRemove, 550, 550, 150, 50, 255, 0, 0));

		startPersonPanel.add(this.setBounds(gameStartButton, 730, 800, 150, 50, 255, 255, 255)).setVisible(false);

		//추가 버튼을 눌렀을 경우
		//https://hianna.tistory.com/563 (List 중복 갯수 체크)
		playerAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clickClip = Audio("./image/click.wav", clickClip);
				//빈칸일 경우
				if(textField.getText().trim().length() == 0) JOptionPane.showMessageDialog(null, "공백은 입력불가합니다.");
				
				else {
					playerNameAdd.add(textField.getText().trim());

					if(Collections.frequency(playerNameAdd, textField.getText().trim()) >= 2) { //중복 값이 2개 이상인 경우
						int yesOrNo = JOptionPane.showConfirmDialog(null, "동명이인이 추가되었습니다.", "정말로 추가하시겠습니까?", JOptionPane.YES_NO_OPTION);

						//예를 눌렀을 경우
						if(yesOrNo == 0) {
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
					
					gameStartButton.setVisible(false); //반드시 확인버튼을 눌러야 함
				}
			}
		});


		//확인 버튼을 눌렀을 경우
		playerCheck.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(playerNameAdd.size() < 2) {//리스트 총 길이 2명이상인지
					JOptionPane.showMessageDialog(null, "최소 2명 입력해주세요.");
					errorClickClip = Audio("./image/error.wav", errorClickClip);
				}
				//프로그램에서 원하는 값으로 플레이어 수가 정해졌을 때(2~4명 사이로)
				else {
					collectClickClip = Audio("./image/correct.wav", collectClickClip);
					playersNum = playerNameAdd.size();
					gameStartButton.setVisible(true); //인원 수가 조건에 맞을 경우에만 게임 시작 버튼 보이게
					JOptionPane.showMessageDialog(null, "확인되었습니다.");
				}
			}
		});

		//삭제 버튼을 눌렀을 경우
		playerRemove.addActionListener(new ActionListener() {
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
		});

		startPersonPanel.add(this.setBounds(beforeButton, 20, 800, 150, 50, 255, 255, 255));

		//이미지 넣기
		ImagePanel img = new ImagePanel(new ImageIcon("./image/player.jpg").getImage());
		startPersonPanel.add(img);

		this.setVisible(startPersonPanel);
		return startPersonPanel;
	}

	//게임 설명 화면
	public Component explanationFrame() {
		mainPanel.setVisible(false);
		expPanel.add(this.setBounds(beforeButton, 20, 800, 150, 50, 255, 255, 255));

		ImagePanel img = new ImagePanel(new ImageIcon("./image/exp.jpg").getImage());
		expPanel.add(img);

		this.setVisible(expPanel);
		return expPanel;
	}

	//게임 보드
	public Component gameFrameBoard() {
		startPersonPanel.setVisible(false);
		Collections.shuffle(playerNameAdd); //배열 순서 무작위 돌리기
		
		this.setBounds(mainButton, 350, 500, 200, 100, 255, 255, 255);
		this.setBounds(penaltyLabel, 0, 300, 900, 100, 255, 255, 255);
		penaltyLabel.setHorizontalAlignment(JLabel.CENTER);
		penaltyLabel.setFont(new Font("굴림", Font.BOLD, 25));
		penaltyLabel.setForeground(Color.white);
		gamePanel.add(mainButton).setVisible(false); //안보이게 했다가 끝났을 경우에만 보이게
		gamePanel.add(penaltyLabel).setVisible(false);

		playerButton = new JButton[playersNum];

		for(int i = 0; i < playersNum; i++)
			gamePanel.add(this.setBounds(playerButton[i] = new JButton(playerNameAdd.get(i)), 100 + (i + 1) * 180, 7, 150, 50, 255, 255, 255));

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
		gamePanel.add(this.setBounds(titleLabel, 0, 0, 900, 60, 255, 255, 255));
		titleLabel.setIcon(new ImageIcon("./image/titleLabel.jpg"));

		buttonPanel.setLayout(new GridLayout(4, 6, 20, 20)); 
		gamePanel.add(this.setBounds(buttonPanel, 0, 60, 900, 800, 0, 102, 0));

		for(int i = 0; i < 24; i++) {
			holeButtons[i] = new JButton(""); //이미 선택한 구멍과 선택하지 않은 구멍 차이점 ('-' 유무)
			buttonPanel.add(holeButtons[i]);
			holeButtons[i].setFont(new Font("굴림", Font.BOLD, 100));
			holeButtons[i].addActionListener(this); //해당 버튼 동작 이벤트
		}

		gamePanel.add(buttonPanel);
		this.setVisible(gamePanel);
		return gamePanel;
	}

	//this 가리킴
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
						gameClip.close();
						penaltyClip = Audio("./image/penalty.wav", penaltyClip);
						for(int j = 0; j < playerButton.length; j++) playerButton[j].setVisible(false);
						JOptionPane.showMessageDialog(null, penalty[holePenalty] + "을(를) 수행해주세요.");
						for(int j = 0; j < 24; j++) holeButtons[j].setVisible(false);
						//https://heaven0713.tistory.com/28 (라벨 \n 사용하기)
						penaltyLabel.setVisible(true);
						penaltyLabel.setText(("<html><body><center>" + playerNameAdd.get(start - 1) + "님은 오늘의 주인공! <br><br>" + penalty[holePenalty] + "을(를) 수행해주세요.</center></body></html>"));
						titleLabel.setBounds(0, 0, 900, 900);
						mainButton.setVisible(true);
						titleLabel.setIcon(new ImageIcon("./image/win.jpg"));
						break;
					}

					else
						if(start != playersNum) { playerButton[start].setBorder(new LineBorder(Color.black, 7)); start++; } //마지막 순서가 아닐 경우
						else { playerButton[0].setBorder(new LineBorder(Color.black, 7)); start = 1; } //마지막 순서일 경우
				} //이미 선택한 구멍과 선택하지 않은 구멍 차이점 ('-' 유무)
	}

	public static void main(String[] args) {
		new TongGame().setVisible(true);
	}
}