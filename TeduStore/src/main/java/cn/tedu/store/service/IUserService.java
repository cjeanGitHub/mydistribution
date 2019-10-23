package cn.tedu.store.service;

import cn.tedu.store.bean.User;

public interface IUserService {
	/**
	 * 通过id获取user对象
	 * @param id
	 * @return
	 */
	User getUserById(Integer id);
	/**
	 * 修改个人信息
	 * @param id
	 * @param username
	 * @param email
	 * @param phone
	 * @param gender
	 */
	void updateUser(Integer id,String username,
			String email,String phone,Integer gender);
	/**
	 * 修改密码
	 * @param id
	 * @param oldPwd
	 * @param newPwd
	 */
	void changePassword(Integer id,String oldPwd,
			String newPwd);
	/**
	 * 注册功能的方法
	 * @param user 封装了页面数据的对象
	 */
	void register(User user);
	/**
	 * 验证邮箱是否存在
	 * @param email
	 * @return 如果存在，返回true，否则返回false
	 */
	boolean checkEmail(String email);
	/**
	 * 电话号码验证
	 * @param phone
	 * @return
	 */
	boolean checkPhone(String phone);
	/**
	 * 用户名验证
	 * @param username
	 * @return 如果是null，返回false；否则，返回true
	 */
	boolean checkUsername(String username);
	/**
	 * 登录
	 * @param username
	 * @param password
	 * @return 登录成功返回user，否则抛出异常；UserNotFountExcetpion和
	 * 			PaasswordNotMatchException
	 */
	User login(String username,String password);
	
	
}







