package com.jaty.repository;

/**
 * Expected to hold the logic for interacting with the sql tables
 * and its rows. A more specialized repository interface will be needed for the models
 * but for now this is good enough.
 * <br>
 * The methods for interacting with tables will be modeled after the list interface.
 * <br>
 * The methods for interacting with the rows would ideally be modelled after the get and set
 * methods of the object being updated
 * @author tomh0
 *
 * @param <T>
 */
public interface jatyRepository <T>{
	/**
	 * select * from tablename where id=?
	 * There may be gaps in the ids such as a row with id 5 not existing
	 * but 4 and 6 do exist.
	 * The row should be returned as an object.
	 * Should make use of the Select statment
	 * @param id The id should be the primary key. Its expected to be a serial type
	 * @return Returns a row from the table with the matching id. 
	 */
	public abstract T get(int id);

	/**
	 * select * from tablename where param1=? and param2=? and ...
	 * if you don't have an id to search, you can use this method to match
	 * attributes of the inputed object with those from the database
	 * @param obj An object with no connection to the database (no id)
	 * @return An object with a connectio to the database (has an id)
	 */
	public abstract T get(T obj);

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
	 * select * from tablename order by id
	 * @return
	 */
	public abstract T[] getAll();
	
	/**
	 * insert into tablename (param1,param2,...)values(?,?,...)";
	 * adds an object into the table as a new row
	 * Should use the Insert statement
	 * @param table_row an object with parameters we can map to the columns of the table
	 */
	public abstract void add(T obj);

	/**
	 * deletes a row from the table
	 * @param obj the row being deleted from the table should match the id of the provided object
	 */
	public abstract void delete(T obj);
	
	/**
	 * select count(*) from tablename
	 * Use the aggregate function, count(*)
	 * select count(*) from tablename
	 * @return size of the table
	 */
	public abstract int size();
	
	/**
	 * Should check if the size==0. 
	 * If it is 0, then its empty and we return true
	 * @return true if the table is empty
	 */
	public abstract boolean isEmpty();
	
	/**
	 * sets a row in the table to be replaced with the data in the provided object
	 * @param obj the id should match a row on the table
	 */
	public abstract void set(T obj);
}
