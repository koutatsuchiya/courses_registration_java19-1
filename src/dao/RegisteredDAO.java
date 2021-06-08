package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import pojo.Registered;
import utils.HibernateUtil;

import java.util.List;

public class RegisteredDAO 
{
    public static List<Registered> getAllRegistered()
    {
        //open session
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Registered> rgs = null;
        try {
            //create query
            final String hql = "select rg from Registered rg";
            Query query = session.createQuery(hql);
            //get all Registered
            rgs = query.list();
        } catch (HibernateException e)
        {
            System.err.println(e);
        } finally
        {
            session.close();
        }
        return rgs;
    }

    public static Registered getRegistered(int rg_id)
    {
        Registered rg = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            rg = (Registered) session.get(Registered.class, rg_id);
        } catch (HibernateException e)
        {
            System.err.println(e);
        } finally
        {
            session.close();
        }
        return rg;
    }

    public static boolean addRegistered(Registered rg)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if(!Integer.toString(rg.getId()).equals(""))
            if(getRegistered(rg.getId()) != null)
                return false;
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(rg);
            transaction.commit();
        } catch (HibernateException e)
        {
            transaction.rollback();
            System.err.println(e);
        } finally
        {
            session.close();
        }
        return true;
    }

    public static boolean updateRegistered(Registered rg)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if(getRegistered(rg.getId()) == null)
            return false;
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(rg);
            transaction.commit();
        } catch (HibernateException e)
        {
            transaction.rollback();
            System.err.println(e);
        } finally
        {
            session.close();
        }
        return true;
    }

    public static boolean deleteRegistered(int rg_id)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Registered rg = getRegistered(rg_id);
        if (rg == null)
            return false;
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(rg);
            transaction.commit();
        } catch (HibernateException e)
        {
            transaction.rollback();
            System.err.println(e);
        } finally
        {
            session.close();
        }
        return true;
    }
}
