package com.xu.common.page;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

public class ModalTag implements Tag {
	private Tag patent;
	private PageContext pageContext;
	/**
	 * 模态框链接id
	 */
	private String modalId;
	/**
	 * 模态框触发按钮样式
	 */
	private String btnStyle;
	/**
	 * 按钮中的内容
	 */
	private String btnContent;
	/**
	 * 模态框标题
	 */
	private String header;
	/**
	 * 是否需要提交按钮
	 */
	private String doSubmit;
	/**
	 * 按钮类型
	 */
	private String type;
	
	/**
	 * 执行到开始标签加载
	 */
	@Override
	public int doStartTag() throws JspException {
		StringBuilder sb = new StringBuilder();
		
		sb.append("<div class='modal fade' id='").append(modalId);
		sb.append("' tabindex='-1' role='dialog' aria-labelledby='myModalLabel' aria-hidden='true'>");
		sb.append("<div class='modal-dialog'>").append("<div class='modal-content'>");
		sb.append("<div class='modal-header'>");
		sb.append("<button type='button' class='close' data-dismiss='modal' aria-hidden='true'>×");
		sb.append("</button><h4 class='modal-title' id='myModalLabel'>").append(header).append("</h4>");
		sb.append("</div>");
		sb.append("<div class='modal-body'>");
		System.out.println(sb);
		try {
			pageContext.getOut().write(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Tag.EVAL_BODY_INCLUDE;
	}
/**
 * 执行到结束标签加载
 */
	@Override
	public int doEndTag() throws JspException {
		StringBuilder sb = new StringBuilder();
		sb.append("</div>");
		if("true".equals(doSubmit)){
			sb.append("<div class='modal-footer'>").append("<button type='button' class='btn btn-default' ");
			sb.append("data-dismiss='modal'>关闭").append("</button>");
			sb.append("<button type='submit' class='btn btn-primary'>提交</button>");
			sb.append("</div>");
		}
		sb.append("</div>").append("</div>").append("</div>");
		System.out.println(sb);
		try {
			pageContext.getOut().write(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Tag.SKIP_BODY;
	}

	@Override
	public Tag getParent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void release() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPageContext(PageContext arg0) {
		this.pageContext = arg0;
		
	}

	@Override
	public void setParent(Tag arg0) {
		this.patent = arg0;
	}

	public Tag getPatent() {
		return patent;
	}

	public void setPatent(Tag patent) {
		this.patent = patent;
	}

	public PageContext getPageContext() {
		return pageContext;
	}

	public String getBtnStyle() {
		return btnStyle;
	}

	public void setBtnStyle(String btnStyle) {
		this.btnStyle = btnStyle;
	}

	public String getBtnContent() {
		return btnContent;
	}

	public void setBtnContent(String btnContent) {
		this.btnContent = btnContent;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getDoSubmit() {
		return doSubmit;
	}

	public void setDoSubmit(String doSubmit) {
		this.doSubmit = doSubmit;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getModalId() {
		return modalId;
	}
	public void setModalId(String modalId) {
		this.modalId = modalId;
	}
	
}
