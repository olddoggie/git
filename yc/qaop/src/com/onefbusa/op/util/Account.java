package com.onefbusa.op.util;

import static com.onefbusa.op.util.OPSpringDB.*;

import org.testng.annotations.Test;

public class Account {

	public static void unRegisterAgent(String agentname) {
		
		String Remove_Agency_Agency_Contact = "delete from Agency_Agency_Contact AAC where AAC.AGENCY_CONTACT_ID in "
				+ "(select t.agency_contact_id  from agency_contact t where credential_id in "
				+ "(select c.credential_id from credential c where user_name='" +  agentname
				+ "'))";

		String Remove_Agency_Contact	= "delete from agency_contact t where credential_id in "
				+ "(select c.credential_id from credential c where user_name= '" 
				+ agentname
				+ "')";	

		String Remove_Credential_Hist = "delete from credential_hist ch where ch.credential_id in "
				+ "(select c.credential_id from credential c where user_name='"
				+ agentname
				+ "')";		

		String Remove_Credential = "delete from credential c where c.user_name='"
				+ agentname
				+ "'";

		opDB.update(Remove_Agency_Agency_Contact);
		opDB.update(Remove_Agency_Contact);
		opDB.update(Remove_Credential_Hist);
		opDB.update(Remove_Credential);

	}

public static void endingLease(String propertyName, String buildingName, String unitName) {
	
	String Ending_Lease = "update lease l set l.end_date = ADD_MONTHS(CURRENT_DATE,1) where l.unit_id = ("
				+ "select u.unit_id from unit u where u.unit_name = '" + unitName 
				+ 	"' and u.floor_id in (" 
				+ "select f.floor_id from floor f where f.building_id in ("
				+ "select b.building_id from building b where b.building_name ='" + buildingName
				+ "' and Property_id = (" 
				+ "select p.property_id from property p where p.property_name = '" + propertyName
				+"') ) ))  ";

	System.out.println(Ending_Lease);

	opDB.update(Ending_Lease);

	}

    public static void removeLeaseByAgencyName(String agency) {

        String Remove_TENANT_LEASE = "delete from TENANT_LEASE tl where tl.lease_id in " +
                "(select l.Lease_ID FROM Lease l where l.entered_by_agency_id in " +
                "(select a.agency_id from agency a where a.business_name='"
                + agency +"'))";

        String Remove_LEASE_RENEWAL ="delete from LEASE_RENEWAL lr where lr.lease_id in " +
                "(select l.Lease_ID FROM Lease l where l.entered_by_agency_id in " +
                "(select a.agency_id from agency a where a.business_name='"
                + agency +"'))";

        String Remove_LEASE_REFUND_HELD = "delete from LEASE_REFUND_HELD lrh where lrh.lease_refund_id in" +
                "(select lrf.lease_refund_id from LEASE_REFUND lrf where lrf.lease_id in " +
                "(select l.Lease_ID FROM Lease l where l.entered_by_agency_id in " +
                "(select a.agency_id from agency a where a.business_name='"
                + agency +"')))";

        String Remove_LEASE_REFUND = "delete from LEASE_REFUND lrf where lrf.lease_id in " +
                "(select l.Lease_ID FROM Lease l where l.entered_by_agency_id in " +
                "(select a.agency_id from agency a where a.business_name='"
                + agency +"'))";

        String Remove_Lease	= "delete from Lease l where l.entered_by_agency_id in " +
                "(select a.agency_id from agency a where a.business_name='" + agency + "')";

        opDB.update(Remove_TENANT_LEASE);
        opDB.update(Remove_LEASE_RENEWAL);
        opDB.update(Remove_LEASE_REFUND_HELD);
        opDB.update(Remove_LEASE_REFUND);
        opDB.update(Remove_Lease);
    }

    public static void removePropertyByAgencyName(String agency) {
        removeLeaseByAgencyName(agency);
        String PROPERTY_AGENCY = "delete from PROPERTY_AGENCY p where p.agency_id in " +
                "(select a.agency_id from agency a where a.business_name='" + agency + "')";
        opDB.update(PROPERTY_AGENCY);
    }

    public static void removeAgency(String... agency_agent) {
        String agency = agency_agent[0];
        String agent;
        if (agency_agent.length > 1) {
            agent = agency_agent[1];
        } else {
            agent = null;
        }
        removeLeaseByAgencyName(agency);
        removePropertyByAgencyName(agency);
        String Remove_Agency_Agency_Contact = "delete from agency_agency_contact aac where aac.agency_id in " +
                "(select t1.agency_id from agency t1 where t1.business_name='" + agency + "')";

        String Remove_Agency_Contact = "delete from agency_contact ac where " +
                "ac.agency_contact_id in " +
                "(select agency_contact_id from agency_agency_contact aac " +
                "where aac.agency_id in (select a.agency_id from agency a where a.business_name='"
                + agency + "'))";

        String Remove_Agency = "delete from agency a where a.business_name='" + agency + "'";

        opDB.update(Remove_Agency_Agency_Contact);
        opDB.update(Remove_Agency_Contact);
        opDB.update(Remove_Agency);

        // because actually agent is combined with agency, so once the agency has been removed, then the agent account is deprecated
        if (agent !=null){
            unRegisterAgent(agent);
        }
    }

}
