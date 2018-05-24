package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import rowmapper.UserRowMapper;
import dao.UserDao;
import entity.User;

public class UserDaoImpl2 extends JdbcDaoSupport implements UserDao {


	public void deleteById(Integer id) {
		String sql=new StringBuffer()
			.append("delete from t_user ")
			.append("where id=? ")
			.toString();
		getJdbcTemplate().update(sql,new Object[]{id});
	}

	public void insert(User user) {
		String sql=new StringBuffer()
			.append("insert into t_user ")
			.append("    (username,password) ")
			.append("values ")
			.append("    (?,?) ")
			.toString();
		getJdbcTemplate().update(sql,new Object[]{user.getUsername(),user.getPassword()});
	}

	public List<User> selectAll() {
		String sql=new StringBuffer()
			.append("select * ")
			.append("from t_user ")
			.toString();
		return getJdbcTemplate().query(sql, new UserRowMapper());
	}

	public User selectById(Integer id) {
		String sql=new StringBuffer()
			.append("select * ")
			.append("from t_user ")
			.append("where id=? ")
			.toString();
		return (User) getJdbcTemplate().queryForObject(sql, new Object[]{id},new UserRowMapper());
	}

	public void update(User user) {
		String sql=new StringBuffer()
			.append("update t_user ")
			.append("set password=? ")
			.append("where id=? ")
			.toString();
		getJdbcTemplate().update(sql,new Object[]{user.getPassword(),user.getId()});
	}

	public Integer save(final User user) {
		KeyHolder keyHolder=new GeneratedKeyHolder();
		
		getJdbcTemplate().update(new PreparedStatementCreator(){

			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				String sql=new StringBuffer()
					.append("insert into t_user ")
					.append("    (username,password) ")
					.append("values ")
					.append("    (?,?) ")
					.toString();
				PreparedStatement ps=con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
//				PreparedStatement ps=con.prepareStatement(sql,new String[]{"id"});
				ps.setString(1, user.getUsername());
				ps.setString(2, user.getPassword());
				return ps;
			}
			
		}, keyHolder);
		
		Long id=(Long) keyHolder.getKey();
		return id.intValue();
	}


}
