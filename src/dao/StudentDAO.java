package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import pojo.Student;
import utils.HibernateUtil;

import java.util.List;

public class StudentDAO
{
    public static List<Student> getAllStudent()
    {
        //open session
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Student> students = null;
        try {
            //create query
            final String hql = "select st from Student st";
            Query query = session.createQuery(hql);
            //get all Student
            students = query.list();
        } catch (HibernateException e)
        {
            System.err.println(e);
        } finally {
            session.close();
        }
        return students;
    }

    public static Student getStudent(int st_id)
    {
        Student st = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            st = (Student) session.get(Student.class, st_id);
        } catch (HibernateException e)
        {
            System.err.println(e);
        } finally
        {
            session.close();
        }
        return st;
    }

    public static boolean addStudent(Student st)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if(Integer.toString(st.getId()) != null)
            if(getStudent(st.getId()) != null)
                return false;
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(st);
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

    public static boolean updateStudent(Student st)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if(getStudent(st.getId()) == null)
            return false;
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(st);
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

    public static boolean deleteStudent(int st_id)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Student st = getStudent(st_id);
        if (st == null)
            return false;
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(st);
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

    public static Student getStudentFromMssv(String st_mssv)
    {
        //open session
        Session session = HibernateUtil.getSessionFactory().openSession();
        Student st = null;
        try {
            //create query
            final String hql = "select st from Student st where st.mssv = :st_mssv";
            Query query = session.createQuery(hql);
            query.setParameter("st_mssv", st_mssv);
            //get all Student
            st = (Student)query.uniqueResult();
        } catch (HibernateException e)
        {
            System.err.println(e);
        } finally {
            session.close();
        }
        return st;
    }
}
