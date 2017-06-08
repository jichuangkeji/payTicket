package com.weirdotech.payticket.bean;

/**
 * Created by Bingo on 17/6/8.
 */
public class CreateCardResult {

    /**
     * status_code : 200
     * data : {"id":"card_1ASROZDulEurMXqCS6STtB5K","object":"card","address_city":null,"address_country":null,"address_line1":null,"address_line1_check":null,"address_line2":null,"address_state":null,"address_zip":null,"address_zip_check":null,"brand":"Visa","country":"US","customer":"cus_Ao3Cyf6E9psAWn","cvc_check":"pass","dynamic_last4":null,"exp_month":12,"exp_year":2020,"fingerprint":"VU5YqXGfULftFOxH","funding":"credit","last4":"4242","metadata":[],"name":"bingocard","tokenization_method":null}
     */

    private int status_code;
    /**
     * id : card_1ASROZDulEurMXqCS6STtB5K
     * object : card
     * address_city : null
     * address_country : null
     * address_line1 : null
     * address_line1_check : null
     * address_line2 : null
     * address_state : null
     * address_zip : null
     * address_zip_check : null
     * brand : Visa
     * country : US
     * customer : cus_Ao3Cyf6E9psAWn
     * cvc_check : pass
     * dynamic_last4 : null
     * exp_month : 12
     * exp_year : 2020
     * fingerprint : VU5YqXGfULftFOxH
     * funding : credit
     * last4 : 4242
     * metadata : []
     * name : bingocard
     * tokenization_method : null
     */

    private CardInfo data;

    public int getStatus_code() {
        return status_code;
    }

    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }

    public CardInfo getData() {
        return data;
    }

    public void setData(CardInfo data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "[status_code: " + status_code + ", cardInfo: " + data.toString() + "]";
    }
}
