package client;

import java.awt.Graphics;
import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ReceiveScreen_thread extends Thread {
    
    Socket socket = null;
    JPanel displayPanel;
    public static Image image1;

    public ReceiveScreen_thread(Socket socket, JPanel displayPanel) {
      this.socket = socket;
      this.displayPanel = displayPanel;
    }
    @Override
    public void run()
    {
        try {
        while (true) {
            DataInputStream input = new DataInputStream(socket.getInputStream());
            String width = input.readUTF();
            String height = input.readUTF();
            int len = input.readInt();
            byte[] data = new byte[len];
            input.readFully(data);
            image1 = ImageIO.read(new ByteArrayInputStream(data));
            image1 = image1.getScaledInstance(displayPanel.getWidth(),displayPanel.getHeight(),
                        Image.SCALE_FAST);
            Graphics graphics = displayPanel.getGraphics();
            graphics.drawImage(image1,0,0,displayPanel.getWidth(),displayPanel.getHeight(),displayPanel);
        }
    } catch (IOException ex) {
      ex.printStackTrace();
    }
    try {
        Thread.sleep(10);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
}
