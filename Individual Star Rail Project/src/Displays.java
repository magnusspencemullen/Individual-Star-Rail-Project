import java.util.Scanner;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Displays
	{
	public static String playerButtonInput;
	public static String playerButtonInput1;
	public static int playerSkillSelectInput;
	public static Scanner userStringInput = new Scanner(System.in);
	public static Scanner userIntInput = new Scanner(System.in);
	public static Player player = new Player("Hero", 15, 100, 0, 100);
	public static Player player2 = new Player("Support", 20, 100, 0, 95);
	public static Player enemy = new Player("Monster", 25, 100, 0, 90);
	public static int skillPoints = 1;
	public static int maxSkillPoints = 3;
	public static boolean stillPicking = true;
	public static double multiplier = 0;
	public static double adjMultiplier = 0;
	public static double aoeMultiplier = 0;
	public static SpeedSorter sorter = new SpeedSorter();
	public static boolean stillPickingSkill = true;
	static int className;
	static JFrame frame = new JFrame();
	static int playing = 0;
	static String nameShower;

	public static void displayBoard()
		{
		System.out.println("             " + enemy.getName() + " HP:" + enemy.getHealthStat());
		System.out.println("   | M |");
		System.out.println("     vs              SP:" + skillPoints + "/" + maxSkillPoints);
		System.out.println(" | H || S |");
		System.out.println("                " + player.getName() + " HP: " + player.getHealthStat() + " | Charge: "
				+ player.getCharge() + "/100");
		System.out.println("             " + player2.getName() + " HP: " + player2.getHealthStat() + " | Charge: "
				+ player2.getCharge() + "/100");
		System.out.println();
		}

	public static void displayButtons()
		{
//		while (true)
//			{
//			System.out.println("| Q |  | E |  | T |        | I |");
//			System.out.println("Basic  Skill   Ult         Info");
//			playerButtonInput = userStringInput.nextLine();
//			if (playerButtonInput.equalsIgnoreCase("I"))
//				{
//				infoDisplay();
//				} 
//			else if (playerButtonInput.equalsIgnoreCase("E") && skillPoints == 0)
//				{
//				System.out.println("Insufficient skill points, try again!");
//				System.out.println("_________________________________");
//				} 
//			else if (playerButtonInput.equalsIgnoreCase("T") && player.getCharge() < 100 && player2.getCharge() < 100)
//				{
//				System.out.println("Insufficient charge, try again!");
//				System.out.println("_________________________________");
//				} 
//			else if (playerButtonInput.equalsIgnoreCase("Q") || playerButtonInput.equalsIgnoreCase("I") || playerButtonInput.equalsIgnoreCase("E") || playerButtonInput.equalsIgnoreCase("T"))
//				{
//				break;
//				} 
//			else
//				{
//				System.out.println("Wrong input! Please enter Q, E, T, or I.");
//				System.out.println("_________________________________");
//				}
//			}
		if (playing==1)
			{
			nameShower=player.getName()+"'s turn!";
			}
		else if (playing==2)
			{
			nameShower=player2.getName()+"'s turn!";
			}
		Object[] options =
			{ "Basic", "Skill", "Ult", "Info" };
		className = JOptionPane.showOptionDialog(frame, "What action would you like to choose?", nameShower,
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);
		switch (className)
			{
			case 0:
				{
				playerButtonInput="Q";
				break;
				}
			case 1:
				{
				if (skillPoints == 0)
					{
					JOptionPane.showMessageDialog(frame, "Insufficient skill points, try again!");
					displayButtons();
					return;
					}
				playerButtonInput="E";
				break;
				}
			case 2:
				{
				if (player.getCharge() < 100 && player2.getCharge() < 100)
					{
					JOptionPane.showMessageDialog(frame, "Insufficient charge, try again!");
					displayButtons();
					return;
					}
				playerButtonInput="T";
				break;
				}
			case 3:
				{
				infoDisplay();
				playerButtonInput="I";
				displayButtons();
				return;
				}
			default:
				{
				JOptionPane.showMessageDialog(frame, "Wrong input! Please select Basic, Skill, Ult, or Info.");
				displayButtons();
				return;
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
			System.out.println("The enemy took " + player.getAttackStat() * multiplier + " damage");
			enemy.setHealthStat(enemy.getHealthStat() - player.getAttackStat() * multiplier);
			player.setCharge(player.getCharge() + 25);
			// player.setSpeed(player.getSpeed()+25);
			if (skillPoints < maxSkillPoints)
				{
				skillPoints += 1;
				} 
			else
				{
				}
			} 
		else if (playerButtonInput.equalsIgnoreCase("E") && skillPoints > 0)
			{
			multiplier = 2;
			adjMultiplier = 1;
			System.out.println("Used Skill");
			System.out.println("_________________________________");
			System.out.println("The enemy took " + player.getAttackStat() * multiplier + " damage");// " and
																									// "+player.getAttackStat()*adjMultiplier+"
																									// damage to
																									// adjacent
																									// enemies");
			enemy.setHealthStat(enemy.getHealthStat() - player.getAttackStat() * multiplier);
			player.setCharge(player.getCharge() + 35);
			skillPoints -= 1;
			} 
		else if (playerButtonInput.equalsIgnoreCase("T") && player.getCharge() >= 100)
			{
			multiplier = 3;
			adjMultiplier = 1.5;
			System.out.println("Used Ultimate");
			System.out.println("_________________________________");
			System.out.println("The enemy took " + player.getAttackStat() * multiplier + " damage");// " and
																									// "+player.getAttackStat()*adjMultiplier+"
																									// damage to
																									// adjacent
																									// enemies");
			enemy.setHealthStat(enemy.getHealthStat() - player.getAttackStat() * multiplier);
			player.setCharge(player.getCharge() - 100);
			player.setCharge(player.getCharge() + 25);
			}
		}

	public static void checkDeath()
		{
		if (enemy.getHealthStat() <= 0)
			{
			System.out.println("You've defeated the enemy! Good game.");
			IndividualSRProject.stillPlaying = false;
			System.exit(0);
			}
		if (player.getHealthStat() <= 0 || player2.getHealthStat() <= 0)
			{
			System.out.println("You died! Good game.");
			IndividualSRProject.stillPlaying = false;
			System.exit(0);
			}
		}

	public static void enemyTurn()
		{
		int enemyChoice = (int) (Math.random() * 2);
		if (enemyChoice == 0)
			{
			int enemyTargetChoice = (int) (Math.random() * 3);
			if (enemyTargetChoice == 0)
				{
				multiplier = 1;
				player.setHealthStat(player.getHealthStat() - enemy.getAttackStat() * multiplier);
				System.out.println(
						"The enemy dealt " + enemy.getAttackStat() * multiplier + " damage to " + player.getName());
				player.setCharge(player.getCharge() + 5);
				} else if (enemyTargetChoice == 1)
				{
				multiplier = 1;
				player2.setHealthStat(player2.getHealthStat() - enemy.getAttackStat() * multiplier);
				System.out.println(
						"The enemy dealt " + enemy.getAttackStat() * multiplier + " damage to " + player2.getName());
				player2.setCharge(player2.getCharge() + 5);
				} else if (enemyTargetChoice == 2)
				{
				multiplier = 1;
				player.setHealthStat(player.getHealthStat() - enemy.getAttackStat() * multiplier);
				player2.setHealthStat(player2.getHealthStat() - enemy.getAttackStat() * multiplier);
				System.out.println("The enemy dealt " + enemy.getAttackStat() * multiplier + " damage to "
						+ player.getName() + " and " + player2.getName());
				player.setCharge(player.getCharge() + 5);
				player2.setCharge(player2.getCharge() + 5);
				}
			} 
		else
			{
			int enemyTargetChoice = (int) (Math.random() * 2);
			if (enemyTargetChoice == 0)
				{
				multiplier = 2;
				player.setHealthStat(player.getHealthStat() - enemy.getAttackStat() * multiplier);
				System.out.println(
						"The enemy dealt " + enemy.getAttackStat() * multiplier + " damage to " + player.getName());
				player.setCharge(player.getCharge() + 10);
				} else
				{
				multiplier = 2;
				player2.setHealthStat(player2.getHealthStat() - enemy.getAttackStat() * multiplier);
				System.out.println(
						"The enemy dealt " + enemy.getAttackStat() * multiplier + " damage to " + player2.getName());
				player2.setCharge(player2.getCharge() + 10);
				}
			}
		System.out.println("---------------------------------");
		}

	public static void infoDisplay()
		{
		System.out.println(enemy.getName() + " HP: " + enemy.getHealthStat());
		System.out.println(enemy.getName() + " ATK: " + enemy.getAttackStat());
		System.out.println(enemy.getName() + " SPD: " + enemy.getSpeed());
		System.out.println("---------------------------------");
		System.out.println("Team Skill Points: " + skillPoints + "/" + maxSkillPoints);
		System.out.println(player.getName() + " HP: " + player.getHealthStat());
		System.out.println(player.getName() + " ATK: " + player.getAttackStat());
		System.out.println(player.getName() + " Charge: " + player.getCharge() + "/100");
		System.out.println(player.getName() + " SPD: " + player.getSpeed());
		System.out.println("---------------------------------");
		System.out.println(player2.getName() + " HP: " + player2.getHealthStat());
		System.out.println(player2.getName() + " ATK: " + player2.getAttackStat());
		System.out.println(player2.getName() + " Charge: " + player2.getCharge() + "/100");
		System.out.println(player2.getName() + " SPD: " + player2.getSpeed());
		System.out.println(" - Descriptions -");
		System.out.println(player.getName() + " Basic ATK: Deals 100% of " + player.getName()
				+ "'s ATK to one enemy. Adds 1 skill point.");
		System.out.println(player.getName() + " Skill: Deals 200% of " + player.getName()
				+ "'s ATK to one enemy. Expends 1 skill point.");// and 100% of "+player.getName()+"'s ATK to one enemy.
																	// Expends 1 skill point.");
		System.out.println(player.getName() + " Ultimate: Deals 300% of " + player.getName()
				+ "'s ATK to one enemy. Expends 100 charge.");// and 150% of "+player.getName()+"'s ATK to one enemy.
																// Expends 100 charge.");
		System.out.println("---------------------------------");
		System.out.println(player2.getName() + " Basic ATK: Deals 100% of " + player2.getName()
				+ "'s ATK to one enemy. Expends 1 skill point.");
		System.out.println(player2.getName() + " Skill: Heals one chosen ally with 100% of " + player2.getName()
				+ "'s ATK. Expends 1 skill point.");
		System.out.println(player2.getName() + " Ultimate: Heals all allies with 225% of " + player2.getName()
				+ "'s ATK. Expends 100 charge.");
		}

	public static void sortDisplay()
		{
		List<Player> players = Arrays.asList(player, player2, enemy);
		Collections.sort(players, sorter);
		System.out.println("=== Sorted by Speed (Fastest -> Slowest) ===");
		for (Player p : players)
			{
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
				System.out.println(p.getName() + "'s turn!");
				displayBoard();
				playing=1;
				displayButtons();
				takeAction();
				} 
			else if (p == enemy)
				{
				System.out.println(p.getName() + "'s turn!");
				displayBoard();
				enemyTurn();
				} 
			else if (p == player2)
				{
				System.out.println(p.getName() + "'s turn!");
				displayBoard();
				playing=2;
				displayButtons();
				SupportTakeAction();
				}
			checkDeath();
			}
		}

	public static void SupportTakeAction()
		{
		
		if (playerButtonInput.equalsIgnoreCase("Q"))
			{
			multiplier = 1;
			System.out.println("Used Basic Attack");
			System.out.println("_________________________________");
			System.out.println("The enemy took " + player2.getAttackStat() * multiplier + " damage");
			enemy.setHealthStat(enemy.getHealthStat() - player2.getAttackStat() * multiplier);
			player2.setCharge(player2.getCharge() + 25);
			// player.setSpeed(player.getSpeed()+25);
			if (skillPoints < maxSkillPoints)
				{
				skillPoints += 1;
				} 
			else
				{
				}
			} 
		else if (playerButtonInput.equalsIgnoreCase("E") && skillPoints > 0)
			{
			multiplier = 1;
			adjMultiplier = 1;
			System.out.println("Used Skill");
			System.out.println("_________________________________");
//			while (stillPickingSkill == true)
//				{
//				System.out.println("Who do you target? |1|" + player.getName() + ", |2|" + player2.getName());
//				String supportSkillInput = userStringInput.nextLine();
//				if (supportSkillInput.equals("1"))
//					{
//					System.out.println(player.getName() + " was healed for " + player2.getAttackStat() * multiplier);
//					player.setHealthStat(player.getHealthStat() + player2.getAttackStat() * multiplier);
//					break;
//					} 
//				else if (supportSkillInput.equals("2"))
//					{
//					System.out.println(player2.getName() + " was healed for " + player2.getAttackStat() * multiplier);
//					player2.setHealthStat(player2.getHealthStat() + player2.getAttackStat() * multiplier);
//					break;
//					} 
//				else
//					{
//					System.out.println("Wrong input! Try again.");
//					}
//				}
				player2.setCharge(player2.getCharge() + 35);
				skillPoints -= 1;
				
				Object[] options =
							{ player.getName(), player2.getName() };
						className = JOptionPane.showOptionDialog(frame, "Who do you target?", nameShower,
								JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
						switch (className)
							{
							case 0:
								{
								System.out.println(player.getName() + " was healed for " + player2.getAttackStat() * multiplier);
								player.setHealthStat(player.getHealthStat() + player2.getAttackStat() * multiplier);
								break;
								}
							case 1:
								{
								System.out.println(player2.getName() + " was healed for " + player2.getAttackStat() * multiplier);
								player2.setHealthStat(player2.getHealthStat() + player2.getAttackStat() * multiplier);
								break;
								}
							default:
								{
								System.out.println("Wrong input! Try again.");
								return;
								}
							}
						player2.setCharge(player2.getCharge() + 35);
						skillPoints -= 1;
				} 
			else if (playerButtonInput.equalsIgnoreCase("T") && player2.getCharge() >= 100)
				{
			multiplier = 3;
			adjMultiplier = 1.5;
			System.out.println("Used Ultimate");
			System.out.println("_________________________________");
			System.out.println(player2.getName() + " healed all allies for " + player2.getAttackStat() * multiplier);
			player.setHealthStat(player.getHealthStat() + player2.getAttackStat() * multiplier);
			player2.setHealthStat(player2.getHealthStat() + player2.getAttackStat() * multiplier);
			player2.setCharge(player2.getCharge() - 100);
			player2.setCharge(player2.getCharge() + 25);
			}
		}
	}