
package chat;

import java.util.ArrayList;

public class AdministraConexion {
    private static AdministraConexion singleton=new AdministraConexion();
    
    public  static AdministraConexion getInstance(){
        return singleton;
    }
    
    private ArrayList<ClaseConexion> conexiones = new ArrayList<ClaseConexion>();
    
    public void enviarTrama(int nCodigo, String sTrama){
        for (ClaseConexion ms:conexiones){
            ms.enviarTrama(nCodigo, sTrama);
        }
    }
    
    public void conectaNuevo(ClaseConexion nuevo){
        for (ClaseConexion ms:conexiones){
            nuevo.enviarTrama(1, ms.getNombre());
        }
        conexiones.add(nuevo);
    }
    
    public void desconecta(ClaseConexion eliminar){
        int nPos=-1;
        for (int n=0;n<conexiones.size();n++){
            if (conexiones.get(n)==eliminar){
                nPos=n;
            }
        }
        if (nPos!=-1){
            for (int n=0;n<conexiones.size();n++){
                if (n!=nPos){
                    conexiones.get(n).enviarTrama(3, ""+nPos);
                }
            }
            conexiones.remove(nPos);
        }
    }    
}
