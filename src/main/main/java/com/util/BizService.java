package com.util;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.biz.IDepbiz;
import com.biz.IEmpbiz;
import com.biz.IWelfarebiz;

@Service("BizService")
public class BizService {
	@Resource(name="Deplpm")
private IDepbiz depbiz;
	@Resource(name="Emplpm")
private IEmpbiz empbiz;
	@Resource(name="Welfarebizlpm")
private IWelfarebiz welfarebiz;
public IDepbiz getDepbiz() {
	return depbiz;
}
public void setDepbiz(IDepbiz depbiz) {
	this.depbiz = depbiz;
}
public IEmpbiz getEmpbiz() {
	return empbiz;
}
public void setEmpbiz(IEmpbiz empbiz) {
	this.empbiz = empbiz;
}
public IWelfarebiz getWelfarebiz() {
	return welfarebiz;
}
public void setWelfarebiz(IWelfarebiz welfarebiz) {
	this.welfarebiz = welfarebiz;
}

}
