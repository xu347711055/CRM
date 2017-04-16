package com.xu.common.page;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

public class PageTag<T> implements Tag {
	private Tag patent;
	private PageContext pageContext;
	private PageVO<T> pagevo;
	// action路径
	private String url;
	
	private int pageSize;

	

	@Override
	public int doEndTag() throws JspException {
		
		if (pagevo != null && url != null) {
			StringBuilder sb = new StringBuilder();
			sb.append("<div class='row'><div class='col-md-12 text-center'><ul class='pagination pagination-lg'>");
			if (pagevo.getCurPage() > 1) {
				sb.append("<li><a href='").append(url).append("?curPage=").append(pagevo.getCurPage() - 1).append("&pageSize=").append(this.pageSize)
						.append("'>&laquo;</a></li>");
			} else {
				sb.append("<li class='disabled'><a class='disabled' href='").append(url).append("?curPage=")
						.append(pagevo.getCurPage() - 1).append("&pageSize=").append(this.pageSize).append("'>&laquo;</a></li>");
			}
			for (int i = (int) pagevo.getPageStart(), j = 0; i <= pagevo.getPageEnd(); i++, j++) {
				if ((pagevo.getPageStart() + j) == pagevo.getCurPage()) {
					sb.append("<li class='active'>").append("<a href='").append(url).append("?curPage=")
							.append(pagevo.getPageStart() + j).append("&pageSize=").append(this.pageSize).append("'>").append(pagevo.getPageStart() + j)
							.append("</a></li>");
				} else {
					sb.append("<li>").append("<a href='").append(url).append("?curPage=")
							.append(pagevo.getPageStart() + j).append("&pageSize=").append(this.pageSize).append("'>").append(pagevo.getPageStart() + j)
							.append("</a></li>");
				}
			}
			if (pagevo.getCurPage() < pagevo.getTotalPage()) {
				sb.append("<li><a href='").append(url).append("?curPage=").append(pagevo.getCurPage() + 1).append("&pageSize=").append(this.pageSize)
						.append("'>&raquo;</a></li>");
			} else {
				sb.append("<li class='disabled'><a class='disabled'href=").append(url).append("?curPage=")
						.append(pagevo.getTotalPage()).append("&pageSize=").append(this.pageSize).append(">&raquo;</a></li>");
			}
			sb.append("</ul></div></div>");
			try {
				pageContext.getOut().append(sb.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				pageContext.getOut().append("");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return Tag.SKIP_BODY;
	}

	@Override
	public int doStartTag() throws JspException {

		return 0;
	}

	@Override
	public Tag getParent() {

		return null;
	}

	@Override
	public void release() {

	}

	@Override
	public void setPageContext(PageContext arg0) {
		this.pageContext = arg0;

	}

	@Override
	public void setParent(Tag arg0) {
		this.patent = arg0;

	}

	public PageVO<T> getPagevo() {
		return pagevo;
	}

	public void setPagevo(PageVO<T> pagevo) {
		this.pagevo = pagevo;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Tag getPatent() {
		return patent;
	}

	public void setPatent(Tag patent) {
		this.patent = patent;
	}
	
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	

}
