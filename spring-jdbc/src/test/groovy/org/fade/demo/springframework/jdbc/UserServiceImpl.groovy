package org.fade.demo.springframework.jdbc

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate

import javax.sql.DataSource
import java.sql.Types

class UserServiceImpl implements UserService {

	JdbcTemplate jdbcTemplate

	@Autowired
	void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	void save(User user) {
		jdbcTemplate.update("insert into user(name, age, sex) values(?, ?, ?)",
				new Object[] {user.getName(), user.getAge(), user.getSex()},
				new int[] {Types.VARCHAR, Types.INTEGER, Types.VARCHAR})
	}

	@Override
	List<User> getUsers() {
		jdbcTemplate.query("select * from user", new UserRowMapper())
	}

}
