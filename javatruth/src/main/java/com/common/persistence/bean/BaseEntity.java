
package com.common.persistence.bean;

import java.io.Serializable;
import java.util.Map;

import javax.xml.bind.annotation.XmlTransient;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.codehaus.jackson.annotate.JsonIgnore;

import com.common.config.Global;
import com.common.persistence.page.Page;
import com.common.utils.StringUtils;
import com.google.common.collect.Maps;
import com.javatruth.entity.User;

/**
 * 
 */
public abstract class BaseEntity<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 当前实体分页对象
	 */
	protected Page<T> page;
	

	@JsonIgnore
	@XmlTransient
	public Page<T> getPage() {
		if (page == null){
			page = new Page<T>();
		}
		return page;
	}
	
	public Page<T> setPage(Page<T> page) {
		this.page = page;
		return page;
	}

    
	/**
	 * 删除标记（0：正常；1：删除；2：审核；）
	 */
	public static final String DEL_FLAG_NORMAL = "0";
	public static final String DEL_FLAG_DELETE = "1";
	public static final String DEL_FLAG_AUDIT = "2";
	
}
