import java.util.*;
import java.io.*;

// 1. Stock Class
class Stock {
    String symbol;
    double price;

    public Stock(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
    }
}

// 2. Portfolio Class
class Portfolio {
    Map<String, Integer> holdings = new HashMap<>();
    double balance = 10000.0; // Starting Cash

    public void buyStock(String symbol, int qty, double price) {
        double cost = qty * price;
        if (balance >= cost) {
            balance -= cost;
            holdings.put(symbol, holdings.getOrDefault(symbol, 0) + qty);
            System.out.println("Bought " + qty + " shares of " + symbol);
        } else {
            System.out.println("Insufficient balance!");
        }
    }
}

// 3. Main System
public class StockTradingPlatform {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Portfolio myPortfolio = new Portfolio();
        
        // Market Simulation Data
        Map<String, Stock> market = new HashMap<>();
        market.put("AAPL", new Stock("AAPL", 150.0));
        market.put("TSLA", new Stock("TSLA", 700.0));
        market.put("GOOG", new Stock("GOOG", 2800.0));

        while (true) {
            System.out.println("\n--- STOCK TRADING PLATFORM ---");
            System.out.println("Cash Balance: $" + myPortfolio.balance);
            System.out.println("1. View Market\n2. Buy Stock\n3. View Portfolio\n4. Exit");
            System.out.print("Choice: ");
            int ch = sc.nextInt();

            if (ch == 1) {
                market.forEach((k, v) -> System.out.println(k + ": $" + v.price));
            } else if (ch == 2) {
                System.out.print("Enter Symbol (AAPL/TSLA/GOOG): ");
                String sym = sc.next().toUpperCase();
                if (market.containsKey(sym)) {
                    System.out.print("Enter Quantity: ");
                    int q = sc.nextInt();
                    myPortfolio.buyStock(sym, q, market.get(sym).price);
                }
            } else if (ch == 3) {
                System.out.println("Your Holdings: " + myPortfolio.holdings);
            } else {
                break;
            }
        }
    }
}