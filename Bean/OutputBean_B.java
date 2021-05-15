package Bean;


public class OutputBean_B {

	private String column1;
	private String column2;
	private int column3;



	public OutputBean_B(String a,String b, int c) {
		column1 = a;
		column2 = b;
		column3 = c;
	}


	//Getters

	public String getColumn1(){
		return column1;
	}

	public String getColumn2(){
		return column2;
	}
	
	public int getColumn3(){
		return column3;
	}


	//Setters

	public void setColumn1(String a){
		column1 = a;
	}

	public void setColumn2(String b){
		column2 = b;
	}
	
	public void setColumn3(int c){
		column3 = c;
	}

}