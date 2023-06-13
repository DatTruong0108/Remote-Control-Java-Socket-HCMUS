package client;

import java.net.Socket;
import javax.swing.JOptionPane;
import java.io.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.status.StatusLogger;

public class Client extends javax.swing.JFrame {
    public static Socket socket;
    public static InputStream in;
    
    public Client() {
        initComponents();
        setCustomText();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        clientLabel = new javax.swing.JLabel();
        enteripLabel = new javax.swing.JLabel();
        ipTextField = new javax.swing.JTextField();
        connectButton = new javax.swing.JButton();
        processButton = new javax.swing.JButton();
        appButton = new javax.swing.JButton();
        keyloggerButton = new javax.swing.JButton();
        picButton = new javax.swing.JButton();
        shutdownButton = new javax.swing.JButton();
        exitButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        infoButton = new javax.swing.JButton();
        displayButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        clientLabel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        clientLabel.setText("Client");

        enteripLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        enteripLabel.setText("Enter IP:");

        connectButton.setText("Connect");
        connectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectButtonActionPerformed(evt);
            }
        });

        processButton.setText("Running Process");
        processButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                processButtonActionPerformed(evt);
            }
        });

        appButton.setText("Running Applications");
        appButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                appButtonActionPerformed(evt);
            }
        });

        keyloggerButton.setText("KeyStroke");
        keyloggerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keyloggerButtonActionPerformed(evt);
            }
        });

        picButton.setText("Print screen");
        picButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                picButtonActionPerformed(evt);
            }
        });

        shutdownButton.setText("Shut down");
        shutdownButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shutdownButtonActionPerformed(evt);
            }
        });

        exitButton.setText("Exit");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });

        infoButton.setText("Get info");
        infoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                infoButtonActionPerformed(evt);
            }
        });

        displayButton.setText("Display");
        displayButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                displayButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator2))
            .addGroup(layout.createSequentialGroup()
                .addGap(172, 172, 172)
                .addComponent(clientLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(enteripLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(ipTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                        .addComponent(connectButton, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(processButton, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(shutdownButton, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(displayButton, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(picButton, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(appButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(infoButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(exitButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(keyloggerButton, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(clientLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(enteripLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ipTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(connectButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(appButton, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(picButton, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                            .addComponent(displayButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(keyloggerButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(processButton, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(shutdownButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(exitButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                    .addComponent(infoButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void setCustomText()
    {
        //enteripLabel.setText("Nhập IP:");
        //connectBut.setText("Kết nối");
        picButton.setText("<html><center>Print<br>screenshot</center></html>");
        //shutdownBut.setText("<html><center>Shut<br>down</center></html>");
        //exitBut.setText("Thoát");
        processButton.setText("<html><center>Running<br>Processes</center></html>");
        //appBut.setText("<html><center>Running<br>applications</center></html>");
    }
    private void processButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_processButtonActionPerformed
        if(socket == null)
        {
            JOptionPane.showMessageDialog(this, "Chưa kết nối đến server");
            return;
        }
        try
        {
            ProcessRunning pr = new ProcessRunning();
            pr.setVisible(true);
            pr.setDefaultCloseOperation(Pic.DISPOSE_ON_CLOSE);
        }
        catch(Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi gửi dữ liệu:\n" + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_processButtonActionPerformed

    private void appButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_appButtonActionPerformed
        if(socket == null)
        {
            JOptionPane.showMessageDialog(this, "Chưa kết nối đến server");
            return;
        }
        try {
            AppRunning ar = new AppRunning();
            ar.setVisible(true);
            ar.setDefaultCloseOperation(Pic.DISPOSE_ON_CLOSE);
        } catch(Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi gửi dữ liệu:\n" + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_appButtonActionPerformed

    private void keyloggerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_keyloggerButtonActionPerformed
        if(socket == null)
        {
            JOptionPane.showMessageDialog(this, "Chưa kết nối đến server");
            return;
        }
        try
        {
            Keylogger keylog = new Keylogger();
            keylog.setVisible(true);
            keylog.setDefaultCloseOperation(Pic.DISPOSE_ON_CLOSE);
        }
        catch(Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi gửi dữ liệu:\n" + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_keyloggerButtonActionPerformed

    private void connectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectButtonActionPerformed
        try{
            int port = 5656;
            
            System.out.println(ipTextField.getText());
            socket = new Socket(ipTextField.getText(), port);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this, "Lỗi kết nối:\n" + e.getMessage());
            e.printStackTrace();

            try {
                if (socket != null) {
                    socket.close();
                }
            } 
            catch (IOException ioe) {
                ioe.printStackTrace();
            }   
        }

        if(socket!=null)
        {
            JOptionPane.showMessageDialog(this, "Kết nối đến server thành công");
        }
    }//GEN-LAST:event_connectButtonActionPerformed

    private void shutdownButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shutdownButtonActionPerformed
        if(socket == null)
        {
            JOptionPane.showMessageDialog(this, "Chưa kết nối đến server");
            return;
        }
        try
        {
            String message = "Shutdown";
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF(message);
            out.flush();
        }
        catch(Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi gửi dữ liệu:\n" + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_shutdownButtonActionPerformed

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
//        String message = "Exit";
//        if(socket != null)
//        {
//            try
//            {
//                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
//                out.writeUTF(message);
//                out.flush();
//            }
//            catch(Exception e)
//            {
//                JOptionPane.showMessageDialog(this, "Lỗi gửi dữ liệu:\n" + e.getMessage());
//                e.printStackTrace();
//            }
//        }
        System.exit(0);
    }//GEN-LAST:event_exitButtonActionPerformed

    private void picButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_picButtonActionPerformed
        if(socket == null)
        {
            JOptionPane.showMessageDialog(this, "Chưa kết nối đến server");
            return;
        }
        try
        {
            Pic pic = new Pic();
            pic.setVisible(true);
            pic.setDefaultCloseOperation(Pic.DISPOSE_ON_CLOSE);
        }
        catch(Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi gửi dữ liệu:\n" + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_picButtonActionPerformed

    private void infoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_infoButtonActionPerformed
        if(socket == null)
        {
            JOptionPane.showMessageDialog(this, "Chưa kết nối đến server");
            return;
        }
        try
        {
            Info info = new Info();
            info.setVisible(true);
            info.setDefaultCloseOperation(Pic.DISPOSE_ON_CLOSE);
        }
        catch(Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi:\n" + e.getMessage());
        }
    }//GEN-LAST:event_infoButtonActionPerformed

    private void displayButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_displayButtonActionPerformed
        if(socket == null)
        {
            JOptionPane.showMessageDialog(this, "Chưa kết nối đến server");
            return;
        }
        try
        {
            Monitor rec = new Monitor();
            rec.setVisible(true);
            rec.setDefaultCloseOperation(rec.DISPOSE_ON_CLOSE);
            rec.receiveScreen();
        }
        catch(Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi:\n" + e.getMessage());
        }
    }//GEN-LAST:event_displayButtonActionPerformed

    public static void main(String args[]) {
        StatusLogger.getLogger().setLevel(Level.OFF);
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Client().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton appButton;
    private javax.swing.JLabel clientLabel;
    private javax.swing.JButton connectButton;
    private javax.swing.JButton displayButton;
    private javax.swing.JLabel enteripLabel;
    private javax.swing.JButton exitButton;
    private javax.swing.JButton infoButton;
    private javax.swing.JTextField ipTextField;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JButton keyloggerButton;
    private javax.swing.JButton picButton;
    private javax.swing.JButton processButton;
    private javax.swing.JButton shutdownButton;
    // End of variables declaration//GEN-END:variables
}
