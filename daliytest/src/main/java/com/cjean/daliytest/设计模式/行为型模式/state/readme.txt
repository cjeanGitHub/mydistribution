设计模式之：
	行为型模式：
		状态模式：
		
			本质：
				- 用于解决系统中复杂对象的状态转换以及不同状态下行为的封装问题
			结构：
				- Context环境状态控制类
					- 环境类中维护一个state对象，他是定义了当前的状态
					
				- State抽象状态类
				
				- ConcrateState具体状态类
					- 每一个封装了一个状态对应的行为
				
			开发中的场景：
				- 银行系统中的账户状态的控制
				- OA系统中公文状态的管理
				- 酒店系统中，房间状态的管理
				- 线程对象各种状态的切换
