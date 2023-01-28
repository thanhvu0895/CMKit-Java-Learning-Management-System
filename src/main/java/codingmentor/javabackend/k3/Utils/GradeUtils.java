package codingmentor.javabackend.k3.Utils;

public class GradeUtils {
	public static String get_color_for_grade(double grade) {
		double red = 0;
		double green = 0;
		double blue = 0;
	  
	  if (grade < 0.5)
		red = 255;
	  else if (grade < 0.75) {
		red = 255;
		green = (grade-0.5)*4 * 255;
	  }
	  else if (grade <= 1.0) {
	    green = 255;
		red = 255 - (grade - 0.75) * 4 * 255;
	  }
	  else
	    green = 255;
	  
	  return "rgb(" + Double.toString(red) + "," + Double.toString(green) +"," + Double.toString(blue) + ")";
	}
	  //helper_method :get_color_for_grade
}