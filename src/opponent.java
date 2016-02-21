import java.awt.Color;


public class opponent {

	private final String name;
	private Color type;
	private int wins;

	public opponent(String name,Color c)
	{
		wins = 0;
		this.name = name;
		setType(c);
	}
	public void win()
	{
		wins++;
	}
	public  Color getType() {
		return type;
	}
	public  void setType(Color type) {
		this.type = type;
	}
	public int getWins() {
		return wins;
	}
	public String getName() {
		return name;
	}
	public String toString()
	{
		return getName() + ":" + String.valueOf(wins);
	}



}
