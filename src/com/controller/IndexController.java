package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.model.Dsj;
import com.model.SpInfo;
import com.service.IndexServiceImpl;

@Controller
public class IndexController {
	
	@Autowired
	private IndexServiceImpl indexService;

	//鑾峰彇棣栭〉鐑棬鐢靛奖
	@RequestMapping("/getIndexMovie")
	public @ResponseBody List<SpInfo> getIndexMovie() {
		return indexService.getIndexMovie();
	}
	
	//鑾峰彇鐐瑰嚮鐨勭數褰�
	@RequestMapping("/getOneMovie")
	public @ResponseBody String getOneMovie(String spImg) {
		return indexService.getOneMovie(spImg);
	}
	
	@RequestMapping("/getIndexTv")
	public @ResponseBody List<Dsj> getIndexTv() {
		return indexService.getIndexTv();
	}
	
	//获取电视剧详情
	@RequestMapping("/getOneTV")
	public @ResponseBody List<Dsj> getOneTV(String dsjHb) {
		return indexService.getOneTV(dsjHb);
	}
	
	//获取所有电视剧
	@RequestMapping("/showAllTv")
	public @ResponseBody List<Dsj> showAllTv() {
		return indexService.showAllTv();
	}
	
	//获取首页综艺
	@RequestMapping("/getIndexVariety")
	public @ResponseBody List<SpInfo> getIndexVariety() {
		return indexService.getIndexVariety();
	}
	
	//获取所有综艺
	@RequestMapping("/showAllVariety")
	public @ResponseBody List<SpInfo> showAllVariety() {
		return indexService.showAllVariety();
	}
	
	//搜索电视剧
	@RequestMapping("/searchDsj")
	public @ResponseBody List<Dsj> searchDsj(String dsjName) {
		return indexService.searchDsj(dsjName);
	}
	
	//搜索电影 综艺
	@RequestMapping("/searchSp")
	public @ResponseBody List<SpInfo> searchSp(String spName) {
		return indexService.searchSp(spName);
	}
}
