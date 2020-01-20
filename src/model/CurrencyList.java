package model;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Arrays;

public class CurrencyList {
    private static CurrencyList instance;
    @SerializedName("results")
    private Map<String,Currency> list;

    private CurrencyList() {
        list = new HashMap();
    }
    
    public static CurrencyList getInstance() {
        if (instance == null) instance = new CurrencyList();
        return instance;
    }
    
    public void add(Currency c){
        list.put(c.getCode(), c);
    }
    
    public Currency get(String key){
        return list.get(key);
    }

    public List<Currency> getList() {
        return new ArrayList<Currency>(list.values());
    }
    
    public String[] getCodeSortedList() {
        String arr[] = new String[list.size()];
        arr = list.keySet().toArray(arr);

        Arrays.sort(arr);
        return arr;
    }
}
