package com.company.emcare.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.Preparable;
import com.company.emcare.service.ReportService;
import com.company.emcare.service.VoiceService;
import com.company.emcare.util.DateUtil;

@Controller
@Scope("prototype")
public class ChartReportAction extends BaseAction implements Preparable {

	private static final long serialVersionUID = 3921239863314087269L;
	private String start;
	private String end;
	private String type;
	@Resource(name="reportService")
	private ReportService reportService;

	public String generatBarChart() throws Exception {
		/*String start = ServletActionContext.getRequest().getParameter("start");
		String end = ServletActionContext.getRequest().getParameter("end");
		String type = ServletActionContext.getRequest().getParameter("type");*/
		prepare();
		String json;
		if(type.equals("state")){
			json = reportService.generateStateBarReport(start, end);
		}else{
			json = reportService.generateTypeBarReport(start, end);
		}
		ServletActionContext.getResponse().setContentType("application/json");
		ServletActionContext.getResponse().getWriter().write(json);
		
		return null;
	}

	public String generatPieChart() throws Exception {
		/*String start = ServletActionContext.getRequest().getParameter("start");
		String end = ServletActionContext.getRequest().getParameter("end");
		String type = ServletActionContext.getRequest().getParameter("type");*/
		String json;
		if(type.equals("state")){
			json = reportService.generateStatePieReport(start, end);
		}else{
			json = reportService.generateTypePieReport(start, end);
		}
		ServletActionContext.getResponse().setContentType("application/json");
		ServletActionContext.getResponse().getWriter().write(json);		
		return null;
	}
	
	public String generateAreaChart() throws Exception{
		String json ="";
		if("state".equals(type)){
		json =	reportService.gnerateStateAreaReport(start,end);
		}
		response.getWriter().print(json);
		return null;
	}

	@Override
	public void prepare() throws Exception {
		
		if(StringUtils.isEmpty(start)&&StringUtils.isEmpty(end)){
			Date date = new Date();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			date.setDate(1);
			start =df.format(date);
			date.setDate(Calendar.getInstance().getLeastMaximum(Calendar.DAY_OF_MONTH));
			end = df.format(date);
		}else if(StringUtils.isEmpty(start)&&!StringUtils.isEmpty(end)){
			start = DateUtil.VERY_BEGINING;
		}
		
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	
}
