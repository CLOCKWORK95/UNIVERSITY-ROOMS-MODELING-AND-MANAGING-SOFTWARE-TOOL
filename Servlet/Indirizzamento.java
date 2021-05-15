package Servlet;


public class Indirizzamento {

	private String message;
	//attributo chiave per l'applicazione del pattern Singleton
	private static Indirizzamento instance = null;


	private Indirizzamento() {

	}

	//SETTER METHODS
	public void setMessage(String message){
		this.message = message;
	}


	public String getMessage() {
		return this.message;
	}

	
	
	public static final Indirizzamento getSingletonInstance()  {

		if (Indirizzamento.instance == null)
			Indirizzamento.instance = new Indirizzamento();
		return instance;
	}



}
