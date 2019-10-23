package cn.tedu.store.mapper;

import cn.tedu.store.bean.User;

/**
 * 对用户管理模块的持久层完成数据库的操作
 * @author adminitartor
 *
 */
public interface UserMapper {
	/**
	 * 通过id查询user信息
	 * @param id
	 * @return 如果存在，返回user对象，否则，返回null
	 * 
	 */
	User selectById(Integer id);
	
	/**
	 * 修改用户信息
	 * @param user
	 */
	void update(User user);
	/**
	 * 向数据库插入数据
	 * @param user
	 */
	void insert(User user);
	/**
	 * 通过用户名查询
	 * @param username：用户名
	 * @return 如果查询数据库有用户名，返回user对象
	 * 		      如果没有用户名，返回null
	 */
	User selectByUsername(String username);
	/**
	 * 通过email查询
	 * @param email
	 * @return 如果email存在返回1，否则，返回0
	 */
	Integer selectByEmail(String email);
	/**
	 * 通过phone查询
	 * @param phone
	 * @return 如果有返回 1， 否则返回0
	 */
	Integer selectByPhone(String phone);
	
}








