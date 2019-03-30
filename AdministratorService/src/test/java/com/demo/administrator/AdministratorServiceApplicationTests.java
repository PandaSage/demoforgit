package com.demo.administrator;

import java.sql.Connection;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdministratorServiceApplicationTests {

	@Autowired
	private DataSource ds;

	/**
	 * db connection 확인 test
	 * @throws Exception
	 */
	@Test
	public void testConnectionForMySql() throws Exception{
		System.out.println("ds : "+ds);
		//ds(DataSource)에서 Connection을 얻어내고
		Connection con = ds.getConnection();
		//확인 후
		System.out.println("con : " + con);
		//close
		con.close();
	}
}
