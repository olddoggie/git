package com.onefbusa.op.scenario;

import com.onefbusa.op.properties.TestData;

import static com.onefbusa.op.util.Account.*;

public class RemovePropertyScenario {
    public static void  RemovePropertyByAgencyName (String agency) throws Exception {
        removePropertyByAgencyName(agency); ;
    }

    public static void main(String[] args) {
        try {
            RemovePropertyByAgencyName(TestData.AgencyName);
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }

}
