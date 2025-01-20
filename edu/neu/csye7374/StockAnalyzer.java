package edu.neu.csye7374;

public class StockAnalyzer {
    // Support and Resistance Levels
    public static double calculateSupport(double[] prices) {
        if (prices == null || prices.length == 0) return 0;
        double min = prices[0];
        for (double price : prices) {
            if (price < min) min = price;
        }
        return min;
    }
    
    public static double calculateResistance(double[] prices) {
        if (prices == null || prices.length == 0) return 0;
        double max = prices[0];
        for (double price : prices) {
            if (price > max) max = price;
        }
        return max;
    }
    
    // Trend Analysis
    public static String analyzeTrend(double[] prices) {
        if (prices == null || prices.length < 2) return "Insufficient data";
        
        int upMoves = 0;
        int downMoves = 0;
        
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i-1]) upMoves++;
            else if (prices[i] < prices[i-1]) downMoves++;
        }
        
        if (upMoves > downMoves * 2) return "Strong Uptrend 📈";
        if (upMoves > downMoves) return "Moderate Uptrend ↗️";
        if (downMoves > upMoves * 2) return "Strong Downtrend 📉";
        if (downMoves > upMoves) return "Moderate Downtrend ↘️";
        return "Sideways Movement ↔️";
    }
    
    // Price Momentum
    public static double calculateMomentum(double[] prices, int period) {
        if (prices == null || prices.length < period) return 0;
        return ((prices[prices.length-1] - prices[prices.length-period]) / prices[prices.length-period]) * 100;
    }
    
    // Risk Analysis
    public static String analyzeRisk(double volatility, double momentum) {
        if (volatility > 5 && Math.abs(momentum) > 10) return "High Risk 🔴";
        if (volatility > 3 || Math.abs(momentum) > 7) return "Medium Risk 🟡";
        return "Low Risk 🟢";
    }
    
    // Market Strength
    public static String analyzeMarketStrength(double rsi, double volume, double avgVolume) {
        StringBuilder strength = new StringBuilder();
        
        // RSI Analysis
        if (rsi > 70) strength.append("Overbought");
        else if (rsi < 30) strength.append("Oversold");
        else strength.append("Neutral");
        
        // Volume Analysis
        if (volume > avgVolume * 1.5) strength.append(" with Strong Volume");
        else if (volume < avgVolume * 0.5) strength.append(" with Weak Volume");
        
        return strength.toString();
    }
    
    // Trading Opportunity
    public static String identifyOpportunity(double price, double support, double resistance) {
        double distanceToSupport = price - support;
        double distanceToResistance = resistance - price;
        
        if (distanceToSupport < price * 0.02) return "Potential Bounce from Support 💫";
        if (distanceToResistance < price * 0.02) return "Near Resistance - Caution ⚠️";
        if (distanceToSupport > distanceToResistance * 2) return "Closer to Resistance ⬆️";
        if (distanceToResistance > distanceToSupport * 2) return "Closer to Support ⬇️";
        return "Mid-Range Trading ↔️";
    }
}