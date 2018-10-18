package com.mapper;

import java.util.List;

import com.model.Dsj;
import com.model.JxSp;
import com.model.SpInfo;

public interface AdminMapper {

	public int addrCommit(JxSp jxsp);
	
	public List<JxSp> getAddrVal();
	
	public int insertSpInfo(SpInfo spInfo);
	
	public void toUpdate(JxSp jxsp);
	
	//新增电视剧
	public int addDsj(Dsj dsj);
	//新增集数
	public int addJs(Dsj dsj);
	
	public List<Dsj> getAllDsj();
	
}
