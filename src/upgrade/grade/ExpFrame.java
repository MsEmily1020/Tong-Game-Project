package upgrade.grade;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ExpFrame extends CommonFrame {
	Clip clickClip;        //https://pgtd.tistory.com/269
	Clip beforeClickClip;  //https://www.soundgator.com/
	
	public ExpFrame() {
		super(900, 900, "설명");
		clickClip = Audio("./image/click.wav", clickClip);
		
		JButton beforeButton = new JButton("이전");
		add(this.setBounds(beforeButton, 20, 800, 150, 50, 255, 255, 255));
		beforeButton.addActionListener(beforeListener);
		
		ImagePanel img = new ImagePanel(new ImageIcon("./image/exp.jpg").getImage());
		add(img);
	}
	
	ActionListener beforeListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			beforeClickClip = Audio("./image/beforeClick.wav", beforeClickClip);
			dispose();
			new MainFrame().setVisible(true);
		}
	};
}
