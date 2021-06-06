package frame;

import dao.*;
import pojo.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.util.List;

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

    public gvFunction()
    {
        super("Giao Vu Window");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPane);
        this.pack();
        this.setBounds(500, 150, 600, 500);
        this.setResizable(false);

        //CAU 2: GV ACCOUNT-----------------------------------------------------------------------
        List<GiaoVu> gvs = GiaoVuDAO.getAllGiaoVu();
        DefaultTableModel giaoVuTable = new DefaultTableModel(null, new String[]{"id", "name"}){
            public boolean isCellEditable(int row, int column){ return false; }
        };
        gvTable.setModel(giaoVuTable);
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
                    GiaoVu temp2 = GiaoVuDAO.getGiaoVuFromName(t);
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
        DefaultTableModel subjectTable = new DefaultTableModel(null, new String[]{"id", "name", "credits"}){
            public boolean isCellEditable(int row, int column){ return false; }
        };
        sjTable.setModel(subjectTable);
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
                } catch(Exception any_e){
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
                        } catch(Exception any_e){
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
        DefaultTableModel semesterTable = new DefaultTableModel(null, new String[]{"id", "name", "year", "start date", "end date", "is current"}){
            public boolean isCellEditable(int row, int column){ return false; }
        };
        smTable.setModel(semesterTable);
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
                } catch (Exception any_e) {
                    JOptionPane.showMessageDialog(null, "Information was not enough or incorrect!");
                    return;
                }
                if (SemesterDAO.addSemester(temp1)) {
                    Semester temp2 = SemesterDAO.getSemesterFromDate(temp1.getDayStart(), temp1.getDayEnd());

                    semesterTable.addRow(new Object[]{temp2.getId(), temp2.getName(), temp2.getDayStart(), temp2.getDayEnd(), temp2.isCurrent()});
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
                    }
                });
                setCurBut3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Semester temp = SemesterDAO.getSemesterFromDate(Date.valueOf(smTable.getValueAt(smTable.getSelectedRow(), 2).toString()), Date.valueOf(smTable.getValueAt(smTable.getSelectedRow(), 3).toString()));
                        if(temp != null)
                        {
                            temp.setCurrent(true);
                            if(!SemesterDAO.updateSemester(temp))
                                JOptionPane.showMessageDialog(null, "Error! Cannot set this semester as current!");
                        }
                    }
                });
            }
        });
        //CAU 5
    }
}
