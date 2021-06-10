package frame;

import pojo.*;
import dao.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.List;

public class stFunction extends JFrame
{
    private JPanel mainPane;
    private JTabbedPane stTab;
    private JTable rgTable;
    private JButton rgBut;
    private JPanel spacingPane1;
    private JScrollPane registerScroll;
    private JPanel registerPane;
    private JPanel coursesPane;
    private JTable rcTable;
    private JButton dBut;
    private JPanel spacingPane2;
    private JScrollPane seeScroll;
    private JTextField idText;
    private JLabel idLabel;
    private JTextField mssvText;
    private JLabel mssvLabel;
    private JTextField nameText;
    private JButton logOutBut;
    private JLabel nameLabel;
    private JLabel session_open;
    private JLabel rgLabel;
    private JLabel in4Label;

    public stFunction(Student acc)
    {
        super("Student Window");
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPane);
        this.pack();
        this.setBounds(250, 150, 1000, 500);

        //CUSTOM FOR STUDENT ACC INFORMATION
        idText.setText(String.valueOf(acc.getId()));
        mssvText.setText(acc.getMssv());
        nameText.setText(acc.getName());
        logOutBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new login();
                dispose();
            }
        });
        //SET UP
        RegisterSession now_rs = RegisterSessionDAO.currentlyInRegisterSession();
        if(now_rs != null)
            session_open.setText("Register duration: " + now_rs.getDayStart() + " to " + now_rs.getDayEnd());
        else
            session_open.setText("Register duration: none");

        DefaultTableModel registerTable = new DefaultTableModel(null, new String[]{"id", "subject id", "subject name", "credits", "gvlt", "room", "weekday", "shift", "slot", "semester name", "year"}){
            public boolean isCellEditable(int row, int column){ return false; }
        };
        rgTable.setModel(registerTable);
        DefaultTableModel course_registeredTable = new DefaultTableModel(null, new String[]{"id", "subject id", "subject name", "credits", "gvlt", "room", "weekday", "shift", "slot", "semester name", "year"}){
            public boolean isCellEditable(int row, int column){ return false; }
        };
        rcTable.setModel(course_registeredTable);

        //CAU 10: DKHP--------------------------------------------------------------------------------------
        List<Course> crs = CourseDAO.getAllCourse();
        for (Course i : crs)
            if(RegisteredDAO.getUniqueRegistered(acc.getId(), i.getId()) == null)
            {
                Subject t1 = SubjectDAO.getSubject(i.getSubjectId());
                Semester t2 = SemesterDAO.getSemester(i.getSemesterId());
                registerTable.addRow(new Object[]{i.getId(), t1.getId(), t1.getName(), t1.getCredits(), i.getGvlt(), i.getRoom(), i.getWeekday(), i.getShift(), i.getSlot(), t2.getName(), t2.getYear()});
            }

        ListSelectionModel listSelectionModel1 = rgTable.getSelectionModel();
        listSelectionModel1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listSelectionModel1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                rgBut.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(now_rs == null)
                            return;
                        int crs_id = (Integer)registerTable.getValueAt(rgTable.getSelectedRow(), 0);
                        String sj_id = registerTable.getValueAt(rgTable.getSelectedRow(), 1).toString();
                        String sj_name = registerTable.getValueAt(rgTable.getSelectedRow(), 2).toString();
                        String sj_cre = registerTable.getValueAt(rgTable.getSelectedRow(), 3).toString();
                        int sm_name = (Integer)registerTable.getValueAt(rgTable.getSelectedRow(), 9);
                        int sm_year = (Integer)registerTable.getValueAt(rgTable.getSelectedRow(), 10);
                        Registered temp = new Registered();
                        temp.setCourseId(crs_id);
                        temp.setStudentId(acc.getId());
                        Course t_crs = CourseDAO.getCourse(crs_id);
                        if(RegisteredDAO.takenSlot(t_crs.getId()) >= t_crs.getSlot())
                            return;
                        else if(RegisteredDAO.isTimeAlreadyTaken(acc.getId(), t_crs.getSemesterId(), t_crs.getWeekday(), t_crs.getShift()))
                            return;
                        else if(RegisteredDAO.addRegistered(temp))
                        {
                            //only can register max 8 course with different subject and time in current semester
                            course_registeredTable.addRow(new Object[]{t_crs.getId(), sj_id, sj_name, sj_cre, t_crs.getGvlt(), t_crs.getRoom(), t_crs.getWeekday(), t_crs.getShift(), t_crs.getSlot(), sm_name, sm_year});
                            registerTable.removeRow(rgTable.getSelectedRow());
                        }
                        rgLabel.setText("");
                    }
                });
            }
        });
        rgTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int n = RegisteredDAO.takenSlot((Integer)rgTable.getValueAt(rgTable.getSelectedRow(), 0));
                rgLabel.setText(String.valueOf(n) + ".");
            }
        });

        //CAU 11: DS CAC MON DA DK-------------------------------------------------------------------------------
        List<Registered> rc = RegisteredDAO.getSpecificRegistered(acc.getId());
        for (Registered i : rc) {
            Course t_crs = CourseDAO.getCourse(i.getCourseId());
            Subject t1 = SubjectDAO.getSubject(t_crs.getSubjectId());
            Semester t2 = SemesterDAO.getSemester(t_crs.getSemesterId());
            course_registeredTable.addRow(new Object[]{t_crs.getId(), t1.getId(), t1.getName(), t1.getCredits(), t_crs.getGvlt(), t_crs.getRoom(), t_crs.getWeekday(), t_crs.getShift(), t_crs.getSlot(), t2.getName(), t2.getYear()});
        }

        //CAU 12: XOA HP NEU CON HAN--------------------------------------------------------------------------------
        ListSelectionModel listSelectionModel2 = rcTable.getSelectionModel();
        listSelectionModel2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listSelectionModel2.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                dBut.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(now_rs == null)
                            return;
                        int crs_id = (Integer)course_registeredTable.getValueAt(rcTable.getSelectedRow(), 0);
                        String sj_id = course_registeredTable.getValueAt(rcTable.getSelectedRow(), 1).toString();
                        String sj_name = course_registeredTable.getValueAt(rcTable.getSelectedRow(), 2).toString();
                        String sj_cre = course_registeredTable.getValueAt(rcTable.getSelectedRow(), 3).toString();
                        int sm_name = (Integer)course_registeredTable.getValueAt(rcTable.getSelectedRow(), 9);
                        int sm_year = (Integer)course_registeredTable.getValueAt(rcTable.getSelectedRow(), 10);

                        RegisteredDAO.deleteRegistered(RegisteredDAO.getUniqueRegistered(acc.getId(), crs_id).getId());
                        Course t_crs = CourseDAO.getCourse(crs_id);
                        registerTable.addRow(new Object[]{t_crs.getId(), sj_id, sj_name, sj_cre, t_crs.getGvlt(), t_crs.getRoom(), t_crs.getWeekday(), t_crs.getShift(), t_crs.getSlot(), sm_name, sm_year});
                        course_registeredTable.removeRow(rcTable.getSelectedRow());
                        JOptionPane.showMessageDialog(null, "Successfully unregistered this course!");
                    }
                });
            }
        });
    }
}
