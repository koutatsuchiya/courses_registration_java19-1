package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import pojo.RegisterSession;
import pojo.Registered;
import utils.HibernateUtil;

import java.sql.Date;
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
        } catch (HibernateException e) {
            System.err.println(e);
        } finally {
            session.close();
        }
        return rg;
    }

    public static boolean less8CourseInCurSemester(int st_id)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        int n = 0;
        try {
            final String hql = "select count(*) from Registered rg, Course crs, Semester sm where rg.studentId = :st_id and rg.courseId = crs.id and crs.semesterId = sm.id and sm.current = true";
            Query query = session.createQuery(hql);
            query.setParameter("st_id", st_id);
            n = Integer.valueOf(query.uniqueResult().toString());
        } catch (HibernateException e) {
            System.err.println(e);
        } finally {
            session.close();
        }
        return n < 8;
    }

    public static boolean subjectAlreadyRegisteredInCurSemester(int st_id, String sj_id)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        int n = 0;
        try {
            final String hql = "select count(*) from Registered rg, Course crs, Semester sm where rg.studentId = :st_id and rg.courseId = crs.id and crs.subjectId = :sj_id and crs.semesterId = sm.id and sm.current = true";
            Query query = session.createQuery(hql);
            query.setParameter("st_id", st_id);
            query.setParameter("sj_id", sj_id);
            n = Integer.valueOf(query.uniqueResult().toString());
        } catch (HibernateException e) {
            System.err.println(e);
        } finally {
            session.close();
        }
        return n == 1;
    }

    public static boolean isTimeAlreadyTaken(int st_id, int sm_id, int crs_wd, int crs_shf)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        int n = 0;
        try {
            final String hql = "select count(*) from Registered rg, Course crs where rg.studentId = :st_id and rg.courseId = crs.id and crs.semesterId = :sm_id and crs.weekday = :crs_wd and crs.shift = :crs_shf";
            Query query = session.createQuery(hql);
            query.setParameter("st_id", st_id);
            query.setParameter("sm_id", sm_id);
            query.setParameter("crs_wd", crs_wd);
            query.setParameter("crs_shf", crs_shf);
            n = Integer.valueOf(query.uniqueResult().toString());
        } catch (HibernateException e) {
            System.err.println(e);
        } finally {
            session.close();
        }
        return n > 0;
    }

    public static int takenSlot(int crs_id)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        int n = 0;
        try {
            final String hql = "select count(*) from Registered rg where rg.courseId = :crs_id";
            Query query = session.createQuery(hql);
            query.setParameter("crs_id", crs_id);
            n = Integer.valueOf(query.uniqueResult().toString());
        } catch (HibernateException e) {
            System.err.println(e);
        } finally {
            session.close();
        }
        return n;
    }

    public static boolean addRegistered(Registered rg)
    {
        if(!less8CourseInCurSemester(rg.getStudentId()))
            return false;
        if(subjectAlreadyRegisteredInCurSemester(rg.getStudentId(), SubjectDAO.getSubject(CourseDAO.getCourse(rg.getCourseId()).getSubjectId()).getId()))
            return false;
        rg.setDateEnroll(new Date(System.currentTimeMillis()));
        Session session = HibernateUtil.getSessionFactory().openSession();
        if(!Integer.toString(rg.getId()).equals(""))
            if(getRegistered(rg.getId()) != null)
                return false;
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(rg);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            System.err.println(e);
        } finally {
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

    public static Registered getUniqueRegistered(int st_id, int crs_id)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Registered rg = null;
        try {
            final String hql = "select rg from Registered rg where rg.studentId = :st_id and rg.courseId = :crs_id";
            Query query = session.createQuery(hql);
            query.setParameter("st_id", st_id);
            query.setParameter("crs_id", crs_id);
            rg = (Registered) query.uniqueResult();
        } catch (HibernateException e)
        {
            System.err.println(e);
        } finally
        {
            session.close();
        }
        return rg;
    }

    public static List<Registered> getSpecificRegistered(int st_id)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Registered> rgs = null;
        try {
            final String hql = "select rg from Registered rg where rg.studentId = :st_id";
            Query query = session.createQuery(hql);
            query.setParameter("st_id", st_id);
            rgs = query.list();
        } catch (HibernateException e) {
            System.err.println(e);
        } finally {
            session.close();
        }
        return rgs;
    }

    public static List<Registered> getCourseRegisteredList(int crs_id)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Registered> rgs = null;
        try {
            final String hql = "select rg from Registered rg where rg.courseId = :crs_id";
            Query query = session.createQuery(hql);
            query.setParameter("crs_id", crs_id);
            rgs = query.list();
        } catch (HibernateException e) {
            System.err.println(e);
        } finally {
            session.close();
        }
        return rgs;
    }

    public static void deleteRegisteredStudent(int st_id)
    {
        List<Registered> rgs = getSpecificRegistered(st_id);
        for(Registered i : rgs)
            deleteRegistered(i.getId());
    }

    public static void deleteRegisteredCourse(int crs_id)
    {
        List<Registered> rgs = getCourseRegisteredList(crs_id);
        for(Registered i : rgs)
            deleteRegistered(i.getId());
    }
}
