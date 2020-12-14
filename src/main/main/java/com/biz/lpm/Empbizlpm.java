package com.biz.lpm;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biz.IEmpbiz;
import com.po.Emp;
import com.po.Empwelfare;
import com.po.PageBean;
import com.po.Salary;
import com.po.Welfare;
import com.util.DaoMapper;
@Service("Emplpm")
@Transactional
public class Empbizlpm implements IEmpbiz {
	@Resource(name="DaoMapper")
private DaoMapper daoMapper;

	public DaoMapper getDaoMapper() {
	return daoMapper;
}

public void setDaoMapper(DaoMapper daoMapper) {
	this.daoMapper = daoMapper;
}

	@Override
	public boolean save(Emp emp) {
		//���EMP������Ϊн�ʺ͸�����Ҫeid������Ҫ�Ȼ�ȡeid
		int code=daoMapper.getEmpMapper().save(emp);
		if(code>0){
		//	��ȡ���ID��ֵ
		Integer eid=daoMapper.getEmpMapper().findMaxeid();	
		//���н��
		Salary sa=new Salary(eid,emp.getEnoney());
		daoMapper.getSalaryMapper().save(sa);//�������н�ʷ��������
		//���Ա��������
		String[] wids=emp.getWids();//���븣��ID
		if(wids!=null&&wids.length>0){
			for(int i=0;i<wids.length;i++){//forѭ���������� ��ȡÿ������ID��Ӧ��ֵ
				Empwelfare wel=new Empwelfare(eid,new Integer (wids[i]));
				//��ÿ��ֵ��ֵ����
				daoMapper.getEmpwelfare().save(wel);
			}
		}
		return true;
		}
		return false;
	}

	@Override
	public boolean update(Emp emp) {
		//�޸�Ա������
		int code=daoMapper.getEmpMapper().update(emp);
		//�޸�н��
		if(code>0){
			//�޸�ԭ��н��
			Salary oldsa=daoMapper.getSalaryMapper().findByEid(emp.getEid());//��ȡԭ��н��
			if(oldsa!=null&&oldsa.getEnoney()!=0){
				if(oldsa.getEnoney()<emp.getEnoney()){//ԭ��н��С���µ�н��
					oldsa.setEnoney(emp.getEnoney());
					daoMapper.getSalaryMapper().update(oldsa);
				}else{//ԭ��н�ʴ����µ�н��
					daoMapper.getSalaryMapper().update(oldsa);
				}
			}
			//�޸ĸ�������ȡԱ������
			List<Welfare> wel=daoMapper.getEmpwelfare().findByEid(emp.getEid());
			if(wel!=null&&wel.size()>0){
				//ɾ��ԭ����
				daoMapper.getEmpwelfare().delByEid(emp.getEid());
			}
				//����¸���
				String[] wids=emp.getWids();//���븣��ID
				if(wids!=null&&wids.length>0){
					for(int i=0;i<wids.length;i++){//forѭ���������� ��ȡÿ������ID��Ӧ��ֵ
						Empwelfare efa=new Empwelfare(emp.getEid(),new Integer (wids[i]));
						//��ÿ��ֵ��ֵ����
						daoMapper.getEmpwelfare().save(efa);
						
					}
			}
			return true;
		
		}
		return false;
	}

	@Override
	public boolean delById(Integer eid) {
	//Ҫɾ��������ɾ�ӱ�Ҳ���Ǹ�����͹��ʱ�
		daoMapper.getEmpwelfare().delByEid(eid);
		daoMapper.getSalaryMapper().delByEid(eid);
		int code=daoMapper.getEmpMapper().delById(eid);
		if(code>0){
			return true;
		}
		return false;
	}

	@Override
	public Emp findById(Integer eid) {
		//�Ȳ�Ա��
		Emp oldemp=daoMapper.getEmpMapper().findById(eid);
		//��н��
		Salary oldsa=daoMapper.getSalaryMapper().findByEid(eid);
		if(oldsa!=null&&oldsa.getEnoney()!=0){
			oldemp.setEnoney(oldsa.getEnoney());//���鵽��н�ʷ��뵽�鵽��Ա������
		}
		//�鸣��
	    List<Welfare> lfaw= daoMapper.getEmpwelfare().findByEid(eid);//��ȡ��������
	    if(lfaw!=null&&lfaw.size()>0){
	    	//���������������
	    	String[] wids=new String[lfaw.size()];
	    	for(int i=0;i<lfaw.size();i++){
	    		Welfare wf=lfaw.get(i);
	    		wids[i]=wf.getWid().toString(); 
	    	}
	    	oldemp.setWids(wids);
	    }
	    oldemp.setLswf(lfaw);
		return oldemp;
	}

	@Override
	public List<Emp> findPageAll(PageBean pb) {
		if(pb==null){
			 pb=new PageBean();
			}
			return daoMapper.getEmpMapper().findPageAll(pb);
		}

	@Override
	public int findMaxrow() {
		
		return daoMapper.getEmpMapper().findMaxrow();
	}

}
