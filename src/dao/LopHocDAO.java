package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import pojo.LopHoc;
import utils.HibernateUtil;

import java.util.List;

public class LopHocDAO
{
    public static List<LopHoc> getAllLopHoc()
    {
        //open session
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<LopHoc> lopHocs = null;
        try {
            //create query
            final String hql = "select lh from LopHoc lh";
            Query query = session.createQuery(hql);
            //get all LopHoc
            lopHocs = query.list();
        } catch (HibernateException e)
        {
            System.err.println(e);
        } finally
        {
            session.close();
        }
        return lopHocs;
    }

    public static LopHoc getLopHoc(int lh_id)
    {
        LopHoc lh = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            lh = (LopHoc) session.get(LopHoc.class, lh_id);
        } catch (HibernateException e)
        {
            System.err.println(e);
        } finally
        {
            session.close();
        }
        return lh;
    }

    public static boolean addLopHoc(LopHoc lh)
    {
        //add
        Session session = HibernateUtil.getSessionFactory().openSession();
        if(!Integer.toString(lh.getId()).equals(""))
            if(getLopHoc(lh.getId()) != null)
                return false;
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(lh);
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

    public static boolean updateLopHoc(LopHoc lh)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if(getLopHoc(lh.getId()) == null)
            return false;
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(lh);
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

    public static boolean deleteLopHoc(int lh_id)
    {
        if(countMale(lh_id) + countFemale(lh_id) > 0)
            return false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        LopHoc lh = getLopHoc(lh_id);
        if (lh == null)
            return false;
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(lh);
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

    public static int countMale(int lh_id)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        int n = 0;
        try {
            final String hql = "select count(*) from LopHoc lh, Student st where lh.id = :lh_id and lh.id = st.classId.id and st.gender = 'Nam'";
            Query query = session.createQuery(hql);
            query.setParameter("lh_id", lh_id);
            n = Integer.valueOf(query.uniqueResult().toString());
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            session.close();
        }
        return n;
    }

    public static int countFemale(int lh_id)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        int n = 0;
        try {
            final String hql = "select count(*) from LopHoc lh, Student st where lh.id = :lh_id and lh.id = st.classId.id and st.gender = 'Nu'";
            Query query = session.createQuery(hql);
            query.setParameter("lh_id", lh_id);
            n = Integer.valueOf(query.uniqueResult().toString());
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            session.close();
        }
        return n;
    }

    public static LopHoc getLopHocFromName(String lh_name)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        LopHoc temp = null;
        try {
            final String hql = "select lh from LopHoc lh where lh.name = :lh_name";
            Query query = session.createQuery(hql);
            query.setParameter("lh_name", lh_name);
            temp = (LopHoc) query.uniqueResult();
        } catch (HibernateException e) {
            System.err.println(e);
        } finally {
            session.close();
        }
        return temp;
    }
}