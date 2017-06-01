package com.weirdotech.payticket.webconf;

/**
 * Created by Bingo on 17/5/31.
 */
public class LoginResult {

    /**
     * createTime : 1496217639293
     * enterpriseId : 798069832187641856
     * expires : 86400
     * token : DWrM5eQykRN3J+26tWyPp5BdmpHrGq3bA7GJ2i5qiMTalM9wIz2ffvZM8poevtOzIkIJdothQK0pkhvEPBDhArgjP2tmyxsaC3ybxDPui/XBAgTNSsgAfL1WcltbNjsW6xogvcnWT9mxyoN6xFr7wktrd2XVIcDi
     * userId : 831548703763333120
     * username : 13910043151
     */

    private long createTime;
    private String enterpriseId;
    private int expires;
    private String token;
    private String userId;
    private String username;

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public int getExpires() {
        return expires;
    }

    public void setExpires(int expires) {
        this.expires = expires;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
