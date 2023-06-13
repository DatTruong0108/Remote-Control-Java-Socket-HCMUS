package server;
        
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import javax.imageio.ImageIO;

class SendScreen extends Thread{

  Socket socket = null;
  

  public SendScreen(Socket socket) {
    this.socket = socket;
    start();
  }
  

  @Override
  public void run() {
    while (true) {        
      try {
        
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        GraphicsEnvironment gEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gDev = gEnv.getDefaultScreenDevice();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        String width = "" + dim.getWidth();
        String height = "" + dim.getHeight();
        
        dos.writeUTF(width);
        dos.writeUTF(height);
        Robot robot = new Robot(gDev);
        
        Rectangle rectangle = new Rectangle(dim);
        BufferedImage image = robot.createScreenCapture(rectangle);
        image.flush();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", baos);
        baos.flush();
        byte[] bytes = baos.toByteArray();
        baos.close();
        System.out.println("Sending image to client. ");
        dos.writeInt(bytes.length);
        System.out.println(bytes.length);
        dos.write(bytes, 0, bytes.length);
        dos.flush();
        
        StringSelection stringSelection = new StringSelection("");
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
     
      } catch (IOException ex) {
        ex.printStackTrace();
      } catch (Exception e) {
        e.printStackTrace();
      }
      
      try {
        Thread.sleep(10);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    
  }
  
}
