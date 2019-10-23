package cn.tedu.store.service.ex;

public class UserNameAlreadyExsitException
	extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	public UserNameAlreadyExsitException(){
		
	}
	public UserNameAlreadyExsitException(String message){
		super(message);
	}

}
