package codingmentor.javabackend.k3.Utils;

public class EnumUtils {
	
	public enum assignment_typeEnum {
		student_file, student_repo, professor_file, grade_only
	}
	
	public enum file_or_linkEnum {
		upload_files_only, provide_urls_only, both
	}
	
	public enum allow_resubmissionsEnum {
		never_resubmit, resubmit_before_deadline, resubmit_before_graded, resubmit_even_after_graded
	}
	
}
