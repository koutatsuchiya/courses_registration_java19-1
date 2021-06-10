package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import pojo.Subject;
import utils.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class SubjectDAO 
{
    public static List<Subject> getAllSubject()
    {
        //open session
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Subject> subjects = null;
        try {
            //create query
            final String hql = "select sj from Subject sj";
            Query query = session.createQuery(hql);
            //get all Subject
            subjects = query.list();
        } catch (HibernateException e)
        {
            System.err.println(e);
        } finally
        {
            session.close();
        }
        return subjects;
    }

    public static Subject getSubject(String sj_id)
    {
        Subject sj = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            sj = (Subject) session.get(Subject.class, sj_id);
        } catch (HibernateException e)
        {
            System.err.println(e);
        } finally
        {
            session.close();
        }
        return sj;
    }

    public static boolean addSubject(Subject sj)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if(getSubject(sj.getId()) != null)
            return false;
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(sj);
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

    public static boolean updateSubject(Subject sj)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if(getSubject(sj.getId()) == null)
            return false;
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(sj);
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

    public static boolean deleteSubject(String sj_id)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Subject sj = getSubject(sj_id);
        if (sj == null)
            return false;
        CourseDAO.deleteCourseWithSubject(sj_id);
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(sj);
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

    public static List<Subject> getSubjectsFromString(String val)
    {
        List<Subject> sjs = SubjectDAO.getAllSubject();
        List<Subject> temp = new ArrayList<>();
        String t = val.toLowerCase();
        for(Subject i : sjs)
            if(i.getId().toLowerCase().contains(t) || i.getName().toLowerCase().contains(t) || String.valueOf(i.getCredits()).contains(t))
                temp.add(i);
        return temp;
    }
}
