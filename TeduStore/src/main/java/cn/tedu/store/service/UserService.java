package cn.tedu.store.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tedu.store.bean.User;
import cn.tedu.store.mapper.UserMapper;
import cn.tedu.store.service.ex.PasswordNotMatchException;
import cn.tedu.store.service.ex.UserNameAlreadyExsitException;
import cn.tedu.store.service.ex.UserNotFoundException;
@Service
public class UserService implements IUserService{
	@Resource
	private UserMapper userMapper;	
	public void register(User user) {
		//判断用户名是否存在，如果不存在 ，实现插入数据的操作
		//否则，抛出异常。
		if(userMapper.selectByUsername
				(user.getUsername())==null){
			userMapper.insert(user);
		}else{
			throw new UserNameAlreadyExsitException(
					"用户名已经存在");
		}
		
	}
	public boolean checkEmail(String email) {
		/*
		 * 如果邮箱存在，返回true，否则返回false
		 */
		return userMapper.selectByEmail(email)>0;
	}
	public boolean checkPhone(String phone) {
		
		return userMapper.selectByPhone(phone)>0;
	}
	public boolean checkUsername(String username) {
		//调用selectByUsername(username);
		User user = 
				userMapper.selectByUsername(username);
		//判断返回值是否为null
		if(user==null){
			//如果为null，返回false
			return false;
		}else{
		    //否则 返回true
		    return true;
		}
	}
	public User login(String username, String password) {
		//1.调用持久层selectByUsername()方法，返回值User 
		User user = userMapper.selectByUsername(username);
		//2.判断user对象是否为null
		if(user==null){
		//3.如果为null，常抛出异UserNotFoundException
			throw new UserNotFoundException("该用户不存在");
		}else{
			//4.如果不为null,从user对象中取出password，用password和页面输入的password比较；
			if(user.getPassword().equals(password)){
			//5.如果是true，返回user
				return user;
			}else{
			//6.如果false，抛出异常PasswordNotMatchException
				throw new PasswordNotMatchException("密码错误");
			}
		}
		
	}
	public void changePassword(Integer id, String oldPwd, String newPwd) {
		//1.调用selectById(id),返回user对象
		User user = userMapper.selectById(id);
		//2.判断（user!=null）,获取密码
		if(user!=null){
			//3.获取密码和oldPwd进行判断
			if(user.getPassword().equals(oldPwd)){
				//4.如果结果返回true
				User u = new User();
				u.setId(id);
				u.setPassword(newPwd);
				//5.调用持久层 update（u）
				userMapper.update(u);
			}else{
				//6.返回false，抛出异常PasswordNotMatchException("")
				throw new PasswordNotMatchException("密码错误");
			}
		}
				
	}
	public void updateUser(Integer id, String username, String email, String phone, Integer gender) {
		User user = new User();
		//判读用户名是否存在
		User u1 = userMapper.selectByUsername(username);
		if(u1==null){
			user.setUsername(username);
		}else{
			//用户名存在
			User u2 = userMapper.selectById(id);
			if(u2!=null){
				//不修改登录用户名
				if(u2.getUsername().equals(username)){
					
				}else{
					throw new 
					UserNameAlreadyExsitException("用户名已经存在");
				}
			}			
		}
		user.setId(id);
		user.setEmail(email);
		user.setPhone(phone);
		user.setGender(gender);
		userMapper.update(user);
	}
	public User getUserById(Integer id) {
		//返回user对象
		return userMapper.selectById(id);
	}

}









