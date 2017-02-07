import java.util.ArrayList;

public class Ship {

	private ArrayList<String> location = new ArrayList<String>(); //array list for locations
	private String name; //name of ships
	private int size; //size of ships
	
	//constructor takes the arguments and initializes it
	public Ship(String name, int size){
		this.name = name;
		this.size = size;
	}
	//get location
	public ArrayList<String> getLocations(){
		return location;
	}
	//get size
	public int getSize(){
		return size;
	}
	//get name
	public String getName(){
		return name;
	}
	//set location and add to arraylist
	public void setLocation(ArrayList<String> locationToSet){
		this.location.addAll(locationToSet);
	}
	//check method
	public String check(String guess){
		String result = "miss";
		if(location.contains(guess)){
			location.remove(guess);
			result = location.isEmpty() ? "kill" : "hit"; //checks location, if empty kill else hit
		}
		return result;
	}
}
