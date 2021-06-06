package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import pojo.Semester;
import utils.HibernateUtil;

import java.sql.Date;
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

    public static boolean anyCurrent()
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        int n = 0;
        try {
            final String hql = "select count(*) from Semester sm where sm.current = true";
            Query query = session.createQuery(hql);
            n = Integer.valueOf(query.uniqueResult().toString());
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            session.close();
        }
        return n > 0;
    }

    public static boolean addSemester(Semester sm)
    {
        if(sm.isCurrent() && anyCurrent())
            return false;
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
        if(sm.isCurrent() && anyCurrent())
            return false;
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

    public static Semester getSemesterFromDate(Date d1, Date d2)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Semester temp = null;
        try {
            final String hql = "select sm from Semester sm where sm.dayStart = :d1 and sm.dayEnd = :d2";
            Query query = session.createQuery(hql);
            query.setParameter("d1", d1);
            query.setParameter("d2", d2);
            temp = (Semester) query.uniqueResult();
        } catch (HibernateException e)
        {
            System.err.println(e);
        } finally
        {
            session.close();
        }
        return temp;
    }

    public static Semester getCurrent()
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Semester temp = null;
        try {
            final String hql = "select sm from Semester sm where sm.current = true";
            Query query = session.createQuery(hql);
            temp = (Semester) query.uniqueResult();
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            session.close();
        }
        return temp;
    }
}
