package uo.ri.conf;

public class Err {
	
	public static void persistence(Exception e){
		//TO-DO: Borrar traza
		//e.printStackTrace();
		throw new RuntimeException("Error de conexión");
	}
	
	public static void transactionScripts(Exception e) {
		//TO-DO: Borrar traza
		//e.printStackTrace();
		throw new RuntimeException("Error de conexión");
	}

}
