import java.util.*; 

class Passenger {
    public String name;
    public int priority; 

    public Passenger(String name, int priority) {
        this.name = name; 
        this.priority = priority;
    }  
}


class Comparable implements Comparator <Passenger>{
    public int compare(Passenger p1, Passenger p2) {
        return p1.priority - p2.priority;
    }
}


public class Upgrade {
    
    //variables
    private int locator;
    boolean copied = false;
    boolean retrieved = false;

    //constructors
    HashMap<Integer, Passenger> upgradeList = new HashMap<>();
    HashMap<Integer, String> getName = new HashMap<>();
    ArrayList<String> name = new ArrayList<>();              //used to check names in the upgrade list
    ArrayList<Integer> priority = new ArrayList<>();         //used to check priorities in the upgrade list
    PriorityQueue<Passenger> passenger = new PriorityQueue<>(new Comparable());

    //Menu view
    static void Menu() {
        System.out.println("Menu");
        System.out.println("u: Request Upgrade");
        System.out.println("c: Cancel Upgrade");
        System.out.println("v: View upgrade priority list");
        System.out.println("q: Quit");
        System.out.println("");
    }

    //function to add a new passenger to the upgrade queue
    void addPassenger(String name, int priority) {
        if(upgradeList.containsKey(intIndexReturn(priority))){
            System.out.println("\nThis priority: " + priority + " has been already filled\n");
        }else {
        passenger.add(new Passenger(name, priority));
        copied = false;  
        }
        
    }

    //function to remove a passenger from the upgrade queue
    void removePassenger(String input) {
        if (upgradeList.isEmpty()){
            System.out.println("The upgrade queue is empty");
            return;
        } 
        else if(!upgradeList.containsKey(stringIndexReturn(input))){
            System.out.println(input + " is not in the upgrade queue");
        }else {
            int index = stringIndexReturn(input);
            upgradeList.remove(index);
            getName.remove(index);
        }
    }

    private int stringIndexReturn(String str) {
		int counter = -1;
		for (int i = 0; i < name.size(); i++) {
			if(str.equals(name.get(i)))
				counter = i;	
		} 
		return counter;
	}

    private int intIndexReturn(int num) {
		int counter = -1;
		for (int i = 0; i < priority.size(); i++) {
			if(num == priority.get(i)){

                counter = i;
            }	
		} 
		return counter;
	}

    void copy() {
        Iterator<Passenger>iterator = passenger.iterator();
        while(iterator.hasNext()) {
            name.add(passenger.peek().name);                //copy to separate list of names 
            priority.add(passenger.peek().priority);        //copy to separate list for priority numbers
            upgradeList.put(locator++, passenger.poll());   //copy to upgradeList hashMap using locators 
        }
    }

    //grab a passenger from the upgrade queue
    private void getName(){
        for(Map.Entry<Integer, Passenger> entry : upgradeList.entrySet()){
            getName.put(entry.getValue().priority, entry.getValue().name);
        }
        retrieved = true;
    }

    //finding the priority to show k-highest
    public void KUpgradeList(int k) {
        getName();

        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>();
        for(Map.Entry<Integer, Passenger> entry: upgradeList.entrySet()) {
            maxHeap.add(entry.getValue().priority);
        }

        for(int i = 0; i < k; i++) {
            System.out.println(/*maxHeap.peek() + " " + */getName.get(maxHeap.peek())); //uncomment to show priority numbers for k-highest
            maxHeap.poll();
        }
    }

    

    int size() {
        return upgradeList.size();
    }

    //menu option functions
    public static char SelectionMenu(Upgrade map) {
        Scanner scnr = new Scanner(System.in);
        int priority; 
        String name; 

        while(true) {
            System.out.println("Choose an option: ");
            char ch = scnr.next().charAt(0);
            scnr.nextLine();

            //commands for the menu

            //upgrade reqeust
            if(ch == 'u' || ch == 'U' ) {
                System.out.println("Enter passenger name: ");
                name = scnr.nextLine();
                System.out.println("Enter passenger a priority number: ");
                if(scnr.hasNextInt()) {
                    priority = scnr.nextInt();
                    map.addPassenger(name, priority);
                    Menu();
                } else {
                    System.out.println("");
                }
            }

            //cancel upgrade request
            else if(ch == 'c' || ch == 'C' ) {
                if(!map.copied) {
                    map.copy();
                }
                if(map.upgradeList.isEmpty()){
                    System.out.println("The list is empty");
                } else {
                    System.out.println("Please enter  the name of the passenger you want to cancel");
                    String str = scnr.nextLine();
                    map.removePassenger(str);
                    System.out.println("");
                    Menu();
                }
            }
            
            //view list
            else if(ch == 'v' || ch == 'V') {
                if(!map.copied) {
                    map.copy();
                    int k = map.size();
                    System.out.println("\nList of Priority Flyers:");
                    map.KUpgradeList(k);
                    System.out.println("");
                }
                Menu();
            }

            //quit program
            else if(ch == 'q' || ch == 'Q') {
                System.exit(0);
            } else {
                System.out.println("\n**Please choose a valid choice**\n");
                Menu();
            }

        }
    }


    //main
    public static void main (String[] args) throws Exception {

        Upgrade map = new Upgrade();
		map.addPassenger("Jane", 45);
		map.addPassenger("Jack", 23);
        map.addPassenger("Louis", 59);
		map.copy();
		Menu();
		SelectionMenu(map);
        
    }


}
