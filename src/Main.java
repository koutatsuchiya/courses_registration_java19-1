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

        Semester temp = new Semester();
        temp.setName(2);
        temp.setYear(2021);
        temp.setDayStart(Date.valueOf(s));
        temp.setDayEnd(Date.valueOf(e));
        temp.setCurrent(true);
        SemesterDAO.addSemester(temp);
	    List<Semester> t = SemesterDAO.getAllSemester();
        for (Semester i : t)
            System.out.println(i.toString());

        /*login myApp = new login();
        myApp.setVisible(true);*/
    }
}
