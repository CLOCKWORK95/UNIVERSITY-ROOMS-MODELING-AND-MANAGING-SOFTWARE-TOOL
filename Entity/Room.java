package Entity;

import java.util.ArrayList;
import dao.daoRooms;
import Entity.Observer;

public abstract class Room implements Observer{

	protected String roomGroup;
    protected String name;
    protected String nomeEdificio ;
    protected String macroarea ;
    
    protected Edificio building;
    
    protected ArrayList<Feature> features;
    protected int codiceStanza;


    //Builders
    public Room(){

    }

    public Room(String name){
        this.name = name;
        features = new ArrayList<Feature>(30);
    }


    public abstract String getRoomGroup();



    //Getter methods
    public String getName() {
        return this.name;
    }


    public int getCodiceStanza(){
    	return codiceStanza;
    }

    public ArrayList<Feature> getFeatures(){
    	return features;
    }
    
    public String getMacroarea() {
    	return macroarea;
    }
    
    public String getNomeEdificio() {
    	return nomeEdificio;
    }
    
    public Edificio getBuilding() {
    	return building;
    }

    

    //Setter methods
    public void setName(String nuovoNome) {
        this.name = nuovoNome;
    }

    public void setCodiceStanza(int codiceStanza){
    	this.codiceStanza = codiceStanza;
    }


    public void setArrayFeatures(ArrayList<Feature> features){
    	this.features = features;
    }
    
    public void setNomeEdificio(String nomeedificio) {
    	this.nomeEdificio = nomeedificio;
    }
    
    public void setMacroarea(String macroarea) {
    	this.macroarea = macroarea;
    }
    
    public void setBuilding(Edificio building) {
    	this.building = building;
    	setNomeEdificio(building.getName());
    	setMacroarea(building.getMacroArea());
    }

    //Features Methods

    public void addFeatures(Feature feature){
    	String name = feature.getName();
    	String description = feature.getDescription();
    	
    	if (feature.getNumberOfInstances() != 0) {
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

    
    }



    public void removeFeatures(Feature feature){
    	String name = feature.getName();
    	String description = feature.getDescription();
    	int numberOfInstances = feature.getNumberOfInstances();
    	
    	if (feature.getNumberOfInstances() != 0) {
    		for (int count = 0; count < features.size(); count ++){
        		Feature candidate = features.get(count);
        		if (candidate.getName() == name && candidate.getDescription() == description){
        			candidate.removeInstances(numberOfInstances);
        			if (candidate.getNumberOfInstances() <= 0){
        				features.remove(count);
        			}
        			break;
        		}
        	}
    	}
    }


    public void updateDamages(String name, int n){
    	for (int count = 0; count < features.size(); count ++){
    		Feature f = features.get(count);
    		if (f.getName() == name){
    			for (int i = 0; i < n; i ++){
    				f.damage();
    			}
    			break;
    		}
    	}
    }


    public void repairDamages(String name, int n){
    	for (int count = 0; count < features.size(); count ++){
    		Feature f = features.get(count);
    		if (f.getName() == name){
    			for (int i = 0; i < n; i ++){
    				f.repair();
    			}
    			break;
    		}
    	}
    }
    
    
    @Override
    public void Update() {
    	daoRooms dr = new daoRooms();
    	String newNomeEdificio = building.getName();
    	String newMacroarea = building.getMacroArea();
    	try {
    		dr.updateBuildingName(nomeEdificio, newNomeEdificio, newMacroarea);
			dr.updateMacroarea(macroarea, newMacroarea);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	setNomeEdificio(newNomeEdificio);
    	setMacroarea(newMacroarea);
    	
    }




}
