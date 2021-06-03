package frame;

import dao.GiaoVuDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class test extends JFrame
{
    private JPanel contentPane;
    private JButton Find;
    private JLabel ID;
    private JTextField tID;
    private JTextField tGV;
    private JLabel infor;

    public test(String title)
    {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(contentPane);
        this.pack();

        Find.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int gv_id = Integer.valueOf(tID.getText());
                tGV.setText(GiaoVuDAO.getGiaoVu(gv_id).toString());
            }
        });
    }

    /*private void createUIComponents()
    {
        //TODO: place custom component creation code here
    }*/
}
