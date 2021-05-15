package Entity;

import java.util.ArrayList;

public class ConferenceRoom extends Room implements Observer {

	public ConferenceRoom(String name) {
        this.name = name;
        features = new ArrayList<Feature>();
        roomGroup = "ConferenceRoom";
    }

    public ConferenceRoom(String name, Edificio edificio) {
        this.name = name;
        this.building = edificio;
        features = new ArrayList<Feature>();
        roomGroup = "ConferenceRoom";
    }

    @Override
    public String getRoomGroup(){
    	return roomGroup;
    }

   

    
}
