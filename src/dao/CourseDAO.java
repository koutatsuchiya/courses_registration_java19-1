package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import pojo.Course;
import pojo.GiaoVu;
import pojo.Semester;
import pojo.Subject;
import utils.HibernateUtil;

import java.util.ArrayList;
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
        if(!Integer.toString(course.getId()).equals(""))
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

    public static Course getUniqueCourse(String sj_id, int crs_wd, int crs_shf, int sm_id)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String id1 = null;
        String id2 = null;
        Course temp = null;
        try {
            final String hql1 = "select sj.id from Subject sj where sj.id = :subject_id";
            Query query1 = session.createQuery(hql1);
            query1.setParameter("subject_id", sj_id);
            final String hql2 = "select sm.id from Semester sm where sm.id = :semester_id";
            Query query2 = session.createQuery(hql2);
            query2.setParameter("semester_id", sm_id);
            //get the ids to check if course exists
            id1 = (String)query1.uniqueResult();
            id2 = query2.uniqueResult().toString();
            if(id1 == null || id2 == null)
                return null;
            //if it exist then get the information
            final String hql3 = "select crs from Course crs where crs.subjectId = :sj_id and crs.weekday = :crs_wd and crs.shift = :crs_shf and crs.semesterId = :sm_id";
            Query query3 = session.createQuery(hql3);
            query3.setParameter("sj_id", sj_id);
            query3.setParameter("crs_wd", crs_wd);
            query3.setParameter("crs_shf", crs_shf);
            query3.setParameter("sm_id", sm_id);
            //get course
            temp = (Course) query3.uniqueResult();
        } catch (HibernateException e) {
            System.err.println(e);
        } finally {
            session.close();
        }
        return temp;
    }

    public static boolean isOpen(int crs_id)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        int n = 0;
        try {
            final String hql = "select count(*) from Course crs, Semester sm where crs.semesterId = sm.id and crs.id = :crs_id and sm.current = true";
            Query query = session.createQuery(hql);
            query.setParameter("crs_id", crs_id);
            n = Integer.valueOf(query.uniqueResult().toString());
        } catch (HibernateException e) {
            System.err.println(e);
        } finally {
            session.close();
        }
        return n > 0;
    }

    public static List<Course> getCoursesFromString(String val)
    {
        List<Course> crs = getAllCourse();
        List<Course> temp = new ArrayList<>();
        String t = val.toLowerCase();
        for (Course i : crs) {
            Subject t1 = SubjectDAO.getSubject(i.getSubjectId());
            Semester t2 = SemesterDAO.getSemester(i.getSemesterId());
            if (String.valueOf(i.getId()).contains(t) || t1.getId().toLowerCase().contains(t) || t1.getName().toLowerCase().contains(t) || String.valueOf(t1.getCredits()).contains(t) ||
                i.getGvlt().toLowerCase().contains(t) || i.getRoom().toLowerCase().contains(t) || String.valueOf(i.getWeekday()).contains(t) || String.valueOf(i.getShift()).contains(t) ||
                String.valueOf(i.getSlot()).contains(t) || String.valueOf(t2.getName()).contains(t) || String.valueOf(t2.getYear()).contains(t))
                temp.add(i);
        }
        return temp;
    }
}
