package repository;

import org.hibernate.Session;
import org.hibernate.Transaction;

import db_utils.HibernateUtil;
import entities.User;

public class AdminReposImp implements AdminRepos {

	@Override
	public boolean validate(String email, String password) {
		Transaction transaction = null;
        User user = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object
            user = (User) session.createQuery("FROM User U WHERE U.email = :email").setParameter("email", email)
                .uniqueResult();

            if (user != null && user.getPassword().equals(password)) {
                return true;
            }
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return false;
    }
	

}
