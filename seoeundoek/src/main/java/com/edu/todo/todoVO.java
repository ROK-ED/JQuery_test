package com.edu.todo;

public class todoVO {
	private String content;
	
	
	
	public todoVO(String content) {
		super();
		this.content = content;
	}
	
	public todoVO() {
		
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
