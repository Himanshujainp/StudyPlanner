package com.planner.model;

import java.time.LocalDate;

public class Plan {

	private LocalDate date;
	private String subject;
	private String unit;

	@Override
	public String toString() {
		return "Plan [subject=" + subject + ", unit=" + unit + "]";
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

}
