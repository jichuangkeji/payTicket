package com.weirdotech.payticket.webconf;

import java.util.List;

/**
 * Created by Bingo on 17/5/31.
 */
public class SynBody {

    /**
     * enterpriseId : 798069831187641858
     * flag : [1,2,3]
     * updateTime : 2017-05-10 00:00:00
     */

    private String enterpriseId;
    private String updateTime;
    private List<Integer> flag;

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public List<Integer> getFlag() {
        return flag;
    }

    public void setFlag(List<Integer> flag) {
        this.flag = flag;
    }
}

