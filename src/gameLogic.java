import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class gameLogic extends JPanel{

	private gameCoin[][] coinsList;
	JPanel coins;
	JLabel wins;
	opponent redOPP,yellowOPP;
	private boolean turns;
	private boolean winner;
	private final int ROWS=6,CELLS=7;
	private final int WINNINGS_POINTS = 4;

	public gameLogic()
	{
		this.setLayout(new BorderLayout());

		wins = new JLabel("", JLabel.CENTER);
		coins = new JPanel();
		coins.setLayout(new GridLayout(ROWS,CELLS));
		coins.setBorder(BorderFactory.createLineBorder(Color.black));

		coinsList = new gameCoin[ROWS][CELLS];
		createGameSurface();
		turns = true; //red turn
		winner = false;
		redOPP  = new opponent("Red",Color.RED);
		yellowOPP  = new opponent("Yellow",Color.YELLOW);
		
		this.add(wins,BorderLayout.NORTH);
		this.add(coins,BorderLayout.CENTER);
		updateWinBoard();

	}
	private void createGameSurface()
	{
		for (int i = 0; i < ROWS ;i++)
			for (int j = 0; j < CELLS ;j++)
			{
				gameCoin r = new gameCoin();
				r.setBorder(BorderFactory.createLineBorder(Color.black));
				r.addMouseListener(new Listener());
				coinsList[i][j] = r;
				coins.add(coinsList[i][j]);
			}
	}
	private void updateWinBoard()
	{
		wins.setText(redOPP + " vs. " + yellowOPP);
	}
	private boolean checkGame(Color opp) 
	{
		int counts = 0;
		//Checks by row
		for (int i = coinsList.length -1; i >=0 ;i--)
		{
			for (int j = 0; j < CELLS ;j++)
			{
				if (opp == coinsList[i][j].coinColor)
					counts++;
				if (counts == WINNINGS_POINTS)
					return true;
				if (opp != coinsList[i][j].coinColor)
					counts=0;
			}
			counts=0;
		}
		counts = 0;
		//check by cell
		for (int i = 0; i < CELLS ;i++)
		{
			for (int j = 0; j < ROWS ;j++)
			{
				if (opp == coinsList[j][i].coinColor)
					counts++;
				if (counts == WINNINGS_POINTS)
					return true;

				if (opp != coinsList[j][i].coinColor)
					counts=0;
			}
		}
		return checkByDiagonal(opp);
	}
	private boolean checkByDiagonal(Color opp) //first time i and j are maxed (starts from buttom)
	{
		int i = coinsList.length -1;
		int j = 0;
		for (i = coinsList.length -1; i >= 3 ;i--)
		{
			for (j = 0; j < CELLS ;j++)
			{
				if (opp == coinsList[i][j].coinColor)
					if (Diagonal(i,j))
						return true;
			}
		}
		return false;
	}
	private boolean Diagonal(int i,int j)
	{
		int counts=0;
		int iLeft=i-1,jLeft=j-1;
		int iRight=i-1,jRight=j+1;
		for (counts = 1; counts < WINNINGS_POINTS; counts++)
		{
			if (((iRight < 0) || ((jRight == CELLS))) || ((iLeft < 0) || ((jLeft < 0))))
				break;
			if (coinsList[i][j].coinColor != coinsList[iRight][jRight].coinColor)
				break;
			iRight--;
			jRight++;

		}
		if (counts == 4)
			return true;
		for (counts = 1; counts < WINNINGS_POINTS; counts++)
		{
			if ((iLeft < 0) || ((jLeft < 0)))
				break;
			if (coinsList[i][j].coinColor != coinsList[iLeft][jLeft].coinColor)
				break;
			iLeft--;
			jLeft--;
		}
		if (counts == 4)
			return true;
		return false;
	}
	public void resetGame()
	{
		coins.removeAll();
		coinsList = new gameCoin[ROWS][CELLS];
		turns = true; //red turn
		winner = false;
		createGameSurface();
		repaint();
	}
	private class Listener implements MouseListener
	{
		public void mouseClicked(MouseEvent e) {
			if ((e.getComponent() instanceof gameCoin) && (!winner))
			{
				
				gameCoin a = (gameCoin)e.getComponent();
				
				if (a.isFlagged())
					return;

				for (int i =0 ;i < ROWS; i++)
					for(int j  = 0; j < CELLS; j++)
						if ((a == coinsList[i][j]) && (!a.isFlagged()))
						{
							//System.out.println(i + " "+j); shows the x,y position
							while (!coinsList[i][j].isFlagged())
							{
								a = coinsList[i][j];
								i++;
								if (i >= ROWS)
								{

									break;
								}
							}
							break;
						}

				a.setSizes(e.getComponent().getWidth(),e.getComponent().getHeight());
				if (turns)
					a.setColor(redOPP.getType());
				else
					a.setColor(yellowOPP.getType());

				turns = turns ? false:true;
				if (checkGame(redOPP.getType()))
				{
					JOptionPane.showMessageDialog(null,
							redOPP.getName()  +    " is the winner");
					redOPP.win();
					winner=true;
				}
				else if	 (checkGame(yellowOPP.getType()))
				{
					JOptionPane.showMessageDialog(null,
							yellowOPP.getName() +    " is the winner");
					yellowOPP.win();
					winner = true;
				}
				updateWinBoard();
			}

		}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		public void mousePressed(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
	}
}
