import java.util.Comparator;
public class SpeedSorter implements Comparator<Player>
	{
	public int compare (Player player, Player player2)
		{
		if(player.getSpeed()<player2.getSpeed())
			return 1;
		else if(player.getSpeed()>player2.getSpeed())
			return -1;
		else
			return 0;
		}
	}