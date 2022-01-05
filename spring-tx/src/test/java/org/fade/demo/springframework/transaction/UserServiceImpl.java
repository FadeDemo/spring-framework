package org.fade.demo.springframework.transaction;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Types;

/**
 * 用户服务实现类
 * 默认情况下Spring的事务只对 {@link RuntimeException} 和 {@link Error} 起作用，
 * 这点在 {@link Transactional#rollbackFor()} 的 JavaDoc 中也有说明
 * @author fade
 * @date 2021/12/23
 */
@Transactional(propagation = Propagation.REQUIRED)
@Service
public class UserServiceImpl implements UserService{

	@Resource(name = "propagationServiceImpl")
	private PropagationService propagationService;

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void save(User user) {
		jdbcTemplate.update("insert into user(name, age, sex) values(?, ?, ?)",
				new Object[] {user.getName(), user.getAge(), user.getSex()},
				new int[] {Types.VARCHAR, Types.INTEGER, Types.VARCHAR});
//		propagationService.nestedThrowException(user);
//		try {
//			propagationService.nestedThrowException(user);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		propagationService.nestedOk(user);
		throw new RuntimeException("test transaction");
	}

}
