package Classes;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import SQLite.SQLite;
import Table.TablePaire;

public class Admin extends User
{
	public static int Role (String user , String pass)
	{
		return SQLite.getRole(user, pass) ;
	}
	public static DefaultTableModel buildTableModel (ResultSet rs)
	{
		ResultSetMetaData metaData = null;
		
	    try 
	    {
			if (rs != null )
			{
				metaData = rs.getMetaData();
			}
		} catch (SQLException e) 
	    {
			e.printStackTrace();
		}

	    // Name Of Columns //
	    Vector<String> columnNames = new Vector<String>();
	    int columnCount = 0;
		try {
			if (metaData != null)
			{
				columnCount = metaData.getColumnCount();
			}
		}
		
		catch (SQLException e) {
			e.printStackTrace();
		}
	    
		for (int column = 1; column <= columnCount; column++)
	    {
	        try 
	        {
				columnNames.add(metaData.getColumnName(column));
			}
	        catch (SQLException e) 
	        {
				e.printStackTrace();
			}
	    }

	    // Data Of The Table //
	    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	    try {
			if (rs != null)
			{
				while (rs.next()) 
				{
				    Vector<Object> vector = new Vector<Object>();
				    for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) 
				    {
				        vector.add(rs.getObject(columnIndex));
				    }
				    data.add(vector);
				}
			}
		}
	    catch (SQLException e) 
	    {
			e.printStackTrace();
		}
	    return new DefaultTableModel(data, columnNames);
	}
	public static TablePaire[] getTables ()
	{
		TablePaire T[] = new TablePaire[12];
		T[0] = new TablePaire(Admin.buildTableModel(SQLite.select_TablePersonne()));
		T[1] = new TablePaire(Admin.buildTableModel(SQLite.select_TableAnalyse()));
		T[2] = new TablePaire(Admin.buildTableModel(SQLite.select_TableConsultation()));
		T[3] = new TablePaire(Admin.buildTableModel(SQLite.select_TableDiagnostique()));
		T[4] = new TablePaire(Admin.buildTableModel(SQLite.select_TableMalade_de()));
		T[5] = new TablePaire(Admin.buildTableModel(SQLite.select_TableMaladie()));
		
		T[6] = new TablePaire(Admin.buildTableModel(SQLite.select_TableMedcin()));
		T[7] = new TablePaire(Admin.buildTableModel(SQLite.select_TableMedicament()));
		T[8] = new TablePaire(Admin.buildTableModel(SQLite.select_TablePatient()));
		T[9] = new TablePaire(Admin.buildTableModel(SQLite.select_TableRDV()));
		T[10] = new TablePaire(Admin.buildTableModel(SQLite.select_TableSecretaire()));
		T[11] = new TablePaire(Admin.buildTableModel(SQLite.select_TableTraitement()));
		return T ;
	}
	public static ResultSet excucte (String Req,String Tab[])
	{
		return SQLite.excute(Req,Tab);
	}

}
