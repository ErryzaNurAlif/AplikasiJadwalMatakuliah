import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author Erryza Nur Alif
 */
public class f_dosen extends javax.swing.JFrame {

    private Connection con;
    private Statement st;
    private ResultSet rs, rsmk;
    private String host = "";
    private String sql = "";
    private String nip, nama, tanggallahir, mk, id_mk, alamat, nohp;

    /**
     * Creates new form f_dosen
     */
    public f_dosen() {
        initComponents();
        koneksi();
        cmbmk();
        tampildosen("SELECT * FROM t_dosen");
        kosongkan();
        txt_id_mk.setVisible(false);
    }

    public void koneksi() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_jadwalmatakuliah", "root", "");
            //JOptionPane.showMessageDialog(rootPane,
            //    "Koneksi Berhasil Broooo");
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(rootPane,
            //      "Koneksi Gagal" + "\n"
            //    + e.getMessage());
        }

    }

    public void kosongkan() {
        txt_nip.setText("");
        txt_nama.setText("");
        jdtanggal.setDate(null);
        cmb_mk.setSelectedIndex(0);
        txt_alamat.setText("");
        txt_no_hp.setText("");
    }

    private void cmbmk() {
        cmb_mk.removeAllItems();
        cmb_mk.addItem("Pilih Matakuliah ");
        try {
            Connection c = ClassKoneksi.getkoneksi();
            Statement st = c.createStatement();
            String sql_tc = "select id_matkul, matkul from t_matakuliah order by id_matkul asc";
            ResultSet rs = st.executeQuery(sql_tc);

            while (rs.next()) {
                String nama = rs.getString("matkul");
                cmb_mk.addItem(nama);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void cari_nama_mk() {
        try {
            Connection c = ClassKoneksi.getkoneksi();
            String sql_t = "select id_matkul from t_matakuliah where matkul='" + cmb_mk.getSelectedItem() + "'";
            Statement st = ClassKoneksi.getkoneksi().createStatement();
            ResultSet rs = st.executeQuery(sql_t);                              // yang anda ingin kan

            while (rs.next()) {
                this.txt_id_mk.setText(rs.getString("id_matkul"));
            }
            rs.close();
            st.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void tampildosen(String sql) {
        DefaultTableModel kolom = new DefaultTableModel();
        kolom.addColumn("No");
        kolom.addColumn("NIP");
        kolom.addColumn("Nama Dosen");
        kolom.addColumn("Tanggal Lahir");
        kolom.addColumn("Mata Kuliah");
        kolom.addColumn("Alamat");
        kolom.addColumn("No HP");

        try {
            int i = 1;
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM t_dosen, t_matakuliah where t_dosen.id_matkul=t_matakuliah.id_matkul");
            while (rs.next()) {
                kolom.addRow(new Object[]{
                    ("" + i++),
                    rs.getString(1), rs.getString(2),
                    rs.getString(3), rs.getString(8),
                    rs.getString(5), rs.getString(6)
                });
                griddosen.setModel(kolom);
                //BtnTambah.requestFocus();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane,
                    "Gagal Tampil ke dalam GRID" + e.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        cmb_mk = new javax.swing.JComboBox<>();
        txt_id_mk = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jdtanggal = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_nama = new javax.swing.JTextField();
        txt_nip = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_alamat = new javax.swing.JTextField();
        txt_no_hp = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        griddosen = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        btntambah = new javax.swing.JButton();
        btnsimpan = new javax.swing.JButton();
        btnubah = new javax.swing.JButton();
        btnkeluar = new javax.swing.JButton();
        btnbatal = new javax.swing.JButton();
        btnhapus = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 1, 36)); // NOI18N
        jLabel1.setText("Data Dosen ");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 10, 660, 32));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Input Data Dosen", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 11))); // NOI18N
        jPanel1.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        jPanel1.setOpaque(false);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cmb_mk.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_mkItemStateChanged(evt);
            }
        });
        cmb_mk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmb_mkMouseClicked(evt);
            }
        });
        jPanel1.add(cmb_mk, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 110, 150, -1));

        txt_id_mk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_id_mkActionPerformed(evt);
            }
        });
        jPanel1.add(txt_id_mk, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 110, 120, -1));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel6.setText("Matakuliah");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 80, -1));
        jPanel1.add(jdtanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 80, 140, -1));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel4.setText("Tanggal Lahir");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 80, -1));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel3.setText("Nama Dosen");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 80, -1));
        jPanel1.add(txt_nama, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, 210, -1));
        jPanel1.add(txt_nip, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, 210, -1));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel2.setText("NIP");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 80, 20));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel7.setText("Alamat");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 80, -1));

        txt_alamat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_alamatActionPerformed(evt);
            }
        });
        jPanel1.add(txt_alamat, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 140, 210, -1));
        jPanel1.add(txt_no_hp, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 170, 210, -1));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel8.setText("No HP");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 80, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 360, 230));

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Informasi Data Dosen", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 12))); // NOI18N
        jPanel3.setOpaque(false);

        jScrollPane1.setName("Informasi Buku"); // NOI18N

        griddosen.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        griddosen.setFont(new java.awt.Font("Tempus Sans ITC", 1, 12)); // NOI18N
        griddosen.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7"
            }
        ));
        griddosen.setName(""); // NOI18N
        griddosen.setOpaque(false);
        griddosen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                griddosenMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(griddosen);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 728, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 60, 760, 390));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Proses", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 12))); // NOI18N
        jPanel2.setOpaque(false);

        btntambah.setFont(new java.awt.Font("Top Secret", 0, 12)); // NOI18N
        btntambah.setText("Tambah");
        btntambah.setOpaque(false);
        btntambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntambahActionPerformed(evt);
            }
        });

        btnsimpan.setFont(new java.awt.Font("Top Secret", 0, 12)); // NOI18N
        btnsimpan.setText("Simpan");
        btnsimpan.setOpaque(false);
        btnsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsimpanActionPerformed(evt);
            }
        });

        btnubah.setFont(new java.awt.Font("Top Secret", 0, 12)); // NOI18N
        btnubah.setText("Ubah");
        btnubah.setOpaque(false);
        btnubah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnubahActionPerformed(evt);
            }
        });

        btnkeluar.setFont(new java.awt.Font("Top Secret", 0, 12)); // NOI18N
        btnkeluar.setText("Keluar");
        btnkeluar.setOpaque(false);
        btnkeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnkeluarActionPerformed(evt);
            }
        });

        btnbatal.setFont(new java.awt.Font("Top Secret", 0, 12)); // NOI18N
        btnbatal.setText("Batal");
        btnbatal.setOpaque(false);
        btnbatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbatalActionPerformed(evt);
            }
        });

        btnhapus.setFont(new java.awt.Font("Top Secret", 0, 12)); // NOI18N
        btnhapus.setText("Hapus");
        btnhapus.setOpaque(false);
        btnhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhapusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btntambah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnkeluar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnsimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnubah, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnhapus, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnbatal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btntambah)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnsimpan)
                    .addComponent(btnubah)
                    .addComponent(btnhapus)
                    .addComponent(btnbatal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnkeluar)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 360, 150));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmb_mkItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_mkItemStateChanged
        // TODO add your handling code here:
        cari_nama_mk();
    }//GEN-LAST:event_cmb_mkItemStateChanged

    private void cmb_mkMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmb_mkMouseClicked
        // TODO add your handling code here:
        cari_nama_mk();
    }//GEN-LAST:event_cmb_mkMouseClicked

    private void griddosenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_griddosenMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_griddosenMouseClicked

    private void btntambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntambahActionPerformed
        // TODO add your handling code here:
        txt_nip.requestFocus();//Jika sudah di pilih btn tambah maka cursorr akan pindah ke txtid
        btntambah.setEnabled(false);//jika sudah di pilih btntambah maka btn tambah sudah tidak bisa di klik lagi
        btnsimpan.setEnabled(true);
        btnubah.setEnabled(false);
        btnhapus.setEnabled(false);
        btnbatal.setEnabled(true);
        kosongkan();
    }//GEN-LAST:event_btntambahActionPerformed

    private void btnsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpanActionPerformed
        // TODO add your handling code here:
        nip = txt_nip.getText();
        nama = txt_nama.getText();
        mk = cmb_mk.getItemAt(cmb_mk.getSelectedIndex()).toString();
        id_mk = txt_id_mk.getText();
        alamat = txt_alamat.getText();
        nohp = txt_no_hp.getText();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd");
        Date tanggal = new Date();
        tanggal = jdtanggal.getDate();
        String tgl_lahir = dateFormat.format(tanggal);
        try {
            sql = "INSERT INTO t_dosen"
                    + "(nip, nama, tgl_lahir"
                    + ", id_matkul, alamat, no_hp) VALUES "
                    + "('" + nip + "',"
                    + "'" + nama + "',"
                    + "'" + tgl_lahir + "',"
                    + "'" + id_mk + "',"
                    + "'" + alamat + "',"
                    + "'" + nohp + "')";

            st = con.createStatement();
            st.execute(sql);
            tampildosen("SELECT * FROM t_dosen");
            JOptionPane.showMessageDialog(rootPane, "Selamat Anda Berhasil Menyimpan Data Dosen");
            btnsimpan.setEnabled(false);
            btntambah.setEnabled(true);
            kosongkan();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Data Gagal Disimpan Mohon Periksa Kembali Codingnya" + e.getMessage());
        }
        
    }//GEN-LAST:event_btnsimpanActionPerformed

    private void btnubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnubahActionPerformed
        // TODO add your handling code here:
        nip = txt_nip.getText();
        nama = txt_nama.getText();
        mk = cmb_mk.getItemAt(cmb_mk.getSelectedIndex()).toString();
        id_mk = txt_id_mk.getText();
        alamat = txt_alamat.getText();
        nohp = txt_no_hp.getText();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd");
        Date tanggal = new Date();
        tanggal = jdtanggal.getDate();
        String tgl_lahir = dateFormat.format(tanggal);
        try {
            sql = "UPDATE t_dosen set nip='" + nip + "',"
                    + "nama='" + nama + "',"
                    + "tgl_lahir='" + tgl_lahir + "',"
                    + "id_matkul='" + id_mk + "',"
                    + "alamat='" + alamat + "',"
                    + "no_hp='" + nohp + "'"
                    + "WHERE nip='" + nip + "'";

            st = con.createStatement();
            st.execute(sql);
            tampildosen("SELECT * FROM t_dosen");
            JOptionPane.showMessageDialog(rootPane, "Selamat Anda Berhasil Mengubah Data Dosen");
            btnubah.setEnabled(false);
            btntambah.setEnabled(true);
            kosongkan();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Data Gagal DiUbah Mohon Periksa Kembali Codingnya" + e.getMessage());
        }
    }//GEN-LAST:event_btnubahActionPerformed

    private void btnkeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnkeluarActionPerformed
        // TODO add your handling code here:
        int selectedoption = JOptionPane.showConfirmDialog(null, "Apakah Anda Ingin Keluar dari Aplikasi?", "Tutup Aplikasi",
                JOptionPane.YES_NO_OPTION);
        if (selectedoption == JOptionPane.YES_OPTION) {
            dispose();
        }
    }//GEN-LAST:event_btnkeluarActionPerformed

    private void btnbatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbatalActionPerformed
        // TODO add your handling code here:
        btntambah.setEnabled(true);//Untuk mengaktifkan kembali tombol btntambah yang sudah di disable tadi
        btnhapus.setEnabled(true);
        btnubah.setEnabled(true);
        btntambah.requestFocus(); // jika sudah di klik btnbatal maka kursor akan berpindah ke btntambah
        kosongkan();
    }//GEN-LAST:event_btnbatalActionPerformed

    private void btnhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhapusActionPerformed
        // TODO add your handling code here:
        nip = txt_nip.getText(); // untuk memanggil id yang sudah di kenalkan di initcomponent
        try {
            sql = "DELETE FROM t_dosen "
                    + "WHERE nip='" + nip + "'";

            st = con.createStatement();
            st.execute(sql);
            tampildosen("SELECT * FROM t_dosen");
            JOptionPane.showMessageDialog(rootPane, "Selamat Anda Berhasil Menghapus Data Dosen");
            btnhapus.setEnabled(false);
            btntambah.setEnabled(true);
            kosongkan();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Data Gagal DiHapus Mohon Periksa Kembali Codingnya" + e.getMessage());
        }
    }//GEN-LAST:event_btnhapusActionPerformed

    private void txt_alamatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_alamatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_alamatActionPerformed

    private void txt_id_mkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_id_mkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_id_mkActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(f_dosen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(f_dosen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(f_dosen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(f_dosen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new f_dosen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnbatal;
    private javax.swing.JButton btnhapus;
    private javax.swing.JButton btnkeluar;
    private javax.swing.JButton btnsimpan;
    private javax.swing.JButton btntambah;
    private javax.swing.JButton btnubah;
    private javax.swing.JComboBox<String> cmb_mk;
    private javax.swing.JTable griddosen;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private com.toedter.calendar.JDateChooser jdtanggal;
    private javax.swing.JTextField txt_alamat;
    private javax.swing.JTextField txt_id_mk;
    private javax.swing.JTextField txt_nama;
    private javax.swing.JTextField txt_nip;
    private javax.swing.JTextField txt_no_hp;
    // End of variables declaration//GEN-END:variables
}