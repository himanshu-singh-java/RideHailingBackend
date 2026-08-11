package repository;

import model.VehicleStatus;
import model.Vehicles;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import util.HibernateUtil;

public class VehicleRepository {

    public void saveVehicle(Vehicles vehicles){

        Transaction transaction = null;
        Session session = null;

        try{
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.persist(vehicles);
            transaction.commit();
            System.out.println("Success! Vehicle saved in database for Driver : " + vehicles.getDriverName());
        }
        catch (Exception e){
            if(transaction != null && transaction.isActive()){
                transaction.rollback();
            }
            System.out.println("Error occurred in saving data of Vehicle");
            e.printStackTrace();
        }
        finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
    }

    public Vehicles getVehicleById(int id){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            return session.find(Vehicles.class, id);
        }
    }

    public Vehicles findAvailableVehicle(String vehicleType){

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            String query = "FROM " + vehicleType + " v WHERE v.vehicleStatus = :status";

            return session.createQuery(query, Vehicles.class)
                    .setParameter("status", VehicleStatus.AVAILABLE)
                    .setMaxResults(1)
                    .uniqueResult();
        }
        catch (Exception e){
            System.out.println("Error finding available vehicle!");
            e.printStackTrace();
            return null;
        }
    }
}
