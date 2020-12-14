package com.biz.lpm;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biz.IDepbiz;
import com.po.Dep;
import com.util.DaoMapper;
@Service("Deplpm")
@Transactional
public class Depbizlpm implements IDepbiz {
	@Resource(name="DaoMapper")
private DaoMapper daoMapper;
	
	public DaoMapper getDaoMapper() {
		return daoMapper;
	}

	public void setDaoMapper(DaoMapper daoMapper) {
		this.daoMapper = daoMapper;
	}

	@Override
	public List<Dep> findAll() {
		// TODO Auto-generated method stub
		return daoMapper.getDepMapper().findAll();
	}

}
