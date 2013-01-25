package com.company.emcare.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.company.emcare.dao.VoiceDao;
import com.company.emcare.model.Voice;
import com.company.emcare.service.VoiceService;
@Controller
@Scope("prototype")
public class FileUploadAction extends BaseAction{
	
	private File imgFile;
	private String imgFileContentType;
	private String imgFileFileName;
	
	@SuppressWarnings("deprecation")
	public void executeUpload() throws Exception{
		
		String savePath = request.getRealPath("/") + "upload/images/";
		// relative saved url
		String saveUrl = request.getContextPath() + "/upload/images/";
		
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String newFileName = df.format(new Date()) + "_"
				+ new Random().nextInt(1000) + "_"
				+ imgFileFileName;
		
		File uploadedFile = new File(savePath, newFileName);
		InputStream input = new FileInputStream(imgFile);
		OutputStream output = new FileOutputStream(uploadedFile);
		
		IOUtils.copy(input, output);
		IOUtils.closeQuietly(output);
		IOUtils.closeQuietly(input);
				
		JSONObject obj = new JSONObject();
		obj.put("error", 0);
		obj.put("url", saveUrl + newFileName);
		this.response.getWriter().print(obj.toString());
	}

	public File getImgFile() {
		return imgFile;
	}

	public void setImgFile(File imgFile) {
		this.imgFile = imgFile;
	}

	public String getImgFileContentType() {
		return imgFileContentType;
	}

	public void setImgFileContentType(String imgFileContentType) {
		this.imgFileContentType = imgFileContentType;
	}

	public String getImgFileFileName() {
		return imgFileFileName;
	}

	public void setImgFileFileName(String imgFileFileName) {
		this.imgFileFileName = imgFileFileName;
	}


	
	
}
