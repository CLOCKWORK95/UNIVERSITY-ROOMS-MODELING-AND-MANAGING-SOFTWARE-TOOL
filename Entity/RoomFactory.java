package Entity;

public class RoomFactory {

    public Room createRoom(int type) throws Exception{


        switch (type)
        {
            case 1: return createRoomClassroom();
            case 2: return createRoomLab();
            case 3: return createRoomConferenceRoom();
            default: throw new Exception("Invalid type : " + type);
        }
    }

    public Room createRoomClassroom(){
    	return new Classroom("");
    }

    public Room createRoomLab(){
    	return new Lab("");
    }

    public Room createRoomConferenceRoom(){
    	return new ConferenceRoom("");
    }



}