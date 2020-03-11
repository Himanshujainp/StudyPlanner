package com.planner.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.planner.model.Plan;

@Service
public class StudyPlannerService {

	public List<Plan> getPlan() {
		List<Plan> plans = new ArrayList<Plan>();

		final Properties prop = new Properties();
		final String propFileName = "plan.properties";
		final InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
		try {
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
		} catch (IOException e) {
			System.out.println("Exception Thrown");
		}
		Set<Object> keySet = prop.keySet();

		keySet.forEach(key -> {
			if (((String) key).startsWith(LocalDate.now().toString())) {
				String property = prop.getProperty((String) key);
				String[] properties = property.split(",");
				Plan plan = new Plan();
				plan.setSubject(properties[0]);
				plan.setUnit(properties[1]);
				plans.add(plan);
			}
		});

		return plans;
	}

	public String getTodaysQuote() {
		Random randomNumber = new Random();
		int quoteNumber = randomNumber.nextInt(12);
		final Properties prop = new Properties();
		final String propFileName = "quotes.properties";
		final InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
		try {
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
		} catch (IOException e) {
			System.out.println("Exception Thrown");
		}
		
		String quote = prop.getProperty("" + quoteNumber);
		return quote;
	}

	public List<Plan> getHistory(LocalDate now) {
		List<Plan> plans = new ArrayList<Plan>();
		final Properties prop = new Properties();
		final String propFileName = "plan.properties";
		final InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
		try {
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
		} catch (IOException e) {
			System.out.println("Exception Thrown");
		}
		Set<Object> keySet = prop.keySet();

		
		for(Object key: keySet) {
			String[] k;
			k = ((String)key).split("-");
			
			LocalDate planDate = LocalDate.of(Integer.parseInt(k[0]), Integer.parseInt(k[1]), Integer.parseInt(k[2]));
			Period p = Period.between(planDate, now);
			
			if (p.getDays() > 0) {
				String property = prop.getProperty((String) key);
				String[] properties = property.split(",");
				Plan plan = new Plan();
				plan.setDate(LocalDate.of(Integer.parseInt(k[0]), Integer.parseInt(k[1]), Integer.parseInt(k[2])));
				plan.setSubject(properties[0]);
				plan.setUnit(properties[1]);
				plans.add(plan);
			}
		}
		plans.sort(Comparator.comparing(Plan::getDate));
		return plans;
		
	}
}
