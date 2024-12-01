package view.jenis_member;

import dao.JenisMemberDao;
import java.util.List;
import javax.swing.*;
import model.JenisMember;

public class JenisMemberFrame extends JFrame {
    private final List<JenisMember> jenisMemberList;
    private final JTextField textFieldNama;
    private final JenisMemberTableModel tableModel;
    private final JenisMemberDao jenisMemberDao;

    public JenisMemberFrame(JenisMemberDao jenisMemberDao) {
        this.jenisMemberDao = jenisMemberDao;
        this.jenisMemberList = jenisMemberDao.findAll();

        // Setup JFrame properties
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(400, 500);
        this.setLayout(null);

        // Label
        JLabel labelInput = new JLabel("Nama:");
        labelInput.setBounds(15, 40, 350, 10);

        // TextField
        textFieldNama = new JTextField();
        textFieldNama.setBounds(15, 60, 350, 30);

        // Button
        JButton button = new JButton("Simpan");
        button.setBounds(15, 100, 100, 40);

        // Table
        JTable table = new JTable();
        JScrollPane scrollableTable = new JScrollPane(table);
        scrollableTable.setBounds(15, 150, 350, 200);

        // Table Model
        tableModel = new JenisMemberTableModel(jenisMemberList);
        table.setModel(tableModel);

        // Button Action Listener
        JenisMemberButtonSimpanActionListener actionListener =
                new JenisMemberButtonSimpanActionListener(this, jenisMemberDao);
        button.addActionListener(actionListener);

        // Add components to JFrame
        this.add(labelInput);
        this.add(textFieldNama);
        this.add(button);
        this.add(scrollableTable);
    }

    public String getNama() {
        return textFieldNama.getText();
    }

    public void addJenisMember(JenisMember jenisMember) {
        tableModel.add(jenisMember);
        textFieldNama.setText("");
    }
}
