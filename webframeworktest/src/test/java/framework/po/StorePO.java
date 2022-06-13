package framework.po;

import java.util.HashMap;

public class StorePO {

    private static StorePO store;

    private HashMap<String, BasePagePO> storePo;

    public static StorePO getInstance(){
        if(store == null){
            store = new StorePO();
        }
        return store;
    }

    public void setStorePo(String name, BasePagePO page) {
        storePo.put(name,page);
    }

    public BasePagePO getStorePo(String name) {
        return storePo.get(name);
    }
}
