
package chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import javax.swing.JOptionPane;
import proyecto.Usuario;
public class ClaseUsuario extends Thread{
    private int puerto;
    private String url;
    private Socket socket;
    private boolean estadoConexion;
    Usuario ventana;
    private String nombre;
    
    //Constructor
    public ClaseUsuario(int port, String url, String nick, Usuario ventana) {
        this.puerto=port;
        this.url=url;
        this.ventana=ventana;
        this.nombre=nick;
    }
    
    public void run(){
        try{
            socket=new Socket(url, puerto);
            DataInputStream dis=new DataInputStream(socket.getInputStream());
            enviarTrama(1,nombre);
            estadoConexion=true;
            while(estadoConexion){
                int nCodigo =dis.readInt();
                String sTrama=dis.readUTF();
                switch(nCodigo){
                    case 1:
                        ventana.nuevaPersona(sTrama);
                        break;
                    case 2:
                        ventana.mensajeRecibido(sTrama);
                        break;
                    case 3:
                        try{
                            int nPos = Integer.parseInt(sTrama);
                            ventana.borrarPersona(nPos);
                        }catch(Exception e2){
                        }
                        break;
                }
            }
            //JOptionPane.showMessageDialog(ventana, "Se ha podido conectar");
        }catch(Exception e){
            JOptionPane.showMessageDialog(ventana, "No se pudo establecer la conexión");
        }
    }
    
    public void enviarMensaje(String sMensaje){
        enviarTrama(2, sMensaje);
    }
    
    public void enviarTrama(int nCodigo, String sTrama){
        try{
            DataOutputStream dos=new DataOutputStream(socket.getOutputStream());
            dos.writeInt(nCodigo);
            dos.writeUTF(sTrama);
        }catch(Exception e){
            JOptionPane.showMessageDialog(ventana, "No se pudo enviar el mensaje");
        }
        
    }
}
