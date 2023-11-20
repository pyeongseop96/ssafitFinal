package com.ssafit.board.model.dto;

import org.springframework.web.bind.annotation.RequestParam;

public class SortInfo {
    private String userID;
    private String channelName;
    private String partInfo;
    private String sort;

    public SortInfo() {
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getPartInfo() {
        return partInfo;
    }

    public void setPartInfo(String partInfo) {
        this.partInfo = partInfo;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
