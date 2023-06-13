package client;

import java.io.*;
import static client.Client.socket;
import javax.swing.JOptionPane;

public class KillProcess extends javax.swing.JFrame {

    public KillProcess() {
        initComponents();
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        kill_id = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("KILL");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                killActionPerformed(evt);
            }
        });

        jLabel1.setText("KILL PROCESS");

        jLabel2.setText("Enter ID");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(kill_id, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(kill_id, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void killActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_killActionPerformed
        // TODO add your handling code here:
        try {
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF("Kill");
            out.flush();
            
            String id = kill_id.getText();
            DataOutputStream out_2 = new DataOutputStream(socket.getOutputStream());
            out_2.writeUTF(id);
            out_2.flush();
            DataInputStream fromSV = new DataInputStream(socket.getInputStream());
            String checkKilled = fromSV.readUTF();
            System.out.println(checkKilled);
            if("Yes".equals(checkKilled)) {
                    System.out.println("Killed process successfully!");
                    JOptionPane.showMessageDialog(this, "KIlled process successfully!");
            }
            else if("Not found".equals(checkKilled)) {
                    System.out.println("The process is not running!");
                    JOptionPane.showMessageDialog(this, "The process is not running!");
            }
            else if("No".equals(checkKilled)) {
                System.out.println("Can't kill process!");
                JOptionPane.showMessageDialog(this, "Can't kill process!");
            }
        } catch (IOException e1) {
                // TODO Auto-generated catch block
                System.out.print("Can't kill process");
                JOptionPane.showMessageDialog(this, "Can't kill process!");
                e1.printStackTrace();
        }
    }//GEN-LAST:event_killActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField kill_id;
    // End of variables declaration//GEN-END:variables
}
