package com.onefbusa.op.scenario;

import com.onefbusa.op.properties.TestData;

import static com.onefbusa.op.util.Account.*;

public class RemoveLeaseScenario {
    public static void  RemoveLeaseByAgencyName(String agency) throws Exception {
        removeLeaseByAgencyName(agency);
    }

    public static void main(String[] args) {
        try {
            RemoveLeaseByAgencyName(TestData.AgencyName);
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }

}
