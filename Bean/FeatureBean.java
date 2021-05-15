package Bean;

public class FeatureBean {
	
	private String name;
	private  int numberOfInstances;
	private int numberOfDamages;
	private String description;
	private int codiceStanza;
	private int codiceFeature;
	private int codiceModello;
	private String text;

	public FeatureBean() {

	}

	//Setters

	public void setName(String name){
		this.name = name;
	}

	public void setCodiceModello(int modelCode){
		codiceModello = modelCode;
	}

	public void setText(String text){
		this.text = text;
	}

	public void setNumberOfInstances(int numberOfInstances){
		this.numberOfInstances = numberOfInstances;
	}


	public void setNumberOfDamages(int numberOfDamages){
		this.numberOfDamages = numberOfDamages;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public void setCodiceStanza(int codiceStanza){
		this.codiceStanza = codiceStanza;
	}

	public void setCodiceFeature(int codiceFeature){
		this.codiceFeature = codiceFeature;
	}



	//Getters

	public String getName(){
		return name;
	}

	public String getText(){
		return text;
	}

	public int getNumberOfInstances(){
		return numberOfInstances;
	}


	public int getCodiceModello(){
		return codiceModello;
	}

	public int getNumberOfDamages(){
		return numberOfDamages;
	}

	public String getDescription(){
		return description;
	}

	public int getCodiceStanza(){
		return codiceStanza;
	}

	public int getCodiceFeature(){
		return codiceFeature;
	}





}
