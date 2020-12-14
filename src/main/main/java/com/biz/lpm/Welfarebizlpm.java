package com.biz.lpm;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biz.IWelfarebiz;
import com.po.Welfare;
import com.util.DaoMapper;
@Service("Welfarebizlpm")
@Transactional
public class Welfarebizlpm implements IWelfarebiz {
	@Resource(name="DaoMapper")
private DaoMapper daoMapper;

	public DaoMapper getDaoMapper() {
	return daoMapper;
}

public void setDaoMapper(DaoMapper daoMapper) {
	this.daoMapper = daoMapper;
}

	@Override
	public List<Welfare> findAll() {
		// TODO Auto-generated method stub
		return daoMapper.getWelfareMapper().findAll();
	}

}
