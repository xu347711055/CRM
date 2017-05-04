package com.xu.attachment.action;

import java.io.File;
import java.util.Calendar;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.xu.attachment.domain.Attachment;
import com.xu.attachment.service.AttachService;
import com.xu.common.util.ConfigUtil;

@ParentPackage("cms")
@Namespace("/attachment")
@Action("doUpdate")
@InterceptorRef("privilegeStack")
@Result(name="success",type="redirectAction",params={"actionName","listAttach","custId","${custId}"})
public class DoUpdateAction {
	@Autowired
	private AttachService attachService;
	private File upload;
	private String uploadFileName;
	private String uploadContentType;
	
	private int custId;
	private int attachId;
	private String fileName;
	
	public String execute() throws Throwable{
		Attachment attachment = attachService.get(Attachment.class, attachId);
		if(fileName!=null){
			attachment.setFileName(fileName);
		}
		
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
			
			attachment.setSaveName(date + "/" + saveName);
		}
		
		attachService.update(attachment);
		return "success";
	}

	public AttachService getAttachService() {
		return attachService;
	}

	public void setAttachService(AttachService attachService) {
		this.attachService = attachService;
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

	public int getAttachId() {
		return attachId;
	}

	public void setAttachId(int attachId) {
		this.attachId = attachId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
}
