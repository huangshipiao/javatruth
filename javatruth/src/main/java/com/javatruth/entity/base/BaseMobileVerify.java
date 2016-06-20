package com.javatruth.entity.base;

import java.util.Date;

import com.common.persistence.bean.BaseEntity;
import com.javatruth.entity.MobileVerify;

public class BaseMobileVerify extends BaseEntity<MobileVerify> {
    private Integer id;

    private String mobile;

    private String code;

    private String content;

    private Integer status;

    private Date sendTime;

    private String ipaddr;

    private Integer sendDayCount;

    private Integer sendHourCount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getIpaddr() {
        return ipaddr;
    }

    public void setIpaddr(String ipaddr) {
        this.ipaddr = ipaddr == null ? null : ipaddr.trim();
    }

    public Integer getSendDayCount() {
        return sendDayCount;
    }

    public void setSendDayCount(Integer sendDayCount) {
        this.sendDayCount = sendDayCount;
    }

    public Integer getSendHourCount() {
        return sendHourCount;
    }

    public void setSendHourCount(Integer sendHourCount) {
        this.sendHourCount = sendHourCount;
    }
}