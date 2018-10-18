package com.service;

import java.util.List;

import com.model.Dsj;
import com.model.SpInfo;

public interface IndexService {
	
	public List<SpInfo> getIndexMovie();
	
	public String getOneMovie(String spImg);
	
	public List<Dsj> getIndexTv();
	
	public List<Dsj> getOneTV(String dsjHb);
	
	public List<Dsj> showAllTv();
	
	public List<SpInfo> getIndexVariety();
	
	public List<SpInfo> showAllVariety();
	
	public List<Dsj> searchDsj(String dsjName);
	
	public List<SpInfo> searchSp(String spName);

}
