import dao.*;
import pojo.*;
import frame.*;

/*hibernate cfg
<property name="connection.username">postgres</property>
<property name="connection.password">lehuudung01</property>
<generator class="increment"/>*/

import java.sql.Date;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        List<GiaoVu> t = GiaoVuDAO.getAllGiaoVu();
        for (GiaoVu i : t)
            System.out.println(i.toString());

        /*new login();

        /*new gvFunction();*/
    }
}
