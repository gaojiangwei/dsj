package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapper.MovieMapper;
import com.model.SpInfo;

@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	private MovieMapper movieMapper;
	
	@Override
	public List<SpInfo> showAllMovie() {
		return movieMapper.showAllMovie();
	}

}
