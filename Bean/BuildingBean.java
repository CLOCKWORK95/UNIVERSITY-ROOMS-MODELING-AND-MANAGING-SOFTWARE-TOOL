package Bean;

public class BuildingBean {


	private String nomeEdificio;
	private String macroarea;
	private String text;
	private String newNomeEdificio;
	private String newMacroarea;


	public BuildingBean() {
	}


	//SETTER METHODS
	public void setNomeEdificio(String name){
		nomeEdificio = name;
	}

	public void setNewNomeEdificio(String newName){
		newNomeEdificio = newName;
	}

	public void setNewMacroarea(String newmacroarea){
		newMacroarea = newmacroarea;
	}

	public void setMacroarea(String macro){
		macroarea = macro;
	}

	public void setText(String rooms){
		text = rooms;
	}

	//GETTER METHODS


	public String getNomeEdificio(){
		return nomeEdificio;
	}

	public String getMacroarea(){
		return macroarea;
	}

	public String getText(){
		return text;
	}

	public String getNewNomeEdificio(){
		return newNomeEdificio;
	}

	public String getNewMacroarea(){
		return newMacroarea;
	}





}