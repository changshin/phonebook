package com.chang.ng.phone.data.service;

import java.io.Serializable;
import java.util.List;
  

/**
 * Generic DAO (Data Access Object) with common methods to CRUD POJOs.
 * <p>
 * Extend this interface if you want typesafe (no casting necessary) DAO's for your domain objects.
 * @author Mmalyala
 * @param <T> a type variable
 * @param <D> 
 * @param <PK> the primary key for that type
 */
public interface GenericService<T, D, PK extends Serializable> {
  
  /**
   * Generic method used to get all entities of a particular type. This is the same as lookup up all
   * rows in a table.
   * @return List of populated entities
   */
  public List<T> findAll();
  
  /**
   * Generic method to get an entity based on class and identifier. An DaoException Runtime
   * Exception is thrown if nothing is found.
   * @param id the identifier (primary key) of the entity to get
   * @return a populated entity
   */
  public T find(PK id);
  
  /**
   * Checks for existence of an entity of type T using the id arg.
   * @param id the id of the entity
   * @return - true if it exists, false if it doesn't
   */
  public boolean exists(PK id);
  
  /**
   * Generic method to save an entity - handles only insert for new created entities.
   * @param transientEntity newly created entity to be persisted
   */
  public void persist(T transientEntity);
  
  /**
   * Generic method to update an entity - handles both update and insert.
   * @param detachedEntity - the detached entity to be persisted
   * @return the detached entity
   */
  public T merge(T detachedEntity);
  
  /**
   * Generic method to delete an entity based on class and id
   * @param id the identifier (primary key) of the entity to remove
   */
  public void remove(PK id);
  
  /**
   * Generic method to delete a persistent entity
   * @param persistentEntity - the persistent entity to be deleted
   */
  public void remove(T persistentEntity);
   
  
}
