/*
Saya Jidan Abdurahman Aufan NIM [2205422] mengerjakan soal Latihan Praktikum-5 dalam mata kuliah DPBO
untuk keberkahanNya maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Menu extends JFrame{
    public static void main(String[] args) {
        // buat object window
        Menu window = new Menu();

        // atur ukuran window
        window.setSize(480, 560);

        // letakkan window di tengah layar
        window.setLocationRelativeTo(null);

        // isi window
        window.setContentPane(window.mainPanel);

        // ubah warna background
        window.getContentPane().setBackground(Color.white);
        // tampilkan window
        window.setVisible(true);

        // agar program ikut berhenti saat window diclose
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    // index baris yang diklik
    private int selectedIndex = -1;
    // list untuk menampung semua mahasiswa
//    private ArrayList<Mahasiswa> listMahasiswa;

    private Database database;

    private JPanel mainPanel;
    private JTextField nimField;
    private JTextField namaField;
    private JTable mahasiswaTable;
    private JButton addUpdateButton;
    private JButton cancelButton;
    private JComboBox jenisKelaminComboBox;
    private JButton deleteButton;
    private JLabel titleLabel;
    private JLabel nimLabel;
    private JLabel namaLabel;
    private JLabel jenisKelaminLabel;
    private JComboBox fakultasComboBox;

    // constructor
    public Menu() {
        // inisialisasi listMahasiswa
//        listMahasiswa = new ArrayList<>();

        database = new Database();

        // isi tabel mahasiswa
        mahasiswaTable.setModel(setTable());

        // ubah styling title
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 20f));

        // atur isi combo box
        String[] jenisKelaminData = {"", "Laki-laki", "Perempuan"};
        String[] fakultasData = {"", "FPMIPA", "FPIPS", "FIP", "FPEB", "FPOK", "FPTK", "FPBS", "FPSD", "KD-Cibiru", "KD-Sumedang", "KD-Purwakarta", "KD-Tasikmalaya", "KD-Serang"};
        jenisKelaminComboBox.setModel(new DefaultComboBoxModel(jenisKelaminData));
        fakultasComboBox.setModel(new DefaultComboBoxModel(fakultasData));


        // sembunyikan button delete
        deleteButton.setVisible(false);

        // saat tombol add/update ditekan
        addUpdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(selectedIndex == -1) {
                    insertData();
                } else {
                    updateData();
                }
            }
        });
        // saat tombol delete ditekan
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(selectedIndex >= 0) {
                    int confirmation = JOptionPane.showConfirmDialog(null, "Confirm Delete?", "Delete", JOptionPane.YES_NO_OPTION);
                    if(confirmation == JOptionPane.YES_OPTION) {
                        deleteData();
                    }
                }
            }
        });
        // saat tombol cancel ditekan
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm();
            }
        });
        // saat salah satu baris tabel ditekan
        mahasiswaTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // ubah selectedIndex menjadi baris tabel yang diklik
                selectedIndex = mahasiswaTable.getSelectedRow();

                // simpan value textfield dan combo box
                String selectedNim = mahasiswaTable.getModel().getValueAt(selectedIndex, 1).toString();
                String selectedNama = mahasiswaTable.getModel().getValueAt(selectedIndex, 2).toString();
                String selectedJenisKelamin = mahasiswaTable.getModel().getValueAt(selectedIndex, 3).toString();
                String selectedFakultas = mahasiswaTable.getModel().getValueAt(selectedIndex, 4).toString();

                // ubah isi textfield dan combo box
                nimField.setText(selectedNim);
                namaField.setText(selectedNama);
                jenisKelaminComboBox.setSelectedItem(selectedJenisKelamin);
                fakultasComboBox.setSelectedItem(selectedFakultas);

                // ubah button "Add" menjadi "Update"
                addUpdateButton.setText("Update");

                // tampilkan button delete
                deleteButton.setVisible(true);

            }
        });
    }

    public final DefaultTableModel setTable() {
        // tentukan kolom tabel
        Object[] column = {"No", "NIM", "Nama", "Jenis Kelamin", "Fakultas / Kamda"};

        // buat objek tabel dengan kolom yang sudah dibuat
        DefaultTableModel temp = new DefaultTableModel(null, column);

        try {
            ResultSet resultSet = database.selectQuery("SELECT * FROM mahasiswa");
            int i = 1;
            while(resultSet.next()) {
                Object[] row = new Object[5];
                row[0] = i;
                row[1] = resultSet.getString("nim");
                row[2] = resultSet.getString("nama");
                row[3] = resultSet.getString("jenis_kelamin");
                row[4] = resultSet.getString("fakultas");
                temp.addRow(row);
                i++;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return temp; // return juga harus diganti
    }

    public void insertData() {
        // ambil value dari textfield dan combobox
        String nim = nimField.getText();
        String nama = namaField.getText();
        String jenisKelamin = jenisKelaminComboBox.getSelectedItem().toString();
        String fakultas = fakultasComboBox.getSelectedItem().toString();

        // cek apakah input ada yang blank
        if(nim.isBlank() || nama.isBlank() || jenisKelamin.isBlank() || fakultas.isBlank()) {
            JOptionPane.showMessageDialog(null, "Please fill all fields", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            if(database.recordExists("mahasiswa", "nim", nim)) {
                String message = String.format("Mahasiswa with NIM: %s already exists.", nim);
                JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
                return;
            };
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



        String sql = "INSERT INTO mahasiswa VALUES (null, '"+nim+"', '"+nama+"', '"+jenisKelamin+"', '"+fakultas+"');";
        database.insertUpdateDeleteQuery(sql);

        // tambahkan data ke dalam list
        // update tabel
        mahasiswaTable.setModel(setTable());
        // bersihkan form
        clearForm();
        // feedback
        System.out.println("Insert berhasil!");
        JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan!");

    }

    public void updateData() {
        // ambil data dari form
        String nim = nimField.getText();
        String nama = namaField.getText();
        String jenisKelamin = jenisKelaminComboBox.getSelectedItem().toString();
        String fakultas = fakultasComboBox.getSelectedItem().toString();

        // cek apakah input ada yang blank
        if(nim.isBlank() || nama.isBlank() || jenisKelamin.isBlank() || fakultas.isBlank()) {
            JOptionPane.showMessageDialog(null, "Please fill all fields", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            if(database.recordExists("mahasiswa", "nim", nim)) {
                String message = String.format("Mahasiswa with NIM: %s already exists.", nim);
                JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
                return;
            };
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String sql = String.format("""
                UPDATE mahasiswa
                SET nama = %s, jenis_kelamin = %s, fakultas = %s
                WHERE nim = %s;
                """,  nama, jenisKelamin, fakultas, nim);

        database.insertUpdateDeleteQuery(sql);
        // update tabel
        mahasiswaTable.setModel(setTable());
        // bersihkan form
        clearForm();
        // feedback
        System.out.println("Update berhasil!");
        JOptionPane.showMessageDialog(null, "Data berhasil diubah!");


    }

    public void deleteData() {
        // hapus data dari list
        String sql = String.format("DELETE FROM mahasiswa WHERE id = %d;", selectedIndex);
        database.insertUpdateDeleteQuery(sql);

        // update tabel
        mahasiswaTable.setModel(setTable());
        // bersihkan form
        clearForm();
        // feedback
        System.out.println("Delete berhasil!");
        JOptionPane.showMessageDialog(null, "Data berhasil dihapus!");


    }

    public void clearForm() {
        // kosongkan semua texfield dan combo box
        nimField.setText("");
        namaField.setText("");
        jenisKelaminComboBox.setSelectedItem("");
        fakultasComboBox.setSelectedItem("");
        // ubah button "Update" menjadi "Add"
        addUpdateButton.setText("Add");
        // sembunyikan button delete
        deleteButton.setVisible(false);
        // ubah selectedIndex menjadi -1 (tidak ada baris yang dipilih)
        selectedIndex = -1;
    }



//    private void populateList() {
//        listMahasiswa.add(new Mahasiswa("2203999", "Amelia Zalfa Julianti", "Perempuan", "FPMIPA"));
//        listMahasiswa.add(new Mahasiswa("2202292", "Muhammad Iqbal Fadhilah", "Laki-laki", "FPMIPA"));
//        listMahasiswa.add(new Mahasiswa("2202346", "Muhammad Rifky Afandi", "Laki-laki", "FPMIPA"));
//        listMahasiswa.add(new Mahasiswa("2210239", "Muhammad Hanif Abdillah", "Laki-laki", "FPMIPA"));
//        listMahasiswa.add(new Mahasiswa("2202046", "Nurainun", "Perempuan", "FPMIPA"));
//        listMahasiswa.add(new Mahasiswa("2205101", "Kelvin Julian Putra", "Laki-laki", "FPMIPA"));
//        listMahasiswa.add(new Mahasiswa("2200163", "Rifanny Lysara Annastasya", "Perempuan", "FPMIPA"));
//        listMahasiswa.add(new Mahasiswa("2202869", "Revana Faliha Salma", "Perempuan", "FPMIPA"));
//        listMahasiswa.add(new Mahasiswa("2209489", "Rakha Dhifiargo Hariadi", "Laki-laki", "FPMIPA"));
//        listMahasiswa.add(new Mahasiswa("2203142", "Roshan Syalwan Nurilham", "Laki-laki", "FPMIPA"));
//        listMahasiswa.add(new Mahasiswa("2200311", "Raden Rahman Ismail", "Laki-laki", "FPMIPA"));
//        listMahasiswa.add(new Mahasiswa("2200978", "Ratu Syahirah Khairunnisa", "Perempuan", "FPMIPA"));
//        listMahasiswa.add(new Mahasiswa("2204509", "Muhammad Fahreza Fauzan", "Laki-laki", "FPMIPA"));
//        listMahasiswa.add(new Mahasiswa("2205027", "Muhammad Rizki Revandi", "Laki-laki", "FPMIPA"));
//        listMahasiswa.add(new Mahasiswa("2203484", "Arya Aydin Margono", "Laki-laki", "FPMIPA"));
//        listMahasiswa.add(new Mahasiswa("2200481", "Marvel Ravindra Dioputra", "Laki-laki", "FPMIPA"));
//        listMahasiswa.add(new Mahasiswa("2209889", "Muhammad Fadlul Hafiizh", "Laki-laki", "FPMIPA"));
//        listMahasiswa.add(new Mahasiswa("2206697", "Rifa Sania", "Perempuan", "FPMIPA"));
//        listMahasiswa.add(new Mahasiswa("2207260", "Imam Chalish Rafidhul Haque", "Laki-laki", "FPMIPA"));
//        listMahasiswa.add(new Mahasiswa("2204343", "Meiva Labibah Putri", "Perempuan", "FPMIPA"));
//    }
}
