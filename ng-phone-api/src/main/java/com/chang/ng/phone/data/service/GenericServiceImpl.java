package com.chang.ng.phone.data.service;

import java.io.Serializable;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * This class serves as the Base class for all other DAOs - namely to hold
 * common CRUD methods that they might all use. You should only need to extend
 * this class when your require custom CRUD logic.
 * 
 * @author mmalyala
 * @param <T>
 *            a type variable
 * @param <D>
 * @param <PK>
 *            the primary key for that type
 */
@Transactional(propagation = Propagation.REQUIRED)
public class GenericServiceImpl<T, D, PK extends Serializable> implements
		GenericService<T, D, PK> {

	// ** Get Generic Dao object **/
	/**
	 * Reference to genericRep
	 */
	@Autowired
	public D genericRep;

	/**
	 * Log variable for all child classes. Uses Logger.getLogger(getClass())
	 * from framework Logging
	 */
	protected final Logger log = LogManager.getLogger(GenericService.class);

	private Class<T> persistentClass;

	/**
	 * Constructor that takes in a class to see which type of entity to persist
	 * 
	 * @param persistentClass
	 *            the class type you'd like to persist
	 */
	public GenericServiceImpl(final Class<T> persistentClass) {
		this.persistentClass = persistentClass;
	}

	/*
	 * @PostConstruct public void setPersistentClass(){
	 * this.genericRep.setPersistanceClass(this.persistentClass); }
	 */
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void persist(final T transientEntity) {

		try {
			((JpaRepository<T, PK>) this.genericRep).save(transientEntity);

			/*
			 * log.debug("persist(final T transientEntity) :: Entity " +
			 * this.persistentClass + " persist successful :: entity: " +
			 * transientEntity);
			 */

		} catch (final RuntimeException re) {
			log.error("persist(final T transientEntity) :: Entity "
					+ this.persistentClass + " persist failed :: entity: "
					+ transientEntity + " :: errMsg: " + re.getMessage());
			if (re.getMessage().contains("ConstraintViolationException")) {
				// throw new ConstraintViolationException(re.getMessage());
			}

		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		try {
			return  ((JpaRepository<T, PK>) this.genericRep).findAll();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public T find(final PK id) {

		final T entity = ((JpaRepository<T, PK>) this.genericRep).findOne(id);

		if (entity == null) {
			final String msg = "Uh oh, Entity '" + this.persistentClass
					+ "' with id '" + id + "' not found...";
			log.warn("find(final PK id) :: " + msg);
			// throw new DaoException(msg);
		}
		/*
		 * log.debug("find(final PK id) :: Entity " + this.persistentClass +
		 * " found successful :: entity id: " + id);
		 */

		return entity;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public boolean exists(final PK id) {
		final T entity = ((JpaRepository<T, PK>) this.genericRep).findOne(id);

		/*
		 * log.debug("exists(final PK id) :: Entity " + this.persistentClass +
		 * " exists : " + (entity != null));
		 */

		return entity != null;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T merge(final T detachedEntity) {

		T entity = null;
		try {
			entity = ((JpaRepository<T, PK>) this.genericRep)
					.save(detachedEntity);

			/*
			 * log.debug("merge(final T detachedEntity) :: Entity " +
			 * this.persistentClass + " merge successful :: entity: " +
			 * detachedEntity);
			 */

		} catch (final RuntimeException re) {
			log.error("merge(final T detachedEntity) :: Entity "
					+ this.persistentClass + " merge failed :: entity: "
					+ detachedEntity + " :: errMsg: " + re.getMessage());

			if (re.getMessage().contains("ConstraintViolationException")) {
				// throw new ConstraintViolationException(re.getMessage());
			}
		}
		return entity;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void remove(final PK id) {
		try {
			((JpaRepository<T, PK>) this.genericRep).delete(id);
			/*
			 * log.debug("remove(final PK id) :: Entity " + this.persistentClass
			 * + "remove successful ::  id: " + id);
			 */

		} catch (final RuntimeException re) {
			log.error("remove(final PK id) :: Entity " + this.persistentClass
					+ " remove failed :: id: " + id + " :: errMsg: "
					+ re.getMessage());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void remove(final T persistentEntity) {
		// log.debug("removing " + persistentEntity + "  instance");
		try {
			((JpaRepository<T, PK>) this.genericRep).delete(persistentEntity);
			/*
			 * log.debug("remove(final T persistentEntity) :: Entity " +
			 * this.persistentClass + " remove successful: entity: " +
			 * persistentEntity);
			 */

		} catch (final RuntimeException re) {
			log.error("remove(final T persistentEntity) :: Entity "
					+ this.persistentClass + " remove failed: entitystate: "
					+ persistentEntity + " :: errMsg: " + re.getMessage());
		}
	}
}
