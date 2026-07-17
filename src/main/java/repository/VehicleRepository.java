package repository;

import model.Vehicles;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

public class VehicleRepository {

    public void saveVehicle(Vehicles vehicles){

        Transaction transaction = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.persist(vehicles);
            transaction.commit();
            System.out.println("Success! Vehicle saved in database for Driver : " + vehicles.getDriverName());
        }
        catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            System.out.println("Error occurred in saving data of Vehicle");
            e.printStackTrace();
        }
    }

    public Vehicles getVehicleById(int id){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            return session.find(Vehicles.class, id);
        }
    }
}
