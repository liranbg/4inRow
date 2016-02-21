import java.awt.BorderLayout;

import javax.swing.JFrame;


public class Main {
	public static void main(String[] args) {
		JFrame frame = new JFrame("4 in Row");
		frame.setSize(550,550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame newGame = new mainFrame(frame.getWidth(),frame.getHeight());
		frame.getContentPane().add(newGame);
		frame.setVisible(true);

	}
}
