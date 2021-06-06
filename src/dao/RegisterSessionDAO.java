package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import pojo.RegisterSession;
import pojo.Semester;
import utils.HibernateUtil;

import java.sql.Date;
import java.util.List;

public class RegisterSessionDAO
{
    public static List<RegisterSession> getAllRegisterSession()
    {
        //open session
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<RegisterSession> registerSessions = null;
        try {
            //create query
            final String hql = "select st from RegisterSession st";
            Query query = session.createQuery(hql);
            //get all RegisterSession
            registerSessions = query.list();
        } catch (HibernateException e)
        {
            System.err.println(e);
        } finally {
            session.close();
        }
        return registerSessions;
    }

    public static RegisterSession getRegisterSession(int rs_id)
    {
        RegisterSession rs = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            rs = (RegisterSession) session.get(RegisterSession.class, rs_id);
        } catch (HibernateException e)
        {
            System.err.println(e);
        } finally
        {
            session.close();
        }
        return rs;
    }

    public static boolean addRegisterSession(RegisterSession rs)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if(Integer.toString(rs.getId()) != null)
            if(getRegisterSession(rs.getId()) != null)
                return false;
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(rs);
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

    public static boolean updateRegisterSession(RegisterSession rs)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if(getRegisterSession(rs.getId()) == null)
            return false;
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(rs);
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

    public static boolean deleteRegisterSession(int rs_id)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        RegisterSession rs = getRegisterSession(rs_id);
        if (rs == null)
            return false;
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(rs);
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

    public static RegisterSession getRegisterSessionFromDate(Date d1, Date d2)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        RegisterSession temp = null;
        try {
            final String hql = "select ss from RegisterSession ss where ss.dayStart = :d1 and ss.dayEnd = :d2";
            Query query = session.createQuery(hql);
            query.setParameter("d1", d1);
            query.setParameter("d2", d2);
            temp = (RegisterSession) query.uniqueResult();
        } catch (HibernateException e)
        {
            System.err.println(e);
        } finally
        {
            session.close();
        }
        return temp;
    }
}
