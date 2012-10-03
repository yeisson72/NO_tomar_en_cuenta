package service.storage;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import service.beans.Persona;

public class PersonaStore {
    private static String[][] data = {
    {"1234","Lusia","88888888","Supervisor","2"},
    {"2345","Carmen","99999999","Cliente","1"},
    {"3456","Maria","11111111","Personal","3"},
    {"4567","Olivia","86968586","Personal","3"}};
    private static Map<String,Persona> store;
    private static PersonaStore instance = null;
    private PersonaStore() {
        store = new HashMap<String,Persona>();
        initStore();
    }
    public static Map<String,Persona> getStore() {
        if(instance==null) {
            instance = new PersonaStore();
        }
        return store;
    }
    private void initStore() {
        for (int i=0;i<data.length;i++) {
            Persona obj = new Persona();
            obj.setcedula(data[i][0]);
			obj.setnombre(data[i][1]);
			obj.settelefono(data[i][2]);
            obj.setopcervacion(data[i][3]);
            obj.settipo(data[i][4]);
            store.put(obj.getcedula(),obj);
        }
    }
}