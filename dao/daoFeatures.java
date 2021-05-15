package dao;

import java.sql.*;
import java.util.ArrayList;

import Bean.OutputBean_F;
import Bean.OutputBean_FOM;
import Bean.OutputBean_FOR;
import Control.FeatureController;
import Entity.Feature;
import Entity.Modello;
import Entity.Room;
import javafx.collections.ObservableList;

public class daoFeatures{



			public void insertNewFeature(String name, String description) throws Exception {

				Connection conn = null;
				try{
					if (checkForInsertion(name,description) == false){
						int newId = getNewId();

						Class.forName("org.postgresql.Driver");
						conn = DriverManager.getConnection("jdbc:postgresql:BQRdb","postgres","totigimmi");
						Statement interrogazione = conn.createStatement();
						interrogazione.executeUpdate("insert into feature(nomefeature,description,codicefeature) "
								+ "values('" + name + "','" + description + "'," + newId + ")");
					}
				}
				catch (SQLException e) {e.printStackTrace();
			    }
		        catch (Exception e) {e.printStackTrace();
			    }
				
		        finally {
		        	if (conn!= null) {
		        		conn.close();
		        }
		        
		        }
				
            }


			public void deleteAllFeaturesOfARoom(int roomCode) throws Exception{
				Connection conn = null;
				try{
					Class.forName("org.postgresql.Driver");
					conn = DriverManager.getConnection("jdbc:postgresql:BQRdb","postgres","totigimmi");
					Statement interrogazione = conn.createStatement();
					interrogazione.executeUpdate(
							  "delete from featureofroom\n"
							  + "where codicestanza = " + roomCode );
					}

			    catch (SQLException e) {e.printStackTrace();
			    }
		        catch (Exception e) {e.printStackTrace();
			    }
		        finally {
		        conn.close();
		        }

			}


			public void updateNumberOfDamages(Feature feature) throws Exception{
				Connection conn = null;
		        try{
		            Class.forName("org.postgresql.Driver");
		            conn = DriverManager.getConnection("jdbc:postgresql:BQRdb","postgres","totigimmi");
		            Statement interrogazione = conn.createStatement();
		            interrogazione.executeUpdate(
		            		"update featureofroom\n "
		                    + "set numberofdamages = '" + feature.getNumberOfDamages() + "'\n"
		            		+ "where codicefeature = '" + feature.getCodiceFeature()+ "'");
		        }

		        catch (SQLException e) {e.printStackTrace();
		        }
		        catch (Exception e) {e.printStackTrace();
		        }
		        finally {
		            conn.close();
		        }
			}

			public void deleteAllFeaturesOfAModel(int modelCode) throws Exception{
				Connection conn = null;
				try{
					Class.forName("org.postgresql.Driver");
					conn = DriverManager.getConnection("jdbc:postgresql:BQRdb","postgres","totigimmi");
					Statement interrogazione = conn.createStatement();
					interrogazione.executeUpdate(
							  "delete from featureofmodel\n"
							  + "where codicemodello = " + modelCode );
					}

			    catch (SQLException e) {e.printStackTrace();
			    }
		        catch (Exception e) {e.printStackTrace();
			    }
		        finally {
		        conn.close();
		        }

			}



			public boolean checkForInsertion(String name,String description) throws Exception{
				boolean x = false;
				Connection conn = null;
				try{
					Class.forName("org.postgresql.Driver");
					conn = DriverManager.getConnection("jdbc:postgresql:BQRdb","postgres","totigimmi");
					Statement interrogazione = conn.createStatement();
					ResultSet res = interrogazione.executeQuery(
							  "select * from feature");
					while (res.next()){
						if (res.getString("nomefeature").equals(name)
								&& res.getString("description").equals(description)){
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




			public int getFeatureCode(String nomeFeature,String description) throws Exception{

				Connection conn = null;
				int featureCode = 0;

				try{

					Class.forName("org.postgresql.Driver");
					conn = DriverManager.getConnection("jdbc:postgresql:BQRdb","postgres","totigimmi");
					Statement interrogazione = conn.createStatement();
					ResultSet result = interrogazione.executeQuery(
							  "select codicefeature\n"
							+ "from feature "
							+ "where nomefeature = '" + nomeFeature
							+ "' and description = '" + description + "'"  );
					while (result.next()){
						featureCode = result.getInt("codicefeature");
					}

				}
			    catch (SQLException e) {e.printStackTrace();
			    }
		        catch (Exception e) {e.printStackTrace();
			    }
		        finally {
		        conn.close();
		        }
				return featureCode;

			}




			public void addFeaturesToRoomArray(Room room) throws Exception{
				int roomCode = room.getCodiceStanza();
				Connection conn = null;
				try{
					FeatureController fc = FeatureController.getInstance();
					Class.forName("org.postgresql.Driver");
					conn = DriverManager.getConnection("jdbc:postgresql:BQRdb","postgres","totigimmi");
					Statement interrogazione = conn.createStatement();
					ResultSet featureCodes = interrogazione.executeQuery(
							"select * from featureofroom\n"
							+ "where codicestanza = "+ roomCode );
					while (featureCodes.next()){
						room.addFeatures(fc.getFeatureOfRoom(featureCodes.getInt("codicefeature"),roomCode));
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



			public void addFeaturesToModelArray(Modello model) throws Exception{
				int modelCode = model.getCodiceModello();
				Connection conn = null;
				try{
					FeatureController fc = FeatureController.getInstance();
					Class.forName("org.postgresql.Driver");
					conn = DriverManager.getConnection("jdbc:postgresql:BQRdb","postgres","totigimmi");
					Statement interrogazione = conn.createStatement();
					ResultSet featureCodes = interrogazione.executeQuery(
							"select * from featureofmodel\n"
							+ "where codicemodello = "+ modelCode );
					while (featureCodes.next()){
						model.addFeatures(fc.getFeatureOfModel(featureCodes.getInt("codicefeature"),modelCode));

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

			public String getFeatureName(int codiceFeature) throws Exception {
				Connection conn = null;
				String result = "";
				try{

					Class.forName("org.postgresql.Driver");
					conn = DriverManager.getConnection("jdbc:postgresql:BQRdb","postgres","totigimmi");
					Statement interrogazione = conn.createStatement();
					ResultSet res = interrogazione.executeQuery(
							"select nomefeature \n"
							+ "from feature \n"
							+ "where codicefeature = " + codiceFeature + "");
					while(res.next()){
						result = res.getString("nomefeature");
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


			public String getFeatureDescription(int codiceFeature) throws Exception {
				Connection conn = null;
				String result = "";
				try{

					Class.forName("org.postgresql.Driver");
					conn = DriverManager.getConnection("jdbc:postgresql:BQRdb","postgres","totigimmi");
					Statement interrogazione = conn.createStatement();
					ResultSet res = interrogazione.executeQuery(
							"select description \n"
							+ "from feature \n"
							+ "where codicefeature = " + codiceFeature + "");
					while (res.next()){
						result = res.getString("description");
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


			public int getFeatureNumberOfInstances(int codiceFeature, int codiceStanza) throws Exception{
				Connection conn = null;
				int result = 0;
				try{

					Class.forName("org.postgresql.Driver");
					conn = DriverManager.getConnection("jdbc:postgresql:BQRdb","postgres","totigimmi");
					Statement interrogazione = conn.createStatement();
					ResultSet res = interrogazione.executeQuery(
							"select numberofinstances \n"
							+ "from featureofroom \n"
							+ "where codicefeature = " + codiceFeature
							+ " and codicestanza = " + codiceStanza );
					while (res.next()){
						result = res.getInt("numberofinstances");
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



			public int getFeatureNumberOfDamages(int codiceFeature, int codiceStanza) throws Exception{
				Connection conn = null;
				int result = 0;
				try{

					Class.forName("org.postgresql.Driver");
					conn = DriverManager.getConnection("jdbc:postgresql:BQRdb","postgres","totigimmi");
					Statement interrogazione = conn.createStatement();
					ResultSet res = interrogazione.executeQuery(
							"select numberofdamages \n"
							+ "from featureofroom \n"
							+ "where codicefeature = " + codiceFeature
							+ " and codicestanza = " + codiceStanza );
					while (res.next()){
						result = res.getInt("numberofdamages");
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



			public int getFeatureNumberOfInstances_models(int codiceFeature, int codiceModello) throws Exception{
				Connection conn = null;
				int result = 0;
				try{

					Class.forName("org.postgresql.Driver");
					conn = DriverManager.getConnection("jdbc:postgresql:BQRdb","postgres","totigimmi");
					Statement interrogazione = conn.createStatement();
					ResultSet res = interrogazione.executeQuery(
							"select numberofinstances \n"
							+ "from featureofmodel \n"
							+ "where codicefeature = " + codiceFeature
							+ " and codicemodello = " + codiceModello );
					while (res.next()){
						result = res.getInt("numberofinstances");
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


			public void AndDelete_models(Feature feature) throws Exception{

				String nome = feature.getName();
				int codiceModello = feature.getCodiceModello();
				String description = feature.getDescription();
				Connection conn = null;
				try{
					Class.forName("org.postgresql.Driver");
					conn = DriverManager.getConnection("jdbc:postgresql:BQRdb","postgres","totigimmi");
					Statement interrogazione = conn.createStatement();
					interrogazione.executeUpdate("delete from featureofmodel\n"
							+ "where nomefeature ='" + nome + "'\n"
							+ "and codicemodello = " + codiceModello + "\n"
							+ "and descrizione = '" + description + "'");
				}
			    catch (SQLException e) {e.printStackTrace();
			    }
		        catch (Exception e) {e.printStackTrace();
			    }
		        finally {
		        conn.close();
		        }
			}

			public void AndDelete(Feature feature) throws Exception{

				String nome = feature.getName();
				int codiceStanza = feature.getCodiceStanza();
				String description = feature.getDescription();
				Connection conn = null;
				try{
					Class.forName("org.postgresql.Driver");
					conn = DriverManager.getConnection("jdbc:postgresql:BQRdb","postgres","totigimmi");
					Statement interrogazione = conn.createStatement();
					interrogazione.executeUpdate("delete from featureofroom\n"
							+ "where nomecaratteristica ='" + nome + "'\n"
							+ "and codicestanza = " + codiceStanza + "\n"
							+ "and descrizione = '" + description + "'");
				}
			    catch (SQLException e) {e.printStackTrace();
			    }
		        catch (Exception e) {e.printStackTrace();
			    }
		        finally {
		        conn.close();
		        }
			}




			public int selectEquals_models(Feature feature) throws Exception{
				int incremento = 0;
				String nome = feature.getName();
				int codiceModello = feature.getCodiceModello();
				String description = feature.getDescription();
				Connection conn = null;
				try{
					Class.forName("org.postgresql.Driver");
					conn = DriverManager.getConnection("jdbc:postgresql:BQRdb","postgres","totigimmi");
					Statement interrogazione = conn.createStatement();
					ResultSet res = interrogazione.executeQuery("select * from featureofmodel\n"
							+ "where nomefeature ='" + nome + "'\n"
							+ "and codicemodello = " + codiceModello + "\n"
							+ "and descrizione = '" + description + "'");
					while (res.next()){
						incremento = res.getInt("numberofinstances");
					}

				}
			    catch (SQLException e) {e.printStackTrace();
			    }
		        catch (Exception e) {e.printStackTrace();
			    }
		        finally {
		        conn.close();
		        }
				return incremento;
			}

			public int selectEquals(Feature feature) throws Exception{
				int incremento = 0;
				String nome = feature.getName();
				int codiceStanza = feature.getCodiceStanza();
				String description = feature.getDescription();
				Connection conn = null;
				try{
					Class.forName("org.postgresql.Driver");
					conn = DriverManager.getConnection("jdbc:postgresql:BQRdb","postgres","totigimmi");
					Statement interrogazione = conn.createStatement();
					ResultSet res = interrogazione.executeQuery("select * from featureofroom\n"
							+ "where nomecaratteristica ='" + nome + "'\n"
							+ "and codicestanza = " + codiceStanza + "\n"
							+ "and descrizione = '" + description + "'");
					while (res.next()){
						incremento = res.getInt("numberofinstances");
					}

				}
			    catch (SQLException e) {e.printStackTrace();
			    }
		        catch (Exception e) {e.printStackTrace();
			    }
		        finally {
		        conn.close();
		        }
				return incremento;
			}


			public void insertFeatureIntoRoom(Feature feature) throws Exception {

				String nome = feature.getName();
				int numberOfInstances = feature.getNumberOfInstances();
				int codiceStanza = feature.getCodiceStanza();
				String description = feature.getDescription();
				int codiceFeature = feature.getCodiceFeature();

				Connection conn = null;
				try{
					if (numberOfInstances != 0) {
						int inc = selectEquals(feature);
						AndDelete(feature);
						Class.forName("org.postgresql.Driver");
						conn = DriverManager.getConnection("jdbc:postgresql:BQRdb","postgres","totigimmi");
						Statement interrogazione = conn.createStatement();
						interrogazione.executeUpdate("insert into featureofroom(nomecaratteristica,numberofinstances,"
								+ "codicestanza,descrizione,codicefeature,numberofdamages) "
								+ "values('"
								+ nome
								+ "',"
								+ (numberOfInstances + inc)
								+ ","
								+ codiceStanza
								+ ",'"
								+ description
								+ "',"
								+ codiceFeature
								+","
								+ 0
								+")");
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



			public void insertFeatureIntoModel(Feature feature) throws Exception {

				String nome = feature.getName();
				int numberOfInstances = feature.getNumberOfInstances();
				int codiceModello = feature.getCodiceModello();
				String description = feature.getDescription();
				int codiceFeature = feature.getCodiceFeature();

				Connection conn = null;
				try{
					int inc = selectEquals_models(feature);
					AndDelete_models(feature);
					Class.forName("org.postgresql.Driver");
					conn = DriverManager.getConnection("jdbc:postgresql:BQRdb","postgres","totigimmi");
					Statement interrogazione = conn.createStatement();
					interrogazione.executeUpdate("insert into featureofmodel(nomefeature,descrizione,"
							+ "codicefeature,numberofinstances,codicemodello) "
							+ "values('"
							+ nome
							+ "','"
							+ description
							+ "',"
							+ codiceFeature
							+ ","
							+ (numberOfInstances + inc)
							+ ","
							+ codiceModello
							+")");
				}
			    catch (SQLException e) {e.printStackTrace();
			    }
		        catch (Exception e) {e.printStackTrace();
			    }
		        finally {
		        conn.close();
		        }
            }



			public void deleteFeatureFromRoom(int codiceFeature, int codiceStanza) throws Exception{

				Connection conn = null;
	            try{
	                Class.forName("org.postgresql.Driver");
	                conn = DriverManager.getConnection("jdbc:postgresql:BQRdb","postgres","totigimmi");
	                Statement interrogazione = conn.createStatement();
	                interrogazione.executeUpdate("delete from featureofroom\n "
							+ "where codicefeature = " + codiceFeature
							+ "and codicestanza = " + codiceStanza);
	                }
	            catch (SQLException e) {e.printStackTrace();
	            }
	            catch (Exception e) {e.printStackTrace();
	            }
	            finally {
	                conn.close();
	            }

			}


			public void deleteFeatureFromModel(int codiceFeature, int codiceModello) throws Exception{

				Connection conn = null;
	            try{
	                Class.forName("org.postgresql.Driver");
	                conn = DriverManager.getConnection("jdbc:postgresql:BQRdb","postgres","totigimmi");
	                Statement interrogazione = conn.createStatement();
	                interrogazione.executeUpdate("delete from featureofmodel\n "
							+ "where codicefeature = " + codiceFeature
							+ "and codicemodello = " + codiceModello);
	                }
	            catch (SQLException e) {e.printStackTrace();
	            }
	            catch (Exception e) {e.printStackTrace();
	            }
	            finally {
	                conn.close();
	            }

			}


			public void deleteFeature(int codiceFeature) throws Exception {

	            Connection conn = null;
	            try{
	                Class.forName("org.postgresql.Driver");
	                conn = DriverManager.getConnection("jdbc:postgresql:BQRdb","postgres","totigimmi");
	                Statement interrogazione = conn.createStatement();
	                interrogazione.executeUpdate("delete from feature\n "
							+ "where codicefeature = " + codiceFeature );
	                }
	            catch (SQLException e) {e.printStackTrace();
	            }
	            catch (Exception e) {e.printStackTrace();
	            }
	            finally {
	                conn.close();
	            }
	        }


			public void deleteAllFeatures() throws Exception{

				Connection conn = null;
	            try{
	                Class.forName("org.postgresql.Driver");
	                conn = DriverManager.getConnection("jdbc:postgresql:BQRdb","postgres","totigimmi");
	                Statement interrogazione = conn.createStatement();
	                interrogazione.executeUpdate("delete from feature ");
	                }
	            catch (SQLException e) {e.printStackTrace();
	            }
	            catch (Exception e) {e.printStackTrace();
	            }
	            finally {
	                conn.close();
	            }

			}


			
			
			
			
			
			public void printFeaturesOfRoomOnTableView(int roomCode,ObservableList<OutputBean_FOR> data,
					ObservableList<ObservableList<OutputBean_FOR>> data2) throws Exception{

				Connection conn = null;
				try{
					Class.forName("org.postgresql.Driver");
					conn = DriverManager.getConnection("jdbc:postgresql:BQRdb","postgres","totigimmi");
					Statement interrogazione = conn.createStatement();
					ResultSet risultato = interrogazione.executeQuery("select * from featureofroom "
							+ "where codicestanza = "
							+ roomCode);

					while (risultato.next()) {
						data.add(new OutputBean_FOR(
								risultato.getString("nomecaratteristica"),
								risultato.getString("descrizione"),
								risultato.getInt("numberofinstances"),
								risultato.getInt("numberofdamages"),
								risultato.getInt("codicefeature")
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
			
			
			public ArrayList<OutputBean_FOR> printFeaturesOfRoom(int roomCode) throws Exception{

				ArrayList<OutputBean_FOR> FoR= new ArrayList<OutputBean_FOR>();
				Connection conn = null;
				try{
					Class.forName("org.postgresql.Driver");
					conn = DriverManager.getConnection("jdbc:postgresql:BQRdb","postgres","totigimmi");
					Statement interrogazione = conn.createStatement();
					ResultSet risultato = interrogazione.executeQuery("select * from featureofroom "
							+ "where codicestanza = "
							+ roomCode);

					while (risultato.next()) {
						FoR.add(new OutputBean_FOR(
								risultato.getString("nomecaratteristica"),
								risultato.getString("descrizione"),
								risultato.getInt("numberofinstances"),
								risultato.getInt("numberofdamages"),
								risultato.getInt("codicefeature")
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
				return FoR;
			}


			
			
			
			
			
			
			
			
			
			
			
			
			

			public void printFeaturesOfModelOnTableView(int modelCode,ObservableList<OutputBean_FOM> data,
					ObservableList<ObservableList<OutputBean_FOM>> data2) throws Exception{

				Connection conn = null;
				try{
					Class.forName("org.postgresql.Driver");
					conn = DriverManager.getConnection("jdbc:postgresql:BQRdb","postgres","totigimmi");
					Statement interrogazione = conn.createStatement();
					ResultSet risultato = interrogazione.executeQuery("select * from featureofmodel "
							+ "where codicemodello = "
							+ modelCode);

					while (risultato.next()) {
						data.add(new OutputBean_FOM(
								risultato.getString("nomefeature"),
								risultato.getString("descrizione"),
								risultato.getInt("numberofinstances"),
								risultato.getInt("codicefeature")
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
			
			
			public ArrayList<OutputBean_FOM> printFeaturesOfModel(int modelCode) throws Exception{

				ArrayList<OutputBean_FOM> FoM = new ArrayList<OutputBean_FOM>();
				Connection conn = null;
				try{
					Class.forName("org.postgresql.Driver");
					conn = DriverManager.getConnection("jdbc:postgresql:BQRdb","postgres","totigimmi");
					Statement interrogazione = conn.createStatement();
					ResultSet risultato = interrogazione.executeQuery("select * from featureofmodel "
							+ "where codicemodello = "
							+ modelCode);

					while (risultato.next()) {
						FoM.add(new OutputBean_FOM(
								risultato.getString("nomefeature"),
								risultato.getString("descrizione"),
								risultato.getInt("numberofinstances"),
								risultato.getInt("codicefeature")
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
				return FoM;
			}



			
			
			
			
			
			
			
			public void printFeaturesOnTable(ObservableList<OutputBean_F> data,
					ObservableList<ObservableList<OutputBean_F>> data2) throws Exception{

				Connection conn = null;
				try{
					Class.forName("org.postgresql.Driver");
					conn = DriverManager.getConnection("jdbc:postgresql:BQRdb","postgres","totigimmi");
					Statement interrogazione = conn.createStatement();
					ResultSet risultato = interrogazione.executeQuery("select * from feature");
					while (risultato.next()) {
						data.add(new OutputBean_F(
								risultato.getString("nomefeature"),
								risultato.getString("description"),
								risultato.getInt("codicefeature")
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



			public ArrayList<OutputBean_F> printFeatures() throws Exception{

				Connection conn = null;
				ArrayList<OutputBean_F> array = new ArrayList<OutputBean_F>();
	            try{
	                Class.forName("org.postgresql.Driver");
	                conn = DriverManager.getConnection("jdbc:postgresql:BQRdb","postgres","totigimmi");
	                Statement interrogazione = conn.createStatement();
	                ResultSet risultato = interrogazione.executeQuery(
	                		"select nomefeature,description,codicefeature"
	                		+ " from feature\n " );
	                while (risultato.next()){
	                	array.add(new OutputBean_F(
								risultato.getString("nomefeature"),
								risultato.getString("description"),
								risultato.getInt("codicefeature")));
								
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
					interrogazione.executeUpdate(
							"update codicestanzagenerator\n"
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