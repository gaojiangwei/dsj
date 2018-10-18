package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapper.IndexMapper;
import com.model.Dsj;
import com.model.SpInfo;
@Service
public class IndexServiceImpl implements IndexService {
	
	@Autowired
	private IndexMapper indexMapper;

	@Override
	public List<SpInfo> getIndexMovie() {
		return indexMapper.getIndexMovie();
	}

	@Override
	public String getOneMovie(String spImg) {
		return indexMapper.getOneMovie(spImg);
	}

	@Override
	public List<Dsj> getIndexTv() {
		return indexMapper.getIndexTv();
	}

	@Override
	public List<Dsj> getOneTV(String dsjHb) {
		return indexMapper.getOneTV(dsjHb);
	}

	@Override
	public List<Dsj> showAllTv() {
		return indexMapper.showAllTv();
	}

	@Override
	public List<SpInfo> getIndexVariety() {
		return indexMapper.getIndexVariety();
	}

	@Override
	public List<SpInfo> showAllVariety() {
		return indexMapper.showAllVariety();
	}

	@Override
	public List<Dsj> searchDsj(String dsjName) {
		return indexMapper.searchDsj("%"+dsjName+"%");
	}

	@Override
	public List<SpInfo> searchSp(String spName) {
		return indexMapper.searchSp("%"+spName+"%");
	}

	

}
