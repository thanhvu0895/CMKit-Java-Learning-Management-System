package codingmentor.javabackend.k3.model;

import java.time.LocalDateTime;

public class Submission {
	private int id;
	private int assigned_id;
	private int repo_id;
	private boolean turned_in;
	private LocalDateTime turned_in_time;
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
	
	public int getId() {
		return id;
	}

	public int getAssigned_id() {
		return assigned_id;
	}

	public int getRepo_id() {
		return repo_id;
	}

	public boolean isTurned_in() {
		return turned_in;
	}

	public LocalDateTime getTurned_in_time() {
		return turned_in_time;
	}

	public double getPoint_adjustment() {
		return point_adjustment;
	}

	public double getPercent_modifier() {
		return percent_modifier;
	}

	public boolean isPoint_override() {
		return point_override;
	}

	public boolean isBlank() {
		return blank;
	}

	public boolean isProfessor_uploaded() {
		return professor_uploaded;
	}

	public String getOverall_comments() {
		return overall_comments;
	}

	public double getCached_grade() {
		return cached_grade;
	}

	public boolean isNotified_graded() {
		return notified_graded;
	}

	public boolean isHide_rubric() {
		return hide_rubric;
	}

	public boolean isForce_allow_resubmit() {
		return force_allow_resubmit;
	}

	public Submission() {}
	
	/* Fluen Style API */
	public Submission id(int id) {
	    this.id = id;
	    return this;
	}
	
	public Submission assigned_id(int assigned_id) {
	    this.assigned_id = assigned_id;
	    return this;
	}
	
	public Submission repo_id(int repo_id) {
	    this.repo_id = repo_id;
	    return this;
	}
	
	public Submission turned_in(boolean turned_in) {
	    this.turned_in = turned_in;
	    return this;
	}
	
	public Submission turned_in_time(LocalDateTime turned_in_time) {
	    this.turned_in_time = turned_in_time;
	    return this;
	}
	
	public Submission point_adjustment(double point_adjustment) {
	    this.point_adjustment = point_adjustment;
	    return this;
	}
	
	public Submission percent_modifier(double percent_modifier) {
	    this.percent_modifier = percent_modifier;
	    return this;
	}
	
	public Submission point_override(boolean point_override) {
	    this.point_override = point_override;
	    return this;
	}
	
	public Submission blank(boolean blank) {
	    this.blank = blank;
	    return this;
	}
	
	public Submission professor_uploaded(boolean professor_uploaded) {
	    this.professor_uploaded = professor_uploaded;
	    return this;
	}
	
	public Submission overall_comments(String overall_comments) {
	    this.overall_comments = overall_comments;
	    return this;
	}
	
	public Submission cached_grade(double cached_grade) {
	    this.cached_grade = cached_grade;
	    return this;
	}
	
	public Submission notified_graded(boolean notified_graded) {
	    this.notified_graded = notified_graded;
	    return this;
	}
	
	public Submission hide_rubric(boolean hide_rubric) {
	    this.hide_rubric = hide_rubric;
	    return this;
	}
	
	public Submission force_allow_resubmit(boolean force_allow_resubmit) {
	    this.force_allow_resubmit = force_allow_resubmit;
	    return this;
	}
}
