package com.weirdotech.payticket.bean;

import java.util.List;

public class PayTicketInfo {
    /**
     * code : 200
     * msg : 查询成功
     * data : [{"violation":"8509254000","view_ticket":"http://nycserv.nyc.gov/NYCServWeb/ShowImage?searchID=VDBSVmQwOVVTVEZPUkVGM1RVRTlQUT09","plate_details":"8888 NY MED","issue_date":"01/31/2017","liability":"95.00","pending":"0.00","violation_description":"20A-No Parking (Non-COM)","name":"args.Violations.0.PAYMENTSELECTOR","appear":true,"pay_enable":true},{"violation":"8509216332","view_ticket":"http://nycserv.nyc.gov/NYCServWeb/ShowImage?searchID=VDBSVmQwOVVTWGhPYWsxNlRXYzlQUT09","plate_details":"8888 NY MED","issue_date":"05/02/2017","liability":"65.00","pending":"0.00","violation_description":"20A-No Parking (Non-COM)","name":"args.Violations.1.PAYMENTSELECTOR","appear":true,"pay_enable":true}]
     * violation_group_prompt :
     */

    private String code;
    private String msg;
    private String violation_group_prompt;

    private List<ViolationInfo> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getViolation_group_prompt() {
        return violation_group_prompt;
    }

    public void setViolation_group_prompt(String violation_group_prompt) {
        this.violation_group_prompt = violation_group_prompt;
    }

    public List<ViolationInfo> getData() {
        return data;
    }

    public void setData(List<ViolationInfo> data) {
        this.data = data;
    }

}
