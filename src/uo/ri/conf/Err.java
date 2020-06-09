package uo.ri.conf;

public class Err {
	
	public static void persistence(Exception e){
		//TODO: Borrar traza
		e.printStackTrace();
		throw new RuntimeException("Error de conexión");
	}
	
	public static void transactionScripts(Exception e) {
		//TODO: Borrar traza
		e.printStackTrace();
		throw new RuntimeException("Error de conexión");
	}

}
