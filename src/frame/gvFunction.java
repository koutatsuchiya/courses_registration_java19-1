package frame;

import dao.*;
import pojo.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class gvFunction extends JFrame
{
    private JPanel mainPane;
    private JTabbedPane gvTab;
    private JTable gvTable;
    private JButton deleteBut;
    private JButton addBut;
    private JButton updateBut;
    private JScrollPane gvScroll;
    private JPanel giaoVu;
    private JLabel testLabel;

    public gvFunction()
    {
        super("Giao Vu Window");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPane);
        this.pack();
        this.setBounds(500, 150, 600, 500);
        this.setResizable(false);

        List<GiaoVu> temp = GiaoVuDAO.getAllGiaoVu();
        DefaultTableModel giaoVuTable = new DefaultTableModel(null, new String[]{"id", "name"});
        gvTable.setModel(giaoVuTable);
        for (GiaoVu i : temp) {
            giaoVuTable.addRow(new Object[]{i.getId(), i.getName()});
        }

        ListSelectionModel listSelectionModel = gvTable.getSelectionModel();
        listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listSelectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int[] rows = gvTable.getSelectedRows();
                int[] cols = gvTable.getSelectedColumns();

                testLabel.setText(String.valueOf(gvTable.getValueAt(rows[0], 1)));
            }
        });
    }
}
