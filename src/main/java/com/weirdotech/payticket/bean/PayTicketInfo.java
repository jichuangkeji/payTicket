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
    /**
     * violation : 8509254000
     * view_ticket : http://nycserv.nyc.gov/NYCServWeb/ShowImage?searchID=VDBSVmQwOVVTVEZPUkVGM1RVRTlQUT09
     * plate_details : 8888 NY MED
     * issue_date : 01/31/2017
     * liability : 95.00
     * pending : 0.00
     * violation_description : 20A-No Parking (Non-COM)
     * name : args.Violations.0.PAYMENTSELECTOR
     * appear : true
     * pay_enable : true
     */

    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    /**
     *  查询出来的罚单的重点内容
     */
    public static class DataBean {
        private String violation;
        private String view_ticket;
        private String plate_details;
        private String issue_date;
        private String liability;
        private String pending;
        private String violation_description;
        private String name;
        private boolean appear;
        private boolean pay_enable;

        public String getViolation() {
            return violation;
        }

        public void setViolation(String violation) {
            this.violation = violation;
        }

        public String getView_ticket() {
            return view_ticket;
        }

        public void setView_ticket(String view_ticket) {
            this.view_ticket = view_ticket;
        }

        public String getPlate_details() {
            return plate_details;
        }

        public void setPlate_details(String plate_details) {
            this.plate_details = plate_details;
        }

        public String getIssue_date() {
            return issue_date;
        }

        public void setIssue_date(String issue_date) {
            this.issue_date = issue_date;
        }

        public String getLiability() {
            return liability;
        }

        public void setLiability(String liability) {
            this.liability = liability;
        }

        public String getPending() {
            return pending;
        }

        public void setPending(String pending) {
            this.pending = pending;
        }

        public String getViolation_description() {
            return violation_description;
        }

        public void setViolation_description(String violation_description) {
            this.violation_description = violation_description;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isAppear() {
            return appear;
        }

        public void setAppear(boolean appear) {
            this.appear = appear;
        }

        public boolean isPay_enable() {
            return pay_enable;
        }

        public void setPay_enable(boolean pay_enable) {
            this.pay_enable = pay_enable;
        }
    }
}
