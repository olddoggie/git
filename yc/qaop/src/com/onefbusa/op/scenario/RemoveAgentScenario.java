package com.onefbusa.op.scenario;

import com.onefbusa.op.properties.TestData;

import static com.onefbusa.op.util.Account.*;

public class RemoveAgentScenario {
    public static void  RemoveAgent(String agent) throws Exception {
        unRegisterAgent(agent);
    }

    public static void main(String[] args) {
        try {
            RemoveAgent(TestData.Login_AgentEmail);
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }

}
