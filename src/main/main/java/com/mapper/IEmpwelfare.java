package com.mapper;

import java.util.List;

import org.springframework.stereotype.Service;

import com.po.Empwelfare;
import com.po.Welfare;

@Service("EmpwelfareDAO")
public interface IEmpwelfare {
//����Ա����������
	public int save(Empwelfare ewf);
	//����Ա�����ɾ����ӦԱ������
	public int delByEid(Integer eid);
	//����Ա����Ų��Ҷ�ӦԱ������
	public List<Welfare> findByEid(Integer eid);
}
