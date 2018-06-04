package org.wuancake.entity;

public class Report {

	private String group;
	private String name;
	private String week;
	private String status;
	private String content;
	public Report() {
		super();
		// TODO 自动生成的构造函数存根
	}
	public Report(String group, String name, String week, String status, String content) {
		super();
		this.group = group;
		this.name = name;
		this.week = week;
		this.status = status;
		this.content = content;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWeek() {
		return week;
	}
	public void setWeek(String week) {
		this.week = week;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
