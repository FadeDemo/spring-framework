package org.fade.demo.springframework.jdbc

import org.springframework.jdbc.core.RowMapper

import java.sql.ResultSet
import java.sql.SQLException

class UserRowMapper implements RowMapper<User> {

	@Override
	User mapRow(ResultSet rs, int rowNum) throws SQLException {
		def user = new User(id: rs.getInt("id"), name: rs.getString("name"),
				age: rs.getInt("age"), sex: rs.getString("sex"))
		user
	}

}
