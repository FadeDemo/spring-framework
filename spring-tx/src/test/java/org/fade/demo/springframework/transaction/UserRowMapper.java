package org.fade.demo.springframework.transaction;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author fade
 * @date 2021/12/21
 */
public class UserRowMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User(rs.getInt("id"),
				rs.getString("name"),
				rs.getInt("age"),
				rs.getString("sex"));
		return user;
	}

}
