
package chat;

import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class ClaseServidor extends Thread{
    private int puerto;
    private JFrame ventana;
    
    
    public ClaseServidor(JFrame ventana, int port) {
        this.puerto = port;
        this.ventana = ventana;
    }
    
    public void run(){
        ServerSocket ss=null;
        try{
            ss=new ServerSocket(puerto);
            while (true){
                //agregamos al array (insertamos)
                Socket s=ss.accept();
                AdministraConexion.getInstance().conectaNuevo(new ClaseConexion(s));
            }
            //JOptionPane.showMessageDialog(ventana,"Se han conectado");
        }catch(Exception e){
            JOptionPane.showMessageDialog(ventana,"Error. Posiblemente ya esté en uso.");
        }
        try{
            ss.close();
        }catch(Exception e){
        }
    }
}
