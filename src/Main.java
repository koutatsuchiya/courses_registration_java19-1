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
        /*GiaoVu temp = new GiaoVu();
        temp.setName("PNST");
        GiaoVuDAO.addGiaoVu(temp);*/

        gvFunction myApp = new gvFunction();
        myApp.setVisible(true);
    }
}
