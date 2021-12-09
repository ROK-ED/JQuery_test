package com.edu.todo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ToDoDAO extends DAO {
	// 전체조회
	public List<ToDoVO> getToDoList() throws SQLException {

		List<ToDoVO> list = new ArrayList<>();
		String sql = "select * from todo_list order by 1 ";
		connect();

		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		while (rs.next()) {
			ToDoVO vo = new ToDoVO(rs.getString("content"));
			list.add(vo);
		}
		disconnect();

		return list;

	}

	// 한건입력
	public ToDoVO insertToDo(ToDoVO vo) throws Exception {
		String sql = "insert into to todo_list(content) values(?) ";
		connect();

		
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getContent());

			int r = psmt.executeUpdate();
			System.out.println(r + "건수 입력!");
		
			disconnect();
		
		return vo;
	}

	// 수정
	public ToDoVO updateToDo(ToDoVO vo) throws Exception {
		String sql = "update todo_list set content = ? where content=?";
		connect();
		
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getContent());

			int r = psmt.executeUpdate();
			System.out.println(r + "건 변경!");

		
			disconnect();
		
		return vo;
	}

	// 삭제
	public String deleteToDo(String content) throws Exception {
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
	public ToDoVO findToDo(String content) throws SQLException {
		String sql = "select * from todo_list where content=?";
		connect();
		psmt = conn.prepareStatement(sql);
		psmt.setString(1, content);
		rs = psmt.executeQuery();
		if(rs.next()) {
			ToDoVO vo = new ToDoVO();
			vo.setContent(rs.getString("content"));
			
			return vo;
		}
		
		disconnect();
		return null;
	}
	

}
