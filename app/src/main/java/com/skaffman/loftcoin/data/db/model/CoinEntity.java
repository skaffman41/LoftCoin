package com.skaffman.loftcoin.data.db.model;

import com.skaffman.loftcoin.utils.Fiat;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Coin")
public class CoinEntity {
    @PrimaryKey
    public int id;
    public String name;
    public String symbol;
    public String slug;
    public String lastUpdate;

    @Embedded(prefix = "usd_")
    public QuoteEntity usd;
    @Embedded(prefix = "eur_")
    public QuoteEntity eur;
    @Embedded(prefix = "rub_")
    public QuoteEntity rub;

    public QuoteEntity getQuote(Fiat fiat) {
        switch (fiat) {
            case USD:
                return usd;
            case EUR:
                return eur;
            case RUB:
                return rub;

        }
        return usd;
    }
}
