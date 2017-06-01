package com.weirdotech.payticket.webconf;

import com.weirdotech.payticket.webconf.model.ConferDepart;
import com.weirdotech.payticket.webconf.model.ConferEnterprise;
import com.weirdotech.payticket.webconf.model.ConferUser;

import java.util.List;

/**
 * Created by Bingo on 17/5/31.
 */
public class SynResult {
    /**
     * code : 200
     * msg : syn is success!
     */

    private ResponseBean response;
    /**
     * enterpriseId : 798069832187641856
     * name : 佳都科技股份有限公司(PCI)
     * departmentNum : 72
     */

    private ConferEnterprise enterprise;
    /**
     * departId : 798069832187641858
     * departName : RCS
     * superiorDepartmentId : 0
     * userNum : 0
     * status : 1
     */

    private List<ConferDepart> departs;
    /**
     * userId : 862517714218909696
     * userName : 余冰
     * mobile : 13826137750
     * email : yubing@jiaxincloud.com
     * belongCompanyId : 798069832187641856
     * belongDepartmentId : 798069832187641856
     * status : 1
     */

    private List<ConferUser> users;

    public ResponseBean getResponse() {
        return response;
    }

    public void setResponse(ResponseBean response) {
        this.response = response;
    }

    public ConferEnterprise getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(ConferEnterprise enterprise) {
        this.enterprise = enterprise;
    }

    public List<ConferDepart> getDeparts() {
        return departs;
    }

    public void setDeparts(List<ConferDepart> departs) {
        this.departs = departs;
    }

    public List<ConferUser> getUsers() {
        return users;
    }

    public void setUsers(List<ConferUser> users) {
        this.users = users;
    }

    public static class ResponseBean {
        private int code;
        private String msg;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }

}
