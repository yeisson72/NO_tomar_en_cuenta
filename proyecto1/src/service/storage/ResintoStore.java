package service.storage;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import service.beans.Resinto;

public class ResintoStore {
    private static String[][] data = {
    {"1","Sabana Sur","5","10b","400","40","10","Empresa de servicios de software"},
    {"2","Pavas","40","5","100","32","10", "Productora de alimentos a base de masa"},
    {"3","Romuser","56","2","25","60","45.2","Venta de vehiculos cacharrito"},
    {"4","pocosol","9","7","10","25","23", "Oficina de correos"}};
    private static Map<String,Resinto> store;
    private static ResintoStore instance = null;
    private ResintoStore() {
        store = new HashMap<String,Resinto>();
        initStore();
    }
    public static Map<String,Resinto> getStore() {
        if(instance==null) {
            instance = new ResintoStore();
        }
        return store;
    }
    private void initStore() {
        for (int i=0;i<data.length;i++) {
            Resinto obj = new Resinto();
            obj.setcodigo(data[i][0]);
			obj.setzona(data[i][1]);
			obj.setcalle(data[i][2]);
            obj.setavenida(data[i][3]);
            obj.setdistancia(data[i][4]);
            obj.setlatitud(data[i][5]);
            obj.setlongitud(data[i][6]);
            obj.setrazon_social(data[i][7]);
            store.put(obj.getcodigo(),obj);
        }
    }
}