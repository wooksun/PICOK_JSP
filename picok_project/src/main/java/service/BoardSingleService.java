package service;

public class BoardSingleService {
	
	public static BoardSingleService instance = new BoardSingleService();
	private BoardSingleService() { }
	public static BoardSingleService getInstance() {
		return instance;
	}
	
}