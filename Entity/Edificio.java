package Entity;

import java.util.ArrayList;
import Entity.Subject;

public class Edificio implements Subject {

   

	private String name;
    private String macroarea;
    private ArrayList<Room> roomList;

    public Edificio(String nome, String macro) {
        name = nome;
        macroarea = macro;
        roomList = new ArrayList<Room>(30);
    }

    //set methods
    public void setName(String name) {
        this.name = name;
    }
    public void setMacroarea(String macroarea) {
        this.macroarea = macroarea;
    }

    //get methods
    public String getName() {
        return name;
    }
    public String getMacroArea() {
        return macroarea;
    }

    public ArrayList<Room> getRoomList(){
    	return roomList;
    }


    @Override
    public void addObserver(Observer room){
    	Room stanza = (Room) room;
    	
    	stanza.setBuilding(this);
    	
    	roomList.add(stanza);
    }


    @Override
    public void removeObserver(Observer room) {
    	Room stanza = (Room) room;
    	stanza.setNomeEdificio(this.name);
    	stanza.setMacroarea(this.macroarea);
        roomList.remove(stanza);
    }

    @Override
    public void notif(){

    	for (Room room : roomList) {
    		room.Update();
    	}
    }


}

