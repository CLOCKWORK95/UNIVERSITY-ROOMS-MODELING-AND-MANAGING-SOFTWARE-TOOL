package Bean;

import java.util.ArrayList;
import Entity.Feature;

public class RoomBean {


	private String name;
	private String roomGroup;
	private String nomeEdificio;
	private String macroarea;
	private int codicestanza;
	private String text;
	private ArrayList<Feature> features;



	public RoomBean() {

	}

	//SETTER METHODS
	public void setName(String name){
		this.name = name;
	}

	public void setRoomGroup(String roomGroup){
		this.roomGroup = roomGroup;
	}

	public void setText(String rooms){
		text = rooms;
	}

	public void setNomeEdificio(String nomeEdificio){
		this.nomeEdificio = nomeEdificio;
	}

	public void setMacroarea(String macroarea){
		this.macroarea = macroarea;
	}

	public void setRoomCode(int codicestanza){
		this.codicestanza = codicestanza;
	}

	public void setArrayFeatures(ArrayList<Feature> features) {
		this.features = features;
	}

	public ArrayList<Feature> getArrayFeatures() {
		return features;
	}

	//GETTER METHODS
	public String getName(){
		return name;
	}

	public String getNomeEdificio(){
		return nomeEdificio;
	}

	public String getMacroarea(){
		return macroarea;
	}

	public String getRoomGroup(){
		return roomGroup;
	}

	public String getText(){
		return text;
	}

	public int getRoomCode(){
		return codicestanza;
	}




}
