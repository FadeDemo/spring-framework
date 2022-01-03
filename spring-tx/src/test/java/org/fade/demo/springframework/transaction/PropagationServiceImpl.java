package org.fade.demo.springframework.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Types;

/**
 * @author fade
 * @date 2022/01/03
 */
@Service
public class PropagationServiceImpl implements PropagationService {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	@Transactional(propagation = Propagation.NESTED)
	public void nestedThrowException(User user) {
		jdbcTemplate.update("insert into user(name, age, sex) values(?, ?, ?)",
				new Object[] {user.getName(), user.getAge(), user.getSex()},
				new int[] {Types.VARCHAR, Types.INTEGER, Types.VARCHAR});
		throw new RuntimeException("test PROPAGATION_NESTED");
	}

	@Override
	@Transactional(propagation = Propagation.NESTED)
	public void nestedOk(User user) {
		jdbcTemplate.update("insert into user(name, age, sex) values(?, ?, ?)",
				new Object[] {user.getName(), user.getAge(), user.getSex()},
				new int[] {Types.VARCHAR, Types.INTEGER, Types.VARCHAR});
	}

}
