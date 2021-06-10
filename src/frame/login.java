package frame;

import dao.GiaoVuDAO;
import dao.StudentDAO;
import pojo.GiaoVu;
import pojo.Student;

import javax.swing.*;
import java.awt.event.*;

public class login extends JFrame
{
    private JTabbedPane lrcTab;
    private JPanel logPanel;
    private JButton logInButton;
    private JTextField tUserName;
    private JPasswordField logPassword;
    private JRadioButton giaoVuRadioButton;
    private JRadioButton sinhVienRadioButton;
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
    private JPanel lPane;

    public login()
    {
        super("Log In");
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(logPanel);
        this.pack();
        this.setBounds(600, 275, 300, 300);

        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = tUserName.getText();
                String password = String.valueOf(logPassword.getPassword());
                if (username.equals("") || password.equals(""))
                    return;
                if(giaoVuRadioButton.isSelected())
                {
                    GiaoVu gv = null;
                    try {
                        gv = GiaoVuDAO.getGiaoVu(Integer.valueOf(username));
                    } catch(Exception any_e){
                        JOptionPane.showMessageDialog(logPanel, "Wrong username or password!");
                        return;
                    }
                    if (gv != null)
                        if(password.equals(gv.getPassword()))
                        {
                            new gvFunction(gv);
                            dispose();
                        }
                    else
                        JOptionPane.showMessageDialog(logPanel, "Wrong username or password!");
                }
                else if(sinhVienRadioButton.isSelected())
                {
                    Student st = StudentDAO.getLogInStudent(username, password);
                    if(st != null) {
                        new stFunction(st);
                        dispose();
                    }
                    else
                        JOptionPane.showMessageDialog(logPanel, "Wrong username or password!");
                }
            }
        });

        changeBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = tCName.getText();
                String pass1 = String.valueOf(cPass1.getPassword());
                String pass2 = String.valueOf(cPass2.getPassword());
                if (username.equals("") || pass1.equals("") || pass2.equals(""))
                    return;
                if(giaoVuRadioButton.isSelected())
                {
                    GiaoVu gv = null;
                    try {
                        gv = GiaoVuDAO.getGiaoVu(Integer.valueOf(username));
                    } catch(Exception any_e){
                        JOptionPane.showMessageDialog(logPanel, "Wrong username or password!");
                        return;
                    }
                    if (!pass1.equals(pass2) && gv != null && pass1.equals(gv.getPassword()))
                    {
                        gv.setPassword(pass2);
                        GiaoVuDAO.updateGiaoVu(gv);
                        JOptionPane.showMessageDialog(logPanel, "Password for teacher has been changed!");
                    }
                    else
                        JOptionPane.showMessageDialog(logPanel, "Wrong username or password, or perhaps 2 password are the same!");
                }
                else if(sinhVienRadioButton.isSelected())
                {
                    Student st = StudentDAO.getLogInStudent(username, pass1);
                    if(st != null && !pass1.equals(pass2))
                    {
                        st.setPassword(pass2);
                        StudentDAO.updateStudent(st);
                        JOptionPane.showMessageDialog(logPanel, "Password for student has been changed!");
                    }
                    else
                        JOptionPane.showMessageDialog(logPanel, "Wrong username or password or 2 password are the same!");
                }
                tCName.setText("");
                cPass1.setText("");
                cPass2.setText("");
            }
        });
    }
}
