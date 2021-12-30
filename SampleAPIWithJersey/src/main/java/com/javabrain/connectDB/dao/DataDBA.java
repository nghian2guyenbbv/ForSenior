package com.javabrain.connectDB.dao;

public class DataDBA {
	private String INSERT_OBJECT ="";
	private String SELECT_OBJECT ="";
	private String UPDATE_OBJECT ="";
	private String SEARCH_CONDITION="";
	private int orgId= 1;
	private String firstName= null;
	private String midddleName =null;
	private String lastName=null;
	private String getInsertbject() {
		return null;
		
	}
	public int getOrgId() {
		return orgId;
	}
	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMidddleName() {
		return midddleName;
	}
	public void setMidddleName(String midddleName) {
		this.midddleName = midddleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void loadDataDAO(DataDAO argDAO) {
		
	}
}
