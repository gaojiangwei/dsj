package com.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.model.Dsj;
import com.model.JxSp;

public interface AdminService {
	
	public String addrCommit(String jxName, String jxAddr);
	
	public List<JxSp> getAddrVal();
	
	public String insertSpInfo(String spName, String spAddr, int spType, int spAddrBm, MultipartFile spImg);
	
	public String toUpdate(int addrId, String jxAddr);
	
	public String addDsj(int addrId, String dsjName, String dsjAddr, MultipartFile dsjHb);
	
	public List<Dsj> getAllDsj();
	
	public String addJs(int dsjId, String dsjAddr);

}
