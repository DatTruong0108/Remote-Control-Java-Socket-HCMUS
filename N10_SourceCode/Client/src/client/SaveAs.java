package client;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.TableModel;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class SaveAs {
    private BufferedImage image = null;
    String path = null;
    JTable j1 = null;
    JTable j2 = null;
    JTextArea jtext = null;
    JFrame frame = new JFrame("Thông báo lưu file");
    
    public SaveAs(JTextArea jtext)
    {
        this.jtext = jtext;
    }
    public SaveAs(BufferedImage image) {
        this.image = image;
    }
    
    public SaveAs(JTable j1, String path) {
        this.j1 = j1;
        this.path = path;
    }
    
    public SaveAs(JTable j1, JTable j2, String path) {
        this.j1 = j1;
        this.j2 = j2;
        this.path = path;
    }
    
    public void saveJPG() throws IOException
    {
        if(image == null)
        {
            System.out.println("Không có ảnh để lưu!");
            return;
        }
        JFrame parentFrame = new JFrame();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save screenshot file");   
 
        int userSelection = fileChooser.showSaveDialog(parentFrame);
 
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();           
            ImageIO.write(image, "jpg", fileToSave);
            
            JOptionPane.showMessageDialog(frame, "Lưu thành công file .jpg");
        }
    }
    
    public void save_to_docx()
    {
        JFrame parentFrame = new JFrame();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Export in Word");   
        int userSelection = fileChooser.showSaveDialog(parentFrame);
 
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            try(XWPFDocument doc = new XWPFDocument()) {
                XWPFParagraph p1 = doc.createParagraph();
                p1.setAlignment(ParagraphAlignment.CENTER);
                
                XWPFRun r1 = p1.createRun();
                r1.setBold(true);
                r1.setFontSize(22);
                r1.setColor("ff0000");
                r1.setText("KEYLOGGER");
                r1.setFontFamily("Times New Roman");
                
                XWPFParagraph p2 = doc.createParagraph();
            
                XWPFRun r2 = p2.createRun();
                r2.setText(jtext.getText());
                
                FileOutputStream out = new FileOutputStream(fileToSave.getAbsolutePath());
                doc.write(out);
                doc.close();
                JOptionPane.showMessageDialog(frame, "Lưu thành công file .docx");
            } catch (IOException ex) {
                Logger.getLogger(ProcessRunning.class.getName()).log(Level.SEVERE, null, ex);
            }catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public void tables_to_xlsx() throws FileNotFoundException, IOException {
        Workbook wb = new XSSFWorkbook(); 
        //sheet 1: OS
        Sheet st = wb.createSheet();
        wb.setSheetName(0, "OS");
        Row r = st.createRow(1); 
        TableModel osModel = j1.getModel(); 

        Row headerR = st.createRow(0);
        for(int i = 0; i < osModel.getColumnCount(); i++) //iterator headings
        {
            headerR.createCell(i).setCellValue(osModel.getColumnName(i));
        }

        for(int rows = 0; rows < osModel.getRowCount(); rows++){ //For each table row
            for(int cols = 0; cols < j1.getColumnCount(); cols++){ //For each table column
                r.createCell(cols).setCellValue(osModel.getValueAt(rows, cols).toString()); //Write value
            }
            r = st.createRow((rows + 2)); 
        }
        
        //Sheet 2: Fill sys root
        Sheet st2 = wb.createSheet();
        wb.setSheetName(1, "File_system_root");
        Row r2 = st2.createRow(1); 
        TableModel filerootModel = j2.getModel(); 
         

        Row headerR2 = st2.createRow(0);
        for(int i = 0; i < filerootModel.getColumnCount(); i++) //iterator headings
        {
            headerR2.createCell(i).setCellValue(filerootModel.getColumnName(i));
        }

        for(int rows = 0; rows < filerootModel.getRowCount(); rows++){ //For each table row
            for(int cols = 0; cols < j2.getColumnCount(); cols++){ //For each table column
                r2.createCell(cols).setCellValue(filerootModel.getValueAt(rows, cols).toString()); //Write value
            }
            r2 = st2.createRow((rows + 2)); 
        }
    wb.write(new FileOutputStream(path));
    wb.close();
    JOptionPane.showMessageDialog(frame, "Lưu thành công file .xlsx");
}
    
    public void table_to_xlsx() throws FileNotFoundException, IOException {
        Workbook wb = new XSSFWorkbook(); 
        Sheet st = wb.createSheet(); 
        wb.setSheetName(0, "Hack");
        Row r = st.createRow(1); 
        TableModel model = j1.getModel(); 

        Row headerR = st.createRow(0);
        for(int i = 0; i < model.getColumnCount(); i++) //iterator headings
        {
            headerR.createCell(i).setCellValue(model.getColumnName(i));//Write column name
        }

        for(int rows = 0; rows < model.getRowCount(); rows++){ //For each table row
            for(int cols = 0; cols < j1.getColumnCount(); cols++){ //For each table column
                r.createCell(cols).setCellValue(model.getValueAt(rows, cols).toString()); //Write value
            } 
            r = st.createRow((rows + 2)); 
        }
    wb.write(new FileOutputStream(path));
    wb.close();
    JOptionPane.showMessageDialog(frame, "Lưu thành công file .xlsx");
}
}
