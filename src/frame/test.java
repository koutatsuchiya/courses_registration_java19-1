package frame;

import dao.*;
import pojo.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class test extends JFrame
{
    private JPanel contentPane;
    private JTable table1;
    private JButton button1;
    private JButton button2;
    private JScrollPane scrollpane;

    public test()
    {
        super("Testing table");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(contentPane);
        this.pack();

        List<GiaoVu> temp = GiaoVuDAO.getAllGiaoVu();
        DefaultTableModel t = new DefaultTableModel(null, new String[]{"id", "name"});
        table1.setModel(t);
        for (GiaoVu i : temp) {
            t.addRow(new Object[]{i.getId(), i.getName()});
        }
    }
}
