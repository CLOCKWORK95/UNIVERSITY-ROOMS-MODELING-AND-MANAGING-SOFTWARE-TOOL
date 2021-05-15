package Entity;

import java.util.ArrayList;
import Entity.Observer;

public class Classroom extends Room implements Observer {

	public Classroom(String name) {
        this.name = name;
        features = new ArrayList<Feature>();
        roomGroup = "Classroom";
    }
	
	

    public Classroom(String name, Edificio edificio) {
        this.name = name;
        this.building = edificio;
        features = new ArrayList<Feature>();
        roomGroup = "Classroom";
    }

    @Override
    public String getRoomGroup(){
    	return roomGroup;
    }


	


}
