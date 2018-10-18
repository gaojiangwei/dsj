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
	
	//�������Ӿ�
	public int addDsj(Dsj dsj);
	//��������
	public int addJs(Dsj dsj);
	
	public List<Dsj> getAllDsj();
	
}
