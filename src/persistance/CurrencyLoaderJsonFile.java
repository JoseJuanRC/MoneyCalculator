/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistance;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.CurrencyList;

/**
 *
 * @author josejuan
 */
public class CurrencyLoaderJsonFile implements CurrencyLoader {

    private static CurrencyLoaderJsonFile instance;
    
    private CurrencyLoaderJsonFile() {}
    
    public static CurrencyLoaderJsonFile getInstance() {
        if (instance == null) instance = new CurrencyLoaderJsonFile();
        return instance;
    }
    @Override
    public CurrencyList load() {
        Gson gson = new Gson();
        try {
            BufferedReader br = new BufferedReader(
                    new FileReader("currencies\\currencies.json"));
            CurrencyList l = gson.fromJson(br, CurrencyList.class);
            return l;
        } catch (FileNotFoundException ex) {
            return null;
        }    
    }
    
}
