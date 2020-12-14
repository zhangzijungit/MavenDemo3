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
		//添加EMP对象，因为薪资和福利表要eid，所以要先获取eid
		int code=daoMapper.getEmpMapper().save(emp);
		if(code>0){
		//	获取最大ID的值
		Integer eid=daoMapper.getEmpMapper().findMaxeid();	
		//添加薪资
		Salary sa=new Salary(eid,emp.getEnoney());
		daoMapper.getSalaryMapper().save(sa);//调用添加薪资方法，添加
		//添加员工福利表
		String[] wids=emp.getWids();//传入福利ID
		if(wids!=null&&wids.length>0){
			for(int i=0;i<wids.length;i++){//for循环遍历数组 获取每个福利ID对应的值
				Empwelfare wel=new Empwelfare(eid,new Integer (wids[i]));
				//将每个值赋值保存
				daoMapper.getEmpwelfare().save(wel);
			}
		}
		return true;
		}
		return false;
	}

	@Override
	public boolean update(Emp emp) {
		//修改员工对象
		int code=daoMapper.getEmpMapper().update(emp);
		//修改薪资
		if(code>0){
			//修改原来薪资
			Salary oldsa=daoMapper.getSalaryMapper().findByEid(emp.getEid());//获取原来薪资
			if(oldsa!=null&&oldsa.getEnoney()!=0){
				if(oldsa.getEnoney()<emp.getEnoney()){//原有薪资小于新的薪资
					oldsa.setEnoney(emp.getEnoney());
					daoMapper.getSalaryMapper().update(oldsa);
				}else{//原有薪资大于新的薪资
					daoMapper.getSalaryMapper().update(oldsa);
				}
			}
			//修改福利，获取员工福利
			List<Welfare> wel=daoMapper.getEmpwelfare().findByEid(emp.getEid());
			if(wel!=null&&wel.size()>0){
				//删除原福利
				daoMapper.getEmpwelfare().delByEid(emp.getEid());
			}
				//添加新福利
				String[] wids=emp.getWids();//传入福利ID
				if(wids!=null&&wids.length>0){
					for(int i=0;i<wids.length;i++){//for循环遍历数组 获取每个福利ID对应的值
						Empwelfare efa=new Empwelfare(emp.getEid(),new Integer (wids[i]));
						//将每个值赋值保存
						daoMapper.getEmpwelfare().save(efa);
						
					}
			}
			return true;
		
		}
		return false;
	}

	@Override
	public boolean delById(Integer eid) {
	//要删主表，得先删从表，也就是福利表和工资表
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
		//先查员工
		Emp oldemp=daoMapper.getEmpMapper().findById(eid);
		//查薪资
		Salary oldsa=daoMapper.getSalaryMapper().findByEid(eid);
		if(oldsa!=null&&oldsa.getEnoney()!=0){
			oldemp.setEnoney(oldsa.getEnoney());//将查到的薪资放入到查到的员工表中
		}
		//查福利
	    List<Welfare> lfaw= daoMapper.getEmpwelfare().findByEid(eid);//获取福利集合
	    if(lfaw!=null&&lfaw.size()>0){
	    	//创建福利编号数组
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
