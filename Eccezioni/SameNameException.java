package Eccezioni;

public class SameNameException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public void printSameNameException() {
		
		System.out.println("Hai tentato di inserire una stanza già esistente! Puoi usare lo stesso nome,\n "
				+ "ma la Stanza deve appartenere a un differente Edificio!");
	}

}
