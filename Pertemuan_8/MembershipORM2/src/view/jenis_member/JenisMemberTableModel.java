package view.jenis_member;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.JenisMember;

public class JenisMemberTableModel extends AbstractTableModel {
    private final String[] columnNames = {"Nama"};
    private final List<JenisMember> data;

    public JenisMemberTableModel(List<JenisMember> data) {
        this.data = data;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Object getValueAt(int row, int col) {
        JenisMember rowItem = data.get(row);
        String value = "";
        switch (col) {
            case 0:
                value = rowItem.getNama();
                break;
        }
        return value;
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    public void add(JenisMember value) {
        data.add(value);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }
}