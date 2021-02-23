package model;
import java.util.*;

public class Cart {
    private HashMap<String,Ware> wares= new HashMap<>();
    
    
    public Ware getWare(String code) {
        return wares.get(code);
    }
    
    public void putWare(Ware ware) {
        String wid=ware.getCode();
        if(wares.containsKey(wid)) {
            Ware w=wares.get(wid);
            w.setAmount(w.getAmount()+1);
        }else{
            wares.put(wid, ware);
        }
    }
    
    public void modifyAmount(String ware, int n) {
        Ware w=wares.get(ware);
        if(null==w) return;
        w.setAmount(n);
        if(n==0) wares.remove(ware);
    }
    
    public double getTotal() {
        double t=0;
        for(Map.Entry<String,Ware> e: wares.entrySet()) {
            t+= e.getValue().getMoney();
        }
        return t;
    }
    
    public Collection<Ware> getWares() {
        return wares.values();
    }
}
