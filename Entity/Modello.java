package Entity;
import java.util.ArrayList;

public class Modello {


    private String name;
    private String roomGroup;
    private ArrayList<Feature> features;
    private int codiceModello;


    public Modello(String name, String roomGroup,int modelCode){
        this.name = name;
        this.roomGroup = roomGroup;
        codiceModello = modelCode;
        features = new ArrayList<Feature>();
    }


    //Getter methods
    public String getName() {
        return this.name;
    }

    public String getRoomGroup(){
     return this.roomGroup;
    }

    public int getCodiceModello(){
    	return codiceModello;
    }

    public ArrayList<Feature> getArrayFeatures(){
    	return features;
    }

    //Setter methods
    public void setName(String nuovoNome) {
        this.name = nuovoNome;
    }

    public void setCodiceModello(int modelCode){
    	codiceModello = modelCode;
    }

    public void setroomGroup(String nuovoRoomGroup) {
        this.roomGroup = nuovoRoomGroup;
    }




    public String view(){

    	String stampa ="Room Group: " + roomGroup +
    			"\nName: " + name +
    			"\nFeatures of the Model:\n" ;
    	for (int count = 0; count < features.size(); count ++){
    		Feature f = features.get(count);
    		stampa = stampa + (f.view());
    	}

    	return stampa;
    }


    //Edit Model

    public void addFeatures(Feature feature){
    	String name = feature.getName();
    	String description = feature.getDescription();
    	features.add(feature);

    	for (int count = 0; count < features.size()-1 ; count ++){
    		Feature comparingFeature = features.get(count);
    		String comparingString = comparingFeature.getName();
    		String comparingDescription = comparingFeature.getDescription();
    		if ( comparingString == name && comparingDescription == description){
    			feature.addInstances(comparingFeature.getNumberOfInstances());
    			feature.setNumberOfDamages(comparingFeature.getNumberOfDamages());
    			features.remove(count);
    			break;
    		}
    	}
    }




    public void removeFeatures(Feature feature){
    	String name = feature.getName();
    	int numberOfInstances = feature.getNumberOfInstances();
    	for (int count = 0; count < features.size(); count ++){
    		Feature candidate = features.get(count);
    		if (candidate.getName() == name){
    			candidate.removeInstances(numberOfInstances);
    			if (candidate.getNumberOfInstances() <= 0){
    				features.remove(count);
    			}
    			break;
    		}
    	}
    }




}


