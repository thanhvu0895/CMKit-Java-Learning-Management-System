package codingmentor.javabackend.k3.model;

import codingmentor.javabackend.k3.Utils.EnumUtils;

public class Assignment {
	private int id;
	private String title;
	private int klass_id;
	private int course_id;
	private Integer grade_category_id;
	private int files_repo_id;
	private int template_repo_id;
	private int assignment_type;
	private String permitted_filetypes;
	private String description;
	private int file_limit;
	private int file_or_link;
	
   /**
    * Repository Functions
    */
	
	public String getFileOrLinkString() {
		return EnumUtils.file_or_linkEnum.values()[this.file_or_link].toString();		
	}
	

	public String getAssignmentType() {
		return EnumUtils.assignment_typeEnum.values()[this.assignment_type].toString();		
	}
	
	/*
	 * Getters, Setters
	 */
	
	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public int getKlass_id() {
		return klass_id;
	}

	public int getCourse_id() {
		return course_id;
	}

	public Integer getGrade_category_id() {
		return grade_category_id;
	}

	public int getFiles_repo_id() {
		return files_repo_id;
	}

	public int getTemplate_repo_id() {
		return template_repo_id;
	}

	public int getAssignment_type() {
		return assignment_type;
	}

	public String getPermitted_filetypes() {
		return permitted_filetypes;
	}

	public String getDescription() {
		return description;
	}

	public int getFile_limit() {
		return file_limit;
	}

	public int getFile_or_link() {
		return file_or_link;
	}

	public Assignment() {}


	/* Fluen Style API */
	public Assignment id(int id) {
	    this.id = id;
	    return this;
	}
	
	public Assignment title(String title) {
	    this.title = title;
	    return this;
	}
	
	public Assignment klass_id(int klass_id) {
	    this.klass_id = klass_id;
	    return this;
	}
	
	public Assignment course_id(int course_id) {
	    this.course_id = course_id;
	    return this;
	}
	
	public Assignment grade_category_id(Integer grade_category_id) {
	    this.grade_category_id = grade_category_id;
	    return this;
	}
	
	public Assignment files_repo_id(int files_repo_id) {
	    this.files_repo_id = files_repo_id;
	    return this;
	}
	
	public Assignment template_repo_id(int template_repo_id) {
	    this.template_repo_id = template_repo_id;
	    return this;
	}
	
	public Assignment assignment_type(int assignment_type) {
	    this.assignment_type = assignment_type;
	    return this;
	}
	
	public Assignment assignment_type(int assignment_type, String assignmentTypeString) {
	    this.assignment_type = assignment_type;
	    return this;
	}
	
	public Assignment permitted_filetypes(String permitted_filetypes) {
	    this.permitted_filetypes = permitted_filetypes;
	    return this;
	}
	
	public Assignment description(String description) {
	    this.description = description;
	    return this;
	}
	
	public Assignment file_limit(int file_limit) {
	    this.file_limit = file_limit;
	    return this;
	}
	
	public Assignment file_or_link(int file_or_link) {
	    this.file_or_link = file_or_link;
	    return this;
	}
	
	
}
