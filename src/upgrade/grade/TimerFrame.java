package upgrade.grade;

import java.util.Timer;
import java.util.TimerTask;

import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class TimerFrame extends CommonFrame {
	Clip count;			   //https://youtubelab.tistory.com/34
	Clip clickClip;        //https://pgtd.tistory.com/269
	Clip bell;			   //https://pgtd.tistory.com/225
	Clip gameClip;		   //https://pixabay.com/ko/music/search/genre/%EB%B9%84%EB%94%94%EC%98%A4%20%EA%B2%8C%EC%9E%84/
	
	public TimerFrame() {
		super(900, 900, "");
		MainFrame.titleClip.close();
		clickClip = Audio("./image/click.wav", clickClip);
		count = Audio("./image/count.wav", count);

		ImageIcon img = new ImageIcon("./image/321.gif");
		JLabel label = new JLabel();
		label.setIcon(img);
		label.setBounds(0, 0, 900, 900);
		add(label);

		setVisible(true);
		setSize(900, 900);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(2);

		//https://ridd-coding.tistory.com/74
		Timer time = new Timer();
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				dispose();
				bell = Audio("./image/bell.wav", bell);
				gameClip = Audio("./image/gameBgm.wav", gameClip);
			}
		};

		time.schedule(task, 2400);
	}
}
