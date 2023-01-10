package codingmentor.javabackend.k3.model;
import java.sql.Timestamp;   

public class Submissions {
	private int assigned_id;
	private int repo_id;
	private boolean turned_in;
	private Timestamp turned_in_time;
	private double point_adjustment;
	private double percent_modifier;
	private boolean point_override;
	private boolean blank;
	private boolean professor_uploaded;
	private String overall_comments;
	private double cached_grade;
	private boolean notified_graded;
	private boolean hide_rubric;
	private boolean force_allow_resubmit;

}
