import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;


public class gameCoin extends JPanel{

	protected Color coinColor;
	private int size = -1;
	private int size2 = -1;
	private boolean flag;
	
	public gameCoin()
	{
		coinColor = Color.WHITE;
		flag = false;
	}
	public void setColor(Color g)
	{
		flag = true;
		coinColor = g;
		repaint();
	}
	public boolean isFlagged()
	{
		return flag;
	}
	public void setSizes(int size1,int size2)
	{
		size = size1;
		this.size2 = size2;
		repaint();
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.setColor(coinColor);
		g.fillOval(0,0, size, size2);
	}

}
