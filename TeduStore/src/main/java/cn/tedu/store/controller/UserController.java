package cn.tedu.store.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.store.bean.ResponseResult;
import cn.tedu.store.bean.User;
import cn.tedu.store.service.IUserService;
import cn.tedu.store.service.ex.UserNameAlreadyExsitException;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
	@Resource
	private IUserService userService;
	
	/**
	 * 显示个人信息
	 * @return
	 */
	@RequestMapping("/showPersonInfo.do")
	public String showPersonInfo(){
		return "personInfo";
	}
	
	@RequestMapping("/showPassword.do")
	public String showPassword(){
		return "personal_password";
	}
	/**
	 * 显示登录页面
	 * @return view组件名 
	 */
	@RequestMapping("/showLogin.do")
//	@ResponseBody
	public String showLogin(){
		return "login";
	}
	/**
	 * 显示页面
	 * @return
	 */
	@RequestMapping("/showRegister.do")
	public String showRegister(){
		return "register";
	}
	/**
	 * 修改个人信息
	 * @param session
	 * @param username
	 * @param email
	 * @param phone
	 * @param gender
	 * @return
	 */
	@RequestMapping("/updateUser.do")
	@ResponseBody
	public ResponseResult<Void> updateUser(
			HttpSession session,String username,
			String email,String phone,Integer gender){
		ResponseResult<Void> rr = 
				new ResponseResult<Void>();
		try{
			userService.updateUser(
					this.getId(session), username, email, phone, gender);
			
			//设置修改后的user对象
			session.setAttribute("user",
					userService.getUserById(
							this.getId(session)));
			rr.setState(1);
			rr.setMessage("修改成功");
		}catch(Exception e){
			rr.setState(0);
			rr.setMessage(e.getMessage());
		}
		return rr;
	}
	
	
	@RequestMapping("/password.do")
	@ResponseBody
	public ResponseResult<Void> password(
			HttpSession session,String oldPwd,
			String newPwd){
		//1.创建对象  rr
		ResponseResult<Void> rr = 
				new ResponseResult<Void>();
		try{
			//2.调用业务层方法 changePassword()
			
			userService.changePassword(
					this.getId(session),
					oldPwd, newPwd);
			//3.1,密码修改成功
			rr.setState(1);
			rr.setMessage("密码修改成功");
		}catch(Exception e){
			//4.0，e.getMessage()
			rr.setState(0);
			rr.setMessage(e.getMessage());
		}
		return rr;
	}
	
	/**
	 * 退出
	 * @param session
	 * @return
	 */
	@RequestMapping("/exit.do")
	public String exit(HttpSession session){
		//session无条件失效
		session.invalidate();
		return "redirect:../main/showIndex.do";
	}
	
	@RequestMapping("/login.do")
	@ResponseBody
	public ResponseResult<Void> login(
			String username,String password,
			HttpSession session){
		ResponseResult<Void> rr = 
				new ResponseResult<Void>();
		try{
			User user = 
					userService.login(username, password);
			//登录成功后，user对象存到session中
			session.setAttribute("user",user);
			rr.setState(1);
			rr.setMessage("登录成功");
		}catch(Exception e){
			rr.setState(0);
			rr.setMessage(e.getMessage());
			
		}
		
		return rr;
	}
	
	
	/**
	 * 验证用户名是否可用
	 */
	@RequestMapping("/checkUsername.do")
	@ResponseBody
	public ResponseResult<Void> 
		checkUsername(String username){
		ResponseResult<Void> rr = 
				new ResponseResult<Void>();
		if(userService.checkUsername(username)){
			rr.setState(0);
			rr.setMessage("用户名已存在");
		}else{
			rr.setState(1);
			rr.setMessage("用户名可以使用");
		}
		return rr;
		
	}
	@RequestMapping("/checkEmail.do")
	@ResponseBody
	public ResponseResult<Void> checkEmail(String email){
		ResponseResult<Void> rr = 
				new ResponseResult<Void>();
		if(userService.checkEmail(email)){
			rr.setState(0);
			rr.setMessage("邮箱已存在");
		}else{
			rr.setState(1);
			rr.setMessage("邮箱可以使用");
		}
		return  rr;
	}
	@RequestMapping("/checkPhone.do")
	@ResponseBody
	public ResponseResult<Void> checkPhone(String phone){
		ResponseResult<Void> rr = 
				new ResponseResult<Void>();
		if(userService.checkPhone(phone)){
			rr.setState(0);
			rr.setMessage("电话号码已存在");
		}else{
			rr.setState(1);
			rr.setMessage("电话号码可以使用");
		}
		return rr;
	}
	/**
	 * 用户注册
	 * @param username
	 * @param password
	 * @param email
	 * @param phone
	 * @return
	 */
	@RequestMapping("/register.do")
	@ResponseBody
	public ResponseResult<Void> register(
			@RequestParam("uname") String username,
			@RequestParam("upwd") String password,
			String email,String phone){
		ResponseResult<Void> rr = 
				new ResponseResult<Void>();
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		user.setPhone(phone);
		try{
			userService.register(user);
		}catch(UserNameAlreadyExsitException e){
			rr.setState(0);
			rr.setMessage("用户名已经存在");
		}
		
		return rr;
		
	}
}







