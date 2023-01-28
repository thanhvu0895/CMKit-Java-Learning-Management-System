package codingmentor.javabackend.k3.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import codingmentor.javabackend.k3.model.GradeCategory;

public class GradeCategoryMapper  implements RowMapper<GradeCategory> {
	@Override
	public GradeCategory map(ResultSet results)  throws SQLException {
		return new GradeCategory()
			.id(results.getInt("id"))
			.title(results.getString("title"))
			.klass_id(results.getInt("klass_id"))
			.course_id(results.getInt("course_id"))
			.weight(results.getDouble("weight"));
	}
}