package persistance;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.CurrencyList;


public class CurrencyLoaderJsonURL implements CurrencyLoader {
    
    private static CurrencyLoaderJsonURL instance;
    
    private CurrencyLoaderJsonURL(){}
    
    public static CurrencyLoaderJsonURL getInstance() {
        if (instance == null){
            instance = new CurrencyLoaderJsonURL();
        }
        return instance;
    }
    
    public CurrencyList load() {
        Gson gson = new Gson();
        try {
            URL url = new URL("https://free.currconv.com/api/v7/currencies?apiKey=do-not-use-this-key");
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            CurrencyList l = gson.fromJson(br, CurrencyList.class);
            return l;
        } catch (MalformedURLException ex) {
            return null;
        } catch (IOException ex) {
            return null;
        }
    }
    
    
}
