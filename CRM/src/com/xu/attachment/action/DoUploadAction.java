package com.xu.attachment.action;

import java.io.File;
import java.util.Calendar;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.xu.attachment.domain.Attachment;
import com.xu.attachment.service.AttachService;
import com.xu.common.util.ConfigUtil;
import com.xu.customer.domain.Customer;
import com.xu.user.domain.User;

@ParentPackage("cms")
@Namespace("/attachment")
@Action("doUpload")
@Result(name="success",type="redirectAction",params={"actionName","listAttach","custId","${custId}"})
public class DoUploadAction {
	@Autowired
	private AttachService attachService;
	private File upload;
	private String uploadFileName;
	private String uploadContentType;
	
	private int custId;
	private String fileName;
	
	public String execute() throws Throwable{
		if(upload!=null){
			String extension = FilenameUtils.getExtension(uploadFileName);
			String saveName = UUID.randomUUID().toString() + "." + extension;
			//创建文件夹
			Calendar ca = Calendar.getInstance();
			String date = "";
			if (ca.get(Calendar.MONTH) + 1 < 10) {
				date = ca.get(Calendar.YEAR) + "0" + (ca.get(Calendar.MONTH) + 1) + "" + ca.get(Calendar.DATE);
			} else {
				date = ca.get(Calendar.YEAR) + "" + (ca.get(Calendar.MONTH) + 1) + "" + ca.get(Calendar.DATE);
			}
			String saveDir = ConfigUtil.getValue("attachmentPath") + date + "/";
			File mkdir = new File(saveDir);
			if (!mkdir.exists()) {
				System.out.println(mkdir.mkdirs());
			}
			System.out.println("保存路径" + saveDir + saveName);
			FileUtils.copyFile(upload, new File(saveDir + saveName));
			
			Attachment attachment = new Attachment();
			attachment.setSaveName(date + "/" + saveName);
			attachment.setFileName(fileName + "." + extension);
			User curUser = (User) ActionContext.getContext().getSession().get("user");
			attachment.setCreator(curUser);
			Customer cust = new Customer();
			cust.setId(custId);
			attachment.setCust(cust);
			attachService.add(attachment);
		}
		return "success";
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
}
