package com.xu.attachment.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.xu.attachment.domain.Attachment;
import com.xu.attachment.service.AttachService;

@ParentPackage("cms")
@Namespace("/attachment")
@Action("del")
@InterceptorRef("privilegeStack")
@Result(name="success",type="redirectAction",params={"actionName","listAttach","custId","${custId}"})
public class DelAction {
	@Autowired
	private AttachService attachService;
	private int custId;
	private int attachId;
	
	public String execute(){
		Attachment attachment = new Attachment();
		attachment.setId(attachId);
		attachService.delete(attachment);
		return "success";
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
	
}
