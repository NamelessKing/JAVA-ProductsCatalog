/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productcatalog;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.sqlite.SQLiteJDBCLoader;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.TimeZone;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author NamelessKing
 */
public class Main_Window extends javax.swing.JFrame {

    String imgPhat = null;
    int pos = 0;
    /**
     * Creates new form Main_Window
     */
    public Main_Window() {
        initComponents();
        //getConnection();
        showProductsInJTable();
    }
    
    
    
    public Connection getConnection(){
        
        try {
            Class.forName("org.sqlite.JDBC");
            Connection con = DriverManager.getConnection("jdbc:sqlite:ProductCatalogDB.sqlite");
            //JOptionPane.showMessageDialog(null, "Connected");
            return con;
            
        } catch (Exception ex) {
            
            Logger.getLogger(Main_Window.class.getName()).log(Level.SEVERE, null, ex);
            //JOptionPane.showMessageDialog(null, "Not Connected");
            return null;
            
        }
    }
    
    /**
     Check Input Fields
     */
    public boolean checkInputs(){
        
        if(     id_jTextField.getText() == null 
                || price_jTextField.getText() == null
                || date_jDateChooser.getDate() == null){
            
            return false;
            
        }else{
            
            try {
                
                Float.parseFloat(price_jTextField.getText());
                return true;
                
            } catch (Exception e) {
                return false;
            }
        }
    }
    
    
    /**
     Resize image
     */
    public ImageIcon ResizeImage(String imagePhat , byte[] pic){
        
        ImageIcon myImage = null;
        
        if(imagePhat != null){
            
            myImage = new ImageIcon(imagePhat);
            
        }else{
            
            myImage = new ImageIcon(pic);
        }
        
        Image img = myImage.getImage();
        Image img2 = img.getScaledInstance(image_jLabel.getWidth(), image_jLabel.getHeight(),
                Image.SCALE_SMOOTH);
        
        ImageIcon image = new ImageIcon(img2);
        return image; 
    }
    
    
    /*
    Display Data In JTable
    */
    //1-Fill ArrayList With The Data
    public ArrayList<Product> getProductList(){
    
            ArrayList<Product> productList = new ArrayList<Product>();
            Connection con = getConnection();
            String query = "SELECT * FROM Products";
        try {
            
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            
            Product product;
            
            while (rs.next()){
                product = new Product(rs.getInt("id"), rs.getString("name"),
                Float.parseFloat(rs.getString("price")),rs.getString("add_date"),
                        rs.getBytes("image"),rs.getString("description"));
                
                productList.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Main_Window.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return productList;
    }
    
    //2-Populate JTable
    public void showProductsInJTable(){
    
        
        ArrayList<Product> list = getProductList();
        DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
        
        //clear JTable content
        model.setRowCount(0);
        Object[] row = new Object[4];
        for (int i = 0; i < list.size(); i++) {
            
            row[0] = list.get(i).getId();
            row[1] = list.get(i).getName();
            row[2] = list.get(i).getPrice();
            row[3] = list.get(i).getAddData();
            
            model.addRow(row);
            
        }
        
    }
    
    /*Convert java date to sql date*/
    public java.sql.Date convertJavaDateToSqlDate(java.util.Date date) {
    return new java.sql.Date(date.getTime());
    }
    
    public void showItem(int index){
        id_jTextField.setText(Integer.toString(getProductList().get(index).getId()));
        name_jTextField.setText(getProductList().get(index).getName());
        price_jTextField.setText(Float.toString(getProductList().get(index).getPrice()));
        
        try {
            Date addDate = convertJavaDateToSqlDate(new SimpleDateFormat("dd-MM-yyyy")
                    .parse(getProductList().get(index).getAddData()));
            date_jDateChooser.setDate(addDate);
        } catch (Exception e) {
            Logger.getLogger(Main_Window.class.getName()).log(Level.SEVERE,null,e);
        }
        
        image_jLabel.setIcon(ResizeImage(null, getProductList().get(index).getImage()));
        description_jTextArea.setText(getProductList().get(index).getDescription());
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        id_jLabel = new javax.swing.JLabel();
        name_jLabel = new javax.swing.JLabel();
        price_jLabel = new javax.swing.JLabel();
        addDate_jLabel = new javax.swing.JLabel();
        id_jTextField = new javax.swing.JTextField();
        name_jTextField = new javax.swing.JTextField();
        price_jTextField = new javax.swing.JTextField();
        date_jDateChooser = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        chooseImage_jButton = new javax.swing.JButton();
        insert_jButton = new javax.swing.JButton();
        update_jButton = new javax.swing.JButton();
        delete_jButton = new javax.swing.JButton();
        first_jButton = new javax.swing.JButton();
        previous_jButton = new javax.swing.JButton();
        next_jButton = new javax.swing.JButton();
        last_jButton = new javax.swing.JButton();
        description_jLabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        description_jTextArea = new javax.swing.JTextArea();
        image_jLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        id_jLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        id_jLabel.setText("ID:");

        name_jLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        name_jLabel.setText("Name:");

        price_jLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        price_jLabel.setText("Price:");

        addDate_jLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        addDate_jLabel.setText("Add Date:");

        id_jTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        id_jTextField.setEnabled(false);
        id_jTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                id_jTextFieldActionPerformed(evt);
            }
        });

        name_jTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        price_jTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        date_jDateChooser.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NAME", "PRICE", "ADD DATE"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        chooseImage_jButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        chooseImage_jButton.setText("Choose Image");
        chooseImage_jButton.setAutoscrolls(true);
        chooseImage_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseImage_jButtonActionPerformed(evt);
            }
        });

        insert_jButton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        insert_jButton.setText("INSERT");
        insert_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insert_jButtonActionPerformed(evt);
            }
        });

        update_jButton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        update_jButton.setText("UPDATE");
        update_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                update_jButtonActionPerformed(evt);
            }
        });

        delete_jButton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        delete_jButton.setText("DELETE");
        delete_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_jButtonActionPerformed(evt);
            }
        });

        first_jButton.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        first_jButton.setText("FIRST");
        first_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                first_jButtonActionPerformed(evt);
            }
        });

        previous_jButton.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        previous_jButton.setText("PREVIOUS");
        previous_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previous_jButtonActionPerformed(evt);
            }
        });

        next_jButton.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        next_jButton.setText("NEXT");
        next_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                next_jButtonActionPerformed(evt);
            }
        });

        last_jButton.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        last_jButton.setText("LAST");
        last_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                last_jButtonActionPerformed(evt);
            }
        });

        description_jLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        description_jLabel.setText("DESCRIPTION");

        description_jTextArea.setColumns(20);
        description_jTextArea.setRows(5);
        jScrollPane2.setViewportView(description_jTextArea);

        image_jLabel.setBackground(new java.awt.Color(255, 255, 255));
        image_jLabel.setOpaque(true);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(first_jButton)
                        .addGap(18, 18, 18)
                        .addComponent(previous_jButton)
                        .addGap(30, 30, 30)
                        .addComponent(next_jButton)
                        .addGap(27, 27, 27)
                        .addComponent(last_jButton)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(id_jLabel)
                                    .addComponent(price_jLabel)
                                    .addComponent(name_jLabel)
                                    .addComponent(addDate_jLabel))
                                .addGap(37, 37, 37)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(id_jTextField)
                                    .addComponent(price_jTextField)
                                    .addComponent(name_jTextField)
                                    .addComponent(date_jDateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(insert_jButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(update_jButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(delete_jButton))
                            .addComponent(chooseImage_jButton, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(image_jLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(description_jLabel)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane2))
                        .addGap(10, 10, 10))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(id_jLabel)
                            .addComponent(id_jTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(name_jLabel)
                            .addComponent(name_jTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(price_jLabel)
                            .addComponent(price_jTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(addDate_jLabel)
                            .addComponent(date_jDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(64, 64, 64)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(delete_jButton)
                            .addComponent(update_jButton)
                            .addComponent(insert_jButton))
                        .addGap(22, 22, 22)
                        .addComponent(chooseImage_jButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(image_jLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(description_jLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(first_jButton)
                    .addComponent(previous_jButton)
                    .addComponent(next_jButton)
                    .addComponent(last_jButton)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void chooseImage_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseImage_jButtonActionPerformed
        // TODO add your handling code here:
        JFileChooser file = new JFileChooser();
        file.setCurrentDirectory(new File(System.getProperty("user.home")));
        
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.image", "jpg","png");
        file.addChoosableFileFilter(filter);
        int result = file.showSaveDialog(null);
        
        if(result == JFileChooser.APPROVE_OPTION){
            File selectedFile = file.getSelectedFile();
            String path = selectedFile.getAbsolutePath();
            
            image_jLabel.setIcon(ResizeImage(path, null));
            imgPhat = path;
        }else{
            System.out.println("No File Selected");
        }
    }//GEN-LAST:event_chooseImage_jButtonActionPerformed

    private void insert_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insert_jButtonActionPerformed
        
        if(checkInputs() && imgPhat != null){
        
            try {
                
                Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement("INSERT INTO products"
                        + "(name, price, add_date, image, description)"
                        + "values(?,?,?,?,?)");
                
               
                ps.setString(1, name_jTextField.getText());
                ps.setString(2, price_jTextField.getText());
                
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                String addDate = dateFormat.format(date_jDateChooser.getDate());
                ps.setString(3, addDate);
                
                File img = new File(imgPhat);
                FileInputStream fis = new FileInputStream(img);
                ps.setBinaryStream(4, fis,(int)img.length());
                
                ps.setString(5, description_jTextArea.getText());
                
                ps.executeUpdate();
                
                //jTable1.removeAll();
                showProductsInJTable();
                
                JOptionPane.showMessageDialog(null, "Data Added");
                        
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        
        }else{
        
            JOptionPane.showMessageDialog(null, "One Or More Field Are Empty");
            
        }
        
    }//GEN-LAST:event_insert_jButtonActionPerformed

    private void update_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_update_jButtonActionPerformed
        
        if(checkInputs() && id_jTextField.getText() != null){
            
            String updateQuery = null;
            PreparedStatement ps = null;
            Connection con = getConnection();
            
            // update without image
            if(imgPhat == null){
                try {
                    updateQuery = "UPDATE products SET name = ?, price = ?, "
                            + "add_date = ?, description = ? WHERE id = ?";
                    ps = con.prepareStatement(updateQuery);
                    
                    ps.setString(1, name_jTextField.getText());
                    ps.setString(2, price_jTextField.getText());
                    
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    String addDate = dateFormat.format(date_jDateChooser.getDate());
                    ps.setString(3, addDate);
                    
                    ps.setString(4, description_jTextArea.getText());
                    
                    ps.setInt(5, Integer.parseInt( id_jTextField.getText()));
                    
                    ps.executeUpdate();
                    showProductsInJTable();
                    
                    
                    JOptionPane.showMessageDialog(null, "Product Deleted");
                    
                } catch (SQLException ex) {
                    
                    Logger.getLogger(Main_Window.class.getName()).log(Level.SEVERE, null, ex);
                    
                }
                
            }
            //Update with Image
            else{
                try{
                    
                    updateQuery = "UPDATE products SET name = ?, price = ?, "
                                + " add_date = ?, image = ?, description = ? WHERE id = ?";
                    
                    ps = con.prepareStatement(updateQuery);
                    
                    ps.setString(1, name_jTextField.getText());
                    ps.setString(2, price_jTextField.getText());
                    
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    String addDate = dateFormat.format(date_jDateChooser.getDate());
                    ps.setString(3, addDate);
                    
                    File img = new File(imgPhat);
                    FileInputStream fis = new FileInputStream(img);
                    ps.setBinaryStream(4, fis,(int)img.length());
                    
                    ps.setString(5, description_jTextArea.getText());
                    
                    ps.setInt(6, Integer.parseInt( id_jTextField.getText()));
                    
                    ps.executeUpdate();
                    showProductsInJTable();
                    
                    
                    JOptionPane.showMessageDialog(null, "Product Deleted");
                    
                }catch(Exception ex){
                    
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
            
        }else{
            JOptionPane.showMessageDialog(null, "One Or More Fields Are Empty Or Wrong");
        }
    }//GEN-LAST:event_update_jButtonActionPerformed

    private void delete_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_jButtonActionPerformed
        
        if(!id_jTextField.getText().equals("")){
            
            try {
                Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement("DELETE FROM products WHERE id = ?");
                int id = Integer.parseInt(id_jTextField.getText());
                ps.setInt(1, id);
                ps.executeUpdate();
                showProductsInJTable();
                
                JOptionPane.showMessageDialog(null, "Product Deleted");
                
            } catch (SQLException ex) {
                Logger.getLogger(Main_Window.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Product Not Deleted");
            }
                    
        }else{
            JOptionPane.showMessageDialog(null, "Product Not Deleted: No ID To Delete");
        }
    }//GEN-LAST:event_delete_jButtonActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        
        int index = jTable1.getSelectedRow();
        showItem(index); 
    }//GEN-LAST:event_jTable1MouseClicked

    private void first_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_first_jButtonActionPerformed
        int pos = 0;
        showItem(pos);
    }//GEN-LAST:event_first_jButtonActionPerformed

    private void last_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_last_jButtonActionPerformed
        int pos = getProductList().size()-1;
        showItem(pos);
    }//GEN-LAST:event_last_jButtonActionPerformed

    private void next_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_next_jButtonActionPerformed
        pos++;
        
        if(pos >= getProductList().size()){
            pos = getProductList().size()-1;
        }
        
        showItem(pos);
    }//GEN-LAST:event_next_jButtonActionPerformed

    private void previous_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previous_jButtonActionPerformed
        pos--;
        
        if(pos < getProductList().size()){
            pos = 0;
        }
        
        showItem(pos);
    }//GEN-LAST:event_previous_jButtonActionPerformed

    private void id_jTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_id_jTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_id_jTextFieldActionPerformed

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
            java.util.logging.Logger.getLogger(Main_Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main_Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main_Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main_Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main_Window().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel addDate_jLabel;
    private javax.swing.JButton chooseImage_jButton;
    private com.toedter.calendar.JDateChooser date_jDateChooser;
    private javax.swing.JButton delete_jButton;
    private javax.swing.JLabel description_jLabel;
    private javax.swing.JTextArea description_jTextArea;
    private javax.swing.JButton first_jButton;
    private javax.swing.JLabel id_jLabel;
    private javax.swing.JTextField id_jTextField;
    private javax.swing.JLabel image_jLabel;
    private javax.swing.JButton insert_jButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton last_jButton;
    private javax.swing.JLabel name_jLabel;
    private javax.swing.JTextField name_jTextField;
    private javax.swing.JButton next_jButton;
    private javax.swing.JButton previous_jButton;
    private javax.swing.JLabel price_jLabel;
    private javax.swing.JTextField price_jTextField;
    private javax.swing.JButton update_jButton;
    // End of variables declaration//GEN-END:variables
}
