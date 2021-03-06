package frame;

import dao.*;
import pojo.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

public class gvFunction extends JFrame
{
    private JPanel mainPane;
    private JTabbedPane gvTab;
    private JTable gvTable;
    private JButton deleteBut1;
    private JButton addBut1;
    private JButton updateBut1;
    private JScrollPane gvScroll;
    private JPanel giaoVuPane;
    private JPanel gvForm;
    private JTextField gv_nameText;
    private JLabel gv_nameLabel;
    private JTextField findText1;
    private JButton findBut1;
    private JButton resetBut1;
    private JTable sjTable;
    private JTextField findText2;
    private JButton findBut2;
    private JButton deleteBut2;
    private JButton addBut2;
    private JButton updateBut2;
    private JPanel subjectForm;
    private JButton resetBut2;
    private JPanel subjectPane;
    private JScrollPane subjectScroll;
    private JTextField sj_idText;
    private JTextField sj_nameText;
    private JTextField sj_creText;
    private JLabel sj_idLabel;
    private JLabel sj_nameLabel;
    private JLabel sj_creLabel;
    private JTable smTable;
    private JButton setCurBut3;
    private JButton addBut3;
    private JButton deleteBut3;
    private JPanel semesterPane;
    private JTextField sm_yearText;
    private JLabel sm_nameLabel;
    private JLabel sm_yearLabel;
    private JLabel sm_dayStartLabel;
    private JLabel sm_dayEndLabel;
    private JLabel sm_curLabel;
    private JScrollPane smScroll;
    private JComboBox sm_day_comboBox1;
    private JComboBox sm_month_comboBox1;
    private JComboBox sm_year_comboBox1;
    private JComboBox sm_day_comboBox2;
    private JComboBox sm_month_comboBox2;
    private JComboBox sm_year_comboBox2;
    private JComboBox sm_cur_comboBox;
    private JComboBox sm_name_comboBox;
    private JPanel semesterForm;
    private JPanel classPane;
    private JTable lhTable;
    private JButton deleteBut4;
    private JButton addBut4;
    private JTextField class_nameText;
    private JPanel classForm;
    private JLabel class_nameLabel;
    private JScrollPane classScroll;
    private JButton resetBut4;
    private JPanel sessionPane;
    private JTable ssTable;
    private JButton addBut6;
    private JPanel sessionForm;
    private JComboBox ss_day_comboBox1;
    private JComboBox ss_month_comboBox1;
    private JComboBox ss_year_comboBox1;
    private JComboBox ss_day_comboBox2;
    private JComboBox ss_month_comboBox2;
    private JComboBox ss_year_comboBox2;
    private JLabel ss_dayStartLabel;
    private JLabel ss_dayEndLabel;
    private JPanel ssSpacing;
    private JScrollPane ssScroll;
    private JButton falseBut3;
    private JTable crsTable;
    private JButton resetBut7;
    private JButton deleteBut7;
    private JButton addBut7;
    private JTextField crs_sj_idText;
    private JTextField crs_gvltText;
    private JTextField crs_roomText;
    private JTextField crs_slotText;
    private JTextField crs_sm_idText;
    private JComboBox crs_weekday_comboBox;
    private JComboBox crs_shift_comboBox;
    private JTextField findText7;
    private JButton findBut7;
    private JLabel crs_sj_idLabel;
    private JLabel crs_gvltLabel;
    private JLabel crs_roomLabel;
    private JLabel crs_weekdayLabel;
    private JLabel crs_shiftLabel;
    private JLabel crs_slotLabel;
    private JLabel crs_sm_idLabel;
    private JPanel coursePane;
    private JScrollPane crsScroll;
    private JPanel crsForm;
    private JButton curBut7;
    private JTextField nameAccText;
    private JTextField idAccText;
    private JLabel idAccLabel;
    private JLabel nameAccLabel;
    private JButton resetPassBut;
    private JButton logOutBut;
    private JPanel studentPane;
    private JTable stTable;
    private JButton addBut5;
    private JButton resetBut5;
    private JScrollPane stScroll;
    private JPanel stForm;
    private JTextField mssvText5;
    private JTextField nameText5;
    private JTextField class_idText5;
    private JComboBox gender_comboBox5;
    private JLabel mssvLabel5;
    private JLabel nameLabel5;
    private JLabel genderLabel5;
    private JLabel class_idLabel5;
    private JPanel registeredPane;
    private JTable rgTable;
    private JButton seeBut8;
    private JButton resetBut8;
    private JPanel spacingPane8;
    private JScrollPane rgScroll;
    private JLabel rgLabel;

    public gvFunction(GiaoVu acc)
    {
        super("Teacher Window");
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPane);
        this.pack();
        this.setBounds(275, 150, 1000, 500);

        //SET UP Account In4
        idAccText.setText(String.valueOf(acc.getId()));
        nameAccText.setText(acc.getName());

        resetPassBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                acc.setPassword("12345678");
                GiaoVuDAO.updateGiaoVu(acc);
            }
        });
        logOutBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new login();
                dispose();
            }
        });

        //SET UP TABLES
        DefaultTableModel giaoVuTable = new DefaultTableModel(null, new String[]{"id", "name"}){
            public boolean isCellEditable(int row, int column){ return false; }
        };
        gvTable.setModel(giaoVuTable);

        DefaultTableModel subjectTable = new DefaultTableModel(null, new String[]{"id", "name", "credits"}){
            public boolean isCellEditable(int row, int column){ return false; }
        };
        sjTable.setModel(subjectTable);

        DefaultTableModel semesterTable = new DefaultTableModel(null, new String[]{"id", "name", "year", "start date", "end date", "is current"}){
            public boolean isCellEditable(int row, int column){ return false; }
        };
        smTable.setModel(semesterTable);

        DefaultTableModel lopHocTable = new DefaultTableModel(null, new String[]{"id", "name", "male", "female", "total"}){
            public boolean isCellEditable(int row, int column){ return false; }
        };
        lhTable.setModel(lopHocTable);

        DefaultTableModel StudentTable = new DefaultTableModel(null, new String[]{"id", "mssv", "name", "gender", "class"}){
            public boolean isCellEditable(int row, int column){ return false; }
        };
        stTable.setModel(StudentTable);

        DefaultTableModel registerSessionTable = new DefaultTableModel(null, new String[]{"id", "start date", "end date", "semester name", "semester year"}){
            public boolean isCellEditable(int row, int column){ return false; }
        };
        ssTable.setModel(registerSessionTable);

        DefaultTableModel courseTable = new DefaultTableModel(null, new String[]{"id", "subject id", "subject name", "credits", "gvlt", "room", "weekday", "shift", "slot", "semester name", "year"}){
            public boolean isCellEditable(int row, int column){ return false; }
        };
        crsTable.setModel(courseTable);

        DefaultTableModel registeredTable = new DefaultTableModel(null, new String[]{"id", "subject id", "subject name", "credits", "gvlt", "room", "weekday", "shift", "slot", "semester name", "year"}){
            public boolean isCellEditable(int row, int column){ return false; }
        };
        rgTable.setModel(registeredTable);

        DefaultTableModel registered_studentTable = new DefaultTableModel(null, new String[]{"mssv", "name", "course id", "subject id", "subject name", "gvlt",  "weekday", "time", "registered date"}){
            public boolean isCellEditable(int row, int column){ return false; }
        };

        //CAU 2: GV ACCOUNT-----------------------------------------------------------------------
        List<GiaoVu> gvs = GiaoVuDAO.getAllGiaoVu();
        for (GiaoVu i : gvs) {
            giaoVuTable.addRow(new Object[]{i.getId(), i.getName()});
        }

        addBut1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String t = gv_nameText.getText();
                if(t.equals("")) { return; }
                GiaoVu temp1 = new GiaoVu();
                temp1.setName(t);
                if(GiaoVuDAO.addGiaoVu(temp1))
                {
                    GiaoVu temp2 = GiaoVuDAO.getGiaoVuFromNameRecentlyAdded(t);
                    giaoVuTable.addRow(new Object[]{temp2.getId(), temp2.getName()});
                    gv_nameText.setText("");
                }
                else
                    JOptionPane.showMessageDialog(null, "Error! Cannot add giao vu account!");
            }
        });
        ListSelectionModel listSelectionModel1 = gvTable.getSelectionModel();
        listSelectionModel1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listSelectionModel1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                updateBut1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String t = gv_nameText.getText();
                        if(t.equals("")) { return; }
                        GiaoVu temp = new GiaoVu();
                        temp.setId((Integer) gvTable.getValueAt(gvTable.getSelectedRow(), 0));
                        temp.setName(t);
                        if(GiaoVuDAO.updateGiaoVu(temp))
                        {
                            gvTable.setValueAt(t, gvTable.getSelectedRow(), 1);
                            gv_nameText.setText("");
                        }
                        else
                            JOptionPane.showMessageDialog(null, "Error! Cannot update giao vu account!");
                    }
                });
                deleteBut1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //cannot delete teacher account on your own
                        if(acc.getId() == (Integer) gvTable.getValueAt(gvTable.getSelectedRow(), 0))
                            return;
                        GiaoVuDAO.deleteGiaoVu((Integer) gvTable.getValueAt(gvTable.getSelectedRow(), 0));
                        giaoVuTable.removeRow(gvTable.getSelectedRow());
                    }
                });
            }
        });
        findBut1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String t = findText1.getText();
                if(t.equals("")) { return; }
                List<GiaoVu> found = GiaoVuDAO.getGiaoVusFromString(t);
                int n_row = giaoVuTable.getRowCount();
                for(int i = n_row - 1; i >= 0; i--)
                    giaoVuTable.removeRow(i);
                for(GiaoVu i : found)
                    giaoVuTable.addRow(new Object[]{i.getId(), i.getName()});
            }
        });
        resetBut1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int n_row = giaoVuTable.getRowCount();
                for(int i = n_row - 1; i >= 0; i--)
                    giaoVuTable.removeRow(i);
                List<GiaoVu> rs = GiaoVuDAO.getAllGiaoVu();
                for(GiaoVu i : rs)
                    giaoVuTable.addRow(new Object[]{i.getId(), i.getName()});
            }
        });

        //CAU 3: MON HOC--------------------------------------------------------------------------------
        List<Subject> sjs = SubjectDAO.getAllSubject();
        for (Subject i : sjs) {
            subjectTable.addRow(new Object[]{i.getId(), i.getName(), i.getCredits()});
        }

        addBut2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] t = { sj_idText.getText(), sj_nameText.getText(), sj_creText.getText() };
                if(t[0].equals("") || t[1].equals("") || t[2].equals("")) { return; }
                Subject temp = new Subject();
                temp.setId(t[0]);
                temp.setName(t[1]);
                try {
                    temp.setCredits(Integer.valueOf(t[2]));
                } catch(NumberFormatException any_e){
                    JOptionPane.showMessageDialog(null, "Value of credits was not an integer!");
                    return;
                }
                if(SubjectDAO.addSubject(temp))
                {
                    subjectTable.addRow(new Object[]{temp.getId(), temp.getName(), temp.getCredits()});
                    sj_idText.setText("");
                    sj_nameText.setText("");
                    sj_creText.setText("");
                }
                else
                    JOptionPane.showMessageDialog(null, "Error! Cannot add this subject!");
            }
        });
        ListSelectionModel listSelectionModel2 = sjTable.getSelectionModel();
        listSelectionModel2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listSelectionModel2.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                updateBut2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String[] t = { sj_idText.getText(), sj_nameText.getText(), sj_creText.getText() };
                        if(t[0].equals("") || t[1].equals("") || t[2].equals("")) { return; }
                        Subject temp = new Subject();
                        //they can't change id
                        temp.setId(sjTable.getValueAt(sjTable.getSelectedRow(), 0).toString());
                        temp.setName(t[1]);
                        try {
                            temp.setCredits(Integer.valueOf(t[2]));
                        } catch(NumberFormatException any_e){
                            JOptionPane.showMessageDialog(null, "Value of credits was not an integer!");
                            return;
                        }
                        if(SubjectDAO.updateSubject(temp))
                        {
                            sjTable.setValueAt(t[1], sjTable.getSelectedRow(), 1);
                            sjTable.setValueAt(t[2], sjTable.getSelectedRow(), 2);
                            sj_idText.setText("");
                            sj_nameText.setText("");
                            sj_creText.setText("");
                        }
                        else
                            JOptionPane.showMessageDialog(null, "Error! Cannot update this subject information!");
                    }
                });
                deleteBut2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        SubjectDAO.deleteSubject(sjTable.getValueAt(sjTable.getSelectedRow(), 0).toString());
                        subjectTable.removeRow(sjTable.getSelectedRow());
                        courseTable.setRowCount(0);
                        List<Course> rs = CourseDAO.getAllCourse();
                        for(Course i : rs) {
                            Subject t1 = SubjectDAO.getSubject(i.getSubjectId());
                            Semester t2 = SemesterDAO.getSemester(i.getSemesterId());
                            courseTable.addRow(new Object[]{i.getId(), t1.getId(), t1.getName(), t1.getCredits(), i.getGvlt(), i.getRoom(), i.getWeekday(), i.getShift(), i.getSlot(), t2.getName(), t2.getYear()});
                        }
                    }
                });
            }
        });
        findBut2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String t = findText2.getText();
                if(t.equals("")) { return; }
                List<Subject> found = SubjectDAO.getSubjectsFromString(t);
                int n_row = subjectTable.getRowCount();
                for(int i = n_row - 1; i >= 0; i--)
                    subjectTable.removeRow(i);
                for(Subject i : found)
                    subjectTable.addRow(new Object[]{i.getId(), i.getName(), i.getCredits()});
            }
        });
        resetBut2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int n_row = subjectTable.getRowCount();
                for(int i = n_row - 1; i >= 0; i--)
                    subjectTable.removeRow(i);
                List<Subject> rs = SubjectDAO.getAllSubject();
                for(Subject i : rs)
                    subjectTable.addRow(new Object[]{i.getId(), i.getName(), i.getCredits()});
                sj_idText.setText("");
                sj_nameText.setText("");
                sj_creText.setText("");
            }
        });
        sjTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                sj_idText.setText(sjTable.getValueAt(sjTable.getSelectedRow(), 0).toString());
                sj_nameText.setText(sjTable.getValueAt(sjTable.getSelectedRow(), 1).toString());
                sj_creText.setText(sjTable.getValueAt(sjTable.getSelectedRow(), 2).toString());
            }
        });

        //CAU 4: HOC KI-----------------------------------------------------------------------------------
        List<Semester> sms = SemesterDAO.getAllSemester();
        for (Semester i : sms) {
            semesterTable.addRow(new Object[]{i.getId(), i.getName(), i.getYear(), i.getDayStart(), i.getDayEnd(), i.isCurrent()});
        }

        addBut3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //getting the date
                StringBuilder date1 = new StringBuilder(), date2 = new StringBuilder();
                date1.append(sm_year_comboBox1.getSelectedItem()); date1.append("-");
                date1.append(sm_month_comboBox1.getSelectedItem()); date1.append("-");
                date1.append(sm_day_comboBox1.getSelectedItem());
                date2.append(sm_year_comboBox2.getSelectedItem()); date2.append("-");
                date2.append(sm_month_comboBox2.getSelectedItem()); date2.append("-");
                date2.append(sm_day_comboBox2.getSelectedItem());

                String[] t = {String.valueOf(sm_name_comboBox.getSelectedItem()), sm_yearText.getText(), date1.toString(), date2.toString(), String.valueOf(sm_cur_comboBox.getSelectedItem())};
                if (t[1].equals("")) { return; }
                Semester temp1 = new Semester();
                try {
                    temp1.setName(Integer.valueOf(t[0]));
                    temp1.setYear(Integer.valueOf(t[1]));
                    temp1.setDayStart(Date.valueOf(t[2]));
                    temp1.setDayEnd(Date.valueOf(t[3]));
                    temp1.setCurrent(t[4].equals("yes"));
                } catch (NumberFormatException any_e) {
                    JOptionPane.showMessageDialog(null, "Information was not enough or incorrect!");
                    return;
                }
                if (SemesterDAO.addSemester(temp1)) {
                    Semester temp2 = SemesterDAO.getSemesterFromDate(temp1.getDayStart(), temp1.getDayEnd());
                    semesterTable.addRow(new Object[]{temp2.getId(), temp2.getName(), temp2.getYear(), temp2.getDayStart(), temp2.getDayEnd(), temp2.isCurrent()});
                    sm_yearText.setText("");
                } else
                    JOptionPane.showMessageDialog(null, "Error! Cannot add this Semester!");
            }
        });
        ListSelectionModel listSelectionModel3 = smTable.getSelectionModel();
        listSelectionModel3.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listSelectionModel3.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                deleteBut3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        SemesterDAO.deleteSemester((int) smTable.getValueAt(smTable.getSelectedRow(), 0));
                        semesterTable.removeRow(smTable.getSelectedRow());
                        registerSessionTable.setRowCount(0);
                        List<RegisterSession> sss = RegisterSessionDAO.getAllRegisterSession();
                        for (RegisterSession i : sss)
                            registerSessionTable.addRow(new Object[]{i.getId(), i.getDayStart(), i.getDayEnd(), i.getSemesterId().getName(), i.getSemesterId().getYear()});
                        courseTable.setRowCount(0);
                        List<Course> rs = CourseDAO.getAllCourse();
                        for (Course i : rs) {
                            Subject t1 = SubjectDAO.getSubject(i.getSubjectId());
                            Semester t2 = SemesterDAO.getSemester(i.getSemesterId());
                            courseTable.addRow(new Object[]{i.getId(), t1.getId(), t1.getName(), t1.getCredits(), i.getGvlt(), i.getRoom(), i.getWeekday(), i.getShift(), i.getSlot(), t2.getName(), t2.getYear()});
                        }
                    }
                });
                setCurBut3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Semester temp = SemesterDAO.getSemesterFromDate(Date.valueOf(smTable.getValueAt(smTable.getSelectedRow(), 3).toString()), Date.valueOf(smTable.getValueAt(smTable.getSelectedRow(), 4).toString()));
                        if(temp != null)
                        {
                            temp.setCurrent(true);
                            if(SemesterDAO.updateSemester(temp))
                                smTable.setValueAt(true, smTable.getSelectedRow(), 5);
                        }
                    }
                });
                falseBut3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Semester temp = SemesterDAO.getSemesterFromDate(Date.valueOf(smTable.getValueAt(smTable.getSelectedRow(), 3).toString()), Date.valueOf(smTable.getValueAt(smTable.getSelectedRow(), 4).toString()));
                        if(temp != null)
                        {
                            temp.setCurrent(false);
                            if(SemesterDAO.updateSemester(temp))
                                smTable.setValueAt(false, smTable.getSelectedRow(), 5);
                        }
                    }
                });
            }
        });

        //CAU 5: LOP HOC---------------------------------------------------------------------------------------
        List<LopHoc> lhs = LopHocDAO.getAllLopHoc();
        for (LopHoc i : lhs) {
            int n = LopHocDAO.countMale(i.getId());
            int m = LopHocDAO.countFemale(i.getId());
            int total = n + m;
            lopHocTable.addRow(new Object[]{i.getId(), i.getName(), n, m, total});
        }

        addBut4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String t = class_nameText.getText();
                if(t.equals("")) { return; }
                LopHoc temp1 = new LopHoc();
                temp1.setName(t);
                if(LopHocDAO.addLopHoc(temp1))
                {
                    LopHoc temp2 = LopHocDAO.getLopHocFromName(t);
                    int n = LopHocDAO.countMale(temp2.getId());
                    int m = LopHocDAO.countFemale(temp2.getId());
                    int total = n + m;
                    lopHocTable.addRow(new Object[]{temp2.getId(), temp2.getName(), n, m, total});
                    class_nameText.setText("");
                }
                else
                    JOptionPane.showMessageDialog(null, "Error! Cannot add class!");
            }
        });
        ListSelectionModel listSelectionModel4 = lhTable.getSelectionModel();
        listSelectionModel4.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listSelectionModel4.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                deleteBut4.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Course crs = null;
                        if(rgTable.getModel() == registered_studentTable)
                            crs = CourseDAO.getCourse((Integer)rgTable.getValueAt(0, 2));
                        if(LopHocDAO.deleteLopHoc((Integer) lhTable.getValueAt(lhTable.getSelectedRow(), 0)))
                        {
                            lopHocTable.removeRow(lhTable.getSelectedRow());
                            StudentTable.setRowCount(0);
                            List<Student> st = StudentDAO.getAllStudent();
                            for(Student i : st)
                                StudentTable.addRow(new Object[]{i.getId(), i.getMssv(), i.getName(), i.getGender(), i.getClassId().getName()});
                            if(crs != null)
                            {
                                registered_studentTable.setRowCount(0);
                                List<Registered> crs_rg = RegisteredDAO.getCourseRegisteredList(crs.getId());
                                Subject sj = SubjectDAO.getSubject(crs.getSubjectId());
                                for (Registered i : crs_rg) {
                                    Student t = StudentDAO.getStudent(i.getStudentId());
                                    String shift;
                                    if(crs.getShift() == 1)
                                        shift = "7:30 - 9:30";
                                    else if(crs.getShift() == 2)
                                        shift = "9:30 - 11:30";
                                    else if(crs.getShift() == 3)
                                        shift = "13:30 - 15:30";
                                    else
                                        shift = "15:30 - 17:30";
                                    registered_studentTable.addRow(new Object[]{t.getMssv(), t.getName(), crs.getId(), sj.getId(), sj.getName(), crs.getGvlt(), crs.getWeekday(), shift, i.getDateEnroll()});
                                }
                            }
                        }
                    }
                });
            }
        });
        resetBut4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int n_row = lopHocTable.getRowCount();
                for(int i = n_row - 1; i >= 0; i--)
                    lopHocTable.removeRow(i);
                List<LopHoc> rs = LopHocDAO.getAllLopHoc();
                for(LopHoc i : rs) {
                    int n = LopHocDAO.countMale(i.getId());
                    int m = LopHocDAO.countFemale(i.getId());
                    int total = n + m;
                    lopHocTable.addRow(new Object[]{i.getId(), i.getName(), n, m, total});
                }
                class_nameText.setText("");
            }
        });

        //CAU 6: SINH VIEN---------------------------------------------------------------------------------
        List<Student> sts = StudentDAO.getAllStudent();
        for (Student i : sts) {
            StudentTable.addRow(new Object[]{i.getId(), i.getMssv(), i.getName(), i.getGender(), i.getClassId().getName()});
        }

        addBut5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] t = {mssvText5.getText(), nameText5.getText(), gender_comboBox5.getSelectedItem().toString(), class_idText5.getText()};
                if(t[0].equals("") || t[1].equals("") || t[3].equals("")) { return; }
                Student temp1 = new Student();
                temp1.setMssv(t[0]);
                temp1.setName(t[1]);
                temp1.setGender(t[2]);
                temp1.setClassId(LopHocDAO.getLopHocFromName(t[3]));
                if(StudentDAO.addStudent(temp1))
                {
                    Student temp2 = StudentDAO.getUniqueStudent(t[0], t[1]);
                    StudentTable.addRow(new Object[]{temp2.getId(), temp2.getMssv(), temp2.getName(), temp2.getGender(), temp2.getClassId().getName()});
                    mssvText5.setText("");
                    nameText5.setText("");
                    class_idText5.setText("");
                    //update class table
                    int n_row = lopHocTable.getRowCount();
                    for(int i = n_row - 1; i >= 0; i--)
                        lopHocTable.removeRow(i);
                    List<LopHoc> rs = LopHocDAO.getAllLopHoc();
                    for(LopHoc i : rs) {
                        int n = LopHocDAO.countMale(i.getId());
                        int m = LopHocDAO.countFemale(i.getId());
                        int total = n + m;
                        lopHocTable.addRow(new Object[]{i.getId(), i.getName(), n, m, total});
                    }
                }
                else
                    JOptionPane.showMessageDialog(null, "Error! Cannot add giao vu account!");
            }
        });
        ListSelectionModel listSelectionModel5 = stTable.getSelectionModel();
        listSelectionModel5.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listSelectionModel5.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                resetBut5.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //reset pass
                        Student temp = StudentDAO.getStudent((Integer)stTable.getValueAt(stTable.getSelectedRow(), 0));
                        temp.setPassword(temp.getMssv());
                        StudentDAO.updateStudent(temp);
                    }
                });
            }
        });

        //CAU 7: KI DANG KI HOC PHAN-----------------------------------------------------------------------
        List<RegisterSession> sss = RegisterSessionDAO.getAllRegisterSession();
        for (RegisterSession i : sss) {
            registerSessionTable.addRow(new Object[]{i.getId(), i.getDayStart(), i.getDayEnd(), i.getSemesterId().getName(), i.getSemesterId().getYear()});
        }

        addBut6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //getting the date
                StringBuilder date1 = new StringBuilder(), date2 = new StringBuilder();
                date1.append(ss_year_comboBox1.getSelectedItem()); date1.append("-");
                date1.append(ss_month_comboBox1.getSelectedItem()); date1.append("-");
                date1.append(ss_day_comboBox1.getSelectedItem());
                date2.append(ss_year_comboBox2.getSelectedItem()); date2.append("-");
                date2.append(ss_month_comboBox2.getSelectedItem()); date2.append("-");
                date2.append(ss_day_comboBox2.getSelectedItem());
                try {
                    String[] t = {date1.toString(), date2.toString()};
                    RegisterSession temp1 = new RegisterSession();
                    temp1.setDayStart(Date.valueOf(t[0]));
                    temp1.setDayEnd(Date.valueOf(t[1]));
                    temp1.setSemesterId(SemesterDAO.getCurrent());
                    //adding this session
                    if (RegisterSessionDAO.addRegisterSession(temp1)) {
                        RegisterSession temp2 = RegisterSessionDAO.getRegisterSessionFromDate(temp1.getDayStart(), temp1.getDayEnd());
                        registerSessionTable.addRow(new Object[]{temp2.getId(), temp2.getDayStart(), temp2.getDayEnd(), temp2.getSemesterId().getName(), temp2.getSemesterId().getYear()});
                    } else
                        JOptionPane.showMessageDialog(null, "Error! Cannot add this register session!\nCheck if there are any session occur!");
                } catch (IllegalArgumentException any_e) {
                    JOptionPane.showMessageDialog(null, "Error! There is no current semester!");
                }
            }
        });

        //CAU 8: COURSE------------------------------------------------------------------------------------------
        List<Course> crs = CourseDAO.getAllCourse();
        for (Course i : crs) {
            Subject t1 = SubjectDAO.getSubject(i.getSubjectId());
            Semester t2 = SemesterDAO.getSemester(i.getSemesterId());
            courseTable.addRow(new Object[]{i.getId(), t1.getId(), t1.getName(), t1.getCredits(), i.getGvlt(), i.getRoom(), i.getWeekday(), i.getShift(), i.getSlot(), t2.getName(), t2.getYear()});
        }

        addBut7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] t = {crs_sj_idText.getText(), crs_gvltText.getText(), crs_roomText.getText(), String.valueOf(crs_weekday_comboBox.getSelectedItem()), String.valueOf(crs_shift_comboBox.getSelectedItem()), crs_slotText.getText(), crs_sm_idText.getText()};
                if(t[0].equals("") || t[1].equals("") || t[2].equals("") || t[5].equals("") || t[6].equals("")) { return; }
                Course temp1 = new Course();
                try {
                    temp1.setSubjectId(t[0]);
                    temp1.setGvlt(t[1]);
                    temp1.setRoom(t[2]);
                    temp1.setWeekday(Integer.valueOf(t[3]));
                    temp1.setShift(Integer.valueOf(t[4]));
                    temp1.setSlot(Integer.valueOf(t[5]));
                    temp1.setSemesterId(Integer.valueOf(t[6]));
                } catch(NumberFormatException any_e) {
                    JOptionPane.showMessageDialog(null, "Number format was incorrect!");
                    return;
                }
                if(CourseDAO.addCourse(temp1))
                {
                    Course temp2 = CourseDAO.getUniqueCourse(temp1.getSubjectId(), temp1.getWeekday(), temp1.getShift(), temp1.getSemesterId());
                    Subject temp_sj = SubjectDAO.getSubject(temp2.getSubjectId());
                    Semester temp_sm = SemesterDAO.getSemester(temp2.getSemesterId());
                    courseTable.addRow(new Object[]{temp2.getId(), temp_sj.getId(), temp_sj.getName(), temp_sj.getCredits(), temp2.getGvlt(), temp2.getRoom(), temp2.getWeekday(), temp2.getShift(), temp2.getSlot(), temp_sm.getName(), temp_sm.getYear()});
                    crs_sj_idText.setText("");
                    crs_gvltText.setText("");
                    crs_roomText.setText("");
                    crs_slotText.setText("");
                    crs_sm_idText.setText("");
                    if(rgTable.getModel() == registeredTable)
                    {
                        registeredTable.setRowCount(0);
                        List<Course> t_crs = CourseDAO.getAllCourse();
                        for (Course i : t_crs) {
                            Subject t1 = SubjectDAO.getSubject(i.getSubjectId());
                            Semester t2 = SemesterDAO.getSemester(i.getSemesterId());
                            registeredTable.addRow(new Object[]{i.getId(), t1.getId(), t1.getName(), t1.getCredits(), i.getGvlt(), i.getRoom(), i.getWeekday(), i.getShift(), i.getSlot(), t2.getName(), t2.getYear()});
                        }
                    }
                }
                else
                    JOptionPane.showMessageDialog(null, "Error! Cannot add course!");
            }
        });
        ListSelectionModel listSelectionModel7 = crsTable.getSelectionModel();
        listSelectionModel7.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listSelectionModel7.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                deleteBut7.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        CourseDAO.deleteCourse((Integer) crsTable.getValueAt(crsTable.getSelectedRow(), 0));
                        courseTable.removeRow(crsTable.getSelectedRow());
                        if(rgTable.getModel() == registeredTable)
                        {
                            registeredTable.setRowCount(0);
                            List<Course> t = CourseDAO.getAllCourse();
                            for (Course i : t) {
                                Subject t1 = SubjectDAO.getSubject(i.getSubjectId());
                                Semester t2 = SemesterDAO.getSemester(i.getSemesterId());
                                registeredTable.addRow(new Object[]{i.getId(), t1.getId(), t1.getName(), t1.getCredits(), i.getGvlt(), i.getRoom(), i.getWeekday(), i.getShift(), i.getSlot(), t2.getName(), t2.getYear()});
                            }
                        }
                        else if(rgTable.getModel() == registered_studentTable)
                        {
                            Course crs = CourseDAO.getCourse((Integer) rgTable.getValueAt(0, 2));
                            registered_studentTable.setRowCount(0);
                            List<Registered> crs_rg = RegisteredDAO.getCourseRegisteredList(crs.getId());
                            if(crs_rg != null)
                            {
                                Subject sj = SubjectDAO.getSubject(crs.getSubjectId());
                                for (Registered i : crs_rg) {
                                    Student st = StudentDAO.getStudent(i.getStudentId());
                                    String shift;
                                    if(crs.getShift() == 1)
                                        shift = "7:30 - 9:30";
                                    else if(crs.getShift() == 2)
                                        shift = "9:30 - 11:30";
                                    else if(crs.getShift() == 3)
                                        shift = "13:30 - 15:30";
                                    else
                                        shift = "15:30 - 17:30";
                                    registered_studentTable.addRow(new Object[]{st.getMssv(), st.getName(), crs.getId(), sj.getId(), sj.getName(), crs.getGvlt(), crs.getWeekday(), shift, i.getDateEnroll()});
                                }
                            }
                        }
                    }
                });
            }
        });
        resetBut7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                courseTable.setRowCount(0);
                List<Course> rs = CourseDAO.getAllCourse();
                for(Course i : rs) {
                    Subject t1 = SubjectDAO.getSubject(i.getSubjectId());
                    Semester t2 = SemesterDAO.getSemester(i.getSemesterId());
                    courseTable.addRow(new Object[]{i.getId(), t1.getId(), t1.getName(), t1.getCredits(), i.getGvlt(), i.getRoom(), i.getWeekday(), i.getShift(), i.getSlot(), t2.getName(), t2.getYear()});
                }
                crs_sj_idText.setText("");
                crs_gvltText.setText("");
                crs_roomText.setText("");
                crs_slotText.setText("");
                crs_sm_idText.setText("");
            }
        });
        curBut7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                courseTable.setRowCount(0);
                List<Course> rs = CourseDAO.getAllCourse();
                for(Course i : rs)
                    if(CourseDAO.isOpen(i.getId()))
                    {
                        Subject t1 = SubjectDAO.getSubject(i.getSubjectId());
                        Semester t2 = SemesterDAO.getSemester(i.getSemesterId());
                        courseTable.addRow(new Object[]{i.getId(), t1.getId(), t1.getName(), t1.getCredits(), i.getGvlt(), i.getRoom(), i.getWeekday(), i.getShift(), i.getSlot(), t2.getName(), t2.getYear()});
                    }
            }
        });
        findBut7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String t = findText7.getText();
                if(t.equals("")) { return; }
                List<Course> found = CourseDAO.getCoursesFromString(t);
                courseTable.setRowCount(0);
                for(Course i : found) {
                    Subject t1 = SubjectDAO.getSubject(i.getSubjectId());
                    Semester t2 = SemesterDAO.getSemester(i.getSemesterId());
                    courseTable.addRow(new Object[]{i.getId(), t1.getId(), t1.getName(), t1.getCredits(), i.getGvlt(), i.getRoom(), i.getWeekday(), i.getShift(), i.getSlot(), t2.getName(), t2.getYear()});
                }
            }
        });

        //CAU 9: SINH VIEN TRONG 1 HOC PHAN-----------------------------------------------------------------------
        //crs is already queried above
        for (Course i : crs) {
            Subject t1 = SubjectDAO.getSubject(i.getSubjectId());
            Semester t2 = SemesterDAO.getSemester(i.getSemesterId());
            registeredTable.addRow(new Object[]{i.getId(), t1.getId(), t1.getName(), t1.getCredits(), i.getGvlt(), i.getRoom(), i.getWeekday(), i.getShift(), i.getSlot(), t2.getName(), t2.getYear()});
        }

        ListSelectionModel listSelectionModel8 = rgTable.getSelectionModel();
        listSelectionModel8.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listSelectionModel8.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                seeBut8.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Course crs = CourseDAO.getCourse((Integer) rgTable.getValueAt(rgTable.getSelectedRow(), 0));
                        rgTable.setModel(registered_studentTable);
                        registered_studentTable.setRowCount(0);
                        List<Registered> crs_rg = RegisteredDAO.getCourseRegisteredList(crs.getId());
                        if(crs_rg != null)
                        {
                            Subject sj = SubjectDAO.getSubject(crs.getSubjectId());
                            for (Registered i : crs_rg) {
                                Student st = StudentDAO.getStudent(i.getStudentId());
                                String shift;
                                if(crs.getShift() == 1)
                                    shift = "7:30 - 9:30";
                                else if(crs.getShift() == 2)
                                    shift = "9:30 - 11:30";
                                else if(crs.getShift() == 3)
                                    shift = "13:30 - 15:30";
                                else
                                    shift = "15:30 - 17:30";
                                registered_studentTable.addRow(new Object[]{st.getMssv(), st.getName(), crs.getId(), sj.getId(), sj.getName(), crs.getGvlt(), crs.getWeekday(), shift, i.getDateEnroll()});
                            }
                            rgLabel.setText("Registered: " + RegisteredDAO.takenSlot(crs.getId()) + ".");
                        }
                    }
                });
            }
        });
        resetBut8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rgTable.setModel(registeredTable);
                registeredTable.setRowCount(0);
                List<Course> rs = CourseDAO.getAllCourse();
                for(Course i : rs) {
                    Subject t1 = SubjectDAO.getSubject(i.getSubjectId());
                    Semester t2 = SemesterDAO.getSemester(i.getSemesterId());
                    registeredTable.addRow(new Object[]{i.getId(), t1.getId(), t1.getName(), t1.getCredits(), i.getGvlt(), i.getRoom(), i.getWeekday(), i.getShift(), i.getSlot(), t2.getName(), t2.getYear()});
                }
                rgLabel.setText("");
            }
        });
    }
}
