package persistance;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import model.Currency;
import model.ExchangeRate;

public class ExchangeRateLoaderFromWeb implements ExchangeRateLoader {

    private static ExchangeRateLoaderFromWeb instance;
    private ExchangeRateLoaderFromWeb (){}
    
    public static ExchangeRateLoaderFromWeb getInstance() {
        if ( instance == null ) instance = new ExchangeRateLoaderFromWeb();
        return instance;
    }
    
    @Override
    public ExchangeRate load(Currency from, Currency to) {
        try {
            URL url =
                    new URL("https://free.currconv.com/api/v7/convert?q=" +
                            from.getCode() + "_" + to.getCode() + 
                            "&compact=ultra&apiKey=6b09f9de604b6502aa1f");
            URLConnection connection = url.openConnection();
            return new ExchangeRate(from, to, 
                    getNumberFromConnection(connection));
            
        } 
        catch (MalformedURLException ex) {} 
        catch (IOException ex) {}
        return new ExchangeRate(from,to,0.0);
    }
    
    private double getNumberFromConnection(URLConnection connection) throws IOException{
        String line = "";
        try (BufferedReader reader = 
                new BufferedReader(
                        new InputStreamReader(connection.getInputStream()))) {
                line = reader.readLine();
                line = line.substring(line.indexOf(":")+1, 
                    line.indexOf("}"));
        }
        return Double.parseDouble(line);
    }
    
}
