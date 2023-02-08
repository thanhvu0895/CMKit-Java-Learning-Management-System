//package codingmentor.javabackend.k3.test;
//
//import java.util.Scanner;
//
//public class Main3 {
//
//  public static String StringChallenge(String str) {
//    String[] strsArray = str.split("\\s");
//    int countRepetition = 0;
//    String repeatedString = "";
//    for (String s : strsArray) {
//    	logger.info("before replace: " + s);
//      s = s.replaceAll("[^A-Za-z]", "");
//      logger.info(s);
//      int maxCount = 0;
//      for (int i = 0; i < s.length(); i ++) {
//        int count = 1;
//        for (int j = i + 1; j < s.length(); j++) {
//          if (s.charAt(i) == Character.toLowerCase(s.charAt(j))) {
//            count ++;
//          }
//        }
//        if (count > maxCount) {
//          maxCount = count;
//          }
//      }
//        if (maxCount > countRepetition) {
//          countRepetition = maxCount;
//          repeatedString = s;
//        }
//    }
//    
//    return (countRepetition > 1) ? repeatedString : "-1";
//  }
//
//  public static void main (String[] args) {  
//    // keep this function call here     
//    Scanner s = new Scanner(System.in);
//    System.out.print(StringChallenge(s.nextLine())); 
//  }
//}
