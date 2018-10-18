package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.model.SpInfo;
import com.service.MovieServiceImpl;

@Controller
public class MovieController {

	@Autowired
	private MovieServiceImpl movieService;
	
	@RequestMapping("/showAllMovie")
	public @ResponseBody List<SpInfo> showAllMovie() {
		return movieService.showAllMovie();
	}
}
