package upgrade.grade;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class MainFrame extends CommonFrame {
	Clip open;			   //https://pixabay.com/ko/music/search/genre/%EB%B9%84%EB%94%94%EC%98%A4%20%EA%B2%8C%EC%9E%84/
	Clip titleClip;		   //https://pixabay.com/ko/music/search/genre/%EB%B9%84%EB%94%94%EC%98%A4%20%EA%B2%8C%EC%9E%84/
	ImagePanel img = new ImagePanel(new ImageIcon("./image/title.jpg").getImage());
	
	public MainFrame() {
		super(900, 900, "메인화면");
		JButton startButton = new JButton("게임 시작");
		JButton expButton = new JButton("게임 설명");
		expButton.addActionListener(expListener);
		JButton endButton = new JButton("게임 종료");
		endButton.addActionListener(endListener);
		
		add(this.setBounds(startButton, 125, 570, 200, 100, 0, 255, 0));
		add(this.setBounds(endButton, 550, 570, 200, 100, 255, 0, 0));
		add(this.setBounds(expButton, 338, 700, 200, 100, 255, 255, 255));

		add(img);
	}
	
	public void setAudio() {
		open = Audio("./image/open.wav", open);
		titleClip = Audio("./image/titleClip.wav", titleClip);
		titleClip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	ActionListener expListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			new ExpFrame().setVisible(true);
		}
	};
	
	ActionListener endListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	};
}
