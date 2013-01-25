package com.company.emcare.service.impl;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;

import com.sun.corba.se.impl.encoding.OSFCodeSetRegistry.Entry;
import com.company.emcare.dao.VoiceDao;
import com.company.emcare.model.Voice;
import com.company.emcare.model.VoiceType;
import com.company.emcare.service.ReportService;
import com.company.emcare.service.VoiceService;
import com.company.emcare.util.DateUtil;
import com.company.emcare.util.MathUtil;

@Service("reportService")
public class ReportServiceImpl implements ReportService {

	@Resource(name = "voiceService")
	private VoiceService voiceService;

	@Override
	public String generateStateBarReport(String start, String end) {
		String json = "";
		try {
			Map result = countStateByTimeUnit(voiceService.getVoicesByPeriod(start, end),countYear(start, end));
			json = generateBarJson(result, countYear(start, end), start);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}

	@Override
	public String generateTypeBarReport(String start, String end) {
		// TODO Auto-generated method stub
		String json = "";
		try {
			Map result = countTypeByTimeUnit(voiceService.getVoicesByPeriod(start, end),countYear(start, end));
			json = generateBarJson(result, countYear(start, end), start);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}

	@Override
	public String generateStatePieReport(String start, String end) {
		// TODO Auto-generated method stub
		String json = "";
		try {
			Map result = countByField(initMapKey("state"),voiceService.getVoicesByPeriod(start, end),"state");
			json = generatePieJson(result, Voice.getStatusString(Voice.STATUS_OPEN));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}

	@Override
	public String generateTypePieReport(String start, String end) {
		// TODO Auto-generated method stub
		String json = "";
		try {
			Map result = countByField(initMapKey("type"),voiceService.getVoicesByPeriod(start, end),"type");
			json = generatePieJson(result, voiceService.getAllVoiceTypes().get(0).getName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}
	

	@SuppressWarnings("unchecked")
	private Map countStateByTimeUnit(List<Voice> voices, int yearAmount) {
		Map countResult = initBarMap(initMapKey("state"),yearAmount);
		if (yearAmount == 0) {
			for (Voice voice : voices) {
				((int[]) countResult.get(Voice.getStatusString(voice.getStatus())))[DateUtil.getMonth(voice.getSubmitTime())] += 1;
			}
		} else {
			for (Voice voice : voices) {
				int voiceYear = DateUtil.getYear(voice.getSubmitTime());
				Integer count = ((Map<Integer, Integer>) countResult.get(Voice.getStatusString(voice.getStatus()))).get(voiceYear);
				if (count == null) {
					((Map<Integer, Integer>) countResult.get(Voice.getStatusString(voice.getStatus()))).put(voiceYear, 1);
				} else {
					((Map<Integer, Integer>) countResult.get(Voice.getStatusString(voice.getStatus()))).put(voiceYear, count + 1);
				}
			}
		}

		return countResult;
	}
	
	@SuppressWarnings("unchecked")
	private Map countTypeByTimeUnit(List<Voice> voices, int yearAmount) {
		Map countResult = initBarMap(initMapKey("type"),yearAmount);
		if (yearAmount == 0) {
			for (Voice voice : voices) {
				String voiceType = null;
				if(voice.getType()!=null){
					voiceType = voice.getType().getName();
				}else{
					voiceType="unclassified";
				}
				((int[]) countResult.get(voiceType))[DateUtil.getMonth(voice.getSubmitTime())] += 1;
			}
		} else {
			for (Voice voice : voices) {
				int voiceYear = DateUtil.getYear(voice.getSubmitTime());
				String voiceType = null;
				if(voice.getType()!=null){
					voiceType = voice.getType().getName();
				}else{
					voiceType="unclassified";
				}
				Integer count = ((Map<Integer, Integer>) countResult.get(voiceType)).get(voiceYear);
				if (count == null) {
					((Map<Integer, Integer>) countResult.get(voiceType)).put(voiceYear, 1);
				} else {
					((Map<Integer, Integer>) countResult.get(voiceType)).put(voiceYear, count + 1);
				}
			}
		}

		return countResult;
	}

	@SuppressWarnings("unchecked")
	private String generateBarJson(Map result, int yearAmount, String start) {
		StringBuffer json = new StringBuffer("[");
		try {
			if (yearAmount == 0) {
				for (Object state : result.keySet()) {
					json.append("{");
					json.append("\"name\":");
					json.append("\"" + (String) state + "\",");
					json.append("\"data\":");
					json.append(generateBarData((int[]) result.get(state)));
					json.append("},");
				}
			} else {
				for (Object state : result.keySet()) {
					json.append("{");
					json.append("\"name\":");
					json.append("\"" + (String) state + "\",");
					json.append("\"data\":");
					json.append(generateBarData((Map<Integer, Integer>)result.get(state),yearAmount, start));
					json.append("},");
				}
			}
			json.deleteCharAt(json.toString().lastIndexOf(","));
			json.append("]");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json.toString();
	}

	private String generateBarData(Map<Integer, Integer> datas, int yearAmount,	String start) throws ParseException {
		int[] temp = new int[yearAmount + 1];
		int startYear = DateUtil.getYear(DateUtil.date2Timestamp(start));
		if (datas.size() > 0) {
			for (Integer year : datas.keySet()) {
				temp[year - startYear] = datas.get(year);
			}
		} else {
			for (int i = 0; i < temp.length; i++) {
				temp[i] = 0;
			}
		}
		return generateBarData(temp);
	}

	private String generateBarData(int[] datas) {
		StringBuffer result = new StringBuffer("[");
		for (int i = 0; i < datas.length; i++) {
			result.append(datas[i] + ",");
		}
		result.deleteCharAt(result.toString().lastIndexOf(","));
		result.append("]");
		return result.toString();
	}

	private int countYear(String start, String end) {
		int yearAmount = -1;
		if (start == null || start.equals("")) {
			start = DateUtil.VERY_BEGINING;
		}
		if (end == null || end.equals("")) {
			end = DateUtil.getDateStringWithPatten(new Date(),
					DateUtil.DATE_FULL_FORMAT);
		}
		try {
			int startYear = DateUtil.getYear(DateUtil.date2Timestamp(start));
			int endYear = DateUtil.getYear(DateUtil.date2Timestamp(end));
			yearAmount = endYear % startYear;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return yearAmount;
	}

	private Map<String, Float> countByField(List<String> keys,List<Voice> voices,String requiredField) {
		Map<String, Float> countResult = initPieMap(keys);
		List<String> fields = new ArrayList<String>();
		if(requiredField.equals("state")){
			for (Voice voice : voices) {
				fields.add(Voice.getStatusString(voice.getStatus()));
			}
		}else if(requiredField.equals("type")){
			for (Voice voice : voices) {
				if(voice.getType()==null){
					fields.add("unclassified");
				}
				else{
					fields.add(voice.getType().getName());
				}
			}
		}
		count(keys,fields,countResult);
		return countResult;
	}
	
	private void count(List<String> keys,List<String> fields,Map<String,Float> countResult){
		for (String field : fields) {
			countResult.put(field,MathUtil.getFloatWith2((countResult.get(field) * fields.size() + 1) / fields.size()));
		}
		float lastShare = 1.0f;
		for(int i = 0; i < keys.size() - 1; i++){
			lastShare -= countResult.get(keys.get(i)); 
		}
		countResult.put(keys.get(keys.size() - 1), MathUtil.getFloatWith2(lastShare));
	}
	
	private Map<String, Float> initPieMap(List<String> keys){
		Map<String, Float> countResult = new HashMap<String, Float>();		
		for(String key : keys){
			countResult.put(key, 0.00f);
		}		
		return countResult;
	}

	private Map initBarMap(List<String> keys,int yearAmount) {
		if (yearAmount == 0) {
			Map<String, int[]> result = new HashMap<String, int[]>();
			for(String key : keys){
				result.put(key, new int[12]);
			}
			return result;
		} else {
			Map<String, Map<Integer, Integer>> result = new HashMap<String, Map<Integer, Integer>>();
			for(String key : keys){
				result.put(key, new HashMap<Integer, Integer>());
			}
			return result;
		}
	}
	
	private List<String> initMapKey(String category){
		List<String> keys = new ArrayList<String>();
		if(category.equals("state")){
			keys.add(Voice.getStatusString(Voice.STATUS_OPEN));
			keys.add(Voice.getStatusString(Voice.STATUS_ASSIGNED));
			keys.add(Voice.getStatusString(Voice.STATUS_PENDING));
			keys.add(Voice.getStatusString(Voice.STATUS_RESOLVED));
			keys.add(Voice.getStatusString(Voice.STATUS_REJECTED));
			keys.add(Voice.getStatusString(Voice.STATUS_CLOSED));
		}else if(category.equals("type")){
			for(VoiceType voiceType : voiceService.getAllVoiceTypes()){
				keys.add(voiceType.getName());
			}
			keys.add("unclassified");
		}
		return keys;
	}

	private String generatePieJson(Map result,String defaultSelected){
		StringBuffer json = new StringBuffer("{");
		try {
			json.append("\"type\":\"pie\",");
			json.append("\"name\":\"Voice Type Share\",");
			json.append("\"data\":[");
			for (Object state : result.keySet()) {				
				if(((String)state).equals(defaultSelected)){
					json.append("{");
					json.append("\"name\":");
					json.append("\"" + (String) state + "\",");	
					json.append("\"y\":");
					json.append(result.get(state) + ",");
					json.append("\"sliced\":");
					json.append("\"true\",");
					json.append("\"selected\":");
					json.append("\"true\"");	
					json.append("},");
				}else{
					json.append("[");
					json.append("\"" + (String) state + "\",");				
					json.append(result.get(state));
					json.append("],");
				}
				
			} 
			json.deleteCharAt(json.toString().lastIndexOf(","));
			json.append("]}");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json.toString();
	}

	@Override
	public String gnerateStateAreaReport(String start, String end) {
		List<Voice> voices = voiceService.getVoicesByPeriod(start, end);
		Map<String,List<Voice>> stateSortedVoices = classifyVoiceByState(voices);
		StringBuilder json = new StringBuilder();
		
		for(Map.Entry<String, List<Voice>> entry:stateSortedVoices.entrySet()){
				Map<Date,Long> map = initDateKeys(start, end);
				for(Date date : map.keySet()){
					// STATISTIC voice amount of specific state
					map.put(date, statisticVoiceByPeriod(entry.getValue(), date));
				}
				json.append("{");
				json.append("\"name\":");
				json.append("\"" + (String) entry.getKey() + "\",");
				json.append("\"data\":");
				JSONObject obj = JSONObject.fromObject(map.values());
				json.append(obj.toString());
				json.append("");
				
				json.append("},");
		}
		return json.substring(0, json.lastIndexOf(","));
	}
	
	
	private  Map<Date, Long> initDateKeys (String start,String end){
		DateFormat df = new SimpleDateFormat(DateUtil.DATE_DEFAULT_FORMAT);
		try {
			df.parse(start);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Date begin = new Date(Date.parse(start));
		Map<Date,Long> result = new TreeMap<Date,Long>();
		for(int i=0;i<DateUtil.daysBetween(start, end);i++){
			result.put(DateUtils.addDays(begin, i+1),null);
		}
		return result;
	}
	   
	
	/*
	 * Classify voices by status
	 */
	private Map<String, List<Voice>> classifyVoiceByState(List<Voice> voices){
		List<String> voiceStates = initMapKey("state");
		Map<String,List<Voice>> result = new HashMap<String, List<Voice>>();
		for(String voiceState:voiceStates){
			for(Voice voice : voices){
				if(voice.getStatusString().equals(voiceState)){
					if(result.get(voiceState)==null){
						result.put(voiceState, new ArrayList<Voice>());
					}
						result.get(voiceState).add(voice);
				}
			}
		}
		return result;
	}
	
	private long statisticVoiceByPeriod(List<Voice> voices, Date period ){
		long count=0;
		for(Voice voice : voices){
			Timestamp submitTime = voice.getSubmitTime();
			if(DateUtils.isSameDay(period, submitTime)){
				count++;
			}
		}
		return count;
	}
	
}
