package service.storage;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import service.beans.Vehiculo;

public class  VehiculoStore {
    private static String[][] data =
  {{"125yg","Sanjose centro"},
     {"404php","Alajuela desamparados"}};

    private static Map<String,Vehiculo> store;
    private static VehiculoStore instance = null;
    private VehiculoStore() {
        store = new HashMap<String,Vehiculo>();
        initStore();
    }
    public static Map<String,Vehiculo> getStore() {
        if(instance==null) {
            instance = new VehiculoStore();
        }
        return store;
    }
    private void initStore() {
        for (int i=0;i<data.length;i++) {
            Vehiculo obj = new Vehiculo();
			obj.setplaca((data[i][0]));
            obj.setruta(data[i][1]);     
			
			store.put(obj.getplaca(),obj);
            
        }
    }
}