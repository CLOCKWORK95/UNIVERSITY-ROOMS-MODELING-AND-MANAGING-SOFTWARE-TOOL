package dao;

import java.sql.*;
import java.util.ArrayList;

import Bean.OutputBean_F;
import Control.ModelController;
import javafx.collections.ObservableList;

public class daoModels{



    public void insertModel(String name, String roomGroup) throws Exception {


        Connection conn = null;
        try{
        	int newModelId = getNewId();
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql:BQRdb","postgres","totigimmi");
            Statement interrogazione = conn.createStatement();
            interrogazione.executeUpdate("insert into models(nomemodello, roomgroup, codicemodello)\n "
                    + "values('" + name + "','" + roomGroup + "', " + newModelId + ")");
           }
        catch (SQLException e) {e.printStackTrace();
        }
        catch (Exception e) {e.printStackTrace();
        }
        finally {
            conn.close();
        }
    }

    public String getModelName(int modelCode) throws Exception{
    	Connection conn = null;
    	String name = "";
        try{
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql:BQRdb","postgres","totigimmi");
            Statement interrogazione = conn.createStatement();
            ResultSet res = interrogazione.executeQuery("select nomemodello\n"
            		+ "from models\n"
            		+ "where codicemodello = " + modelCode );
            while(res.next()){
            	name = res.getString("nomemodello");
            }
           }
        catch (SQLException e) {e.printStackTrace();
        }
        catch (Exception e) {e.printStackTrace();
        }
        finally {
            conn.close();
        }
        return name;
    }

    public String getModelRoomGroup(int modelCode) throws Exception{
    	Connection conn = null;
    	String roomGroup = "";
        try{
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql:BQRdb","postgres","totigimmi");
            Statement interrogazione = conn.createStatement();
            ResultSet res = interrogazione.executeQuery("select roomgroup\n"
            		+ "from models\n"
            		+ "where codicemodello = " + modelCode );
            while(res.next()){
            	roomGroup = res.getString("roomgroup");
            }
           }
        catch (SQLException e) {e.printStackTrace();
        }
        catch (Exception e) {e.printStackTrace();
        }
        finally {
            conn.close();
        }
        return roomGroup;
    }


    public int getModelCode(String nomeModello,String roomGroup) throws Exception{
    	Connection conn = null;
    	int roomCode = 0;
        try{
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql:BQRdb","postgres","totigimmi");
            Statement interrogazione = conn.createStatement();
            ResultSet res = interrogazione.executeQuery("select codicemodello\n"
            		+ "from models\n"
            		+ "where nomemodello = '" + nomeModello + "' and roomgroup = '" + roomGroup + "'" );
            while(res.next()){
            	roomCode = res.getInt("codicemodello");
            }
           }
        catch (SQLException e) {e.printStackTrace();
        }
        catch (Exception e) {e.printStackTrace();
        }
        finally {
            conn.close();
        }
        return roomCode;
    }




    public int getNewId() throws Exception {

		int CodiceStanza = 0;
		Connection conn = null;

		try{
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql:BQRdb","postgres","totigimmi");

			Statement interrogazione = conn.createStatement();
			ResultSet OldCodiceStanza = interrogazione.executeQuery("select * from codicestanzagenerator");
			while (OldCodiceStanza.next()){
				CodiceStanza = OldCodiceStanza.getInt("identita") + 1;
			}
			interrogazione.executeUpdate("update codicestanzagenerator\n"
					+ "set identita = " + CodiceStanza);
		}
	    catch (SQLException e) {e.printStackTrace();
	    }
        catch (Exception e) {e.printStackTrace();
	    }
        finally {
        conn.close();

        }
		return CodiceStanza;

	}




    public void deleteModel(int modelCode) throws Exception {
            Connection conn = null;
            try{
            	ModelController mc = ModelController.getInstance();
            	mc.deleteAllFeaturesOfAModel(modelCode);
                Class.forName("org.postgresql.Driver");
                conn = DriverManager.getConnection("jdbc:postgresql:BQRdb","postgres","totigimmi");
                Statement interrogazione = conn.createStatement();
                interrogazione.executeUpdate("delete from models\n "
						+ "where codicemodello = " + modelCode + "");
                }
            catch (SQLException e) {e.printStackTrace();
            }
            catch (Exception e) {e.printStackTrace();
            }
            finally {
                conn.close();
            }
      }





    public void updateNameModello(String oldName,String roomGroup, String newName) throws Exception {

        Connection conn = null;
        try{
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql:BQRdb","postgres","totigimmi");
            Statement interrogazione = conn.createStatement();
            interrogazione.executeUpdate(
            		"UPDATE models\n "
                    + "SET nomemodello = '" + newName + "'\n"
            		+ "WHERE nomemodello = '" + oldName +
                    "' and roomgroup = '" + roomGroup + "' ");
        }

        catch (SQLException e) {e.printStackTrace();
        }
        catch (Exception e) {e.printStackTrace();
        }
        finally {
            conn.close();
        }
    }



    public void updateRoomGroupModello(String name, String oldRoomGroup, String newRoomGroup ) throws Exception {

        Connection conn = null;
        try{
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql:BQRdb","postgres","totigimmi");
            Statement interrogazione = conn.createStatement();
            interrogazione.executeUpdate("UPDATE modello\n "
                    + "SET roomGroup = '" + newRoomGroup + "'\n"
            		+ "WHERE name = '" + name +
                    "' and roomGroup = '" + oldRoomGroup + "' ");
        }

        catch (SQLException e) {e.printStackTrace();
        }
        catch (Exception e) {e.printStackTrace();
        }
        finally {
            conn.close();
        }
    }
    
    
    
    
    
    
    public void printModelsOnTableView(ObservableList<OutputBean_F> data,ObservableList<ObservableList<OutputBean_F>> data2) throws Exception{
    	Connection conn = null;
		try{
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql:BQRdb","postgres","totigimmi");
			Statement interrogazione = conn.createStatement();
			ResultSet risultato = interrogazione.executeQuery("select * from models");

			while (risultato.next()) {
				data.add(new OutputBean_F(
						risultato.getString("nomemodello"),
						risultato.getString("roomgroup"),
						risultato.getInt("codicemodello")
						));
				data2.add(data);
			}
		}
	    catch (SQLException e) {e.printStackTrace();
	    }
        catch (Exception e) {e.printStackTrace();
	    }
        finally {
        conn.close();
        }
    }
    
    public ArrayList<OutputBean_F> printModels() throws Exception{
    	ArrayList<OutputBean_F> models = new ArrayList<OutputBean_F>();
    	Connection conn = null;
		try{
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql:BQRdb","postgres","totigimmi");
			Statement interrogazione = conn.createStatement();
			ResultSet risultato = interrogazione.executeQuery("select * from models");

			while (risultato.next()) {
				models.add(new OutputBean_F(
						risultato.getString("nomemodello"),
						risultato.getString("roomgroup"),
						risultato.getInt("codicemodello")
						));
			}
		}
	    catch (SQLException e) {e.printStackTrace();
	    }
        catch (Exception e) {e.printStackTrace();
	    }
        finally {
        conn.close();
        }
		return models;
    }

    
    
    

}

