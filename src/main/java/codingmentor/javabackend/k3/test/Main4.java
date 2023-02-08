//package codingmentor.javabackend.k3.test;
//
//import java.util.Scanner;
//
//public class Main4 {
//
//	  public static int MathChallenge(int num) {
//	    String temp = Integer.toString(num);
//
//	    if (num < 10) {
//	      return 0;
//	    }
//	    
//	    int[] numArray = new int[temp.length()];
//	    
//	    for (int i = 0; i < temp.length(); i++) {
//	      numArray[i] = Character.getNumericValue(temp.charAt(i));
//	    }
//
//	    int sum = numArray[0];
//	    
//	    for (int i = 1; i < temp.length(); i++) {
//	      sum += numArray[i];
//	    }
//
//	    if (sum == 10) return 2;
//	    if (sum < 10) return sum;
//
//	    while (sum > 10) {
//	    for (int i = 1; i < temp.length(); i++) {
//	      sum += numArray[i];
//	    }
//
//	    if (sum == 10) {
//	      return 2;
//	    }
//	    if (sum < 10) {
//	      break;
//	    }    
//	  }
//	   
//
//	    logger.info("Final sum is: " + sum);
//	    return sum;
//
//	  }
//
//
//	  public static void main (String[] args) {  
//	    // keep this function call here     
//	    Scanner s = new Scanner(System.in);
////	    System.out.print(MathChallenge(s.nextLine())); 
//	  }
//
//}
