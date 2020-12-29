
package imageencryptor;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class Imageencryptor {
    
    public static void operate(int key){
        JFileChooser fc = new JFileChooser();
        fc.showOpenDialog(null);
        File file = fc.getSelectedFile();
        try{
            FileInputStream fis = new FileInputStream(file);
            byte[] data = new byte[fis.available()];
            fis.read(data);
            int i= 0;
            for(byte b : data)
            {
                System.out.println(b);
                data[i]=(byte)(b^key);
                i++;
            }
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(data);
            fos.close();
            fis.close();
            
            JOptionPane.showMessageDialog(null,"done");
            
        }catch(HeadlessException | IOException e){
            System.out.println("error"+e);
        }
    }

    
    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setTitle("image operation");
        f.setSize(400,500);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
        Font fo = new Font("Lucinda",Font.BOLD,20);
        
        JButton b = new JButton();
        
        b.setText("open image");
        b.setFont(fo);
        JTextField tf = new JTextField(10);
        tf.setFont(fo);
        f.setLayout(new FlowLayout());
        b.addActionListener(e->{
        
            System.out.println("button clicked");
            String text = tf.getText();
            System.out.println(text);
            if(text!=" "){
            int temp = Integer.parseInt(text);
            operate(temp);
            }
            else{
                JOptionPane.showMessageDialog(null,"You have to enter something");
            }
        });
        f.add(b);
        f.add(tf);
        f.setVisible(true);
        System.out.println("opened successfully");
    }
    
}
