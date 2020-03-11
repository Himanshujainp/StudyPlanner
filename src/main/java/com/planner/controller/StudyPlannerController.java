package com.planner.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.planner.model.Plan;
import com.planner.service.StudyPlannerService;

@Controller
public class StudyPlannerController {

	@Autowired
	StudyPlannerService plannerService;

	@GetMapping("/")
	public String home(Model model) {
		LocalDate date = LocalDate.now();
		List<Plan> plan = plannerService.getPlan();
		String quote = plannerService.getTodaysQuote();
		model.addAttribute("day", date);
		model.addAttribute("plans", plan);
		model.addAttribute("quote", quote);
		return "home";
	}

	@GetMapping("/history")
	public String viewHistory(Model model) {
		List<Plan> history = plannerService.getHistory(LocalDate.now());
		model.addAttribute("historyData", history);
		return "history";
	}
}
