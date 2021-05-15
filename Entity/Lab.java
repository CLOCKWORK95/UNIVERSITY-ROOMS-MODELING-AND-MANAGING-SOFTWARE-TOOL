package Entity;

import java.util.ArrayList;
import Entity.Observer;

public class Lab extends Room implements Observer{

	public Lab(String name) {
        this.name = name;
        features = new ArrayList<Feature>();
        roomGroup = "Laboratory";
    }

    public Lab(String name, Edificio edificio) {
        this.name = name;
        this.building = edificio;
        features = new ArrayList<Feature>();
        roomGroup = "Laboratory";
    }

    @Override
    public String getRoomGroup(){
    	return roomGroup;
    }

	

	

}
