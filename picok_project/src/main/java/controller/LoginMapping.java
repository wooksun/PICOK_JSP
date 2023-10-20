package controller;

public class LoginMapping {
		// singleton pattern으로 객체 생성
		private static LoginMapping instance = new LoginMapping();
		private LoginMapping() { }
		public static LoginMapping getInstance() {
			return instance;
		}
		
		/* create method() 생성
		 * command에 따라 개별의 Controller를 만들어주는 기능
		 */
		public Controller create(String command) {
			Controller controller = null;
			
			if(command.contentEquals("findmemberbyid"))
				controller = new FindMemberByIdController();
			else if(command.contentEquals("login"))
				controller = new LoginokController();
			else if(command.contentEquals("logout"))
				controller = new LogoutController();
			return controller;
		}
	}