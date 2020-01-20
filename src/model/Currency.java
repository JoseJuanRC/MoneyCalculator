package model;

import com.google.gson.annotations.SerializedName;

public class Currency {
    @SerializedName("id")
    private String code;
    @SerializedName("currencyName")
    private String name;
    @SerializedName("currencySymbol")
    private String symbol;

    public Currency(String code, String name, String symbol) {
        this.code = code;
        this.name = name;
        this.symbol = symbol;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return code + "(" + name + ")" + symbol;
    }
}
