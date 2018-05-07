package entegrasyon;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.account.AccountInfo;
import org.knowm.xchange.dto.account.Balance;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.kucoin.KucoinExchange;
import org.knowm.xchange.service.account.AccountService;
import org.knowm.xchange.service.marketdata.MarketDataService;

public class Kucoin {

	public static void main(String[] args) throws IOException {
		
		ExchangeSpecification exSpec = new ExchangeSpecification(KucoinExchange.class);
	    exSpec.setApiKey("5aa950603f705c1142b5afdd");
	    exSpec.setSecretKey("d8911725-6558-440c-a83e-7fb84f124e50");
		exSpec.setUserName("gokhanmemis@gmail.com");

		Exchange kucoin = ExchangeFactory.INSTANCE.createExchange(exSpec);
		MarketDataService marketDataService = kucoin.getMarketDataService();

		Ticker ticker = marketDataService.getTicker(CurrencyPair.BTC_USDT);

		System.out.println(ticker.toString());
		

		// Get the account information
		AccountService accountService = kucoin.getAccountService();
		AccountInfo accountInfo = accountService.getAccountInfo();


		for (Currency currency : accountInfo.getWallet().getBalances().keySet()) {
			Balance balance = accountInfo.getWallet().getBalances().get(currency);
			if(balance.getTotal().compareTo(BigDecimal.ZERO) > 0){
				System.err.println(currency +" --> "+balance.getTotal());
			}
		}
		
		//kucoinTradeService tradeService = (kucoinTradeService) kucoin.getTradeService();
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		//cal.set(Calendar.MINUTE, 30);
		/*
		// place a limit buy order
	    LimitOrder limitOrder = new LimitOrder((OrderType.BID), new BigDecimal("13"), new CurrencyPair(Currency.XRP, Currency.BTC), null,new Timestamp(cal.getTimeInMillis()), new BigDecimal("0.00008300"));
	    String limitOrderReturnValue = tradeService.placeLimitOrder(limitOrder);
	    System.out.println("Limit Order return value: " + limitOrderReturnValue);
	    */
	    //OpenOrders orders = tradeService.getOpenOrders(new DefaultOpenOrdersParamCurrencyPair(new CurrencyPair(Currency.XRP, Currency.BTC)));
	    
	    //List<LimitOrder> listLimitOrder =  orders.getOpenOrders();
	    
	    
	 // Cancel the added order
	    //kucoinCancelledOrder cancelResult = tradeService.cancelOrder(new CurrencyPair(Currency.XRP, Currency.BTC), Long.parseLong(listLimitOrder.get(0).getId() ),null, null, 0L, cal.getTimeInMillis());
	    //System.out.println("Canceling returned " + cancelResult.orderId);
		
	}
	
}
