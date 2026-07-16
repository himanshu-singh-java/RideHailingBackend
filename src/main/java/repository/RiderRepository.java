package repository;

import model.Riders;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;


public class RiderRepository {

    public void saveRiders(Riders rider){

        Transaction transaction = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.persist(rider);
            transaction.commit();
            System.out.println("Success! Rider Saved in database: " + rider.getRiderName());
        } catch (Exception e) {
            if(transaction != null){
                transaction.rollback();
            }
            System.out.println("Error occured during saving data of Rider:");
            e.printStackTrace();
        }
    }

    public Riders getRiderById(int id){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            return  session.find(Riders.class, id);
        }
    }
}
