package com.ListDao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ListUtil.JDBCUtils;
import com.Listmodel.List;


public class ListDao {
	
	public int addList(List list)
	throws ClassNotFoundException {
			
		int count = 0;
		int result = 0;
			
		try {
				
			Connection con = JDBCUtils.getConnection();
			    
			PreparedStatement ps1 = con.prepareStatement("insert into tblproduct values(?,?,?,?,?,?,?,?)");
			PreparedStatement ps2 = con.prepareStatement("select id from tblproduct");
					
			ResultSet res=ps2.executeQuery();
				
			while(res.next()) {
				count++;
			}
					
			int id = count + 1;
					
			ps1.setInt(1, id);
			ps1.setString(5, list.getName());
			ps1.setString(6, list.getPrice());
			ps1.setString(8, list.getCategory());
			ps1.setString(4, list.getImageFileName());
			ps1.setString(3,list.getDescription());
			ps1.setString(7, list.getMrp_price());
			ps1.setString(2,list.getStatus());
			
			
			

					
		    result = ps1.executeUpdate();
				
		} catch (SQLException e) {
				
			JDBCUtils.printSQLException(e);
				
		}
			
		return result;
		
	}
	
	public ResultSet viewList(List list)
			throws ClassNotFoundException {
					
                try {
						
					Connection con = JDBCUtils.getConnection();
					    
					PreparedStatement ps = con.prepareStatement("select * from  tblproduct where id ='"+ list.getId()+"'" );
							
					ResultSet res =ps.executeQuery();
					
					return res;
						
				} catch (SQLException e) {
						
					JDBCUtils.printSQLException(e);
					
					return null;
					
				}
					
	}
	
	public int updateList(List list)
			throws ClassNotFoundException {
					
                try {
						
					Connection con = JDBCUtils.getConnection();
					    
					PreparedStatement ps = con.prepareStatement("update tblproduct set Category ='" + list.getCategory()
					+ "', Name ='" + list.getName()
					+ "', Email ='" + list.getPrice() 
					
					+ "' where id =" + list.getId());					
					int rs = ps.executeUpdate();
					
					return rs;
						
				} catch (SQLException e) {
						
					JDBCUtils.printSQLException(e);
					
					return 0;
					
				}
					
	}
	
	public int deleteList(List list)
			throws ClassNotFoundException {
					
                try {
						
					Connection con = JDBCUtils.getConnection();
					    
					PreparedStatement ps = con.prepareStatement("delete   from tblproduct where id ='" + list.getId()+"'"
                    );
		
					int res = ps.executeUpdate();
					
					return res;
						
				} catch (SQLException e) {
						
					JDBCUtils.printSQLException(e);
					
					return 0;
					
				}
					
	}

	public java.util.List getId(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
}