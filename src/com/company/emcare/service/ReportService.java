package com.company.emcare.service;


public interface ReportService {
	public String generateStateBarReport(String start, String end);
	
	public String generateTypeBarReport(String start, String end);	
	
	public String generateStatePieReport(String start, String end);
	
	public String generateTypePieReport(String start, String end);	
	
	public String gnerateStateAreaReport(String start,String end);
}
