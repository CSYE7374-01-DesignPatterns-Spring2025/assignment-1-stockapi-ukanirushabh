package edu.neu.csye7374;

public class MarketAnalyzer {    
    public static String generateSummary(StockAPI stock, double previousPrice) {
        StringBuilder summary = new StringBuilder();
        double priceChange = ((stock.price - previousPrice) / previousPrice) * 100;
        
        summary.append("\nMARKET ANALYSIS:\n");
        summary.append("-".repeat(30)).append("\n");
        
        // Price Movement
        summary.append("Price Change: ")
               .append(MarketSymbols.formatWithSymbol(priceChange, ""))
               .append("\n");
        
        // Volume Analysis
        String volumeStatus = MarketIndicators.getVolumeStatus(stock.volume, stock.averageVolume);
        summary.append("Trading Volume: ").append(volumeStatus).append("\n");
        
        // Technical Analysis
        if (stock.currentBidIndex >= 2) {
            double rsi = MarketIndicators.calculateRSI(stock.bidHistory);
            summary.append(String.format("RSI: %.2f ", rsi));
            if (rsi > 70) summary.append("[Overbought]\n");
            else if (rsi < 30) summary.append("[Oversold]\n");
            else summary.append("[Neutral]\n");
        }
        
        // Market Phase
        String phase = stock.getMarketPhase();
        summary.append("Market Phase: ").append(phase).append("\n");
        
        // Trading Recommendation
        summary.append("Recommendation: ").append(stock.getRecommendation()).append("\n");
        
        return summary.toString();
    }

    public static String compareSectors(StockAPI techStock, StockAPI bankStock) {
        StringBuilder comparison = new StringBuilder();
        comparison.append("\nSECTOR COMPARISON:\n");
        comparison.append("-".repeat(30)).append("\n");
        
        double techReturn = calculateReturn(techStock);
        double bankReturn = calculateReturn(bankStock);
        
        comparison.append("Tech Sector Return: ")
                 .append(MarketSymbols.formatWithSymbol(techReturn, ""))
                 .append("\n");
        comparison.append("Banking Sector Return: ")
                 .append(MarketSymbols.formatWithSymbol(bankReturn, ""))
                 .append("\n");
        
        comparison.append("\nSector Leadership: ");
        if (techReturn > bankReturn) {
            comparison.append("Technology Outperforming [TECH+]\n");
        } else if (bankReturn > techReturn) {
            comparison.append("Banking Outperforming [BANK+]\n");
        } else {
            comparison.append("Sectors Aligned [=]\n");
        }
        
        return comparison.toString();
    }
    
    private static double calculateReturn(StockAPI stock) {
        if (stock.currentBidIndex < 2) return 0;
        double initialPrice = stock.bidHistory[0];
        double currentPrice = stock.price;
        return ((currentPrice - initialPrice) / initialPrice) * 100;
    }
    
    public static String generateMarketSnapshot(StockAPI stock) {
        StringBuilder snapshot = new StringBuilder();
        snapshot.append("\nMARKET SNAPSHOT:\n");
        snapshot.append("-".repeat(30)).append("\n");
        
        snapshot.append(String.format("Stock: %s [%s]\n", stock.description, stock.id));
        snapshot.append(String.format("Current Price: $%.2f\n", stock.price));
        snapshot.append(String.format("Day's Range: $%.2f - $%.2f\n", stock.dayLow, stock.dayHigh));
        snapshot.append(String.format("Volume: %,d\n", stock.volume));
        
        if (stock.currentBidIndex >= 2) {
            double rsi = MarketIndicators.calculateRSI(stock.bidHistory);
            double macd = MarketIndicators.calculateMACD(stock.bidHistory);
            String trend = rsi > 50 ? "UP" : "DOWN";
            snapshot.append(String.format("Technical: RSI: %.2f | MACD: %.2f %s\n", 
                rsi, macd, MarketSymbols.getTrendSymbol(trend)));
        }
        
        snapshot.append("Market Status: ").append(determineMarketStatus(stock));
        
        return snapshot.toString();
    }
    
    private static String determineMarketStatus(StockAPI stock) {
        if (stock.currentBidIndex < 2) return "Initializing [*]";
        
        double rsi = MarketIndicators.calculateRSI(stock.bidHistory);
        double currentPrice = stock.price;
        double previousPrice = stock.bidHistory[stock.currentBidIndex - 2];
        double priceChange = ((currentPrice - previousPrice) / previousPrice) * 100;
        
        if (rsi > 70 && priceChange > 2) return "Strong Uptrend " + MarketSymbols.STRONG_BULLISH;
        if (rsi < 30 && priceChange < -2) return "Strong Downtrend " + MarketSymbols.STRONG_BEARISH;
        if (rsi > 60) return "Bullish " + MarketSymbols.BULLISH;
        if (rsi < 40) return "Bearish " + MarketSymbols.BEARISH;
        return "Neutral " + MarketSymbols.NEUTRAL;
    }
}