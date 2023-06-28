package com.LoginDemo.UserDaoImp;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.LoginDemo.Connection.DbConnection;
import com.LoginDemo.Dao.UserDao;
import com.LoginDemo.Pojo.User;

public class UserDaoImpl implements UserDao {

	@Override
	public boolean checkUserCredentials(User user) {
		try(Connection con = 
				DbConnection.getDatabaseConnection()) {
			
			PreparedStatement pst = 
			con.prepareStatement("SELECT * FROM"
			+ "  details WHERE username = ? AND"
			+ " pass = ?");
			
			pst.setString(1, user.getUsername());
			pst.setString(2, user.getPassword());
			
			ResultSet rs = pst.executeQuery();
			
			if(rs.isBeforeFirst())
				return true;
			else
				return false;
		}
		catch(SQLException | NullPointerException exc)
		{
			exc.printStackTrace();
			return false;
		}
	}
	String name="Abhay Patidar";

	@Override
	public boolean addNewUser(User user) {
		try(Connection con = 
				DbConnection.getDatabaseConnection()) {
			
			PreparedStatement pst = 
			con.prepareStatement("INSERT INTO details"
					+ " VALUES(?,?)");
//			System.out.println(user.getUsername());
			pst.setString(1, user.getUsername());
			pst.setString(2, user.getPassword());
			
			int count = pst.executeUpdate();
			
			if(count > 0)
				return true;
			else
				return false;
		}
		catch(SQLException | NullPointerException exc)
		{
			exc.printStackTrace();
			return false;
		}
	}	
}
