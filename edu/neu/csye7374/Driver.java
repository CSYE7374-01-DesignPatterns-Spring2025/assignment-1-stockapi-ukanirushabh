package edu.neu.csye7374;

public class Driver {
    public static void main(String[] args) {
        System.out.println("============Advanced Market Simulation Start===================\n");

        // Scenario 1: Tech Stock during Product Launch
        simulateTechStock();
        
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        // Scenario 2: Bank Stock during Economic Report
        simulateBankStock();
        
        // Scenario 3: Compare Different Sectors
        System.out.println("\n" + "=".repeat(50) + "\n");
        compareSectors();

        System.out.println("\n============Advanced Market Simulation End===================");
    }
    
    private static void simulateTechStock() {
        TechStock appleStock = new TechStock("AAPL", 180.0, "Apple Inc");
        System.out.println("[TECH] Simulating Apple stock during product launch event:");
        System.out.println(MarketAnalyzer.generateMarketSnapshot(appleStock));
        
        String[] appleBids = {
            "182.50", // Anticipation
            "185.75", // Product announcement
            "183.25", // Profit taking
            "187.00", // Positive reviews
            "189.50", // Market enthusiasm
            "188.75"  // Consolidation
        };
        
        simulateTrading(appleStock, appleBids, "Tech Sector Events", true);
    }
    
    private static void simulateBankStock() {
        BankStock citiStock = new BankStock("C", 55.0, "Citigroup Inc");
        System.out.println("[BANK] Simulating Citigroup during economic report:");
        System.out.println(MarketAnalyzer.generateMarketSnapshot(citiStock));
        
        String[] citiBids = {
            "55.75", // Report anticipation
            "54.50", // Initial reaction
            "53.25", // Market adjustment
            "54.00", // Recovery begins
            "54.75", // Stabilization
            "55.25"  // New equilibrium
        };
        
        simulateTrading(citiStock, citiBids, "Banking Sector Events", true);
    }
    
    private static void compareSectors() {
        System.out.println("[ANALYSIS] Sector Comparison Analysis");
        
        TechStock msftStock = new TechStock("MSFT", 390.0, "Microsoft Corporation");
        BankStock jpStock = new BankStock("JPM", 172.0, "JPMorgan Chase & Co");
        
        // Simulate parallel trading sessions
        String[] bullishBids = {
            "392.50", "395.75", "398.25", "401.00", "403.50", "405.75"
        };
        String[] bearishBids = {
            "171.25", "170.50", "169.75", "168.25", "167.50", "166.75"
        };
        
        System.out.println("\nParallel Trading Simulation:\n");
        
        for (int i = 0; i < bullishBids.length; i++) {
            System.out.println("Trading Session " + (i + 1) + ":");
            System.out.println("-".repeat(30));
            
            double prevMsftPrice = msftStock.price;
            double prevJpmPrice = jpStock.price;
            
            // Update both stocks
            msftStock.setBid(bullishBids[i]);
            jpStock.setBid(bearishBids[i]);
            
            // Tech Sector Update
            System.out.println("Tech Sector (MSFT):");
            System.out.println(msftStock);
            System.out.println(MarketAnalyzer.generateSummary(msftStock, prevMsftPrice));
            
            // Banking Sector Update
            System.out.println("\nBanking Sector (JPM):");
            System.out.println(jpStock);
            System.out.println(MarketAnalyzer.generateSummary(jpStock, prevJpmPrice));
            
            // Sector Comparison
            if (i == bullishBids.length - 1) {
                System.out.println(MarketAnalyzer.compareSectors(msftStock, jpStock));
            }
            
            System.out.println("-".repeat(30) + "\n");
            
            // Add small delay for better readability
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
    
    private static void simulateTrading(StockAPI stock, String[] bids, String context, boolean showNews) {
        System.out.println("[TRADING] Starting " + context + " Simulation\n");
        double previousPrice = stock.price;
        
        for (int i = 0; i < bids.length; i++) {
            System.out.println("Trading Session " + (i + 1) + ":");
            System.out.println("-".repeat(30));
            
            // Generate market news (30% chance)
            if (showNews && Math.random() < 0.3) {
                MarketNews.MarketEvent news = MarketNews.generateNews(
                    stock instanceof TechStock ? "TECH" : "BANK"
                );
                System.out.println("\n" + news.toString() + "\n");
            }
            
            // Update stock price
            stock.setBid(bids[i]);
            
            // Market updates
            System.out.println("Market Update:");
            System.out.println(stock);
            System.out.println(MarketAnalyzer.generateSummary(stock, previousPrice));
            
            previousPrice = stock.price;
            System.out.println("-".repeat(30) + "\n");
            
            // Add delay for better readability
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}