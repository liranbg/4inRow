import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;


public class mainFrame extends JPanel {

	JPanel gameCMD;
	JButton restartGame,pauseGame;
	private int width, height;

	private gameLogic gameSurface;

	public mainFrame(int width, int height)
	{
		this.width = width;
		this.height = height;

		gameSurface = new gameLogic();
		restartGame = new JButton("Restart");
		pauseGame = new JButton("Pause");

		gameCMD= new JPanel();

		gameCMD.add(pauseGame);
		gameCMD.add(restartGame);
		restartGame.addActionListener(new Listener());
		pauseGame.addActionListener(new Listener());
		
		this.setLayout(new BorderLayout());
		this.setSize(this.width,this.height);
		this.add(gameCMD,BorderLayout.SOUTH);
		this.add(gameSurface,BorderLayout.CENTER);
	}
	private class Listener implements ActionListener
	{

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == restartGame)
			{
				gameSurface.resetGame();
				revalidate();
			}
			if (e.getSource() == pauseGame)
			{
				//mainFrame.this.
				boolean flag;
				flag = gameSurface.isVisible() ? false:true;
				gameSurface.setVisible(flag);
			}
		}

	}

}
