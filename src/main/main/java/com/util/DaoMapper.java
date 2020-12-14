package com.util;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mapper.IDepMapper;
import com.mapper.IEmpMapper;
import com.mapper.IEmpwelfare;
import com.mapper.ISalaryMapper;
import com.mapper.IWelfareMapper;
@Service("DaoMapper")
public class DaoMapper {
	@Resource(name="DepDAO")
private IDepMapper depMapper;
	@Resource(name="EmpDAO")
private IEmpMapper empMapper;
	@Resource(name="EmpwelfareDAO")
private IEmpwelfare empwelfare;
	@Resource(name="SalaryDAO")
private ISalaryMapper salaryMapper;
	@Resource(name="WelfareDAO")
private IWelfareMapper welfareMapper;
	public IDepMapper getDepMapper() {
		return depMapper;
	}
	public void setDepMapper(IDepMapper depMapper) {
		this.depMapper = depMapper;
	}
	public IEmpMapper getEmpMapper() {
		return empMapper;
	}
	public void setEmpMapper(IEmpMapper empMapper) {
		this.empMapper = empMapper;
	}
	public IEmpwelfare getEmpwelfare() {
		return empwelfare;
	}
	public void setEmpwelfare(IEmpwelfare empwelfare) {
		this.empwelfare = empwelfare;
	}
	public ISalaryMapper getSalaryMapper() {
		return salaryMapper;
	}
	public void setSalaryMapper(ISalaryMapper salaryMapper) {
		this.salaryMapper = salaryMapper;
	}
	public IWelfareMapper getWelfareMapper() {
		return welfareMapper;
	}
	public void setWelfareMapper(IWelfareMapper welfareMapper) {
		this.welfareMapper = welfareMapper;
	}
	
}
