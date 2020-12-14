package com.mapper;

import java.util.List;

import org.springframework.stereotype.Service;

import com.po.Empwelfare;
import com.po.Welfare;

@Service("EmpwelfareDAO")
public interface IEmpwelfare {
//保存员工福利数据
	public int save(Empwelfare ewf);
	//根据员工编号删除对应员工数据
	public int delByEid(Integer eid);
	//根据员工编号查找对应员工数据
	public List<Welfare> findByEid(Integer eid);
}
