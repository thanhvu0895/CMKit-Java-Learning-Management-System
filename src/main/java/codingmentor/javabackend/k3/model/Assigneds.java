package codingmentor.javabackend.k3.model;
import java.sql.Time;

public class Assigneds {
	private int assignment_id;
	private int klass_id;
	private Time due_date;
	private boolean allow_late_submissions;
	private int max_contributors;
	private double max_points_override;
	private double point_value_scale;
	private int repo_id;
	private boolean limit_resubmissions;
	private int resubmission_limit;
	private int allow_resubmissions;
	private boolean hide_grades;
    
}
