import java.util.Scanner;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;
public class Displays
	{
	public static String playerButtonInput;
	public static String playerButtonInput1;
	public static Scanner userStringInput = new Scanner(System.in);
	public static Player player = new Player("Hero", 15, 100, 0, 100);
	public static Player player2 = new Player("Support", 15, 100, 0, 95);
	public static Player enemy = new Player("Monster", 15, 100, 0, 90);
	public static int skillPoints = 1;
	public static int maxSkillPoints = 3;
	public static boolean stillPicking=true;
	public static double multiplier = 0;
	public static double adjMultiplier = 0;
	public static double aoeMultiplier = 0;
	public static SpeedSorter sorter = new SpeedSorter();
	public static Player p1 = player;
	public static Player p2 = player2;

	public static void displayBoard() 
		{
	    System.out.println("      "+enemy.getName()+"          HP: "+enemy.getHealthStat());
	    System.out.println("       | X |");
	    System.out.println("        vs          Skill Points:"+skillPoints+"/"+maxSkillPoints);
	    System.out.println("       | O |       Charge: "+player.getCharge()+"/100");
	    System.out.println("        "+player.getName()+"           HP: "+player.getHealthStat());
	    System.out.println();
		}
	public static void displayButtons()
		{
		    while (true)
		    {
	        System.out.println("| Q |  | E |  | T |        | I |");
	        System.out.println("Basic  Skill   Ult         Info");
	        playerButtonInput = userStringInput.nextLine();
	        if (playerButtonInput.equalsIgnoreCase("I"))
		        {
		        infoDisplay();
		        }
	        else if (playerButtonInput.equalsIgnoreCase("E") && skillPoints==0)
	        	{
	        	System.out.println("Insufficient skill points, try again!");
				System.out.println("_________________________________");
	        	}
	        else if (playerButtonInput.equalsIgnoreCase("T") && player.getCharge()<100)
	        	{
	        	System.out.println("Insufficient charge, try again!");
				System.out.println("_________________________________");
	        	}
	        else
		        {
		        break;
		        }
		    }
		}
	public static void takeAction()
		{
			if (playerButtonInput.equalsIgnoreCase("Q"))
				{
				multiplier = 1;
				System.out.println("Used Basic Attack");
				System.out.println("_________________________________");
				System.out.println("The enemy took "+player.getAttackStat()*multiplier+" damage");
				enemy.setHealthStat(enemy.getHealthStat()-player.getAttackStat()*multiplier);
				player.setCharge(player.getCharge()+25);
				player.setSpeed(player.getSpeed()+25);
				if (skillPoints<maxSkillPoints)
					{
					skillPoints+=1;
					}
				else
					{
					}
				}
			else if (playerButtonInput.equalsIgnoreCase("E") && skillPoints>0)
				{
				multiplier = 2;
				adjMultiplier = 1;
				System.out.println("Used Skill");
				System.out.println("_________________________________");
				System.out.println("The enemy took "+player.getAttackStat()*multiplier+" damage");//" and "+player.getAttackStat()*adjMultiplier+" damage to adjacent enemies");
				enemy.setHealthStat(enemy.getHealthStat()-player.getAttackStat()*multiplier);
				player.setCharge(player.getCharge()+35);
				skillPoints-=1;
				}
			else if (playerButtonInput.equalsIgnoreCase("T") && player.getCharge()>=100)
				{
				multiplier = 3;
				adjMultiplier = 1.5;
				System.out.println("Used Ultimate");
				System.out.println("_________________________________");
				System.out.println("The enemy took "+player.getAttackStat()*multiplier+" damage");//" and "+player.getAttackStat()*adjMultiplier+" damage to adjacent enemies");
				enemy.setHealthStat(enemy.getHealthStat()-player.getAttackStat()*multiplier);
				player.setCharge(player.getCharge()-100);
				player.setCharge(player.getCharge()+25);
				}
			}
	public static void checkDeath()
		{
		if(enemy.getHealthStat()<=0)
			{
			System.out.println("You've defeated the enemy! GG");
			IndividualSRProject.stillPlaying=false;
			}
		if(player.getHealthStat()<=0)
			{
			System.out.println("You died! GG");
			IndividualSRProject.stillPlaying=false;
			}
		}
	public static void enemyTurn()
		{
		int enemyChoice=(int)(Math.random()*2);
		if (enemyChoice==0)
			{
			multiplier=1;
			player.setHealthStat(player.getHealthStat()-enemy.getAttackStat()*multiplier);
			System.out.println("The enemy dealt "+enemy.getAttackStat()*multiplier+" damage to you");
			player.setCharge(player.getCharge()+5);
			}
		else
			{
			multiplier=2;
			player.setHealthStat(player.getHealthStat()-enemy.getAttackStat()*multiplier);
			System.out.println("The enemy dealt "+enemy.getAttackStat()*multiplier+" damage to you");
			player.setCharge(player.getCharge()+10);
			}
		System.out.println("---------------------------------");
		}
	public static void infoDisplay()
		{
		System.out.println(enemy.getName()+" HP: "+enemy.getHealthStat());
		System.out.println(enemy.getName()+" ATK: "+enemy.getAttackStat());
		System.out.println(enemy.getSpeed()+" SPD: "+enemy.getSpeed());
		System.out.println("---------------------------------");
		System.out.println("Team Skill Points: "+skillPoints+"/"+maxSkillPoints);
		System.out.println(player.getName()+" HP: "+player.getHealthStat()); 
		System.out.println(player.getName()+" ATK: "+player.getAttackStat());
		System.out.println(player.getName()+" charge: "+player.getCharge()+"/100");
		System.out.println("---------------------------------");
		System.out.println(" - Descriptions -");
		System.out.println(player.getName()+" Basic ATK: Deals 100% of "+player.getName()+"'s ATK to one enemy");
		System.out.println(player.getName()+" Skill: Deals 200% of "+player.getName()+"'s ATK to one enemy and 100% of "+player.getName()+"'s ATK to adjacent enemies. Expends 1 skill point.");
		System.out.println(player.getName()+" Ultimate: Deals 300% of "+player.getName()+"'s ATK to one enemy and 150% of "+player.getName()+"'s ATK to adjacent enemies. Expends 100 charge.");
		}
	public static void sortDisplay()
		{
		List<Player> players = Arrays.asList(player, player2, enemy);
	    Collections.sort(players, sorter);
	    System.out.println("=== Sorted by Speed (Fastest -> Slowest) ===");
	    for (Player p : players) {
	        System.out.println(p.getName() + " - SPD: " + p.getSpeed());
	    }
	    System.out.println("===========================================");
		}
	public static void processTurnOrder()
		{
		List<Player> turnOrder = Arrays.asList(player, player2, enemy);
	    Collections.sort(turnOrder, sorter);
	    System.out.println("TURN ORDER:");
	    for (Player p : turnOrder) 
	    	{
	        System.out.println("  " + p.getName() + " (SPD " + p.getSpeed() + ")");
	    	}
	    System.out.println("----------------------------------");
	    for (Player p : turnOrder) 
	    	{
	        if (p == player) 
	        	{
	            System.out.println(p.getName()+"'s turn!");
	            displayBoard();
	            displayButtons();
	            takeAction();
	        	} 
	        else if (p == enemy) 
	        	{
	            System.out.println(p.getName()+"'s turn!");
	            displayBoard();
	            enemyTurn();
	        	}
	        else if (p == player2) 
	        	{
	            System.out.println(p.getName()+"'s turn!");
	            displayBoard();
	            displayButtons();
	            takeAction();
	        	}
	        checkDeath();
	    	}
		}
	}