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
        Course temp = new Course();
        temp.setSubjectId("MH001");
        temp.setGvlt("Pham Nguyen Cuong");
        temp.setRoom("G202");
        temp.setWeekday(7);
        temp.setShift(1);
        temp.setSlot(100);
        temp.setSemesterId(1);
        CourseDAO.addCourse(temp);

	    List<Course> t = CourseDAO.getAllCourse();
        for (Course i : t)
            System.out.println(i.toString());

        /*login myApp = new login();
        myApp.setVisible(true);*/
    }
}
