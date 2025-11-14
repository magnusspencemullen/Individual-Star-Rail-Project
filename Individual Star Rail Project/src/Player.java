public class Player
	{
	private String name;
	private double attackStat;
	private double healthStat;
	private int charge;
	private double speed;
	public Player(String n, double a, double h, int c, double s)
		{
		name = n;
		attackStat = a;
		healthStat = h;
		charge = c;
		speed = s;
		}
	public String getName()
		{
		return name;
		}

	public void setName(String name)
		{
		this.name = name;
		}

	public double getAttackStat()
		{
		return attackStat;
		}

	public void setAttackStat(double attackStat)
		{
		this.attackStat = attackStat;
		}

	public double getHealthStat()
		{
		return healthStat;
		}

	public void setHealthStat(double healthStat)
		{
		this.healthStat = healthStat;
		}
	public int getCharge()
		{
		return charge;
		}

	public void setCharge(int charge)
		{
		this.charge =charge;
		}
	public double getSpeed()
		{
		return speed;
		}

	public void setSpeed(double speed)
		{
		this.speed = speed;
		}

	}