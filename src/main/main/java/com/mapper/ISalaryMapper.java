package com.mapper;

import org.springframework.stereotype.Service;

import com.po.Salary;

@Service("SalaryDAO")
public interface ISalaryMapper {
 //н�ʱ���
	public int save(Salary sa);
	//н���޸�
	public int update(Salary sa);
	//н��ɾ��
	public int delByEid(Integer eid);
	//н�ʲ�ѯ
	public Salary findByEid(Integer eid);
}
