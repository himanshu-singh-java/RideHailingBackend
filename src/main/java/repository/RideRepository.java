package repository;

import model.Rides;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

public class RideRepository {

    public void saveRide(Rides rides){

        Transaction transaction = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.persist(rides);
            transaction.commit();
            System.out.println("Success! Ride saved in database!");
        } catch (Exception e) {
            if(transaction != null){
                transaction.rollback();
            }
            System.out.println("Error occurred during saving data of Ride:");
            e.printStackTrace();
        }
    }

    public Rides getRideById(int id){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            return session.find(Rides.class, id);
        }
    }

    public void updateRide(Rides ride){
        Transaction transaction = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();

            session.merge(ride);

            transaction.commit();
            System.out.println("Success! Ride updated in database!");
        }
        catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            System.out.println("Error occurred during updating data of Ride:");
            e.printStackTrace();
        }

    }
}
