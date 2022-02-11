import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static jdk.nashorn.internal.runtime.Debug.id;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Erryza
 */
public class f_jadwal extends javax.swing.JFrame {

    private Connection con;
    private Statement st;
    public PreparedStatement pst;
    private ResultSet rs;
    private String host = "";
    private String sql = "";
    private String nip, nama, mk, ruangan,npm;

    /**
     * Creates new form f_jadwal
     */
    public f_jadwal() {
        initComponents();
        koneksi();
        tampiljadwalperkuliahan("SELECT * FROM t_matakuliah, t_dosen, t_ruangan, t_mahasiswa, t_jadwal where t_matakuliah.id_matkul=t_jadwal.id_matkul and t_dosen.nip=t_jadwal.nip and t_ruangan.id_ruangan=t_jadwal.id_ruangan and t_mahasiswa.npm=t_jadwal.npm");
        cmbmatakuliah();
        cmbdosen();
        cmbmahasiswa();
        cmbhari();
        cmbruangan();
        txt_hari.setVisible(false);
        txt_nip.setVisible(false);
        txt_mk.setVisible(false);
        txt_ruangan.setVisible(false);
        txt_npm.setVisible(false);
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

    public void tampiljadwalperkuliahan(String sql) {
        DefaultTableModel kolom = new DefaultTableModel();
        kolom.addColumn("No");
        kolom.addColumn("NPM");
        kolom.addColumn("Hari");
        kolom.addColumn("Jam");
        kolom.addColumn("Dosen");
        kolom.addColumn("Matakuliah");
        kolom.addColumn("Ruangan");

        try {
            int i = 1;
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM t_matakuliah, t_dosen, t_ruangan, t_mahasiswa, t_jadwal where t_matakuliah.id_matkul=t_jadwal.id_matkul and t_dosen.nip=t_jadwal.nip and t_ruangan.id_ruangan=t_jadwal.id_ruangan and t_mahasiswa.npm=t_jadwal.npm");
            while (rs.next()) {
                kolom.addRow(new Object[]{
                    ("" + i++),
                    rs.getString(12), rs.getString(16), 
                    rs.getString(17), rs.getString(5), 
                    rs.getString(2), rs.getString(11),    
                });
                gridjadwal.setModel(kolom);
                //BtnTambah.requestFocus();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane,
                    "Gagal Tampil ke dalam GRID" + e.getMessage());
        }
    }

    
    private void cmbmahasiswa() {
        cmb_npm.removeAllItems();
        cmb_npm.addItem("Pilih NPM");
        try {
            Connection c = ClassKoneksi.getkoneksi();
            Statement st = c.createStatement();
            String sql_tc = "select npm from t_mahasiswa order by npm asc";
            ResultSet rs = st.executeQuery(sql_tc);

            while (rs.next()) {
                String nama = rs.getString("npm");
                cmb_npm.addItem(nama);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
     public void cari_npm() {
        try {
            Connection c = ClassKoneksi.getkoneksi();
            String sql_t = "select npm from t_mahasiswa where npm='" + cmb_npm.getSelectedItem() + "'";
            Statement st = ClassKoneksi.getkoneksi().createStatement();
            ResultSet rs = st.executeQuery(sql_t);                              // yang anda ingin kan

            while (rs.next()) {
                this.txt_npm.setText(rs.getString("npm"));
            }
            rs.close();
            st.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    
    private void cmbmatakuliah() {
        cmb_mk.removeAllItems();
        cmb_mk.addItem("Pilih Matakuliah");
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
                this.txt_mk.setText(rs.getString("id_matkul"));
            }
            rs.close();
            st.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void cmbdosen() {
        cmb_dosen.removeAllItems();
        cmb_dosen.addItem("Pilih Dosen ");
        try {
            Connection c = ClassKoneksi.getkoneksi();
            Statement st = c.createStatement();
            String sql_tc = "select nip, nama from t_dosen order by nip asc";
            ResultSet rs = st.executeQuery(sql_tc);

            while (rs.next()) {
                String nama = rs.getString("nama");
                cmb_dosen.addItem(nama);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void cari_nama_dosen() {
        try {
            Connection c = ClassKoneksi.getkoneksi();
            String sql_t = "select nip, id_matkul from t_dosen where nama='" + cmb_dosen.getSelectedItem() + "'";
            Statement st = ClassKoneksi.getkoneksi().createStatement();
            ResultSet rs = st.executeQuery(sql_t);                              // yang anda ingin kan

            while (rs.next()) {
                this.txt_nip.setText(rs.getString("nip"));

            }
            rs.close();
            st.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    private void cmbruangan() {
        cmb_ruangan.removeAllItems();
        cmb_ruangan.addItem("Pilih Ruangan");
        try {
            Connection c = ClassKoneksi.getkoneksi();
            Statement st = c.createStatement();
            String sql_tc = "select id_ruangan, nama_ruangan from t_ruangan order by id_ruangan asc";
            ResultSet rs = st.executeQuery(sql_tc);

            while (rs.next()) {
                String nama = rs.getString("nama_ruangan");
                cmb_ruangan.addItem(nama);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
       public void cari_nama_ruangan() {
        try {
            Connection c = ClassKoneksi.getkoneksi();
            String sql_t = "select id_ruangan from t_ruangan where nama_ruangan='" + cmb_ruangan.getSelectedItem() + "'";
            Statement st = ClassKoneksi.getkoneksi().createStatement();
            ResultSet rs = st.executeQuery(sql_t);                              // yang anda ingin kan

            while (rs.next()) {
                this.txt_ruangan.setText(rs.getString("id_ruangan"));
            }
            rs.close();
            st.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    private void cmbhari() {
        cmb_hari.removeAllItems();
        cmb_hari.addItem("Pilih Hari ");
        cmb_hari.addItem("Senin");
        cmb_hari.addItem("Selasa");
        cmb_hari.addItem("Rabu");
        cmb_hari.addItem("Kamis");
        cmb_hari.addItem("Jum'at");
    }

    private void kosongkan() {
        cmb_hari.setSelectedIndex(0);
        cmb_dosen.setSelectedIndex(0);
        cmb_mk.setSelectedIndex(0);
        cmb_ruangan.setSelectedIndex(0);
        cmb_npm.setSelectedIndex(0);
        txt_jam.setText("");

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel9 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        gridjadwal = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        cmb_dosen = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        txt_jam = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cmb_mk = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        btnsimpan = new javax.swing.JButton();
        btnbatal = new javax.swing.JButton();
        btnkeluar2 = new javax.swing.JButton();
        cmb_hari = new javax.swing.JComboBox<>();
        txt_hari = new javax.swing.JTextField();
        txt_mk = new javax.swing.JTextField();
        txt_nip = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cmb_ruangan = new javax.swing.JComboBox<>();
        txt_ruangan = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cmb_npm = new javax.swing.JComboBox<>();
        txt_npm = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Comic Sans MS", 1, 36)); // NOI18N
        jLabel9.setText("Jadwal Perkuliahan");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 40, 340, 50));

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Jadwal Perkuliahan", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 12))); // NOI18N
        jPanel3.setMaximumSize(new java.awt.Dimension(3000, 3000));
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(500, 200));

        jScrollPane1.setName("Informasi Buku"); // NOI18N

        gridjadwal.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        gridjadwal.setFont(new java.awt.Font("Tempus Sans ITC", 1, 12)); // NOI18N
        gridjadwal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6"
            }
        ));
        gridjadwal.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        gridjadwal.setName(""); // NOI18N
        gridjadwal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                gridjadwalMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(gridjadwal);
        if (gridjadwal.getColumnModel().getColumnCount() > 0) {
            gridjadwal.getColumnModel().getColumn(0).setResizable(false);
            gridjadwal.getColumnModel().getColumn(1).setResizable(false);
        }

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 848, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 430, 880, 200));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Input Data", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 12))); // NOI18N
        jPanel1.setOpaque(false);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cmb_dosen.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Dosen" }));
        cmb_dosen.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_dosenItemStateChanged(evt);
            }
        });
        cmb_dosen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmb_dosenMouseClicked(evt);
            }
        });
        cmb_dosen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_dosenActionPerformed(evt);
            }
        });
        jPanel1.add(cmb_dosen, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 130, 150, 30));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel4.setText("Dosen");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 110, -1, -1));

        txt_jam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_jamActionPerformed(evt);
            }
        });
        jPanel1.add(txt_jam, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, 100, 30));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel5.setText("Jam");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, -1, -1));

        cmb_mk.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Mata Kuliah " }));
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
        jPanel1.add(cmb_mk, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 130, 140, 30));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel1.setText("Mata Kuliah");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 110, -1, 10));

        btnsimpan.setFont(new java.awt.Font("Top Secret", 0, 12)); // NOI18N
        btnsimpan.setText("SIMPAN");
        btnsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsimpanActionPerformed(evt);
            }
        });
        jPanel1.add(btnsimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 80, 130, 40));

        btnbatal.setFont(new java.awt.Font("Top Secret", 0, 12)); // NOI18N
        btnbatal.setText("BATAL");
        btnbatal.setOpaque(false);
        btnbatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbatalActionPerformed(evt);
            }
        });
        jPanel1.add(btnbatal, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 140, 130, 40));

        btnkeluar2.setFont(new java.awt.Font("Top Secret", 0, 12)); // NOI18N
        btnkeluar2.setText("KELUAR");
        btnkeluar2.setOpaque(false);
        btnkeluar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnkeluar2ActionPerformed(evt);
            }
        });
        jPanel1.add(btnkeluar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 200, 130, 40));

        cmb_hari.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Hari " }));
        cmb_hari.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_hariItemStateChanged(evt);
            }
        });
        cmb_hari.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmb_hariMouseClicked(evt);
            }
        });
        cmb_hari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_hariActionPerformed(evt);
            }
        });
        jPanel1.add(cmb_hari, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 90, 30));

        txt_hari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_hariActionPerformed(evt);
            }
        });
        jPanel1.add(txt_hari, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 70, -1));

        txt_mk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_mkActionPerformed(evt);
            }
        });
        jPanel1.add(txt_mk, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 140, 110, -1));

        txt_nip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nipActionPerformed(evt);
            }
        });
        jPanel1.add(txt_nip, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 140, 110, -1));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel2.setText("Hari");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel3.setText("Ruangan");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 110, -1, -1));

        cmb_ruangan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Ruangan" }));
        cmb_ruangan.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_ruanganItemStateChanged(evt);
            }
        });
        cmb_ruangan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmb_ruanganMouseClicked(evt);
            }
        });
        cmb_ruangan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_ruanganActionPerformed(evt);
            }
        });
        jPanel1.add(cmb_ruangan, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 130, 150, 30));

        txt_ruangan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_ruanganActionPerformed(evt);
            }
        });
        jPanel1.add(txt_ruangan, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 140, 120, 20));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel6.setText("NPM");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, 20));

        cmb_npm.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih NPM" }));
        cmb_npm.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_npmItemStateChanged(evt);
            }
        });
        cmb_npm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmb_npmMouseClicked(evt);
            }
        });
        jPanel1.add(cmb_npm, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 150, 30));
        jPanel1.add(txt_npm, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 150, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 880, 310));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void gridjadwalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gridjadwalMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_gridjadwalMouseClicked

    private void cmb_mkItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_mkItemStateChanged
        // TODO add your handling code here:
        cari_nama_mk();
    }//GEN-LAST:event_cmb_mkItemStateChanged

    private void cmb_mkMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmb_mkMouseClicked
        // TODO add your handling code here:
        cari_nama_mk();
    }//GEN-LAST:event_cmb_mkMouseClicked

    private void cmb_dosenItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_dosenItemStateChanged
        // TODO add your handling code here:
        cari_nama_dosen();
    }//GEN-LAST:event_cmb_dosenItemStateChanged

    private void cmb_dosenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmb_dosenMouseClicked
        // TODO add your handling code here:
        cari_nama_dosen();
    }//GEN-LAST:event_cmb_dosenMouseClicked

    private void txt_hariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_hariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_hariActionPerformed

    private void txt_jamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_jamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_jamActionPerformed

    private void cmb_hariItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_hariItemStateChanged
        // TODO add your handling code here:
        String text;
        text = (String) cmb_hari.getSelectedItem();
        txt_hari.setText(text);

    }//GEN-LAST:event_cmb_hariItemStateChanged

    private void cmb_hariMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmb_hariMouseClicked
        // TODO add your handling code here:
        String text;
        text = (String) cmb_hari.getSelectedItem();
        txt_hari.setText(text);
    }//GEN-LAST:event_cmb_hariMouseClicked

    private void btnsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpanActionPerformed
        // TODO add your handling code here:
        String hari = txt_hari.getText();
        String jam = txt_jam.getText();
        nip = txt_nip.getText();
        mk = txt_mk.getText();
        ruangan = txt_ruangan.getText();
        npm =txt_npm.getText();

        try {
            sql = "INSERT INTO t_jadwal"
                    + "(hari, jam, nip, id_matkul, id_ruangan, npm) VALUES "
                    + "('" + hari + "',"
                    + "'" + jam + "',"
                    + "'" + nip + "',"
                    + "'" + mk + "',"
                    + "'" + ruangan + "',"
                    + "'" + npm + "')";

            st = con.createStatement();
            st.execute(sql);
            JOptionPane.showMessageDialog(rootPane, "Sukses Menyimpan Data");
            btnsimpan.setEnabled(true);
            kosongkan();
            tampiljadwalperkuliahan("SELECT * FROM t_matakuliah, t_dosen, t_ruangan, t_mahasiswa, t_jadwal where t_matakuliah.id_matkul=t_jadwal.id_matkul and t_dosen.nip=t_jadwal.nip and t_ruangan.id_ruangan=t_jadwal.id_ruangan and t_mahasiswa.npm=t_jadwal.npm");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Data Gagal Disimpan Mohon Periksa Kembali Codingnya" + e.getMessage());
        }
    }//GEN-LAST:event_btnsimpanActionPerformed

    private void btnkeluar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnkeluar2ActionPerformed
        // TODO add your handling code here:
        int selectedoption = JOptionPane.showConfirmDialog(null, "Apakah Anda Ingin Keluar dari Aplikasi?", "Tutup Aplikasi",
                JOptionPane.YES_NO_OPTION);
        if (selectedoption == JOptionPane.YES_OPTION) {
            dispose();
        }
    }//GEN-LAST:event_btnkeluar2ActionPerformed

    private void cmb_dosenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_dosenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_dosenActionPerformed

    private void txt_nipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nipActionPerformed
       // TODO add your handling code here:
    }//GEN-LAST:event_txt_nipActionPerformed

    private void cmb_ruanganMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmb_ruanganMouseClicked
        // TODO add your handling code here:
        cari_nama_ruangan();
    }//GEN-LAST:event_cmb_ruanganMouseClicked

    private void cmb_ruanganItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_ruanganItemStateChanged
        // TODO add your handling code here:
        cari_nama_ruangan();
    }//GEN-LAST:event_cmb_ruanganItemStateChanged

    private void txt_ruanganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_ruanganActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_ruanganActionPerformed

    private void cmb_ruanganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_ruanganActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_ruanganActionPerformed

    private void txt_mkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_mkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_mkActionPerformed

    private void cmb_hariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_hariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_hariActionPerformed

    private void btnbatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbatalActionPerformed
        // TODO add your handling code here:
        kosongkan();
    }//GEN-LAST:event_btnbatalActionPerformed

    private void cmb_npmMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmb_npmMouseClicked
        // TODO add your handling code here:
         cari_npm();
    }//GEN-LAST:event_cmb_npmMouseClicked

    private void cmb_npmItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_npmItemStateChanged
        // TODO add your handling code here:
         cari_npm();
    }//GEN-LAST:event_cmb_npmItemStateChanged

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
            java.util.logging.Logger.getLogger(f_jadwal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(f_jadwal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(f_jadwal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(f_jadwal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new f_jadwal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnbatal;
    private javax.swing.JButton btnkeluar2;
    private javax.swing.JButton btnsimpan;
    private javax.swing.JComboBox<String> cmb_dosen;
    private javax.swing.JComboBox<String> cmb_hari;
    private javax.swing.JComboBox<String> cmb_mk;
    private javax.swing.JComboBox<String> cmb_npm;
    private javax.swing.JComboBox<String> cmb_ruangan;
    private javax.swing.JTable gridjadwal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txt_hari;
    private javax.swing.JTextField txt_jam;
    private javax.swing.JTextField txt_mk;
    private javax.swing.JTextField txt_nip;
    private javax.swing.JTextField txt_npm;
    private javax.swing.JTextField txt_ruangan;
    // End of variables declaration//GEN-END:variables
}
