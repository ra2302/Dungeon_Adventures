import java.util.Scanner;
import java.util.Random;
import java.io.IOException;
class venture{
public static void main(String args[]) {

	Scanner in = new Scanner(System.in);
	Random rand = new Random();

	System.out.println("---------------------------------------------");
	System.out.println("\tChoose your Difficulty -");
	System.out.println("\t1. Easy");
	System.out.println("\t2. Medium");
	System.out.println("\t3. Hard");
	String difficulty = in.nextLine();
	System.out.print("\033[H\033[2J");
			System.out.flush();
	//Game Vars
	String[] enemies = {"Skeleton", "Zombie", "Warrior", "Assasin"};
	int maxEnemyHealth = 75;
	int EnemyAttackDamage = 35;
	int enemyDefeated = 0;
	
	//Player Vars
	int health = 100;
	int attackDamage = 35;
	int numHealthPotions = 5;
	int healthPotionHealAmount = 40;
	int healthPotionDropChance = 50;
	boolean running = true;

	if(difficulty.equals("1")){
		maxEnemyHealth -= 25;
		EnemyAttackDamage -= 15;
	}
	else if(difficulty.equals("2")){
		maxEnemyHealth -= 15;
		EnemyAttackDamage -= 10;
		numHealthPotions -= 1;
		healthPotionHealAmount -= 5;
		healthPotionDropChance -= 10;
	}
	else if(difficulty.equals("3")){
		numHealthPotions -= 1;
		healthPotionHealAmount -= 10;
		healthPotionDropChance -= 15;
	}
	System.out.println("Welcome to the Dungeon");
	GAME:

	while(running){
		System.out.println("---------------------------------------------");
		int enemyHealth = rand.nextInt(maxEnemyHealth);
		String enemy = enemies[rand.nextInt(enemies.length)];
	
		System.out.println("\t#"+ enemy + " Has Appeared! #\n");

		while(enemyHealth > 0){
			System.out.println("\tYour HP: "+ health);
			System.out.println("\t"+ enemy + "'s HP: "+ enemyHealth);
			System.out.println("\n\tWhat would you like to do?");
			System.out.println("\t1. Attack");
			System.out.println("\t2. Drink Health Potion");
			System.out.println("\t3. Run!");
			System.out.println("\t9. Exit");

			String input = in.nextLine();
			if(input.equals("1")){
				int damageDealt = rand.nextInt(attackDamage);
				int damageTaken = rand.nextInt(EnemyAttackDamage);

				enemyHealth -= damageDealt;
				health -= damageTaken;

				System.out.println("\t> You strike the "+ enemy +" for "+ damageDealt + " damage");
				System.out.println("\t> You received "+ damageTaken +" in retaliation ");

				if(health < 1){
					System.out.println(">You have taken too much damage and will have to retreat!");
					break;
				}
			}
			else if(input.equals("2")){
				if(numHealthPotions>0){
					health += healthPotionHealAmount;
					numHealthPotions--;
					System.out.println("\t> You drank the health potion. "+ healthPotionHealAmount + " has been regenerated. You now have " + health + " HP. You have " + numHealthPotions + " health potions left.\n");
				}
				else{
					System.out.println("\t> You have no health Potions left. Defeat the enemy for a chance to get one!\n");
				}
			}
			else if(input.equals("3")){
				System.out.println("\t>You run away from the " + enemy + "!");
				continue GAME;
			}
			else if(input.equals("9")){
				System.out.print("\033[H\033[2J");
				System.out.flush();
				System.out.println("You have exited the dungeon, successful from your adventures");
				System.out.println("You defeated "+ enemyDefeated + " enemies");
				System.exit(1);
			}
			else{
				System.out.println("Invalid Command");
			}
			System.out.println("Press Enter key to continue...");
        		try
        		{
            			System.in.read();
        		}  
        		catch(Exception e)
        		{}  
			System.out.print("\033[H\033[2J");
			System.out.flush();
		}
		if(health<1){
			System.out.println("You were too weak for battle, you ran out of the dungeon!");
			System.out.println("You defeated "+ enemyDefeated + " enemies");
			break;
		}
		System.out.println("---------------------------------------------");
		System.out.println(" # " + enemy + " was defeated! #");
		enemyDefeated++;
		System.out.println("# You have " + health + " HP left");
		if(rand.nextInt(100) < healthPotionDropChance){
			numHealthPotions++;
			System.out.println(" # The "+enemy+ " dropped a health potion! #");
			System.out.println(" # You now have "+ numHealthPotions + " health potion(s) # ");
		}
		System.out.println("What would you like to you?");
		System.out.println("1. Continue Fighting");
		System.out.println("2. Exit Dungeon");
		String input = in.nextLine();
		while(!input.equals("1") && !input.equals("2")){
			System.out.println("Invalid Command!");
			input = in.nextLine();
		}
		if(input.equals("1")){
			System.out.println("You Continue on your adventure!");
			System.out.print("\033[H\033[2J");
			System.out.flush();
		}
		else if(input.equals("2")){
			System.out.println("You have exited the dungeon, successful from your adventures");
			System.out.println("You defeated "+ enemyDefeated + " enemies");
			break;
		}
	}
}
}
