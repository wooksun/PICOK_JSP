package service;

public class BoardCommentService {
	
	public static BoardCommentService instance = new BoardCommentService();
	private BoardCommentService() { }
	public static BoardCommentService getInstance() {
		return instance;
	}
	
	
	
}
