package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import pojo.Semester;
import utils.HibernateUtil;

import java.util.List;

public class SemesterDAO
{
    public static List<Semester> getAllSemester()
    {
        //open session
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Semester> semesters = null;
        try {
            //create query
            final String hql = "select sm from Semester sm";
            Query query = session.createQuery(hql);
            //get all Semester
            semesters = query.list();
        } catch (HibernateException e)
        {
            System.err.println(e);
        } finally
        {
            session.close();
        }
        return semesters;
    }

    public static Semester getSemester(int sm_id)
    {
        Semester sm = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            sm = (Semester) session.get(Semester.class, sm_id);
        } catch (HibernateException e)
        {
            System.err.println(e);
        } finally
        {
            session.close();
        }
        return sm;
    }

    public static boolean addSemester(Semester sm)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if(Integer.toString(sm.getId()) != null)
            if(getSemester(sm.getId()) != null)
                return false;
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(sm);
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

    public static boolean updateSemester(Semester sm)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if(getSemester(sm.getId()) == null)
            return false;
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(sm);
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

    public static boolean deleteSemester(int sm_id)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Semester sm = getSemester(sm_id);
        if (sm == null)
            return false;
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(sm);
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
