package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import pojo.Course;
import utils.HibernateUtil;

import java.util.List;

public class CourseDAO 
{
    public static List<Course> getAllCourse()
    {
        //open session
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Course> Courses = null;
        try {
            //create query
            final String hql = "select course from Course course";
            Query query = session.createQuery(hql);
            //get all Course
            Courses = query.list();
        } catch (HibernateException e)
        {
            System.err.println(e);
        } finally
        {
            session.close();
        }
        return Courses;
    }

    public static Course getCourse(int course_id)
    {
        Course course = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            course = (Course) session.get(Course.class, course_id);
        } catch (HibernateException e)
        {
            System.err.println(e);
        } finally
        {
            session.close();
        }
        return course;
    }

    public static boolean isExist(Course course)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String id1 = null;
        String id2 = null;
        try {
            final String hql1 = "select sj.id from Subject sj where sj.id = :subject_id";
            Query query1 = session.createQuery(hql1);
            query1.setParameter("subject_id", course.getSubjectId());
            final String hql2 = "select sm.id from Semester sm where sm.id = :semester_id";
            Query query2 = session.createQuery(hql2);
            query2.setParameter("semester_id", course.getSemesterId());
            //get the ids
            id1 = (String)query1.uniqueResult();
            id2 = query2.uniqueResult().toString();
            if(id1 == null || id2 == null)
                return false;
        } catch (HibernateException e)
        {
            System.err.println(e);
            return false;
        } finally
        {
            session.close();
        }
        return true;
    }

    public static boolean addCourse(Course course)
    {
        if(!isExist(course))
            return false;
        //add
        Session session = HibernateUtil.getSessionFactory().openSession();
        if(Integer.toString(course.getId()) != null)
            if(getCourse(course.getId()) != null)
                return false;
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(course);
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

    public static boolean updateCourse(Course course)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if(getCourse(course.getId()) == null)
            return false;
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(course);
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

    public static boolean deleteCourse(int course_id)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Course course = getCourse(course_id);
        if (course == null)
            return false;
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(course);
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
