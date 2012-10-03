package service.storage;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import service.beans.Oficina;
import service.beans.Persona;
import service.beans.Resinto;

public class OficinaStore {
    private static String[][] data =
  {{"1","85479658","4","1234"}};

    private static Map<String,Oficina> store;
    private static OficinaStore instance = null;
    private OficinaStore() {
        store = new HashMap<String,Oficina>();
        initStore();
    }
    public static Map<String,Oficina> getStore() {
        if(instance==null) {
            instance = new OficinaStore();
        }
        return store;
    }
    private void initStore() {
        for (int i=0;i<data.length;i++) {
            Oficina obj = new Oficina();
			obj.setcodigo(data[i][0]);
            obj.settelefono(data[i][1]);
            
			Resinto obj1 = new Resinto();
			obj1.setcodigo(data[i][3]);
			
			obj.setresinto(obj1);
			
            Persona obj2 = new Persona();
			obj2.setcedula(data[i][4]);
            
			obj.setpersona(obj2);
			
			store.put(obj.getcodigo(),obj);
        }
    }
}