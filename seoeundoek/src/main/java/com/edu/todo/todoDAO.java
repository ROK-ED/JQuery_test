package com.edu.todo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class todoDAO extends DAO {
	// 전체조회
	public List<todoVO> getTodoList() throws SQLException {

		List<todoVO> list = new ArrayList<>();
		String sql = "select * from todo_list ";
		connect();

		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		
		while (rs.next()) {
			todoVO vo = new todoVO(rs.getString("content"));	
			list.add(vo);
		}
		disconnect();

		return list;

	}

	// 한건입력
	public todoVO insertTodo(todoVO vo) throws Exception {
		String sql = "insert into todo_list(content) values(?) ";
		connect();
			
			
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getContent());
			
			System.out.println(vo.getContent());
			
			int r = psmt.executeUpdate();
			System.out.println(r + "건수 입력!");
		
			disconnect();
		
		return vo;
	}

	// 수정
	public todoVO updateTodo(String before, String content) throws Exception {
		String sql = "update todo_list set content = ? where content=?";
		connect();
		todoVO vo = new todoVO();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, content);
			psmt.setString(2, before);

			int r = psmt.executeUpdate();
			System.out.println(r + "건 변경!");

		
			disconnect();
		
		return vo;
	}
	
//	public Map<String, String> updateTodo() throws Exception{
//		Map<String, String> map = new HashMap<String, String>();
//		String sql = "update todo_list set content = ? where content=?";
//		connect();
//		psmt = conn.prepareStatement(be);
//		psmt.setString(1, map.get(sql));
//		psmt.setString(2, before);
//	}

	// 삭제
	public String deleteTodo(String content) throws Exception {
		String sql = "delete from todo_list where content= ?";

		connect();
		
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, content);

			int r = psmt.executeUpdate();
			System.out.println(r + "건 삭제!");
		
			disconnect();

		return content;

	}
	
	//한건조회
	public todoVO findTodo(String content) throws SQLException {
		String sql = "select * from todo_list where content=?";
		connect();
		psmt = conn.prepareStatement(sql);
		psmt.setString(1, content);
		rs = psmt.executeQuery();
		if(rs.next()) {
			todoVO vo = new todoVO();
			vo.setContent(rs.getString("content"));
			
			return vo;
		}
		
		disconnect();
		return null;
	}
	

}
