public class IndividualSRProject
	{
	static boolean stillPlaying=true;
	public static void main(String[] args)
		{
		PlayHSR.displayBoard();
		while (stillPlaying)
			{
			PlayHSR.displayButtons();
			PlayHSR.takeAction();
			PlayHSR.checkDeath();
			PlayHSR.enemyTurn();
			PlayHSR.displayBoard();
			PlayHSR.checkDeath();
			}
		}
	}