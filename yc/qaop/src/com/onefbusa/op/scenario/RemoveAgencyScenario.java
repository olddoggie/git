package com.onefbusa.op.scenario;

import com.onefbusa.op.properties.TestData;

import static com.onefbusa.op.util.Account.*;

public class RemoveAgencyScenario {
    public static void  RemoveAgencyByName(String... agency_agent) throws Exception {
        removeAgency(agency_agent);
    }

    public static void main(String[] args) {
        try {
            RemoveAgencyByName(TestData.AgencyName,TestData.Login_AgentEmail);
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }

}
