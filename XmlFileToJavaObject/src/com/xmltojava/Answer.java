package com.xmltojava;

public class Answer {
 private String answername;
 private int id;
 private String postedby;
 public Answer() {}
 public Answer(int id, String answername, String postedby) {
	 this.id = id;
	 this.answername = answername;
	 this.postedby = postedby;
 }
public String getAnswername() {
	return answername;
}
public void setAnswername(String answername) {
	this.answername = answername;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getPostedby() {
	return postedby;
}
public void setPostedby(String postedby) {
	this.postedby = postedby;
}
 
 
}
