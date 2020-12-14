package com.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.po.Dep;
import com.po.Emp;
import com.po.PageBean;
import com.po.Welfare;
import com.util.AJAXUtils;
import com.util.BizService;

@Controller
public class Empcontrpller {
	@Resource(name="BizService")
 private BizService bizse;

public BizService getBizse() {
	return bizse;
}

public void setBizse(BizService bizse) {
	this.bizse = bizse;
}
//����
@RequestMapping(value="save.do")
 public String save(HttpServletRequest request,HttpServletResponse response,Emp emp){
	 String realpath=request.getRealPath("/");
	 /************�ļ��ϴ�****************/
	   //��ȡ�ļ��ϴ��Ķ���
	   MultipartFile multipartFile=emp.getPic();
	   if(multipartFile!=null&&!multipartFile.isEmpty()){
		   //��ȡ�ļ��ϴ�����
		   String fname=multipartFile.getOriginalFilename();
		   //����
		   if(fname.lastIndexOf(".")!=-1){
			   //��ȡ���ڵĺ�׺
			   String ext=fname.substring(fname.lastIndexOf("."));
			   //�жϺ�׺��ʽ
			   if(ext.equalsIgnoreCase(".jpg")){
				   String newfname=new Date().getTime()+ext;
				   //�����ļ�����ָ���ϴ�·��
				   File dostfile=new File(realpath+"/uppic/"+newfname);
				   //�����ļ�����,�ϴ�
				   try {
					FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), dostfile);
					emp.setPhoto(newfname);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			   }
		   }
	   }
	 /************�ļ��ϴ�end****************/
	 boolean flag=bizse.getEmpbiz().save(emp);
	 if(flag){
			AJAXUtils.printString(response, 1+"");
		}else{
			AJAXUtils.printString(response, 0+"");
		}
	 return null;
	 
 }
 //�޸�
@RequestMapping(value="update.do")
 public String update(HttpServletRequest request,HttpServletResponse response,Emp emp){
	//��ȡԭ����Ƭ
		String oldphoto=bizse.getEmpbiz().findById(emp.getEid()).getPhoto();
		String realpath=request.getRealPath("/");
		 /************�ļ��ϴ�****************/
		   //��ȡ�ļ��ϴ��Ķ���
		   MultipartFile multipartFile=emp.getPic();
		   if(multipartFile!=null&&!multipartFile.isEmpty()){
			   //��ȡ�ļ��ϴ�����
			   String fname=multipartFile.getOriginalFilename();
			   //����
			   if(fname.lastIndexOf(".")!=-1){
				   //��ȡ���ڵĺ�׺
				   String ext=fname.substring(fname.lastIndexOf("."));
				   //�жϺ�׺��ʽ
				   if(ext.equalsIgnoreCase(".jpg")){
					   String newfname=new Date().getTime()+ext;
					   //�����ļ�����ָ���ϴ�·��
					   File dostfile=new File(realpath+"/uppic/"+newfname);
					   //�����ļ�����,�ϴ�
					   try {
						FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), dostfile);
						emp.setPhoto(newfname);
						//ɾ��ԭ����Ƭ
						File oldfile=new File(realpath+"/uppic/"+oldphoto);
						if(oldfile.exists()&&!oldphoto.equalsIgnoreCase("default.jpg")){
							oldfile.delete();
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				   }
			   }
		   }else{
			   emp.setPhoto(oldphoto);
		   }
		   /************�ļ��ϴ�end****************/
		   boolean flag=bizse.getEmpbiz().update(emp);
		   if(flag){
				AJAXUtils.printString(response, 1+"");
			}else{
				AJAXUtils.printString(response, 0+"");
			}
	return null;
	 
 }
 //ɾ��
@RequestMapping(value="delById.do")
 public String delById(HttpServletRequest request,HttpServletResponse response,Integer eid){
	boolean flag=bizse.getEmpbiz().delById(eid);
	if(flag){
		AJAXUtils.printString(response, 1+"");
	}else{
		AJAXUtils.printString(response, 0+"");
	}
	return null;
	 
 }
 //��ѯ����
@RequestMapping(value="findById.do")
 public String findById(HttpServletRequest request,HttpServletResponse response,Integer eid){
	Emp oldemp=bizse.getEmpbiz().findById(eid);
	PropertyFilter propertyFilter=AJAXUtils.filterProperts("birthday","pic");
	String jsonstr=JSONObject.toJSONString(oldemp,propertyFilter,SerializerFeature.DisableCircularReferenceDetect);
	AJAXUtils.printString(response,jsonstr);
	return null;
	 
 }
@RequestMapping(value="findDetail.do")
public String findDetail(HttpServletRequest request,HttpServletResponse response,Integer eid){
	Emp oldemp=bizse.getEmpbiz().findById(eid);
	PropertyFilter propertyFilter=AJAXUtils.filterProperts("birthday","pic");
	String jsonstr=JSONObject.toJSONString(oldemp,propertyFilter,SerializerFeature.DisableCircularReferenceDetect);
	AJAXUtils.printString(response,jsonstr);  
	return null;
  }
 //��ѯ����
@RequestMapping(value="findPageAll.do")
 public String findPageAll(HttpServletRequest request,HttpServletResponse response,Integer page,Integer rows){
	Map<String,Object> map= new HashMap<String,Object>();
	PageBean pb=new PageBean();
	page=page==null||page<1?pb.getPage():page;
	rows=rows==null||rows<1?pb.getRows():rows;
	pb.setPage(page);
	pb.setRows(rows);
	List<Emp> lsemp=bizse.getEmpbiz().findPageAll(pb);
	int maxrows=bizse.getEmpbiz().findMaxrow();
	//����easyUI�ĸ�ʽ��װ����
	map.put("page",page);
	map.put("rows",lsemp);
	map.put("total",maxrows);
	PropertyFilter propertyFilter=AJAXUtils.filterProperts("birthday","pic");
	String jsonstr=JSONObject.toJSONString(map,propertyFilter,SerializerFeature.DisableCircularReferenceDetect);
	AJAXUtils.printString(response,jsonstr); 
	return null;
 }
@RequestMapping(value="doinit.do")
public String doinit(HttpServletRequest request,HttpServletResponse response){
	Map<String,Object> map= new HashMap<String,Object>();
	List<Dep> lsdep=bizse.getDepbiz().findAll();
	List<Welfare> lswf=bizse.getWelfarebiz().findAll();
	map.put("lsdep", lsdep);
	map.put("lswf", lswf);
	PropertyFilter propertyFilter=AJAXUtils.filterProperts("birthday","pic");
	String jsonstr=JSONObject.toJSONString(map,propertyFilter,SerializerFeature.DisableCircularReferenceDetect);
	AJAXUtils.printString(response,jsonstr); 
	return null;
  }
}
