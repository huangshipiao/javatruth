package com.javatruth.entity.base;

import java.util.Date;

import com.common.persistence.bean.BaseEntity;
import com.javatruth.entity.AdminLog;

public class BaseAdminLog  extends BaseEntity<AdminLog>{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long logId;

    private Integer userId;

    private Integer logType;

    private Integer logResult;

    private Date logTime;

    private String logDetails;

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getLogType() {
        return logType;
    }

    public void setLogType(Integer logType) {
        this.logType = logType;
    }

    public Integer getLogResult() {
        return logResult;
    }

    public void setLogResult(Integer logResult) {
        this.logResult = logResult;
    }

    public Date getLogTime() {
        return logTime;
    }

    public void setLogTime(Date logTime) {
        this.logTime = logTime;
    }

    public String getLogDetails() {
        return logDetails;
    }

    public void setLogDetails(String logDetails) {
        this.logDetails = logDetails == null ? null : logDetails.trim();
    }

	@Override
	public String toString() {
		return "BaseAdminLog [logId=" + logId + ", userId=" + userId + ", logType=" + logType + ", logResult="
				+ logResult + ", logTime=" + logTime + ", logDetails=" + logDetails + "]";
	}
    
    
}