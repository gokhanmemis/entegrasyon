package entegrasyon;

import java.io.IOException;
import java.math.BigDecimal;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.bittrex.BittrexExchange;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order.OrderType;
import org.knowm.xchange.dto.account.AccountInfo;
import org.knowm.xchange.dto.account.Balance;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.kucoin.KucoinExchange;
import org.knowm.xchange.service.account.AccountService;
import org.knowm.xchange.service.marketdata.MarketDataService;
import org.knowm.xchange.service.trade.TradeService;

public class Bittrex {

	public static void main(String[] args) throws IOException {
		

		/*
		MarketDataService marketDataService = bitstamp2.getMarketDataService();

		Ticker ticker = marketDataService.getTicker(CurrencyPair.BTC_USDT);

		System.out.println(ticker.toString());
		*/
		
		ExchangeSpecification exSpec = new ExchangeSpecification(BittrexExchange.class);
		exSpec.setUserName("--------------");
		exSpec.setApiKey("--------------");
		exSpec.setSecretKey("--------------");
		Exchange Bittrex = ExchangeFactory.INSTANCE.createExchange(exSpec);

		// Get the account information
		AccountService accountService = Bittrex.getAccountService();
		AccountInfo accountInfo = accountService.getAccountInfo();

		for (Currency currency : accountInfo.getWallet().getBalances().keySet()) {
			Balance balance = accountInfo.getWallet().getBalances().get(currency);
			if(balance.getTotal().compareTo(BigDecimal.ZERO) > 0){
				System.err.println(currency +" --> "+balance.getTotal());
			}
		}
		
		/*
		TradeService tradeService = Bittrex.getTradeService();
		// place a limit buy order
	    LimitOrder limitOrder = new LimitOrder((OrderType.ASK), new BigDecimal("1500"), new CurrencyPair(Currency.DOGE, Currency.BTC), null, null, new BigDecimal("0.00008300"));
	    String limitOrderReturnValue = tradeService.placeLimitOrder(limitOrder);
	    System.out.println("Limit Order return value: " + limitOrderReturnValue);
	    */
		
	}
	
}
