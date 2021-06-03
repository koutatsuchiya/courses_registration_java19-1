package utils;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil
{
    private static SessionFactory sessionFactory;

    static {
        try {
            //set sf
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (HibernateException e)
        {
            throw e;
        }
    }

    public static SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }
}
