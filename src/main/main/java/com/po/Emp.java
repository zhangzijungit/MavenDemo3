package com.po;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.MultipartFilter;

public class Emp implements Serializable{
private Integer eid;
private String ename;
private String sex;
private String address;
private Date birthday;
private String photo="default.jpg";
private Integer depid;//部门ID
//与前台页面有关的临时属性
private String depname;//员工部门
private float enoney;//员工薪资
//部门福利 因为部门福利有多个 是与员工是多对多关系，返回的只能是数组或者集合，但是在AJAX中只有数组
private String[] wids;//先获取ID，福利的编号数组，接受页面复选框的值，往后台存
private List<Welfare> lswf;//往前台展示时，福利集合
//与照片
private MultipartFile pic;
//与日期
private String sdate;
public Emp() {
	super();
	// TODO Auto-generated constructor stub
}
public Emp(Integer eid, String ename, String sex, String address, Date birthday, String photo, Integer depid) {
	super();
	this.eid = eid;
	this.ename = ename;
	this.sex = sex;
	this.address = address;
	this.birthday = birthday;
	this.photo = photo;
	this.depid = depid;
}
public Integer getEid() {
	return eid;
}
public void setEid(Integer eid) {
	this.eid = eid;
}
public String getEname() {
	return ename;
}
public void setEname(String ename) {
	this.ename = ename;
}
public String getSex() {
	return sex;
}
public void setSex(String sex) {
	this.sex = sex;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public Date getBirthday() {
	return birthday;
}
public void setBirthday(Date birthday) {
	this.birthday = birthday;
}
public String getPhoto() {
	return photo;
}
public void setPhoto(String photo) {
	this.photo = photo;
}
public Integer getDepid() {
	return depid;
}
public void setDepid(Integer depid) {
	this.depid = depid;
}
public String getDepname() {
	return depname;
}
public void setDepname(String depname) {
	this.depname = depname;
}
public float getEnoney() {
	return enoney;
}
public void setEnoney(float enoney) {
	this.enoney = enoney;
}
public String[] getWids() {
	return wids;
}
public void setWids(String[] wids) {
	this.wids = wids;
}
public List<Welfare> getLswf() {
	return lswf;
}
public void setLswf(List<Welfare> lswf) {
	this.lswf = lswf;
}
public MultipartFile getPic() {
	return pic;
}
public void setPic(MultipartFile pic) {
	this.pic = pic;
}
public String getSdate() {
	sdate=new SimpleDateFormat("yyyy-MM-dd").format(birthday);
	return sdate;
}
public void setSdate(String sdate) {
	try {
		birthday=new SimpleDateFormat("yyyy-MM-dd").parse(sdate);
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	this.sdate = sdate;
}
@Override
public String toString() {
	return "Emp [eid=" + eid + ", ename=" + ename + ", sex=" + sex + ", address=" + address + ", birthday=" + birthday
			+ ", photo=" + photo + ", depid=" + depid + ", depname=" + depname + ", enoney=" + enoney + ", wids="
			+ Arrays.toString(wids) + ", lswf=" + lswf + ", pic=" + pic + ", sdate=" + sdate + "]";
}

}
