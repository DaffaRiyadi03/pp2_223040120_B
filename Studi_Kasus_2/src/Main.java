import view.KaryawanView;
import controller.KaryawanController;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Membuat instance dari KaryawanView dan KaryawanController
            KaryawanView karyawanView = new KaryawanView();
            new KaryawanController(karyawanView);

            // Menampilkan KaryawanView di tengah layar
            karyawanView.setLocationRelativeTo(null);
            karyawanView.setVisible(true);
        });
    }
}
