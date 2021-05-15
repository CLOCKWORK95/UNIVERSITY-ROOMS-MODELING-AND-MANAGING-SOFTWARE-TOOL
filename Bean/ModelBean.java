package Bean;

import Entity.Feature;
import java.util.ArrayList;



public class ModelBean {

    private String nomeModello;
    private String roomGroup;
    private String newName;
    private String newRoomGroup;
    private String text;
    private int codiceModello;
    private ArrayList<Feature> features;


    public ModelBean(){
    }


    public void setNomeModello(String nomeModello){
        this.nomeModello = nomeModello;
    }

    public void setCodiceModello(int modelCode){
    	codiceModello = modelCode;
    }

    public void setRoomGroup(String roomGroup){
        this.roomGroup = roomGroup;
    }

    public void setNewName(String newName) {
    	this.newName = newName;
    }

    public void setNewRoomGroup(String newRoomGroup) {
    	this.newRoomGroup = newRoomGroup;
    }

    public String getName(){
        return nomeModello;
    }

    public String getRoomGroup(){
        return roomGroup;
    }

    public String getText(){
        return text;
    }

	public String getNewName() {
		return newName;
	}

	public String getNewRoomGroup(){
		return newRoomGroup;
	}

	public int getCodiceModello(){
		return codiceModello;
	}

	public void setText(String rooms){
		text = rooms;
	}

    public ArrayList<Feature> getFeatures(){
        return features;
    }



}