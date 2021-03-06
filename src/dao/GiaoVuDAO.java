package dao;

import pojo.GiaoVu;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GiaoVuDAO
{
    public static List<GiaoVu> getAllGiaoVu()
    {
        //open session
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<GiaoVu> giaoVus = null;
        try {
            //create query
            final String hql = "select gv from GiaoVu gv";
            Query query = session.createQuery(hql);
            //get all giaovu
            giaoVus = query.list();
        } catch (HibernateException e)
        {
            System.err.println(e);
        } finally
        {
            session.close();
        }
        return giaoVus;
    }

    public static GiaoVu getGiaoVu(int gv_id)
    {
        GiaoVu gv = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            gv = (GiaoVu) session.get(GiaoVu.class, gv_id);
        } catch (HibernateException e)
        {
            System.err.println(e);
        } finally
        {
            session.close();
        }
        return gv;
    }

    public static boolean addGiaoVu(GiaoVu gv)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if(!Integer.toString(gv.getId()).equals(""))
            if(getGiaoVu(gv.getId()) != null)
                return false;
        gv.setPassword("12345678");
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(gv);
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

    public static boolean updateGiaoVu(GiaoVu gv)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if(getGiaoVu(gv.getId()) == null)
            return false;
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(gv);
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

    public static boolean deleteGiaoVu(int gv_id)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        GiaoVu gv = getGiaoVu(gv_id);
        if (gv == null)
            return false;
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(gv);
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

    //get the recently add of that name
    public static GiaoVu getGiaoVuFromNameRecentlyAdded(String gv_name)
    {
        List<GiaoVu> gvs = getAllGiaoVu();
        List<GiaoVu> temp = new ArrayList<>();
        for (GiaoVu i : gvs)
            if(gv_name.equals(i.getName()))
                temp.add(i);
        GiaoVu maxId = null;
        if(temp.size() >= 1)
            for (GiaoVu i : temp)
                if(maxId == null)
                    maxId = i;
                else if(maxId.getId() < i.getId())
                    maxId = i;
        return maxId;
    }

    public static List<GiaoVu> getGiaoVusFromString(String val)
    {
        List<GiaoVu> gvs = getAllGiaoVu();
        List<GiaoVu> temp = new ArrayList<>();
        String t = val.toLowerCase();
        for (GiaoVu i : gvs)
            if(String.valueOf(i.getId()).contains(t) || i.getName().toLowerCase().contains(t))
                temp.add(i);
        return temp;
    }
}
