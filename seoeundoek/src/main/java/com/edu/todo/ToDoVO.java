package com.edu.todo;

public class ToDoVO {
	private String content;
	
	
	
	public ToDoVO(String content) {
		super();
		this.content = content;
	}
	
	public ToDoVO() {
		
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "SampleVO [content=" + content + "]";
	}
	
	

}
