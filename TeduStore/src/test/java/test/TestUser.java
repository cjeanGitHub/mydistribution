package test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.store.bean.User;
import cn.tedu.store.mapper.UserMapper;
import cn.tedu.store.service.IUserService;

public class TestUser {
	@Test
	public void testGetUserById(){
		ApplicationContext ac = 
				new ClassPathXmlApplicationContext(
						"spring-dao.xml",
						"spring-service.xml");
		IUserService us = ac.getBean("userService",
				IUserService.class);
		System.out.println(
				us.getUserById(2));
	}
	@Test
	public void testUpdateUser(){
		ApplicationContext ac = 
				new ClassPathXmlApplicationContext(
						"spring-dao.xml",
						"spring-service.xml");
		IUserService us = ac.getBean("userService",
				IUserService.class);
		us.updateUser(2, "manager", 
				null,"13800138000", null);
	}
	@Test
	public void testChangePassword(){
		ApplicationContext ac = 
				new ClassPathXmlApplicationContext(
						"spring-dao.xml",
						"spring-service.xml");
		IUserService us = ac.getBean("userService",
				IUserService.class);
		try{
			us.changePassword(1,"111111","123456");
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testSelectById(){
		ApplicationContext ac = 
				new ClassPathXmlApplicationContext(
						"spring-dao.xml",
						"spring-service.xml");
		UserMapper um = ac.getBean("userMapper",
				UserMapper.class);
		System.out.println(
				um.selectById(1).getUsername());
	}
	@Test
	public void testUpdate(){
		ApplicationContext ac = 
				new ClassPathXmlApplicationContext(
						"spring-dao.xml",
						"spring-service.xml");
		
		UserMapper um = ac.getBean("userMapper",
				UserMapper.class);
		User user = new User();
		user.setId(1);
		//user.setPassword("111111");
		user.setUsername("王晓华");
		user.setGender(1);
		user.setEmail("wangxiaohua@tedu.cn");
		user.setPhone("13800139000");
		
		um.update(user);
	}
	@Test
	public void testLogin(){
		ApplicationContext ac = 
				new ClassPathXmlApplicationContext(
						"spring-dao.xml",
						"spring-service.xml");
		
		IUserService us = 
				ac.getBean("userService",IUserService.class);
	
		System.out.println(
				us.login("admin123","123459"));
	
	}
	@Test
	public void testCheckUsername(){
		ApplicationContext ac = 
				new ClassPathXmlApplicationContext(
						"spring-dao.xml",
						"spring-service.xml");
		IUserService us = 
				ac.getBean("userService",
						IUserService.class);
		System.out.println(
				us.checkUsername("admin123"));
	}
	@Test
	public void testCheckEmail(){
		ApplicationContext ac = 
				new ClassPathXmlApplicationContext(
						"spring-dao.xml",
						"spring-service.xml");
		IUserService us = ac.getBean("userService",
				IUserService.class);
		System.out.println(us.checkPhone("13800138001"));
	}
	@Test
	public void testSelectByEmail(){
		ApplicationContext ac = 
				new ClassPathXmlApplicationContext(
						"spring-dao.xml",
						"spring-service.xml");
		UserMapper um = ac.getBean("userMapper",
				UserMapper.class);
		System.out.println(
				um.selectByPhone("13800138001"));
		
	}
	@Test
	public void testRegister(){
		ApplicationContext ac = 
				new ClassPathXmlApplicationContext(
						"spring-dao.xml",
						"spring-service.xml");
		IUserService us = ac.getBean("userService",
				IUserService.class);
		User user = new User();
		user.setUsername("admin124");
		user.setPassword("123456");
		user.setEmail("admin@tedu.cn");
		user.setPhone("13800138000");
		us.register(user);
	}
	
	
	@Test
	public void testSelectByUsername(){
		ApplicationContext ac = 
				new ClassPathXmlApplicationContext(
						"spring-dao.xml");
		UserMapper um = ac.getBean("userMapper",
				UserMapper.class);
		User user = um.selectByUsername("admin124");
		System.out.println(user);
	}
	
	@Test
	public void testInsert(){
		ApplicationContext ac = 
				new ClassPathXmlApplicationContext(
						"spring-dao.xml");
		UserMapper um = 
				ac.getBean("userMapper",
						UserMapper.class);
		User user = new User();
		user.setUsername("admin123");
		user.setPassword("123456");
		user.setEmail("admin@tedu.cn");
		user.setPhone("13800138000");
		um.insert(user);
	}

}










