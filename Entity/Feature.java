package Entity;

public class Feature {

	private String name;
	private  int numberOfInstances;
	private int numberOfDamages;
	private String description;
	private int codiceStanza;
	private int codiceModello;
	private int codiceFeature;

	//Builders
	public Feature(){

	}


	public Feature(String name) {
		this.name = name;
		numberOfInstances = 1;
		numberOfDamages = 0;
	}

	public Feature(String name, int numberOfInstances ) {
		this.name = name;
		this.numberOfInstances = numberOfInstances;
		numberOfDamages = 0;
	}



	//Print methods
	public String view(){
		return ("Number of " + name + " = " + numberOfInstances + " ; (Damaged " +
				name +" = " + getNumberOfDamages() + ")\n" );

	}



	//Getter methods
	public int getNumberOfInstances() {
		return numberOfInstances;
	}

	public String getName(){
		return name;
	}

	public int getCodiceModello(){
		return codiceModello;
	}

	public int getNumberOfDamages(){
		return numberOfDamages;
	}

	public int getCodiceStanza(){
		return codiceStanza;
	}

	public String getDescription(){
		return description;
	}
	public int getCodiceFeature(){
		return codiceFeature;
	}


	//Setter methods
	public void setNumberOfInstances(int numberOfInstances){
		this.numberOfInstances = numberOfInstances;
	}

	public void setName(String name){
		this.name = name;
	}

	public void setCodiceModello(int modelCode){
		codiceModello = modelCode;
	}

	public void setNumberOfDamages(int numberOfDamages){
		this.numberOfDamages = numberOfDamages;
	}

	public void setCodiceStanza(int codiceStanza){
		this.codiceStanza = codiceStanza;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public void setCodiceFeature(int codiceFeature){
		this.codiceFeature = codiceFeature;
	}

	//Status methods
	public void damage(){
		numberOfDamages++;
	}

	public void repair(){
		numberOfDamages--;
	}


	//Instances methods
	public void addInstances(int inc) {
		for (int count = 0; count < inc; count++){
			numberOfInstances ++ ;
		}
	}

	public void removeInstances(int inc) {
		for (int count = 0; count < inc; count++){
			numberOfInstances -- ;
		}
	}



}
