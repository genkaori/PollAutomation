package com.poll.framgia.automation.bean;

public class PollBean {
	private String title;
	private String option;
	private String description;
	private String location;
	
	public PollBean(){
		
	}
	
	public PollBean (String title, String option, String description, String location){
		this.title = title;
		this.option = option;
		this.description = description;
		this.location = location;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	
}
