package com.po;

import java.io.Serializable;

public class Salary implements Serializable{ 
private Integer sid;
private Integer eid;
private float enoney;
public Salary() {
	super();
	// TODO Auto-generated constructor stub
}
public Salary(Integer sid, Integer eid, float enoney) {
	super();
	this.sid = sid;
	this.eid = eid;
	this.enoney = enoney;
}
//��Empbizlpm����ΪҪ��ȡԱ��н������
public Salary(Integer eid, float enoney) {
	super();
	this.eid = eid;
	this.enoney = enoney;
}
public Integer getSid() {
	return sid;
}
public void setSid(Integer sid) {
	this.sid = sid;
}
public Integer getEid() {
	return eid;
}
public void setEid(Integer eid) {
	this.eid = eid;
}
public float getEnoney() {
	return enoney;
}
public void setEnoney(float enoney) {
	this.enoney = enoney;
}
@Override
public String toString() {
	return "Salary [sid=" + sid + ", eid=" + eid + ", enoney=" + enoney + "]";
}

}
