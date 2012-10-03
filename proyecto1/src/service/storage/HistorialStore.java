package service.storage;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import service.beans.Historial;
import service.beans.Persona;
import service.beans.Paquete;

public class  HistorialStore {
    private static String[][] data =
  {{"1","3456","2012/12/12","Se examina y pesa","1"},
     {"2","4567","2012/08/09","Se clasifica y asigna a ruta","1"}};

    private static Map<String,Historial> store;
    private static HistorialStore instance = null;
    private HistorialStore() {
        store = new HashMap<String,Historial>();
        initStore();
    }
    public static Map<String,Historial> getStore() {
        if(instance==null) {
            instance = new HistorialStore();
        }
        return store;
    }
    private void initStore() {
        for (int i=0;i<data.length;i++) {
            Historial obj = new Historial();
			obj.setcodigo(data[i][0]);
			
			Persona obj2 = new Persona();
			obj2.setcedula(data[i][1]);
            
			obj.setpersona(obj2);
			
            obj.setfecha(data[i][2]);
			obj.setdescripcion(data[i][3]);  

			Paquete obj1 = new Paquete();
			obj1.setcodigo(data[i][4]);
            
			obj.setpaquete(obj1);			
			
			store.put(obj.getcodigo(),obj);
            
        }
    }
}