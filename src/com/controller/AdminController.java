package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.model.Dsj;
import com.model.JxSp;
import com.service.AdminServiceImpl;

@Controller
public class AdminController {
	
	@Autowired
	private AdminServiceImpl adminService;

	@RequestMapping("/addrcommit")
	public @ResponseBody String addrCommit(String jxName, String jxAddr) {
		return adminService.addrCommit(jxName, jxAddr);
	}
	
	@RequestMapping("/getAddrVal")
	public @ResponseBody List<JxSp> getAddrVal() {
		return adminService.getAddrVal();
	}
	
	//添加视频信息
	@RequestMapping(value="/insertSpInfo", method=RequestMethod.POST)
	public @ResponseBody String insertSpInfo(String spName, String spAddr, int spType, int spAddrBm, MultipartFile spImg) {
		return adminService.insertSpInfo(spName, spAddr, spType, spAddrBm, spImg);
	}
	
	@RequestMapping("/toUpdate")
	public @ResponseBody String toUpdate(int addrId, String jxAddr) {
		return adminService.toUpdate(addrId, jxAddr);
	}
	
	//�������Ӿ�
	@RequestMapping("/addDsj")
	public @ResponseBody String addDsj(int addrId, String dsjName, String dsjAddr, MultipartFile dsjHb) {
		return adminService.addDsj(addrId, dsjName, dsjAddr, dsjHb);
	}
	//��ȡ���е��Ӿ���
	@RequestMapping("/getAllDsj")
	public @ResponseBody List<Dsj> getAllDsj() {
		return adminService.getAllDsj();
	}
	//��������
	@RequestMapping("/addJs")
	public @ResponseBody String addJs(int dsjId, String dsjAddr) {
		return adminService.addJs(dsjId, dsjAddr);
	}
}
