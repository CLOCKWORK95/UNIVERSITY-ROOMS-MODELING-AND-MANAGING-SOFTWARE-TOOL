package dao;

import java.sql.*;


public class daoUsers{

			public void insertUser(String username, String password) throws Exception {

				Connection conn = null;
				try{
					Class.forName("org.postgresql.Driver");
					conn = DriverManager.getConnection("jdbc:postgresql:BQRdb","postgres","totigimmi");
					Statement interrogazione = conn.createStatement();
					interrogazione.executeUpdate("insert into login(username,pass)\n "
							+ "values('" + username + "','" + password +"')");
				}
			    catch (SQLException e) {e.printStackTrace();
			    }
		        catch (Exception e) {e.printStackTrace();
			    }
		        finally {
		        conn.close();
		        }
            }


			public boolean checkUser(String username, String password) throws Exception {
				boolean access = false;

				Connection conn = null;

				try{
					Class.forName("org.postgresql.Driver");
					conn = DriverManager.getConnection("jdbc:postgresql:BQRdb","postgres","totigimmi");
					Statement interrogazione = conn.createStatement();
					ResultSet risultato = interrogazione.executeQuery("select username, pass from login ");
					while (risultato.next()) {
						if (username.equals( risultato.getString("username") ) &&
								password.equals( risultato.getString("pass") ) ) {
							access = true;
							break;
						}
						access = false;
				    }

				}
			    catch (SQLException e) {e.printStackTrace();
			    }
		        catch (Exception e) {e.printStackTrace();
			    }
		        finally {
		        conn.close();
		        }
				return access;
			}




}