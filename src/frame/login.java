package frame;

import dao.GiaoVuDAO;
import dao.StudentDAO;
import pojo.GiaoVu;
import pojo.Student;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.IOException;

public class login extends JFrame
{
    private JTabbedPane lrcTab;
    private JPanel logPanel;
    private JButton logInButton;
    private JTextField tUserName;
    private JPasswordField logPassword;
    private JRadioButton giaoVuRadioButton;
    private JRadioButton sinhVienRadioButton;
    private JButton rBut;
    private JTextField tRName;
    private JPasswordField tRPass1;
    private JPasswordField tRPass2;
    private JLabel rName;
    private JLabel rPass1;
    private JLabel rPass2;
    private JButton changeBut;
    private JTextField tCName;
    private JPanel changePass;
    private JLabel cName;
    private JLabel cOldPass;
    private JLabel cNewPass;
    private JPasswordField cPass1;
    private JPasswordField cPass2;
    private JLabel lName;
    private JLabel lPass;
    private JPanel rPane;
    private JPanel lPane;

    public login()
    {
        super("Account Management");
        try {
            GiaoVuDAO.initPass();
        } catch(IOException ioe) {
            System.err.println(ioe);
        }
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(logPanel);
        this.pack();

        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = tUserName.getText();
                String password = String.valueOf(logPassword.getPassword());
                if(giaoVuRadioButton.isSelected())
                {
                    GiaoVu gv = null;
                    if (password.equals(GiaoVuDAO.getPasswordGv()))
                        if (username != null)
                            gv = GiaoVuDAO.getGiaoVuFromName(username);
                    if (gv != null)
                        JOptionPane.showMessageDialog(logPanel, gv.toString());
                    else
                        JOptionPane.showMessageDialog(logPanel, "Wrong username or password!");
                }
                else if(sinhVienRadioButton.isSelected())
                {
                    Student st = null;
                    if(username.equals(password))
                        if((st = StudentDAO.getStudentFromMssv(password)).getMssv().equals(password))
                            JOptionPane.showMessageDialog(logPanel, st.toString());
                    if(st == null)
                        JOptionPane.showMessageDialog(logPanel, "Wrong username or password!");
                }
            }
        });

        rBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = tRName.getText();
                String pass1 = String.valueOf(tRPass1.getPassword());
                String pass2 = String.valueOf(tRPass2.getPassword());
                GiaoVu gv = null;
                if(giaoVuRadioButton.isSelected())
                {
                    if (pass1.equals(pass2) && pass1.equals(GiaoVuDAO.getPasswordGv()))
                    {
                        if (username != null)
                            gv = GiaoVuDAO.getGiaoVuFromName(username);
                    }
                    else {
                        JOptionPane.showMessageDialog(logPanel, "Wrong password or password confirmation is incorrect!");
                    }
                    if (gv != null)
                        JOptionPane.showMessageDialog(logPanel, "Username has already existed!");
                    else {
                        gv = new GiaoVu();
                        gv.setName(username);
                        GiaoVuDAO.addGiaoVu(gv);
                    }
                }
                else if(sinhVienRadioButton.isSelected())
                {
                    JOptionPane.showMessageDialog(logPanel, "Student can't register account on their own!");
                }
            }
        });

        changeBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = tCName.getText();
                String pass1 = String.valueOf(cPass1.getPassword());
                String pass2 = String.valueOf(cPass2.getPassword());
                GiaoVu gv = null;
                if(giaoVuRadioButton.isSelected())
                {
                    if (pass1.equals(GiaoVuDAO.getPasswordGv()))
                        if (username != null)
                            gv = GiaoVuDAO.getGiaoVuFromName(username);
                    if (gv != null) {
                        GiaoVuDAO.setPasswordGv(pass2);
                        try {
                            GiaoVuDAO.changePass();
                        } catch(IOException ioe) {
                            System.err.println(ioe);
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(logPanel, "Wrong username or password!");
                    }
                }
                else if(sinhVienRadioButton.isSelected())
                {
                    //do sth
                }
            }
        });
    }
}
