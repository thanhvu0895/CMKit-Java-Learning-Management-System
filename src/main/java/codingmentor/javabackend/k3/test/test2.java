package codingmentor.javabackend.k3.test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import codingmentor.javabackend.k3.Utils.DateValidatorDateTimeFormatter;

public class test2 {
	enum Color{
		RED, GREEN, BLUE;
	}
	
	public static void main(String[] args) {
		
		Color c1 = Color.GREEN;
		
		System.out.println(c1.ordinal());
		System.out.println(c1.name());
		
		String[] emailList = "a@      1, b@3, th a  nh v u@ 49  3208"
				.replaceAll("\\s","").split(",");
		for (String string : emailList) {
			System.out.println(string);
		}
	}
}
