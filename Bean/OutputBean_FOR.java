package Bean;


public class OutputBean_FOR {

	private String column1;
	private String column2;
	private int column3;
	private int column4;
	private int column5;



	public OutputBean_FOR(String a,String b,int c,int d,int e) {
		column1 = a;
		column2 = b;
		column3 = c;
		column4 = d;
		column5 = e;
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

	public int getColumn4(){
		return column4;
	}

	public int getColumn5(){
		return column5;
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

	public void setColumn4(int d){
		column4 = d;
	}

	public void setColumn5(int e){
		column5 = e;
	}

}