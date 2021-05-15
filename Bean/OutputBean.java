package Bean;


public class OutputBean {

	private String column1;
	private String column2;
	private String column3;
	private String column4;
	private int column5;



	public OutputBean(String a,String b,String c,String d,int e) {
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

	public String getColumn3(){
		return column3;
	}

	public String getColumn4(){
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

	public void setColumn3(String c){
		column3 = c;
	}

	public void setColumn4(String d){
		column4 = d;
	}

	public void setColumn5(int e){
		column5 = e;
	}

}
