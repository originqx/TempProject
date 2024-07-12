package com.zyb.springweb;

import com.zyb.springweb.bean.User;
import com.zyb.springweb.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest
class SpringwebApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private DataSource dataSource;

	@Test
	void dateSourceTest() throws SQLException {
		System.out.println("12312414123412-------------------");
		System.out.println(dataSource.getConnection());
	}

	@Test
	void getUserInfoByName() throws SQLException {
		System.out.println(dataSource.getConnection());
		System.out.println(userMapper.getUserInfoByName("lisi"));

		System.out.println("12312414123412-------------------");

	}

	@Test
	void getUserInfoById() throws SQLException {
		System.out.println(userMapper.getUserInfoById(2));

		System.out.println("12312414123412-------------------");

	}

	@Test
	void getAllUser() throws SQLException {
		System.out.println(dataSource.getConnection());
		System.out.println(userMapper.getAllUser());

		System.out.println("12312414123412-------------------");

	}

	@Test
	void insertUser() throws SQLException {
		System.out.println(userMapper.insertUser(new User(3,"wangwu", 43)));

		System.out.println("12312414123412-------------------");

	}

	@Test
	void updateUser() throws SQLException {
		System.out.println(userMapper.updateUser(new User(3,"zhaoliu", 11)));

		System.out.println("12312414123412-------------------");

	}

	@Test
	void deleteUser() throws SQLException {
		System.out.println(userMapper.deleteUser(3));

		System.out.println("12312414123412-------------------");

	}
}
