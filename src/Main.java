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
        String s = "2021-3-10";
        String e = "2021-7-15";

        SemesterDAO.addSemester(temp);
	    List<Semester> t = SemesterDAO.getAllSemester();
        for (Semester i : t)
            System.out.println(i.toString());

        /*login myApp = new login();
        myApp.setVisible(true);*/
    }
}
