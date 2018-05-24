package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import rowmapper.UserRowMapper;
import dao.UserDao;
import entity.User;

public class UserDaoImpl implements UserDao {

	private JdbcTemplate jdbcTemplate;

	public void deleteById(Integer id) {
		String sql=new StringBuffer()
			.append("delete from t_user ")
			.append("where id=? ")
			.toString();
		jdbcTemplate.update(sql,new Object[]{id});
	}

	public void insert(User user) {
		String sql=new StringBuffer()
			.append("insert into t_user ")
			.append("    (username,password) ")
			.append("values ")
			.append("    (?,?) ")
			.toString();
		jdbcTemplate.update(sql,new Object[]{user.getUsername(),user.getPassword()});
	}

	public List<User> selectAll() {
		String sql=new StringBuffer()
			.append("select * ")
			.append("from t_user ")
			.toString();
		return jdbcTemplate.query(sql, new UserRowMapper());
	}

	public User selectById(Integer id) {
		String sql=new StringBuffer()
			.append("select * ")
			.append("from t_user ")
			.append("where id=? ")
			.toString();
		return (User) jdbcTemplate.queryForObject(sql, new Object[]{id},new UserRowMapper());
	}

	public void update(User user) {
		String sql=new StringBuffer()
			.append("update t_user ")
			.append("set password=? ")
			.append("where id=? ")
			.toString();
		jdbcTemplate.update(sql,new Object[]{user.getPassword(),user.getId()});
	}

	public Integer save(final User user) {
		KeyHolder keyHolder=new GeneratedKeyHolder();
		
		jdbcTemplate.update(new PreparedStatementCreator(){

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

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}
