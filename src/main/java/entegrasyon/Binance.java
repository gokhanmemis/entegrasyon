package entegrasyon;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.binance.BinanceExchange;
import org.knowm.xchange.binance.service.BinanceTradeService;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.account.AccountInfo;
import org.knowm.xchange.dto.account.Balance;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.kucoin.KucoinExchange;
import org.knowm.xchange.service.account.AccountService;
import org.knowm.xchange.service.marketdata.MarketDataService;

public class Binance {

	public static void main(String[] args) throws IOException {
		
		ExchangeSpecification exSpec = new ExchangeSpecification(BinanceExchange.class);
		exSpec.setUserName("gokhanaycangokayilay");
		exSpec.setApiKey("6qiuA0iFY9hp5vqPjV44jChF9X3mavoMGEVglRUJLxYZ1o5rHpOVAHha4mhFNcUD");
		exSpec.setSecretKey("rqoZLa0Ox6Wbp0WVMG4HJRUyxChnl20aiIG4S0G0cNqHIUjI50Kd8LWrOvZcB1iU");

		Exchange Binance = ExchangeFactory.INSTANCE.createExchange(exSpec);
		MarketDataService marketDataService = Binance.getMarketDataService();

		Ticker ticker = marketDataService.getTicker(CurrencyPair.BTC_USDT);

		System.out.println(ticker.toString());
		

		// Get the account information
		AccountService accountService = Binance.getAccountService();
		AccountInfo accountInfo = accountService.getAccountInfo();
//		System.out.println(accountInfo.getWallet().getBalances());
		
		for (Currency currency : accountInfo.getWallet().getBalances().keySet()) {
			Balance balance = accountInfo.getWallet().getBalances().get(currency);
			if(balance.getTotal().compareTo(BigDecimal.ZERO) > 0){
				System.err.println(currency +" --> "+balance.getTotal());
			}
		}
		
		BinanceTradeService tradeService = (BinanceTradeService) Binance.getTradeService();
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		//cal.set(Calendar.MINUTE, 30);
		/*
		// place a limit buy order
	    LimitOrder limitOrder = new LimitOrder((OrderType.BID), new BigDecimal("13"), new CurrencyPair(Currency.XRP, Currency.BTC), null,new Timestamp(cal.getTimeInMillis()), new BigDecimal("0.00008300"));
	    String limitOrderReturnValue = tradeService.placeLimitOrder(limitOrder);
	    System.out.println("Limit Order return value: " + limitOrderReturnValue);
	    
	    OpenOrders orders = tradeService.getOpenOrders(new DefaultOpenOrdersParamCurrencyPair(new CurrencyPair(Currency.XRP, Currency.BTC)));
	    
	    List<LimitOrder> listLimitOrder =  orders.getOpenOrders();
	    
	    
	 // Cancel the added order
	    BinanceCancelledOrder cancelResult = tradeService.cancelOrder(new CurrencyPair(Currency.XRP, Currency.BTC), Long.parseLong(listLimitOrder.get(0).getId() ),null, null, 0L, cal.getTimeInMillis());
	    System.out.println("Canceling returned " + cancelResult.orderId);
		*/
	}
	
}
