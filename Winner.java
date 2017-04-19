import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
//import java.util.StringTokenizer;
public class Winner {
	public static void main(String [] args) throws IOException{
		File winners = new File("winners.txt");
		ArrayList<String> users = new ArrayList<String>();
		ArrayList<Integer>wins = new ArrayList<Integer>();
		ArrayList<String> usersOrd = new ArrayList<String>();
		ArrayList<Integer>winsOrd = new ArrayList<Integer>();
		Scanner win = new Scanner(winners);//new Scanner to read file
		int gamesW = -1;
		String name = "Todd";//TODO DELETE
		boolean won = true;//TODO DELETE
		
		for(int i = 0; win.hasNext(); i++){
			users.add(win.nextLine());
			wins.add(Integer.parseInt((win.nextLine())));
		}
		win.close();
		
		if(!users.contains(name)){
			users.add(name);
			wins.add(0);
		}
		if(won){
			for (int i = 0; i<users.size(); i++)
				if(users.get(i).equalsIgnoreCase(name)){
					gamesW = wins.get(i);
					wins.remove(i);
					wins.add(i, gamesW+1);
				}
		}
		
		for(int i = 0; i<wins.size(); i++){
			if(winsOrd.isEmpty()){//if there are no values in the arrayList
				winsOrd.add(wins.get(i));
				usersOrd.add(users.get(i));
			}
			else{//if there are values in the arrayList
				boolean set = false;//variable to check if current has been added
				for(int j = 0; j<winsOrd.size() & !set; j++){//loop continues until arrayList is gone through or current is added
					if(winsOrd.get(j)<wins.get(i)){//if the number at the index is less than the current number
						winsOrd.add(j,wins.get(i));//add the current number at that index
						usersOrd.add(j,users.get(i));
						set = true;//current has been added
					}
					else if(j == winsOrd.size()-1 && !set){//if the entire array list has been gone through and current is not added
						winsOrd.add(wins.get(i));
						usersOrd.add(users.get(i));
						set = true;//current is added
					}			
				}
			}
		}
		
		PrintWriter winOT = new PrintWriter("winners.txt");//create file
		for(int i = 0; i<users.size(); i++){
			winOT.println(users.get(i));
			winOT.println(wins.get(i));
		}
		winOT.close();//close file
		
		PrintWriter winP = new PrintWriter("winnersP.txt");//create file
		winP.println("Win Count: \n");
		for(int i = 0; i<usersOrd.size(); i++){
			winP.println(usersOrd.get(i) + " has won " + winsOrd.get(i) + " game(s).");
		}
		winP.close();//close file
	}
}

