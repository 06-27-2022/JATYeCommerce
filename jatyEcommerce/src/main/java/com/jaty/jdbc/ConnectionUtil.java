package com.jaty.jdbc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.postgresql.util.PSQLException;

/**
 * Designed to work with postgres.
 * I recommend you type the commands into dbeaver before you use them here 
 * to make sure they work. Most of these methods use the getConnection() method so
 * make sure it works first before you try anything else.
 * @author tomh0
 *
 */
public class ConnectionUtil {

	private static final String url=System.getenv("db_url");
	private static final String user=System.getenv("db_username");
	private static final String password=System.getenv("db_password");		
	
	/**
	 * Uses environment variables 
	 * <br>
	 * db_url
	 * <br>
	 * db_username
	 * <br>
	 * db_password
	 * @return
	 */
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(url,user,password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * close provided resources
	 * @param conn Connection object received from getConnection()
	 * @return true if connection successfully closed;
	 */
	public static void closeConnection(Connection conn) {
		try{
			conn.close();
		}catch(SQLException e) {
			e.printStackTrace();			
		}
	}
	/**
	 * close provided resources
	 * @param conn Connection object received from getConnection()
	 * @param stmt Statement objects such as Statement and PreparedStatement
	 * @return true if closed successfully
	 */
	public static void closeConnection(Connection conn,Statement stmt) {
		try{
			conn.close();
			stmt.close();
		}catch(SQLException e) {
			e.printStackTrace();			
		}
	}
	
	/**
	 * close provided resources
	 * @param conn Connection object received from getConnection()
	 * @param stmt Statement objects such as Statement and PreparedStatement
	 * @param set ResultSet object. Tends to throw exceptions if something goes wrong with Statement.
	 * @return true if closed successfully
	 */
	public static void closeConnection(Connection conn,Statement stmt,ResultSet set) {
		try{
			conn.close();
			stmt.close();
			set.close();
		}catch(SQLException e) {
			e.printStackTrace();			
		}
	}
	/**
	 * simply executes the statement given
	 * @param SQL String used in prepareStatement()
	 * @return returns true if the statement executed
	 */
//	public static boolean stmtExecute(String SQL) {
//		Connection conn=getConnection();
//		PreparedStatement stmt=null;
//		boolean success = false;
//		try{
//			stmt=conn.prepareStatement(SQL);			
//			stmt.execute();
//			success = true;
//		}catch(SQLException e){
//			e.printStackTrace();
//		}finally {
//			closeConnection(conn,stmt);			
//		}
//		return success;
//	}

	/**
	 * executes a prepared statement
	 * @param SQL string used for the prepareStatement() function
	 * @param args object used for stmt.set____(), see setStatement() for more info
	 * @return returns true if no SQLExceptions occurred and the statement executed
	 */
//	public static boolean stmtExecute(String SQL,Object args) {
//		return stmtExecute(SQL,new Object[] {args});
//	}
	/**
	 * executes a prepared statement
	 * @param SQL string used for the prepareStatement() function
	 * @param args list of objects used for stmt.set____(), see setStatement() for more info
	 * @return returns true if no SQLExceptions occurred and the statement executed
	 */
	public static void stmtExecute(String SQL,Object...args) {
		Connection conn = getConnection();
		PreparedStatement stmt=null;
		try {
			stmt = conn.prepareStatement(SQL);	
			for(int i=1;i<=args.length;i++)
				setStatement(stmt,i,args[i-1]);
			stmt.execute();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection(conn,stmt);
		}
	}
		
		/**
		 * automatically selects the correct datatype for stmt.set___() 
		 * supports int, double, char, string, boolean
		 * chars will be submitted as a string
		 * resultset causes exceptions when closing when trying to manually choose the type of set
		 * only uses stmt.setObject for now
		 * @param stmt The ProtectedStatement object you are trying to set
		 * @param i The parameterindex for stmt
		 * @param o	The object being set into stmt
		 * @throws SQLException
		 */
		private static void setStatement(PreparedStatement stmt,int i,Object o) throws SQLException {		
			if(o==null)
				stmt.setNull(i, Types.NULL);
			else if(o.getClass().equals(File.class)) {
				File f = (File)o;
				try{
					stmt.setBinaryStream(i,new FileInputStream(f),f.length());				
				}catch(FileNotFoundException e) {
					e.printStackTrace();
				}
			}
			else
				stmt.setObject(i, o);
		}
		
	/**
	 * Runs a resultSet.
	 * @param SQL used for a preparedstatement
	 * @return returns an object from stmtExecute2d(SQL,args)[0][0]
	 */	
	public static Object stmtExecuteQuery(String SQL, Object...args) {
		try{
			return stmtExecuteQuery2D(SQL,args)[0][0];
		}catch(ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}
	/**
	 * Runs a resultSet.
	 * @param SQL used for a preparedstatement
	 * @param args	arguments for the preparedstatement
	 * @return returns an object from stmtExecute2d(SQL,args)[0]
	 */	
	public static Object[]stmtExecuteQuery1D(String SQL, Object...args) {
		try{
			return stmtExecuteQuery2D(SQL,args)[0];
		}catch(ArrayIndexOutOfBoundsException e) {
			return new Object[] {};
		}
	}	
	
	/**
	 * Runs a resultSet.
	 * @param SQL used for a preparedstatement
	 * @param args	arguments for the preparedstatement
	 * @return returns a 2d object array obj[rows][columns]
	 */
	public static Object[][] stmtExecuteQuery2D(String SQL,Object...args) {
		Connection conn=ConnectionUtil.getConnection();
		PreparedStatement stmt=null;
		ResultSet set=null;
		Object[][]obj= {{}};//=new Object[rows][columns];
		ArrayList<ArrayList<Object>>output=new ArrayList<ArrayList<Object>>();
		try {
			stmt=conn.prepareStatement(SQL);
			for(int i=1;i<=args.length;i++)
				setStatement(stmt,i,args[i-1]);
			set=stmt.executeQuery();
			int rows=0;
			int columns=0;
			while(set.next()) {
				output.add(new ArrayList<Object>());
				int c=0;
				while(columns==0||c<columns) {
					try{
						output.get(rows).add(setGetObject(set, c+1));
					}catch(PSQLException e) {
						columns=c;
						break;
					}
					c++;
				}
				rows++;					
			}
			obj=new Object[rows][columns];
			for(int y=0;y<rows;y++)
				for(int x=0;x<columns;x++)
					obj[y][x]=output.get(y).get(x);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(set!=null)
				ConnectionUtil.closeConnection(conn,stmt,set);
			else
				ConnectionUtil.closeConnection(conn,stmt);
		}		
		return obj;
	}
	/**
	 * supports int, double, string
	 * defaults to object as last resort
	 * @param set A non null ResultSet object
	 * @param i columnindex
	 * @return attempts to return double, int, string, then object
	 * @throws SQLException
	 */
	private static Object setGetObject(ResultSet set, int i) throws SQLException {		
		Object obj = set.getObject(i);
		if(obj==null)
			return null;
		else if(obj.getClass()==Long.class){
			return set.getInt(i);
		}
		else if(obj.getClass()==BigDecimal.class)
			return set.getDouble(i);
		else if(obj.getClass().toString().equalsIgnoreCase("class [B"))
			return set.getBinaryStream(i);
		return obj;
	}	
	
	/**
	 * postgres stores and returns files as bytea. This method is for converting from bytea back into file
	 * @param filename name of the file the bytea will be written into. Extensions like .png must be included
	 * @param bytea the byte array returned from postgres
	 * @return file object using the filename provided. 
	 * files sharing the same filename will end up overwriting each other.
	 * This means you should not be storing these in any data structures and closing them as soon as possible
	 * with methods such as delete()
	 */
	public static File getFile(String filename, ByteArrayInputStream bytea) {
		File outFile = new File(filename);
		try {
			InputStream in=bytea;
			OutputStream out=new FileOutputStream(outFile.getName());
			int data;
			do{
				data=in.read();
				out.write(data);
			}while(data!=-1); 
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return outFile;
	}
	
	/**
	 * This and getFile should probably go to another class.
	 * @param f
	 * @param width
	 * @param height
	 */
	public static void displayPicture(File f, int width, int height){
		//loads the picture from the file
		BufferedImage bufferedImage = null;
		try {
			bufferedImage = ImageIO.read(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
        ImageIcon imageIcon = new ImageIcon(bufferedImage);		
		JFrame frame = new JFrame();
		JLabel label = new JLabel();

		//should automatically resize jlabel to the size of the jframe
		//it does the opposite, i got more pressing things to worry about so this will have to wait
		frame.setLayout(new BorderLayout());
		label.setIcon(imageIcon);
		frame.add(label, BorderLayout.CENTER);
		frame.getContentPane().setSize(new Dimension(width,height));
		frame.setSize(width, height);
		frame.setAlwaysOnTop(true);
		frame.setVisible(true);
		
		/*
		 *	JFrame.EXIT_ON_CLOSE — Exit the application.
			JFrame.HIDE_ON_CLOSE — Hide the frame, but keep the application running.
			JFrame.DISPOSE_ON_CLOSE — Dispose of the frame object, but keep the application running.
			JFrame.DO_NOTHING_ON_CLOSE — Ignore the click.
		 */
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}	
	
}
