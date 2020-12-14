package com.mapper;

import org.springframework.stereotype.Service;

import com.po.Salary;

@Service("SalaryDAO")
public interface ISalaryMapper {
 //薪资保存
	public int save(Salary sa);
	//薪资修改
	public int update(Salary sa);
	//薪资删除
	public int delByEid(Integer eid);
	//薪资查询
	public Salary findByEid(Integer eid);
}
