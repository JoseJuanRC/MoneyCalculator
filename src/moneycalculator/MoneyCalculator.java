package moneycalculator;

import model.CurrencyList;
import persistance.CurrencyLoaderJsonFile;
import persistance.CurrencyLoaderJsonURL;
import ui.MoneyExchangeWindow;
import ui.SwingMoneyExchangeWindow;


public class MoneyCalculator {

    
    public static void main(String[] args) {
        new MoneyCalculator();        
    }

    public MoneyCalculator(){
        MoneyExchangeWindow mew = new SwingMoneyExchangeWindow(readCurrency());
        mew.execute();    
    }

    
    private CurrencyList readCurrency() {  
        CurrencyList list = null;
        list = CurrencyLoaderJsonURL.getInstance().load();
        if (list == null)
            list = CurrencyLoaderJsonFile.getInstance().load();
        return list;
    }    
}
