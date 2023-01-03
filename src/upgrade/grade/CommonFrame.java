package upgrade.grade;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CommonFrame extends JFrame{
	public CommonFrame(int width, int height, String title) {
		setSize(width, height);
		setResizable(false);
		setTitle(title);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
	}

	JComponent setBounds(JComponent comp, int x, int y, int width, int height, int r, int g, int b) {
		comp.setBounds(x, y, width, height);
		comp.setFont(new Font("굴림", Font.PLAIN, 20));
		comp.setBackground(new Color(r, g, b));
		return comp;
	}
	
	public void setVisible(JComponent comp) {    	
		comp.setVisible(true);
		comp.setLayout(null);
		comp.setBounds(0, 0, 900, 900);
	}

	//https://blog.naver.com/vest2004/130180280903
	public Clip Audio(String f, Clip clip) {
		try {
			File file = new File(f);
			clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(file));
			clip.loop(0);

		} catch (Exception e) {
			System.err.println("파일을 찾을 수 없습니다.");
		}
		
		return clip;
	}	
}

//패널에 그림을 올려주는 클래스
//https://eunbc-2020.tistory.com/54
class ImagePanel extends JPanel {
	private Image img;

	public ImagePanel(Image img) {
		this.img = img;
		setSize(new Dimension(img.getWidth(null), img.getHeight(null)));
		setPreferredSize(new Dimension(img.getWidth(null), img.getHeight(null)));
		setLayout(null);
	}

	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, null);
	}
}
