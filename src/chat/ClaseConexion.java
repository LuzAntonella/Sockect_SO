
package chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class ClaseConexion extends Thread{
    private Socket socket;
    private DataInputStream dis;
    private DataOutputStream dos;
    private String nombre;
    
    /** Creates a new instance of MSConexion */
    public ClaseConexion (Socket s) {
        try{
            this.socket=s;
            dis=new DataInputStream(s.getInputStream());
            dos=new DataOutputStream(s.getOutputStream());
            start();
        }catch(Exception e){
        }
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public void run(){
        while (true){
            try{
                int nCodigo=dis.readInt();
                String sTrama=dis.readUTF();
                switch(nCodigo){
                    case 1:
                        nombre=sTrama;
                        AdministraConexion.getInstance().enviarTrama(nCodigo, sTrama);
                        break;
                    case 2:
                        sTrama="" + nombre + " : " + sTrama;
                        AdministraConexion.getInstance().enviarTrama(nCodigo, sTrama);
                        break;
                    case 3:
                        AdministraConexion.getInstance().desconecta(this);
                        break;
                }
                
            }catch(Exception e){
            }
            
        }
    }
    
    public void enviarTrama(int nCodigo, String sTrama){
        try{
           dos.writeInt(nCodigo);
           dos.writeUTF(sTrama);
        }catch(Exception e){
        }
    }
}
