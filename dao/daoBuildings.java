package dao;

import java.sql.*;
import java.util.ArrayList;

import Bean.OutputBean_B;
import javafx.collections.ObservableList;

public class daoBuildings {

	public void insertBuilding(String nome, String macroarea) throws Exception {

		Connection conn = null;
		try{
			int newBuildingId = getNewBuildingId();
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql:BQRdb","postgres","totigimmi");
			Statement interrogazione = conn.createStatement();
			interrogazione.executeUpdate("insert into building(nomeedificio,macroarea,codiceedificio) "
					+ "values('" + nome + "','" + macroarea + "'," + newBuildingId + ")");
		}
	    catch (SQLException e) {e.printStackTrace();
	    }
        catch (Exception e) {e.printStackTrace();
	    }
        finally {
        conn.close();
        }
    }
	
	public int getNewBuildingId() throws Exception {

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
	
	
	public String getBuildingName(int codiceedificio) throws Exception{
		Connection conn = null;
		String nome = "";
		try{
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql:BQRdb","postgres","totigimmi");
			Statement interrogazione = conn.createStatement();
			ResultSet risultato = interrogazione.executeQuery("select * from building\n"
					+ "where codiceedificio =" + codiceedificio);

			while (risultato.next()) {
				nome = risultato.getString("nomeedificio");
			}
		}
	    catch (SQLException e) {e.printStackTrace();
	    }
        catch (Exception e) {e.printStackTrace();
	    }
        finally {
        conn.close();
        }
		return nome;
		
	}
	
	
	public String getBuildingMacroarea(int codiceedificio) throws Exception{
		Connection conn = null;
		String macroarea = "";
		try{
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql:BQRdb","postgres","totigimmi");
			Statement interrogazione = conn.createStatement();
			ResultSet risultato = interrogazione.executeQuery("select * from building\n"
					+ "where codiceedificio =" + codiceedificio);

			while (risultato.next()) {
				macroarea = risultato.getString("macroarea");
			}
		}
	    catch (SQLException e) {e.printStackTrace();
	    }
        catch (Exception e) {e.printStackTrace();
	    }
        finally {
        conn.close();
        }
		return macroarea;
		
	}
	
	
	

	public boolean isThere(String nome, String macroarea) throws Exception{
		Connection conn = null;
		boolean x = false;
		try{
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql:BQRdb","postgres","totigimmi");
			Statement interrogazione = conn.createStatement();
			ResultSet result = interrogazione.executeQuery("select * from building");
			while (result.next()){
				if (result.getString("nomeedificio").equals(nome) && result.getString("macroarea").equals(macroarea)){
					x = true;
					break;
				}

			}
		}
	    catch (SQLException e) {e.printStackTrace();
	    }
        catch (Exception e) {e.printStackTrace();
	    }
        finally {
        conn.close();
        }
		return x;
	}

	

	public void deleteEdificio(String nomeEdificio, String macroarea) throws Exception {

        Connection conn = null;

        try{
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql:BQRdb","postgres","totigimmi");
            Statement interrogazione = conn.createStatement();
            interrogazione.executeUpdate("DELETE FROM building\n "
                    + "WHERE nomeedificio = '" + nomeEdificio + "' and macroarea = '" + macroarea + "' ");
        }

        catch (SQLException e) {e.printStackTrace();
        }
        catch (Exception e) {e.printStackTrace();
        }
        finally {
            conn.close();
        }
    }

    public void updateNomeEdificio(String oldName,String macroarea, String newName ) throws Exception {

        Connection conn = null;
        Statement interrogazione = null;
        try{
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql:BQRdb","postgres","totigimmi");
            interrogazione = conn.createStatement();
            interrogazione.executeUpdate(
            		"update building\n "
                    + "set nomeedificio = '" + newName + "'\n"
            		+ "where nomeedificio = '" + oldName
            		+ "' and macroarea = '" + macroarea + "'");
        }

        catch (SQLException e) {e.printStackTrace();
        }
        
        catch (Exception e) {e.printStackTrace();
        }
        
        finally {
        	if(interrogazione != null) {
        		interrogazione.close();
        	}
        	interrogazione = null;
        	conn.close();
        }
    }

    public void updateMacroareaEdificio(String name, String oldMacroarea, String newMacroarea ) throws Exception {


        Connection conn = null;
        try{
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql:BQRdb","postgres","totigimmi");
            Statement interrogazione = conn.createStatement();
            interrogazione.executeUpdate(
            		  "update building\n "
                    + "set macroarea = '" + newMacroarea + "'\n"
            		+ "where nomeedificio = '" + name
            		+ "' and macroarea = '" + oldMacroarea + "'");
        }

        catch (SQLException e) {e.printStackTrace();
        }
        catch (Exception e) {e.printStackTrace();
        }
        finally {
            conn.close();
        }
    }



    public void printBuildingsOnTableView(ObservableList<OutputBean_B> data, ObservableList<ObservableList<OutputBean_B>> data2) throws Exception{
		Connection conn = null;
		try{
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql:BQRdb","postgres","totigimmi");
			Statement interrogazione = conn.createStatement();
			ResultSet risultato = interrogazione.executeQuery("select * from building");

			while (risultato.next()) {
				data.add(new OutputBean_B(
						risultato.getString("nomeedificio"),
						risultato.getString("macroarea"),
						risultato.getInt("codiceedificio")
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

    public ArrayList<OutputBean_B> printBuildings_B() throws Exception{
    	ArrayList<OutputBean_B> buildings = new ArrayList<OutputBean_B>();
		Connection conn = null;
		try{
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql:BQRdb","postgres","totigimmi");
			Statement interrogazione = conn.createStatement();
			ResultSet risultato = interrogazione.executeQuery("select * from building");

			while (risultato.next()) {
				buildings.add(new OutputBean_B(
						risultato.getString("nomeedificio"),
						risultato.getString("macroarea"),
						risultato.getInt("codiceedificio")
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
		return buildings;

    }
    

    
    
}
