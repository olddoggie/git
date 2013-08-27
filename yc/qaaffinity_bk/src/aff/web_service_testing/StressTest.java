package aff.web_service_testing;

import com.onefbusa.afddo.AdjustmentDDO;
import com.onefbusa.afddo.AdjustmentListDDO;
import com.onefbusa.afddo.AdjustmentQueryDDO;
import com.onefbusa.afddo.AgentDDO;
import com.onefbusa.afddo.AutopayQueryDDO;
import com.onefbusa.afddo.CallResultDDO;
import com.onefbusa.afddo.NewNoteDDO;
import com.onefbusa.afddo.NoPaymentCallResultDDO;
import com.onefbusa.afddo.NoteQueryDDO;
import com.onefbusa.afddo.ProductivityDDO;
import com.onefbusa.afddo.ProductivityQueryDDO;
import com.onefbusa.afddo.UserDataDDO;
import com.onefbusa.afddo.UserDataInputDDO;
import com.onefbusa.afddo.UserQueryDDO;
import com.onefbusa.afwebservice.AFWebService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import org.apache.log4j.Logger;



public class StressTest {
  private static Logger logger = Logger.getLogger(StressTest.class);
     
  private static ArrayList accountNumberList; 
  private static int accountNumberIndex = 0;
  private static String ITO_URL = "http://ditoap03.1fb.net:8004";
  private static String LOAD_BALANCER = "http://qabpm.1fbusa.com:7770";
  private static String QA_01 = "http://qsfoap01.1fbusa.com:8004";
  private static String QA_02 = "http://qsfoapv2.1fbusa.com:8004";
  static String LOCAL_HOST = "http://localhost:8004";
  private static String STG_07 = "http://s363apv3.1fbusa.com:8004";
  private static String STG_08 = "http://s363ap08.1fbusa.com:8004";

  public static void main(String[] args) {
     int threadCount = 1;
     int loopCount   = 10;
     int sleepTime   = 50;

     StressTest run = new StressTest();
     run.loadAccountNumberBuffer();
    
     run.new mainThread(threadCount, loopCount, sleepTime);
  }         

  class mainThread extends Thread{
     int threadCount;
     int loopCount;
     int sleepTime;
     mainThread(int threadCount, int loopCount, int sleepTime){
        this.threadCount = threadCount;
        this.loopCount   = loopCount;
        this.sleepTime   = sleepTime;
        start();
     }
     public void run(){
        try{
           AFWebService webService = getAFWebService(QA_02);
			AFWebService service = webService;
           for(int i = 0; i < threadCount; i++){
              new GetUserDataThread(service, loopCount, sleepTime, i);
              //new GetProductivityThread(service, loopCount, sleepTime, i);
              sleep(100);
              System.out.println(Thread.currentThread().getId() + "start ----" + new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
           }
        }catch(Exception e){
           System.out.println(e);
        }
     }
  }

  class GetUserDataThread extends Thread{

     int loopCount;
     int sleepTime;
     int thread;
     AFWebService service = null;
     
     GetUserDataThread(AFWebService service, int loopCount, int sleepTime, int thread){
        this.service   = service;   
        this.loopCount = loopCount;
        this.sleepTime = sleepTime;
        this.thread     = thread;
        
        start();
     }

     public void run(){

        UserDataInputDDO userDataInputDDO;
        
        String accountNumber = "4017240003000044"; 
        String agentCode     = "169";
        int    contactId     = 3432;
        userDataInputDDO =  getUserDataInputDDO(accountNumber,     //accountNumber
                                             agentCode,          //agentCode
                                             "Not Set",          //agentProfile
                                              1,                 //channelId
                                              contactId,         //contactId
                                              "Not Set",         //collectorGroup  
                                              1068,              //employeeId
                                              2,                 //queryId
                                              4);                //sourceId 

        Runtime rt = Runtime.getRuntime();
        try{
           for(int i = 0; i < loopCount; i++){
               UserDataDDO ddo = service.getUserData(userDataInputDDO);
               System.out.println(Thread.currentThread().getId() + "end ----" + new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
              if(ddo.getIsDataComplete() == false){
                 System.out.println(ddo.getErrDesc());
              }
              sleep(sleepTime);
           }
           
        }catch(Exception e){
           System.out.println("User Data Exception   " +e );
           return;
        }
     }
  }

  class GetProductivityThread extends Thread{

     int loopCount;
     int sleepTime;
     int thread;
     AFWebService service = null;
     
     GetProductivityThread(AFWebService service, int loopCount, int sleepTime, int thread){
        this.service   = service;   
        this.loopCount = loopCount;
        this.sleepTime = sleepTime;
        this.thread     = thread;
        
        start();
     }

     public void run(){
       
        ProductivityQueryDDO productivityQueryDDO = new ProductivityQueryDDO();
        productivityQueryDDO.setAgentCode("169");

        Runtime rt = Runtime.getRuntime();
        try{
           for(int i = 0; i < loopCount; i++){
              ProductivityDDO productivityDDO = service.getProductivity(productivityQueryDDO);
              sleep(sleepTime);
           }
        }catch(Exception e){
              System.out.println("Productivity Exception   " +e );
           return;
        }
     }
  }

 
  static AFWebService getAFWebService(String host){
     AFWebService service = new AFWebService();  
     service.setWebServiceUrl(host);
     service.setLogMessages(false);
     return service;
  }

  protected UserDataInputDDO  getUserDataInputDDO(String accountNumber,
                                                String agentCode,
                                                String agentProfile,
                                                int    channelId,
                                                int    contactId,
                                                String collectorGroup,
                                                int    employeeId,
                                                int    queryId,
                                                int    sourceId) {

     UserQueryDDO userQueryDDO = new UserQueryDDO();
     userQueryDDO.setAcctNum(accountNumber);
     userQueryDDO.setContactId(contactId);
     
     UserDataInputDDO userDataInputDDO = new UserDataInputDDO(); 
     userDataInputDDO.setAgentCode(agentCode);             //F00105
     userDataInputDDO.setAgentProfile(agentProfile);       //F00109      
     userDataInputDDO.setChannelId(channelId);             //F00200
     userDataInputDDO.setCollectorGroup(collectorGroup);   //F00108
     userDataInputDDO.setEmployeeId(employeeId);           //F00100
     userDataInputDDO.setSourceId(sourceId);               //F00201
     userDataInputDDO.setUserDataQueryId(queryId);     // ????    
     userDataInputDDO.setUserQueryDDO(userQueryDDO);
     
     return userDataInputDDO;
  }            

  private NoteQueryDDO  getNoteQueryDDO(String accountNumber,
                                        String agentCode,
                                        int channelId,
                                        int contactId,
                                        int noteQueryTypeFlag,
                                        int sourceId)  {
     NoteQueryDDO noteQueryDDO = new NoteQueryDDO();
     noteQueryDDO.setAcctNum(accountNumber);                 //F00001
     noteQueryDDO.setAgentCode(agentCode);                   //F00105
     noteQueryDDO.setChannelId(channelId);                   //F00200
     noteQueryDDO.setContactId(contactId);                   //F10406
     noteQueryDDO.setNoteQueryTypeFlag(noteQueryTypeFlag);   //F10116
     noteQueryDDO.setSourceId(sourceId);                     //F00201 
     
     return noteQueryDDO;
  }
  
 private   AdjustmentQueryDDO getAdjustmentQueryDDO(String accountNumber,
                                                    int contactId,
                                                    String fromDate,
                                                    String toDate){
   AdjustmentQueryDDO adjustmentQueryDDO = new AdjustmentQueryDDO();
   adjustmentQueryDDO.setAcctNum(accountNumber); //F00001
   adjustmentQueryDDO.setContactId(contactId);   //F10406
   adjustmentQueryDDO.setFromDate(fromDate);
   adjustmentQueryDDO.setToDate(toDate);
   return adjustmentQueryDDO; 
 }
 

 private AutopayQueryDDO getAutopayQueryDDO(String accountNumber,
                                            int contactId,
                                            String fromDate,
                                            String toDate){
   AutopayQueryDDO autopayQueryDDO = new AutopayQueryDDO();
   autopayQueryDDO.setAcctNum(accountNumber);  //F00001
   autopayQueryDDO.setContactId(contactId);    //F10406
   autopayQueryDDO.setFromDate(fromDate);
   autopayQueryDDO.setToDate(toDate);

   return autopayQueryDDO;    
 }

 private CallResultDDO  getCallResultDDO(int[] activityIds,       
                                         int contactId,            
                                         int contactTypeId,        
                                         boolean permToDiscuss,    
                                         int[] surveyTypeIds,      
                                         int transferTypeId,       
                                         NewNoteDDO freeFormNote,  
                                         NewNoteDDO resultNote ){

   CallResultDDO callResultDDO = new CallResultDDO();
   callResultDDO.setActivityIds(activityIds);           //F10400 
   callResultDDO.setContactId(contactId);               //F10406
   callResultDDO.setContactTypeId(contactTypeId);       //F00203           
   callResultDDO.setPermToDiscuss(permToDiscuss);       //F00020          
   callResultDDO.setSurveyTypeIds(surveyTypeIds);       //F10404   
   callResultDDO.setTransferTypeId(4);                  //F10402
//   callResultDDO.setFreeFormNote(freeFormNote);         //F10104  
//   callResultDDO.setResultNote(resultNote);             //F10104

   return callResultDDO;            
 }


 private NewNoteDDO getNewNoteDDO(String accountNumber,
                                  boolean accountNotInFDR,
                                  String agentCode,
                                  int channelId,
                                  int contactId,
                                  String content,
                                  int priorityTypeId,
                                  int sourceId){

   NewNoteDDO	newNoteDDO = new NewNoteDDO();
   newNoteDDO.setAcctNum(accountNumber);            //F00001
   newNoteDDO.setAccountNotInFDR(accountNotInFDR);  //F10113
   newNoteDDO.setAgentCode(agentCode);              //F00105
   newNoteDDO.setChannelId(channelId);              //F00200
   newNoteDDO.setContactId(contactId);              //F10406 
   newNoteDDO.setContent(content);                  //F10104
   newNoteDDO.setPriorityTypeId(priorityTypeId);    //F10105
   newNoteDDO.setSourceId(sourceId);                //F00201 
   
   return newNoteDDO;
 } 

 private NoPaymentCallResultDDO getNoPaymentCallResultDDO(AgentDDO agentDDO,
                                                          int accountInFdrStatusId,
                                                          int contactId,
                                                          NewNoteDDO freeFormNote,
                                                          NewNoteDDO resultNote,
                                                          int resultActionEntryId ){
   NoPaymentCallResultDDO noPaymentCallResultDDO = new NoPaymentCallResultDDO();
   noPaymentCallResultDDO.setAccountInFdrStatusId(accountInFdrStatusId);        //F10114 
   noPaymentCallResultDDO.setActivityIds(new int[]{19,14});  //F10400
   noPaymentCallResultDDO.setAgent(agentDDO);                //F00116 
   noPaymentCallResultDDO.setContactId(contactId);           //F10406
   noPaymentCallResultDDO.setContactTypeId(2);               //F00203
//   noPaymentCallResultDDO.setFreeFormNote(freeFormNote);     //F10104
   noPaymentCallResultDDO.setNextWorkDate(getDatePlus(5));     //F01422
   noPaymentCallResultDDO.setNextWorkTime("09:30:00");       //F01423 
   noPaymentCallResultDDO.setPermToDiscuss(false);           //F00020
//   noPaymentCallResultDDO.setResultNote(resultNote);         //F10104
   noPaymentCallResultDDO.setResultActionEntryId(resultActionEntryId);  //F10417
   noPaymentCallResultDDO.setResultActionEntryToPost("UP TEST 01");     //F10419
   noPaymentCallResultDDO.setResultActionNote("ResultActionNote");      //F10510 
   noPaymentCallResultDDO.setResultContactDetailTypeId(0);              //F10490
   noPaymentCallResultDDO.setResultCorrespondenceReasonId(0);           //F10520
   noPaymentCallResultDDO.setResultInboundReasonId(0);	                 //F10443
   noPaymentCallResultDDO.setResultLetterId(0);                         //F10442 
   noPaymentCallResultDDO.setResultOtherOrWhatId(0);                    //F10500
   noPaymentCallResultDDO.setResultTransferSourceId(0);                 //F10470
   noPaymentCallResultDDO.setSurveyTypeIds(new int[]{1});               //F10404
   noPaymentCallResultDDO.setTransferTypeId(4);                         //F10402
   noPaymentCallResultDDO.setWhereContactLocatedId(0);                  //F10440
   
   return noPaymentCallResultDDO;
 }

 private static String getDatePlus(int days) {
   Calendar calendar = Calendar.getInstance();
   calendar.add(Calendar.DATE, days);
   
   SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
   simpleDateFormat.setLenient(false);
   
   return simpleDateFormat.format(calendar.getTime());
 }
 
 private void loadAccountNumberBuffer(){
   accountNumberList = new ArrayList();
//   accountNumberList.add("4017241522907628");
//   accountNumberList.add("4017240003000036");
//   accountNumberList.add("4017240003000044");
//   accountNumberList.add("4017240003000051");
//   accountNumberList.add("4017240003000077");
//   accountNumberList.add("4017240003000085");
//   accountNumberList.add("4017240003000093");
//   accountNumberList.add("4017240003000101");
//   accountNumberList.add("4017240003000119");
//   accountNumberList.add("5432270003000128");
//   accountNumberList.add("5432270003000136");
//   accountNumberList.add("5432270003000144");
//   accountNumberList.add("5432270003000151");
//   accountNumberList.add("5432270003000169");
//   accountNumberList.add("5432270003000177");
//   accountNumberList.add("5432270003000185");
//   accountNumberList.add("5432270003000193");
//   accountNumberList.add("5432270003000201");
//   accountNumberList.add("5432270003000219");
//   accountNumberList.add("5432270003000029");
//   accountNumberList.add("5432270003000037");
//   accountNumberList.add("5432270003000045");
//   accountNumberList.add("5432270003000052");
//   accountNumberList.add("5432270003000060");
accountNumberList.add("1231231231231234");
accountNumberList.add("1234567890123456");
accountNumberList.add("4000000000000002");
accountNumberList.add("4000000000000119");
accountNumberList.add("4017240000015961");
accountNumberList.add("4017240000037643");
accountNumberList.add("4017240000041850");
accountNumberList.add("4017240000052071");
accountNumberList.add("4017240000053145");
accountNumberList.add("4017240000900071");
accountNumberList.add("4017240001047484");
accountNumberList.add("4017240001079750");
accountNumberList.add("4017240001901672");
accountNumberList.add("4017240001917090");
accountNumberList.add("4017240002000029");
accountNumberList.add("4017240002901960");
accountNumberList.add("4017240003000028");
accountNumberList.add("4017240003000036");
accountNumberList.add("4017240003000044");
accountNumberList.add("4017240003000051");
accountNumberList.add("4017240003000069");
accountNumberList.add("4017240003000077");
accountNumberList.add("4017240003000085");
accountNumberList.add("4017240003000093");
accountNumberList.add("4017240003000101");
accountNumberList.add("4017240003000119");
accountNumberList.add("4017240003000201");
accountNumberList.add("4017240090000071");
accountNumberList.add("4017240100002562");
accountNumberList.add("4017240100037048");
accountNumberList.add("4017240100058028");

 }
 
 private synchronized String getNextAccount(){
   if(accountNumberIndex >= accountNumberList.size()){
     accountNumberIndex = 0;  
   }
   
   return (String)accountNumberList.get(accountNumberIndex++);
 }



}
