package com.edu.todo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@WebServlet("/todoServlet")
public class todoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public todoServlet() {
		super();
	}
/*	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doGet(req, resp);
		resp.getWriter().append("Served at:").append(req.getContextPath());
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
*/	

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/json;charset=UTF-8");

		JSONObject obj = new JSONObject();
		todoDAO dao = new todoDAO();

		try {
			List<todoVO> list = dao.getTodoList();
			JSONArray ary = new JSONArray();
			for (todoVO vo : list) {
				JSONObject ino = new JSONObject();
				ino.put("content", vo.getContent());

				ary.add(ino);
			}
			obj.put("retCode", "Success");
			obj.put("retVal", ary);

		} catch (SQLException e) {
			e.printStackTrace();
			obj.put("retCode", "Fail");
			obj.put("retVal", e.getMessage());
		}
		response.getWriter().print(obj.toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/json;charset=UTF-8");

		String cmd = request.getParameter("cmd");
		String content = request.getParameter("content");
		String before = request.getParameter("before");
		todoDAO dao = new todoDAO();
		JSONObject obj = new JSONObject();

		if (cmd.equals("insert")) {
			todoVO vo = new todoVO();
			vo.setContent(content);
			
			try {
				dao.insertTodo(vo);
				JSONObject inobj = new JSONObject();
				inobj.put("content", vo.getContent());

				obj.put("retCode", "Success");
				obj.put("retVal", inobj);
			} catch (Exception e) {
				e.printStackTrace();
				obj.put("retCode", "Fail");
				obj.put("retVal", e.getMessage());

			}
			response.getWriter().print(obj.toString());
		} else if (cmd.equals("update")) {
			todoVO vo = new todoVO();
			vo.setContent(content);
			
			try {
				
				dao.updateTodo(before, content);
				
				JSONObject ino = new JSONObject();
				ino.put("content", vo.getContent());

				obj.put("retCode", "Success");
				obj.put("retVal", ino);

			} catch (Exception e) {
				e.printStackTrace();

				obj.put("retCode", "Fail");
				obj.put("retVal", e.getMessage());
			}
			response.getWriter().print(obj.toString());

		} else if (cmd.equals("delete")) {

			try {
				dao.deleteTodo(content);

				obj.put("retCode", "Success");
				obj.put("retVal", content);

			} catch (Exception e) {
				e.printStackTrace();
				obj.put("retCode", "Fail");
				obj.put("retVal", e.getMessage());

			}
			response.getWriter().print(obj.toString());
		} else if (cmd.equals("search")) {
			todoVO vo;
			try {
				vo = dao.findTodo(content);
				JSONObject ino = new JSONObject();
				ino.put("content", vo.getContent());

				obj.put("retCode", "Success");
				obj.put("retVal", ino);
			} catch (SQLException e) {
				e.printStackTrace();
				obj.put("retCode", "Fail");
				obj.put("retVal", e.getMessage());
			}
			response.getWriter().print(obj.toString());
		}

	}// end of doPost();

}
