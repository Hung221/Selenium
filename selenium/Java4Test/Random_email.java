package Java4Test;

import java.util.Random;
// Ko duoc dat ten class trung voi ten ham
public class Random_email {
	static String emailAdd;
	public static void main (String[] args) {
		Random rand = new Random();
		emailAdd = "automation" + rand.nextInt(999) + "@gmail.com";
		
		System.out.println("automation" + rand.nextInt(9999999) + "@gmail.com");
	}

}
