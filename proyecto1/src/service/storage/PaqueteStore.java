package service.storage;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import service.beans.Paquete;
import service.beans.Resinto;

public class PaqueteStore {
    private static String[][] data =
  {{"1","Jose Perez","Maria Tereza","1","1","NO","0"}};

    private static Map<String,Paquete> store;
    private static PaqueteStore instance = null;
    private PaqueteStore() {
        store = new HashMap<String,Paquete>();
        initStore();
    }
    public static Map<String,Paquete> getStore() {
        if(instance==null) {
            instance = new PaqueteStore();
        }
        return store;
    }
    private void initStore() {
        for (int i=0;i<data.length;i++) {
            Paquete obj = new Paquete();
			obj.setcodigo(data[i][0]);
            obj.setremitente(data[i][1]);
			obj.setdestinatario(data[i][2]);
			
			Resinto obj1 = new Resinto();
			obj1.setcodigo(data[i][3]);
			
			obj.setresintos(obj1);
			
			obj.setpeso(data[i][4]);
			obj.setcobro(data[i][5]);
            obj.setmonto(data[i][6]);
			
			store.put(obj.getcodigo(),obj);
        }
    }
}