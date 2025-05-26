package com.data.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;


public class MainScreen extends JFrame{
    private JPanel panelMain;
    private JList jListMahasiswa;
    private JTextField txtFilter;
    private JButton btnFilter;
    private JTextField txtNama;
    private JTextField txtNim;
    private JTextField txtIpk;
    private JButton btnSave;
    private JButton btnDelete;
    private JButton btnClear;
    private JLabel lblNama;
    private JLabel lblNim;
    private JLabel lblIpk;

    private Mahasiswa[] arrayMahasiswa = new Mahasiswa[100]; // Array untuk menyimpan data Mahasiswa
    private int countMahasiswa = 0; // Variabel untuk menghitung jumlah mahasiswa yang disimpan

    DefaultListModel defaultListModel = new DefaultListModel();


    class Mahasiswa{
        public String getNama() {

            return nama;
        }

        public void setNama(String nama) {

            this.nama = nama;
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

        public String nama;
        public String nim;
        public float ipk;
    }

    public MainScreen(){
        super("Data Mahasiswa");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);//kalau tekan exit langsung keluar
        this.setResizable(false);//ukuran ga bisa diubah
        this.pack();

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nama = txtNama.getText();
                String nim = txtNim.getText();
                float ipk = Float.parseFloat(txtIpk.getText());

                Mahasiswa mahasiswa = new Mahasiswa();
                mahasiswa.setNama(nama);
                mahasiswa.setNim(nim);
                mahasiswa.setIpk(ipk);

                arrayMahasiswa[countMahasiswa] = mahasiswa;
                countMahasiswa++;

                clearForm();
                bubbleSort(arrayMahasiswa, countMahasiswa);
                fromArrayMahasiswaToListModel();
            }
        });

        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm();
            }
        });
        jListMahasiswa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                int index = jListMahasiswa.getSelectedIndex();

                if (index < 0 || index >= countMahasiswa)
                    return; //menghentikan eksekusi lebih lanjut

                Mahasiswa mahasiswa = arrayMahasiswa[index];
                txtNama.setText(mahasiswa.getNama());
                txtNim.setText(mahasiswa.getNim());
                txtIpk.setText(String.valueOf(mahasiswa.getIpk()));
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = jListMahasiswa.getSelectedIndex();

                if (index < 0 || index >= countMahasiswa)
                    return; //menghentikan eksekusi lebih lanjut

                // Menggeser data setelah index yang dihapus
                for (int i = index; i < countMahasiswa - 1; i++) {
                    arrayMahasiswa[i] = arrayMahasiswa[i + 1];
                }

                countMahasiswa--;

                clearForm();
                bubbleSort(arrayMahasiswa, countMahasiswa); //mengurutkan kembali
                fromArrayMahasiswaToListModel();
            }
        });

        btnFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String keyword = txtFilter.getText().toLowerCase();

                List<Mahasiswa> filteredList = new ArrayList<>();
                for (int i = 0; i < countMahasiswa; i++) {
                    if (arrayMahasiswa[i].getNama().toLowerCase().contains(keyword)) {
                        filteredList.add(arrayMahasiswa[i]);
                    }
                }

                fromArrayMahasiswaToListModel(filteredList);
            }
        });
    }

    public void clearForm(){
        txtNama.setText("");
        txtNim.setText("");
        txtIpk.setText("");
    }
    public void fromArrayMahasiswaToListModel() {
        defaultListModel.clear();
        for (int i = 0; i < countMahasiswa; i++) {
            defaultListModel.addElement(arrayMahasiswa[i].getNama());
        }
        jListMahasiswa.setModel(defaultListModel);
    }



    public void fromArrayMahasiswaToListModel(List<Mahasiswa> list) {
        defaultListModel.clear();
        for (Mahasiswa mahasiswa : list) {
            defaultListModel.addElement(mahasiswa.getNama());
        }
        jListMahasiswa.setModel(defaultListModel);
    }

    public void bubbleSort(Mahasiswa[] array, int n) {
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j].getNama().compareToIgnoreCase(array[j + 1].getNama()) > 0) {

                    Mahasiswa temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }


    public static void main(String[] args) {
        MainScreen mainScreen = new MainScreen();
        mainScreen.setVisible(true);//untuk menampilkan
    }
}
