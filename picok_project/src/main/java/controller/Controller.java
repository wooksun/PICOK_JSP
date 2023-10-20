package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Controller {
	
	//execute 메서드 생성
	public String execute(HttpServletRequest request, HttpServletResponse response) 
			throws Exception;
}