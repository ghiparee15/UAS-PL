package com.data.gui2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataJurusan  extends JFrame {
    private JPanel panelMain;
    private JTextField txtSearch;
    private JButton btnSearch;
    private JTextField txtNamaDosen;
    private JLabel lblNamaDosen;
    private JTextField txtMatakuliah;
    private JButton btnTambahkan;
    private JButton btnHapus;
    private JLabel lblMatakuliah;
    private JButton btnClear;
    private JList<String> jListDosen;
    private JTextField txtCari;
    private JButton btnCari;
    private JList jListMahasiswa;
    private JLabel lblNamaMahasiswa;
    private JTextField txtNamaMahasiswa;
    private JLabel lblNim;
    private JTextField txtNim;
    private JLabel lblIpk;
    private JTextField txtIpk;
    private JButton btnTambah;
    private JButton btnDelete;
    private JButton btnSelesai;

    List<Dosen> arrayListDosen = new ArrayList<>();

    DefaultListModel<String> defaultListModel = new DefaultListModel<>();

    List<Mahasiswa> arrayListMahasiswa = new ArrayList<>();


    class Dosen{
        public String getNamaDosen() {
            return namaDosen;
        }

        public void setNamaDosen(String namaDosen) {
            this.namaDosen = namaDosen;
        }

        public String getMataKuliah() {
            return mataKuliah;
        }

        public void setMataKuliah(String mataKuliah) {
            this.mataKuliah = mataKuliah;
        }

        String namaDosen;
        String mataKuliah;
    }

    class Mahasiswa{
        String namaMahasiswa;
        String nim;

        public String getNamaMahasiswa() {
            return namaMahasiswa;
        }

        public void setNamaMahasiswa(String namaMahasiswa) {
            this.namaMahasiswa = namaMahasiswa;
        }

        public String getNim() {
            return nim;
        }

        public void setNim(String nim) {
            this.nim = nim;
        }

        public float getIpk() {
            return ipk;
        }

        public void setIpk(float ipk) {
            this.ipk = ipk;
        }

        float ipk;
    }


    public DataJurusan(){
        this.setTitle("Data Jurusan");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();

        jListDosen.setModel(defaultListModel);


        btnTambahkan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String namaDosen = txtNamaDosen.getText();
                String mataKuliah = txtMatakuliah.getText();

                Dosen dosen = new Dosen();
                dosen.setNamaDosen(namaDosen);
                dosen.setMataKuliah(mataKuliah);

                arrayListDosen.add(dosen);
                clearForm();

                fromListDosenToListModel();

            }
        });

        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm();

            }
        });
        jListDosen.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                int index = jListDosen.getSelectedIndex();

                if (index < 0)
                    return;

                String namaDosen = jListDosen.getSelectedValue().toString();

                for (int i = 0; i < arrayListDosen.size(); i++){
                    if (arrayListDosen.get(i).getNamaDosen().equals(namaDosen)){
                        Dosen dosen = arrayListDosen.get(i);

                        txtNamaDosen.setText(dosen.getNamaDosen());
                        txtMatakuliah.setText(dosen.getMataKuliah());
                        break;
                    }
                }

            }
        });
        btnHapus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = jListDosen.getSelectedIndex();

                if (index < 0)
                    return;

                String namaDosen = jListDosen.getSelectedValue();

                for (int i = 0; i < arrayListDosen.size(); i++ ){
                    if (arrayListDosen.get(i).getNamaDosen().equals(namaDosen)){
                        arrayListDosen.remove(i);
                        break;
                    }
                }

                clearForm();
                fromListDosenToListModel();
            }
        });
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String keyWord = txtSearch.getText();

                List<String> search = new ArrayList<>();

                for (int i = 0; i < arrayListDosen.size(); i++){
                    if (arrayListDosen.get(i).getNamaDosen().contains(keyWord)){
                        search.add(arrayListDosen.get(i).getNamaDosen());
                    }
                }
                refreshListModel(search);
            }
        });

        jListMahasiswa.setModel(defaultListModel);
        btnTambah.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String namaMahasiswa = txtNamaMahasiswa.getText();
                String nim = txtNim.getText();
                float ipk = Float.parseFloat(txtIpk.getText());

                Mahasiswa mahasiswa = new Mahasiswa();
                mahasiswa.setNamaMahasiswa(namaMahasiswa);
                mahasiswa.setNim(nim);
                mahasiswa.setIpk(ipk);

                arrayListMahasiswa.add(mahasiswa);

                clearForm();

                fromListMahasiswaToListModel();

            }
        });
    }

    public void clearForm() {
        txtNamaDosen.setText("");
        txtMatakuliah.setText("");
    }
    public void fromListDosenToListModel(){
        List<String> listNamaDosen = new ArrayList<>();

        for (int i = 0; i < arrayListDosen.size(); i++){
            listNamaDosen.add(
                    arrayListDosen.get(i).getNamaDosen()
            );
        }
        refreshListModel(listNamaDosen);
    }

    public void refreshListModel(List<String> list) {
        Collections.sort(list);
        defaultListModel.clear();
        defaultListModel.addAll(list);
        jListDosen.setModel(defaultListModel);
    }
    public void fromListMahasiswaToListModel(){
        List<String> listNamaMahasiswa = new ArrayList<>();

        for (int i = 0; i < arrayListMahasiswa.size(); i++){
            listNamaMahasiswa.add(
                    arrayListMahasiswa.get(i).getNamaMahasiswa()
            );
        }
        refreshListModel(listNamaMahasiswa);
    }


    public static void main(String[] args) {
        DataJurusan dataJurusan = new DataJurusan();
        dataJurusan.setVisible(true);
    }
}
