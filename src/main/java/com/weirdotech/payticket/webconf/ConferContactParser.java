package com.weirdotech.payticket.webconf;

import com.weirdotech.payticket.utils.contact.BaseContactItem;
import com.weirdotech.payticket.webconf.model.ConferContactItem;
import com.weirdotech.payticket.webconf.model.ConferDepart;
import com.weirdotech.payticket.webconf.model.ConferEnterprise;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Bingo on 17/5/31.
 */
public class ConferContactParser {
    public ConferEnterprise companyInfo = null;
    public List<ConferContactItem> contactGroups = new ArrayList<>();
    public HashMap<String, ConferContactItem> contactGroupMap = new LinkedHashMap<>();
    public HashMap<String, ConferContactItem> contactNumberMap = new LinkedHashMap<>();
    public HashMap<String, ConferContactItem> contactLookupMap = new LinkedHashMap<>();
    public Map<String, List<BaseContactItem>> numberListMap = new HashMap<>();
    public List<ConferContactItem> allContacts = new ArrayList<>();


    public static ConferContactParser parser(SynResult synResult) {
        ConferContactParser parser = new ConferContactParser();
        parser.companyInfo = synResult.getEnterprise();
        parserGroup(synResult.getDeparts(), parser.contactGroups, parser.contactGroupMap);
        return parser;
    }

    public static void parserGroup(List<ConferDepart> departs,
                                   List<ConferContactItem> contactGroups,
                                   Map<String, ConferContactItem>  groupMap) {

        if(departs == null) {
            return;
        }

        for (ConferDepart depart : departs) {
            ConferContactItem group = new ConferContactItem(1, "");
            group.setGroup(true);
            group.setName(depart.getDepartName());
            group.setDepId(Long.parseLong(depart.getDepartId()));
            group.setParentId(Long.parseLong(depart.getSuperiorDepartmentId()));

            contactGroups.add(group);


        }
    }



}
