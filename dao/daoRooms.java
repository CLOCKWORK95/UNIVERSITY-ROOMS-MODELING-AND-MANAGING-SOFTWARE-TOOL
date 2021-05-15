package dao;

import java.sql.*;
import java.util.ArrayList;

import Bean.OutputBean;
import Control.RoomController;
import Eccezioni.SameNameException;
import Entity.Edificio;
import Entity.Room;
import javafx.collections.ObservableList;

public class daoRooms{

			public void insertRoom(String name, String roomGroup, String nomeEdificio, String macroarea) throws Exception {

				Connection conn = null;
				
				try{
					int newRoomId = getNewRoomId();
					boolean pass = checkForEquals(name,roomGroup,nomeEdificio,macroarea);
					
					if (pass == false) {
						Class.forName("org.postgresql.Driver");
						conn = DriverManager.getConnection("jdbc:postgresql:BQRdb","postgres","totigimmi");
						Statement interrogazione = conn.createStatement();
						interrogazione.executeUpdate("insert into room(nome,roomgroup,nomeedificio,macroarea,codicestanza) "
								+ "values('" + name + "','" + roomGroup + "','" + nomeEdificio  + "','" + macroarea +"',"
								+ newRoomId + ")");
					}
					
					else {
						SameNameException e = new SameNameException();
						throw e;
					}

				}
			    catch (SQLException e) {e.printStackTrace();
			    }
				catch (SameNameException e) {e.printSameNameException();
			    }
		        finally {
		        conn.close();
		        }
            }

			
			
			
			
			public boolean checkForEquals(String name, String roomgroup, String nomeEdificio, String macroarea) throws Exception {
				
				boolean answer = false;
				Connection conn = null;
				
				try{
					
					Class.forName("org.postgresql.Driver");
					conn = DriverManager.getConnection("jdbc:postgresql:BQRdb","postgres","totigimmi");
					Statement interrogazione = conn.createStatement();
					ResultSet res = interrogazione.executeQuery("select * from room");
					while (res.next()) {
						if (res.getString("nome").equals(name) &&
							res.getString("roomgroup").equals(roomgroup) &&
							res.getString("nomeedificio").equals(nomeEdificio) &&
							res.getString("macroarea").equals(macroarea)
								) {answer = true;}
					}
					
				}
			    catch (SQLException e) {e.printStackTrace();
			    }
		        catch (Exception e) {e.printStackTrace();
			    }
		        finally {
		        conn.close();
		        }
				return answer;
			}

			
			
			
			public String selectRoom(int codiceStanza) throws Exception{
				Connection conn = null;
				String stampa = "";
				try{

					Class.forName("org.postgresql.Driver");
					conn = DriverManager.getConnection("jdbc:postgresql:BQRdb","postgres","totigimmi");
					Statement interrogazione = conn.createStatement();
					ResultSet risultato = interrogazione.executeQuery(
							"select * from room \n"
							+ "where codicestanza = " + codiceStanza);
					while (risultato.next()){
						stampa = stampa + risultato.getString("nome") + "  " +  risultato.getString("roomGroup")
						+ "  " +  risultato.getString("nomeedificio") + "  " +  risultato.getString("macroarea") +
						" " + risultato.getInt("codicestanza") + "\n";
					}
				}
			    catch (SQLException e) {e.printStackTrace();
			    }
		        catch (Exception e) {e.printStackTrace();
			    }
		        finally {
		        conn.close();
		        }
				return stampa;

			}



			public int getRoomCode(String name, String roomGroup, String nomeEdificio, String macroarea) throws Exception {
				Connection conn = null;
				int result = 0;
				try{

					Class.forName("org.postgresql.Driver");
					conn = DriverManager.getConnection("jdbc:postgresql:BQRdb","postgres","totigimmi");
					Statement interrogazione = conn.createStatement();
					ResultSet res = interrogazione.executeQuery(
							"select * from room\n"
							+ "where nome = '" + name
							+ "' AND roomgroup = '" + roomGroup
							+ "' AND nomeedificio = '" + nomeEdificio
							+ "' AND macroarea = '" + macroarea + "'");

					if (res.next()){
						result = res.getInt("codicestanza");
					}
				}
			    catch (SQLException e) {e.printStackTrace();
			    }
		        catch (Exception e) {e.printStackTrace();
			    }
		        finally {
		        conn.close();
		        }
				return result;
            }


			public void updateMacroarea(String oldMacroarea,String newMacroarea) throws Exception{
				Connection conn = null;
				Statement interrogazione = null;
				

				try{
					Class.forName("org.postgresql.Driver");
					conn = DriverManager.getConnection("jdbc:postgresql:BQRdb","postgres","totigimmi");

					interrogazione = conn.createStatement();
					interrogazione.executeUpdate("update room\n"
							+ "set macroarea = '" + newMacroarea
							+ "' where macroarea = '" + oldMacroarea + "'");
				}
				
			    catch (SQLException e) {e.printStackTrace();
			    }
		        catch (Exception e) {e.printStackTrace();
			    }
		        finally {
		        	if (interrogazione != null) {
			        	interrogazione.close();
			        }
		        	interrogazione = null;
			        conn.close();
		     
		        }
				
			}


			public void updateBuildingName(String oldName,String newName,String macroarea) throws Exception{
				Connection conn = null;
				Statement interrogazione = null;

				try{
					Class.forName("org.postgresql.Driver");
					conn = DriverManager.getConnection("jdbc:postgresql:BQRdb","postgres","totigimmi");

					interrogazione = conn.createStatement();
					interrogazione.executeUpdate("update room\n"
							+ "set nomeedificio = '" + newName
							+ "' where nomeedificio = '" + oldName
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

			public ArrayList<Room> addRoomsToArray(Edificio building) throws Exception{
				ArrayList<Room> array = new ArrayList<Room>();
				String nomeEdificio = building.getName();
				String macroarea = building.getMacroArea();
				Connection conn = null;
				try{
					RoomController rc = RoomController.getInstance();
					Class.forName("org.postgresql.Driver");
					conn = DriverManager.getConnection("jdbc:postgresql:BQRdb","postgres","totigimmi");
					Statement interrogazione = conn.createStatement();
					ResultSet roomCodes = interrogazione.executeQuery(
							"select codicestanza\n"
							+ "from room\n"
							+ "where nomeedificio = '" + nomeEdificio + "'"
							+ "and macroarea = '" + macroarea + "'");
					while (roomCodes.next()){
						array.add(rc.getRoom(roomCodes.getInt("codicestanza")));
					}

				}
			    catch (SQLException e) {e.printStackTrace();
			    }
		        catch (Exception e) {e.printStackTrace();
			    }
		        finally {
		        conn.close();
		        }
				return array;

			}

			public void deleteAllRooms() throws Exception{

				Connection conn = null;
	            try{
	                Class.forName("org.postgresql.Driver");
	                conn = DriverManager.getConnection("jdbc:postgresql:BQRdb","postgres","totigimmi");
	                Statement interrogazione = conn.createStatement();
	                interrogazione.executeUpdate(
	                		"delete from room");
	                }
	            catch (SQLException e) {e.printStackTrace();
	            }
	            catch (Exception e) {e.printStackTrace();
	            }
	            finally {
	                conn.close();
	            }

			}

			public String getRoomName(int codicestanza) throws Exception {
				Connection conn = null;
				String result = "";
				try{

					Class.forName("org.postgresql.Driver");
					conn = DriverManager.getConnection("jdbc:postgresql:BQRdb","postgres","totigimmi");
					Statement interrogazione = conn.createStatement();
					ResultSet res = interrogazione.executeQuery(
							"select nome \n"
							+ "from room \n"
							+ "where codicestanza = '" + codicestanza + "'");
					while (res.next()){
						result = res.getString("nome");
					}
				}
			    catch (SQLException e) {e.printStackTrace();
			    }
		        catch (Exception e) {e.printStackTrace();
			    }
		        finally {
		        conn.close();
		        }
				return result;
            }


			public String getRoomroomgroup(int codicestanza) throws Exception {
				Connection conn = null;
				String result = "";
				try{

					Class.forName("org.postgresql.Driver");
					conn = DriverManager.getConnection("jdbc:postgresql:BQRdb","postgres","totigimmi");
					Statement interrogazione = conn.createStatement();
					ResultSet res = interrogazione.executeQuery(
							"select roomgroup \n"
							+ "from room \n"
							+ "where codicestanza = " + codicestanza + "");
					while (res.next()){
						result = res.getString("roomgroup");
					}
				}
			    catch (SQLException e) {e.printStackTrace();
			    }
		        catch (Exception e) {e.printStackTrace();
			    }
		        finally {
		        conn.close();
		        }
				return result;
            }




			public String getRoomNomeEdificio(int codicestanza) throws Exception {
				Connection conn = null;
				String result = "";
				try{

					Class.forName("org.postgresql.Driver");
					conn = DriverManager.getConnection("jdbc:postgresql:BQRdb","postgres","totigimmi");
					Statement interrogazione = conn.createStatement();
					ResultSet res = interrogazione.executeQuery(
							"select nomeedificio \n"
							+ "from room \n"
							+ "where codicestanza = '" + codicestanza + "'");
					while (res.next()){
						result = res.getString("nomeedificio");
					}
				}
			    catch (SQLException e) {e.printStackTrace();
			    }
		        catch (Exception e) {e.printStackTrace();
			    }
		        finally {
		        conn.close();
		        }
				return result;
            }


			public String getRoomMacroarea(int codicestanza) throws Exception {
				Connection conn = null;
				String result = "";
				try{

					Class.forName("org.postgresql.Driver");
					conn = DriverManager.getConnection("jdbc:postgresql:BQRdb","postgres","totigimmi");
					Statement interrogazione = conn.createStatement();
					ResultSet res = interrogazione.executeQuery(
							"select macroarea \n"
							+ "from room \n"
							+ "where codicestanza = '" + codicestanza + "'");
					while (res.next()){
						result = res.getString("macroarea");
					}
				}
			    catch (SQLException e) {e.printStackTrace();
			    }
		        catch (Exception e) {e.printStackTrace();
			    }
		        finally {
		        conn.close();
		        }
				return result;
            }



			public void deleteRoom(int codicestanza) throws Exception {

	            Connection conn = null;
	            try{
	                Class.forName("org.postgresql.Driver");
	                conn = DriverManager.getConnection("jdbc:postgresql:BQRdb","postgres","totigimmi");
	                Statement interrogazione = conn.createStatement();
	                interrogazione.executeUpdate(
	                		"delete from room\n "
							+ "where codicestanza = '" + codicestanza + "'" );
	                }
	            catch (SQLException e) {e.printStackTrace();
	            }
	            catch (Exception e) {e.printStackTrace();
	            }
	            finally {
	                conn.close();
	            }
	      }


			
			
			


			public ArrayList<OutputBean> printRooms() throws Exception{

				ArrayList<OutputBean> rooms = new ArrayList<OutputBean>();
				Connection conn = null;
				try{
					Class.forName("org.postgresql.Driver");
					conn = DriverManager.getConnection("jdbc:postgresql:BQRdb","postgres","totigimmi");
					Statement interrogazione = conn.createStatement();
					ResultSet risultato = interrogazione.executeQuery("select * from room");
					while (risultato.next()) {
						OutputBean ob = new OutputBean(
								risultato.getString("nome"),
								risultato.getString("roomgroup"),
								risultato.getString("nomeedificio"),
								risultato.getString("macroarea"),
								risultato.getInt("codicestanza"));
						rooms.add(ob);
					}
				}
			    catch (SQLException e) {e.printStackTrace();
			    }
		        catch (Exception e) {e.printStackTrace();
			    }
		        finally {
		        conn.close();
		        }
				return rooms;

		    }
			
			



			public void printRoomsOnTableView(ObservableList<OutputBean> data,ObservableList<ObservableList<OutputBean>> data2) throws Exception{
				Connection conn = null;
				try{
					Class.forName("org.postgresql.Driver");
					conn = DriverManager.getConnection("jdbc:postgresql:BQRdb","postgres","totigimmi");
					Statement interrogazione = conn.createStatement();
					ResultSet risultato = interrogazione.executeQuery("select * from room");

					while (risultato.next()) {
						data.add(new OutputBean(
								risultato.getString("nome"),
								risultato.getString("roomgroup"),
								risultato.getString("nomeedificio"),
								risultato.getString("macroarea"),
								risultato.getInt("codicestanza")
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




			public int getNewRoomId() throws Exception {

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





}