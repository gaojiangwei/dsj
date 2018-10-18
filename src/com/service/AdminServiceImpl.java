package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mapper.AdminMapper;
import com.model.Dsj;
import com.model.JxSp;
import com.model.SpInfo;

import util.Path;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private AdminMapper adminMapper;

	@Override
	public String addrCommit(String jxName, String jxAddr) {
		JxSp jxsp = new JxSp();
		jxsp.setJxAddr(jxAddr);
		jxsp.setJxName(jxName);
		int x = adminMapper.addrCommit(jxsp);
		if(x == 1) {
			return "success";
		}else {
			return "failed";
		}
	}

	@Override
	public List<JxSp> getAddrVal() {
		return adminMapper.getAddrVal();
	}

	@Override
	public String insertSpInfo(String spName, String spAddr, int spType, int spAddrBm, MultipartFile spImg) {
		
		SpInfo spInfo = new SpInfo();
		spInfo.setSpName(spName);
		spInfo.setSpAddr(spAddr);
		spInfo.setSpType(spType);
		spInfo.setSpAddrBm(spAddrBm);
		if(spImg != null) {
			spInfo.setSpImg(Path.saveImg(spImg));
		}else {
			spInfo.setSpImg("noImage.jpg");
		}
		
		int x = adminMapper.insertSpInfo(spInfo);
		if(x == 1) {
			return "success";
		}else {
			return "failed";
		}
	}

	@Override
	public String toUpdate(int addrId, String jxAddr) {
		JxSp jxsp = new JxSp();
		jxsp.setAddrId(addrId);
		jxsp.setJxAddr(jxAddr);
		adminMapper.toUpdate(jxsp);
		return "success";
	}

	//新增电视剧
	@Override
	public String addDsj(int addrId, String dsjName, String dsjAddr, MultipartFile dsjHb) {
		Dsj dsj = new Dsj();
		dsj.setAddrId(addrId);
		dsj.setDsjName(dsjName);
		dsj.setDsjAddr(dsjAddr);
		dsj.setDsjHb(Path.saveImg(dsjHb));
		adminMapper.addDsj(dsj);
		
		int x = adminMapper.addJs(dsj);
		if(x == 1) {
			return "success";
		}else {
			return "failed";
		}
	}

	//获取所有电视剧
	@Override
	public List<Dsj> getAllDsj() {
		return adminMapper.getAllDsj();
	}

	@Override
	public String addJs(int dsjId, String dsjAddr) {
		Dsj dsj = new Dsj();
		dsj.setDsjId(dsjId);
		dsj.setDsjAddr(dsjAddr);
		adminMapper.addJs(dsj);
		return "success";
	}

}
