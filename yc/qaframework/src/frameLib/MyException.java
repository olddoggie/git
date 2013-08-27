package frameLib;

@SuppressWarnings("serial")
public class MyException extends RuntimeException  {

	public static void main(String[] args) {
		
		try {
			throw new MyException("aaaa");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("bbb");

	}
	 public MyException() {
		    super();
		  }


		  public MyException(String message) {
		    super(message);
		  }


		  public MyException(Throwable cause) {
		    super(cause);
		  }


		  public MyException(String message, Throwable cause) {
		    super(message, cause);
		  }
		  
		  @Override
		  public String getMessage() {
		    return super.getMessage();
		  }

}
