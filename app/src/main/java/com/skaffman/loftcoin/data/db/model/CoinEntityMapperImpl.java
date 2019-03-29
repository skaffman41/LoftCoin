package com.skaffman.loftcoin.data.db.model;

import com.skaffman.loftcoin.data.api.model.Coin;
import com.skaffman.loftcoin.data.api.model.Quote;
import com.skaffman.loftcoin.utils.Fiat;

import java.util.ArrayList;
import java.util.List;

public class CoinEntityMapperImpl implements CoinEntityMapper {
    @Override
    public List<CoinEntity> map(List<Coin> coins) {
        List<CoinEntity> coinEntities = new ArrayList<>();

        for (Coin coin : coins) {
            coinEntities.add(mapEntity(coin));
        }

        return coinEntities;
    }

    private CoinEntity mapEntity(Coin coin) {
        CoinEntity coinEntity = new CoinEntity();

        coinEntity.id = coin.id;
        coinEntity.name = coin.name;
        coinEntity.symbol = coin.symbol;
        coinEntity.slug = coin.slug;
        coinEntity.lastUpdate = coin.lastUpdate;

        coinEntity.usd = mapQuote(coin.quote.get(Fiat.USD.name()));
        coinEntity.eur = mapQuote(coin.quote.get(Fiat.EUR.name()));
        coinEntity.rub = mapQuote(coin.quote.get(Fiat.RUB.name()));

        return coinEntity;
    }

    private QuoteEntity mapQuote(Quote quote) {
        QuoteEntity quoteEntity = new QuoteEntity();

        quoteEntity.price = quote.price;
        quoteEntity.percentChange1h = quote.percentChange1h;
        quoteEntity.percentChange24h = quote.percentChange24h;
        quoteEntity.percentChange7d = quote.percentChange7d;

        return quoteEntity;
    }
}
