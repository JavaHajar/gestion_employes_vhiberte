package dao;

import java.util.List;



import org.hibernate.Session;
import org.hibernate.Transaction;

import db_utils.HibernateUtil;
import entities.User;

public class UserDaoImp implements UserDao {

	@Override
	 @SuppressWarnings("unchecked")
	public List<User> findAll() {
		 Transaction transaction = null;
	        List<User> users = null; 
	        //Employee employee = null;
	        try(Session session = HibernateUtil.getSessionFactory().openSession()){
	            // start the transaction
	            transaction = session.beginTransaction();
	            
	            // get student employee
	            users = session.createQuery("from User").list();
	            // we also can use load() method
	            // employee = session.load(Employee.class, id)
	            //users = session.load(users, "User.class, id").list();
	            // commit the transaction
	            transaction.commit();
	        } catch(Exception e) {
	            if(transaction != null) {
	                transaction.rollback();
	            }
	        }
	        return users;
	}

	@Override
	public User findById(int id) {
		 Transaction transaction = null;
	        User user = null;
	        try(Session session = HibernateUtil.getSessionFactory().openSession()){
	            // start the transaction
	            transaction = session.beginTransaction();
	            
	            // get student employee
	            user = session.get(User.class, id);
	            // we also can use load() method
	            // employee = session.load(Employee.class, id)
	            
	            // commit the transaction
	            transaction.commit();
	        } catch(Exception e) {
	            if(transaction != null) {
	                transaction.rollback();
	            }
	        }
	        return user;
	}

	@Override
	public void save(User user) {
		Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            // start the transaction
            transaction = session.beginTransaction();
            
            // save employee
            session.save(user);
            
            // commit the transaction
            transaction.commit();
        } catch(Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
        }
		
	}

	@Override
	public void update(User user) {
		Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            // start the transaction
            transaction = session.beginTransaction();
            
            // update employee
            session.saveOrUpdate(user);
            
            // commit the transaction
            transaction.commit();
        } catch(Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
        }
		
	}

	@Override
	public void delete(int id) {
		Transaction transaction = null;
        User user = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            // start the transaction
            transaction = session.beginTransaction();
            user = session.get(User.class, id);
            session.delete(user);
            
            
            // commit the transaction
            transaction.commit();
        } catch(Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
        }
        
	}

}
