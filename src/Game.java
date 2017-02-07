import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class Game {
	private ArrayList <Ship> ship = new ArrayList<Ship>();
	static Game game = new Game();
	ArrayList<String> hList = new ArrayList<String>();
	ArrayList<String> mList = new ArrayList<String>();
	String guess, result;
	String cell = "";
	String alpha = "ABCDEFG";
	int num = 1;
	int incl;
	int incn;
	int numGuess = 0;
	boolean worked;
	
	public static void main(String[] args) {
		System.out.println("---GRID---");
		game.setGrid();
		game.setup();
	}
	
	public void setup(){
		ship.add(new Ship("SHIP1", 3));
		ship.add(new Ship("SHIP2", 3));
		ship.add(new Ship("SHIP3", 3));
		setLocations();
		System.out.println();
		
		//TEST - LOCATION OF SHIPS
		System.out.println("---SHIP LOCATION---");
		int j = 1;
		for(int i = 0; i < ship.size(); i++){
			System.out.println("Ship " + j + " location: " + ship.get(i).getLocations());
			j++;
		}
		
		System.out.println();
		System.out.println("Battleship game will commence!!");
		play();
	}
	
	public void setGrid(){
		int z = 0;
		for(int w = 0; w < alpha.length(); w++){
			num = 1;
			for(int q = 0; q < alpha.length(); q++){
				String character = "" + alpha.charAt(z);
				cell = character + num;
				System.out.print(cell + " ");
				num++;
			}
			z++;
			System.out.println();
		}
	}
	
	public void refreshGrid(){
		int z = 0;
		for(int w = 0; w < alpha.length(); w++){
			num = 1;
			for(int q = 0; q < alpha.length(); q++){
				String character = "" + alpha.charAt(z);
				cell = character + num;
				num++;
				System.out.print(cell + " ");
			}
			z++;
			System.out.println();
		}
	}
	
	public void play(){
		Scanner input = new Scanner (System.in);
		while(! ship.isEmpty()){
			result = "miss";
			System.out.println("Enter a guess: ");
			guess = input.nextLine();
			guess = guess.toUpperCase();
			mList.add(guess);
			numGuess++;
			for(int a = 0; a < ship.size(); a++){
				result = ship.get(a).check(guess);
				if(result.equals("kill")){
					result = ("You sunk " + ship.get(a).getName());
					hList.add(guess);
					mList.remove(guess);
					ship.remove(a);
					break;
				}if(result.equals("hit")){
					hList.add(guess);
					mList.remove(guess);
					break;
				}
			}
			System.out.println();
			System.out.println(guess + ": " + result);
			System.out.println("Number of ships remaining: " + ship.size());
			System.out.println("HIT List: " + hList);
			System.out.println("MISS List: " + mList);
		}
		input.close();
		finish();
	}

	public void finish(){
		if(numGuess == 9){
			System.out.println("PERFECT SCORE");
		}else{
			System.out.println("Number of guesses: " + numGuess);
		}
	}

	public void setLocations(){
		Random rand = new Random();
		ArrayList<String> locationToSet = new ArrayList<String>();
		ArrayList<String> temp = null;
		int let = 0;
		boolean worked;
		for(int b = 0; b < ship.size(); b++){
			worked = false;
			start:
				while(!worked){
					locationToSet.clear();
					worked = true;
					let = rand.nextInt(5);
					num = 1 + rand.nextInt(5);
					if(num % 2 == 0){
						incl = 1;
						incn = 0;
					}else{
						incl = 0;
						incn = 1;
					}
					for(int c = 0; c < ship.get(b).getSize(); c++){
						String loc = "" + alpha.charAt(let) + num;
						let += incl;
						num += incn;
						//check to make sure there are no duplicate cell locations
						for(int d = 0; d < ship.size(); d++){
							if(d != b){
								temp = ship.get(d).getLocations();
								if(temp.contains(loc)){
									worked = false;
									continue start;
								}
							}
						}
						locationToSet.add(loc);
					}
					ship.get(b).setLocation(locationToSet);
				}
		}
	}
}