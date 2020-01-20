package model;

import persistance.ExchangeRateLoaderFromWeb;

public class MoneyExchanger {
    public static Money exchange(Money from, Currency to){
        ExchangeRateLoaderFromWeb er = ExchangeRateLoaderFromWeb.getInstance();
        ExchangeRate e = er.load(from.getCurrency(), to);
        return new Money(from.getAmount()*e.getRate(),to);
    }
}
