package tools;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

//public class S2C implements Comparable<S2C> {
// equals() and hashcode() need to be implemented for hashmap
public class __S2C_bk {	
	private String F02073_001_CPPORM;
	private String F02100_001_CPPOMP;
	private String F01014_001_CurrentBalance;
	public static Map<__S2C_bk,String> s2cMap;
	
	
	public static void main(String[] args)  {
		System.out.println(getF02073_001_CPPORM("MPDPN108","N/A","2200"));
	}
	
	public __S2C_bk(String cpporm, String cppomp, String currentBalance){
		this.F02073_001_CPPORM = cpporm;
		this.F02100_001_CPPOMP = cppomp;
		this.F01014_001_CurrentBalance = currentBalance;
		initialMap();
	}
	

	private static void initialMap(){
		if(s2cMap == null){
			s2cMap = new HashMap<__S2C_bk, String>();
			s2cMap.put(new __S2C_bk("2%FFC$15","N/A","N/A"), "2%x(bal less FFC) or FFC+$15 or $15 (Max3%)");
			s2cMap.put(new __S2C_bk("2%FFC$30","N/A","N/A"), "2%x(bal less FFC) or FFC+$15 or $30 (Max3%)");
			s2cMap.put(new __S2C_bk("2%FFC$35","N/A","N/A"), "2%x(bal less FFC) or FFC+$15 or $35 (Max3%)");
			
			s2cMap.put(new __S2C_bk("MPDBS108","N/A","x<7000"), "2.3% or $15");
			s2cMap.put(new __S2C_bk("108CBASE","N/A","x<7000"), "2.3% or $15");
			//5
			s2cMap.put(new __S2C_bk("MPDBS108","N/A","x>=7000"), "2.4% or $15");
			s2cMap.put(new __S2C_bk("108CBASE","N/A","x>=7000"), "2.4% or $15");
			//6
			s2cMap.put(new __S2C_bk("MPDPN108","N/A","x<750"), "2.3% or $15");
			s2cMap.put(new __S2C_bk("108CPNLT","N/A","x<750"), "2.3% or $15");
			//7
			s2cMap.put(new __S2C_bk("MPDPN108","N/A","x>=750&&x<3000"), "2.3%+$5 or $15");
			s2cMap.put(new __S2C_bk("108CPNLT","N/A","x<750"), "2.3%+$5 or $15");
		}
	}
	
//	public static String getF02073_001_CPPORM(String cpporm, String cppomp, String currentBalance){
//		S2C test = new S2C(cpporm,cppomp,currentBalance);
//		Iterator it = s2cMap.entrySet().iterator();
//		 while (it.hasNext()) {
//		        Map.Entry pairs = (Map.Entry)it.next();
//		       if(pairs.getKey().equals(test)){
//		    	   return (String) pairs.getValue();		    	  
//		       } 
//		    }
//		 return "no F02073_001_CPPORM found";
//	}
		
	public static String getF02073_001_CPPORM(String cpporm, String cppomp, String currentBalance){
		__S2C_bk test = new __S2C_bk(cpporm,cppomp,currentBalance);		
		return s2cMap.get(test);
	}
	
	@Override
	public boolean equals(Object aThat) {
		if (this == aThat) return true;
		if(!(aThat instanceof __S2C_bk))return false;
		final __S2C_bk that = (__S2C_bk)aThat;
		boolean balanceCompare=false;
		boolean cppormCompare= false;
		boolean cppompCompare= false;
		if(this.getF01014_001_CurrentBalance().equalsIgnoreCase("N/A")||that.getF01014_001_CurrentBalance().equalsIgnoreCase("N/A"))
		{
			balanceCompare= true;
		}else if(compareBalance(this.getF01014_001_CurrentBalance(),that.getF01014_001_CurrentBalance())){
			balanceCompare= true;
		}
		
		if(this.getF02073_001_CPPORM().equalsIgnoreCase("N/A")||that.getF02073_001_CPPORM().equalsIgnoreCase("N/A")){
			cppormCompare= true;
		}else if(this.getF02073_001_CPPORM().equalsIgnoreCase(that.getF02073_001_CPPORM())){
			cppormCompare= true;
		}
		
		if(this.getF02100_001_CPPOMP().equalsIgnoreCase("N/A")||that.getF02100_001_CPPOMP().equalsIgnoreCase("N/A")){
			cppompCompare= true;
		}else if(this.getF02100_001_CPPOMP().equalsIgnoreCase(that.getF02100_001_CPPOMP())){
			cppompCompare= true;
		}
		
		if(balanceCompare&&cppompCompare&&cppormCompare) return true;
		return false;
	}
	
	public static boolean compareBalance(String a, String b){
		ExpressionParser parser = new SpelExpressionParser();
		String expStr;
		if(a.matches("\\d+")&&!(b.matches("\\d+"))) {
			expStr = b.replaceAll("x", a);
			return (Boolean)parser.parseExpression(expStr).getValue();	
		}
		if(b.matches("\\d+")&&!(a.matches("\\d+"))) {
			expStr = a.replaceAll("x", b);
			return (Boolean)parser.parseExpression(expStr).getValue();	
		}		
		return a.equals(b);

		
	}
	@Override
	public int hashCode(){
		final int prime = 31;
		int hashNumber = 0;
	if(!(F02073_001_CPPORM.equalsIgnoreCase("N/A"))){
		hashNumber= F02073_001_CPPORM.hashCode();
	}
	if(!(F02100_001_CPPOMP.equalsIgnoreCase("N/A"))){
		hashNumber= F02100_001_CPPOMP.hashCode() + prime*hashNumber;
	}
	
	return hashNumber;
	}
	
	public String getF02073_001_CPPORM() {
		return F02073_001_CPPORM;
	}


	public String getF02100_001_CPPOMP() {
		return F02100_001_CPPOMP;
	}

	public String getF01014_001_CurrentBalance() {
		return F01014_001_CurrentBalance;
	}


	public static Map<__S2C_bk, String> getS2cMap() {
		return s2cMap;
	}
	

	
//	@Override
//	public int compareTo(S2C aThat) {
//		if (this == aThat) return 0;
//
//		final S2C that = (S2C)aThat;
//		int balanceCompare=-1;
//		int cppormCompare= -1;
//		int cppompCompare= -1;
//		if(this.getF01014_001_CurrentBalance().equalsIgnoreCase("N/A")||that.getF01014_001_CurrentBalance().equalsIgnoreCase("N/A"))
//		{
//			balanceCompare= 0;
//		}else if(compareBalance(this.getF01014_001_CurrentBalance(),that.getF01014_001_CurrentBalance())){
//			balanceCompare= 0;
//		}else{
//			this.getF01014_001_CurrentBalance().compareTo(that.getF01014_001_CurrentBalance());
//		}
//		
//		if(this.getF02073_001_CPPORM().equalsIgnoreCase("N/A")||that.getF02073_001_CPPORM().equalsIgnoreCase("N/A")){
//			cppormCompare= 0;
//		}else if(this.getF02073_001_CPPORM().equalsIgnoreCase(that.getF02073_001_CPPORM())){
//			cppormCompare= 0;
//		}else{
//			this.getF02073_001_CPPORM().compareTo(that.getF02073_001_CPPORM());
//		}
//		
//		if(this.getF02100_001_CPPOMP().equalsIgnoreCase("N/A")||that.getF02100_001_CPPOMP().equalsIgnoreCase("N/A")){
//			cppompCompare= 0;
//		}else if(this.getF02100_001_CPPOMP().equalsIgnoreCase(that.getF02100_001_CPPOMP())){
//			cppompCompare= 0;
//		}else{
//			this.getF02100_001_CPPOMP().compareTo(that.getF02100_001_CPPOMP());
//		}
//		
//
//		if(cppormCompare==0){
//			if(cppompCompare==0){
//				return balanceCompare;
//			}else 
//			{
//				return cppompCompare;
//			}
//		}else{
//			return cppormCompare;
//		}
//	}

	}
