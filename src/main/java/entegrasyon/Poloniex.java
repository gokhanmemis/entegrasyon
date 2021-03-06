package entegrasyon;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.account.AccountInfo;
import org.knowm.xchange.dto.account.Balance;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.dto.trade.UserTrade;
import org.knowm.xchange.kucoin.KucoinExchange;
import org.knowm.xchange.poloniex.PoloniexExchange;
import org.knowm.xchange.poloniex.service.PoloniexTradeService;
import org.knowm.xchange.service.account.AccountService;
import org.knowm.xchange.service.marketdata.MarketDataService;

public class Poloniex {

	public static void main(String[] args) throws IOException {
		
		/*
		MarketDataService marketDataService = bitstamp2.getMarketDataService();

		Ticker ticker = marketDataService.getTicker(CurrencyPair.BTC_USDT);

		System.out.println(ticker.toString());
		*/
		
		ExchangeSpecification exSpec = new ExchangeSpecification(PoloniexExchange.class);
		exSpec.setUserName("---------");
		exSpec.setApiKey("--------------");
		exSpec.setSecretKey("--------------");
		Exchange poloniex = ExchangeFactory.INSTANCE.createExchange(exSpec);

		// Get the account information
		AccountService accountService = poloniex.getAccountService();
		AccountInfo accountInfo = accountService.getAccountInfo();


		for (Currency currency : accountInfo.getWallet().getBalances().keySet()) {
			Balance balance = accountInfo.getWallet().getBalances().get(currency);
			if(balance.getTotal().compareTo(BigDecimal.ZERO) > 0){
				System.err.println(currency +" --> "+balance.getTotal());
			}
		}
		
		PoloniexTradeService.PoloniexTradeHistoryParams params =
                ((PoloniexTradeService.PoloniexTradeHistoryParams) poloniex.getTradeService().createTradeHistoryParams());
        params.setStartTime(new Date(1498780800000L));
        params.setEndTime(new Date());

        List<UserTrade> list = poloniex
                .getTradeService()
                .getTradeHistory(params)
                .getUserTrades();
		
	}
	
}
