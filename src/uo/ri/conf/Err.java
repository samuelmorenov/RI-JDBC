package uo.ri.conf;

public class Err {
	
	public static void persistence(Exception e){
		//e.printStackTrace();
		throw new RuntimeException("Error de conexión");
	}
	
	public static void transactionScripts(Exception e) {
		//e.printStackTrace();
		throw new RuntimeException("Error de conexión");
	}

}
