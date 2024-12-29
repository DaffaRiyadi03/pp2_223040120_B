package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import model.*;
import org.apache.ibatis.session.SqlSession;
import view.UserPdf;
import view.UserView;

public class UserController {
    private UserView view;
    private UserMapper mapper;
    private UserPdf pdf;

    public UserController(UserView view, UserMapper mapper, UserPdf pdf) {
        this.view = view;
        this.mapper = mapper;
        this.pdf = pdf;

        this.view.addAddUserListener(new AddUserListener());
        this.view.addRefreshListener(new RefreshListener());
        this.view.addExportListener(new ExportListener());
    }

    class AddUserListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = view.getNameInput();
            String email = view.getEmailInput();
            if (!name.isEmpty() && !email.isEmpty()) {
                SqlSession session = MyBatisUtil.getSqlSession(); 
                try {
                    UserMapper mapper = session.getMapper(UserMapper.class);
                    User user = new User();
                    user.setName(name);
                    user.setEmail(email);

                    mapper.insertUser(user); // Insert data
                    session.commit(); // Commit transaksi
                    JOptionPane.showMessageDialog(view, "Berhasil menambahkan user!");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(view, "Gagal menambahkan user: " + ex.getMessage());
                } finally {
                    session.close(); // Tutup session
                }
            } else {
                JOptionPane.showMessageDialog(view, "Silahkan isi semua form.");
            }
        }
    }

    class RefreshListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = view.getNameInput();
            String email = view.getEmailInput();
            if (!name.isEmpty() && !email.isEmpty()) {
                SwingWorker<Void, Integer> worker = new SwingWorker<>() {
                    @Override
                    protected Void doInBackground() throws Exception {
                        for (int i = 1; i <= 100; i++) {
                            String newName = name + i;
                            String newEmail = email + i + "@gmail.com";

                            addUser(newName, newEmail);

                            publish(i);
                        }
                        return null;
                    }

                    @Override
                    protected void process(List<Integer> chunks) {
                        int progress = chunks.get(chunks.size() - 1);
                        view.setProgress(progress);
                    }

                    @Override
                    protected void done() {
                        List<User> users = mapper.getAllUsers();
                        String[] userArray = users.stream()
                            .map(u -> u.getName() + " (" + u.getEmail() + ")")
                            .toArray(String[]::new);
                        view.setUserList(userArray);
                    }

                    private void addUser(String newName, String newEmail) {
                        SqlSession session = MyBatisUtil.getSqlSession();
                        try {
                            UserMapper mapper = session.getMapper(UserMapper.class);
                            User user = new User();
                            user.setName(newName);
                            user.setEmail(newEmail);
                            mapper.insertUser(user);
                            session.commit();
                        } finally {
                            session.close();
                        }
                    }
                };

                worker.execute();
            } else {
                JOptionPane.showMessageDialog(view, "Silahkan isi semua kolom.");
            }
        }
    }

    class ExportListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            SwingWorker<Void, Void> worker = new SwingWorker<>() {
                @Override
                protected Void doInBackground() throws Exception {
                    List<User> users = mapper.getAllUsers();
                    pdf.exportPdf(users);
                    return null;
                }

                @Override
                protected void done() {
                    JOptionPane.showMessageDialog(view, "Data user telah di ekspor ke PDF.");
                }
            };

            worker.execute();
        }
    }
}
