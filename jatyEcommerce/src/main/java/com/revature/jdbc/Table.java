package com.revature.jdbc;

/**
 * This interface is meant to mimic the list interface from java collections.
 * Implementing the real list interface would add a lot of work for methods we'll probably
 * never use as well as create confusion on what methods are available for use.
 * @author tomh0
 *
 * @param <T> This generic type should be one of the interfaces such as Account. This
 * allows us to use any extension of the Account interface such as a 
 * LocalAccount or RemoteAccount
 */
public interface Table <T>{

	/**
	 * There may be gaps in the ids such as a row with id 5 not existing
	 * but 4 and 6 do exist.
	 * The row should be returned as an object.
	 * Should make use of the Select statment
	 * @return Returns a row from the table with the matching id. 
	 */
	public abstract T get(int id);
	
	/**
	 * select * from table order by id asc limit ? offset ?
	 * <br>
	 * This method should give us part of the table instead of the entire table
	 * Useful if we want to implement things like pages to cut down on loading times.
	 * @param limit the amount of rows returned
	 * @param offset offset from the 1st row of the table
	 * @return the rows within the defined limit and offset
	 */
	public abstract T[] getSome(int limit, int offset);
	
	/**
	 * select * from tablename
	 * @return
	 */
	public abstract T[] getAll();
	
	/**
	 * adds an object into the table as a new row
	 * Should use the Insert statement
	 * @param table_row an object with parameters we can map to the columns of the table
	 */
	public abstract void add(T table_row);

	/**
	 * Use the aggregate function, count(*)
	 * select count(*) from tablename
	 * @return size of the table
	 */
	public abstract int size();
	/**
	 * Should checks if the size==0. 
	 * If it is 0, then its empty and we return true
	 * @return true if the table is empty
	 */
	public abstract boolean isEmpty();
}
