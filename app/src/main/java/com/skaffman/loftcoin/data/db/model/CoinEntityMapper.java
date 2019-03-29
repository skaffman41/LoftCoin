package com.skaffman.loftcoin.data.db.model;

import com.skaffman.loftcoin.data.api.model.Coin;

import java.util.List;

public interface CoinEntityMapper {

    List<CoinEntity> map(List<Coin> coins);

}
