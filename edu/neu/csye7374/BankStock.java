package edu.neu.csye7374;

public class BankStock extends StockAPI {
    private double interestRateSensitivity;

    public BankStock(String id, double price, String description) {
        super(id, price, description);
        this.interestRateSensitivity = Math.random() * 2 + 1;
    }

    @Override
    public int getMetric() {
        if (currentBidIndex < 4) return 0;
        
        double priceStability = calculatePriceStability();
        double trendStrength = calculateTrendStrength();
        double economicFactor = simulateEconomicConditions();
        
        int metric = (int) (priceStability * 0.4 + trendStrength * 0.4 + economicFactor * 0.2);
        return Math.min(Math.max(metric, -100), 100);
    }

    private double calculatePriceStability() {
        double volatility = calculateVolatility();
        return 50 - (volatility * interestRateSensitivity);
    }

    private double calculateTrendStrength() {
        double recentAvg = (bidHistory[currentBidIndex - 1] + bidHistory[currentBidIndex - 2]) / 2;
        double oldAvg = (bidHistory[currentBidIndex - 3] + bidHistory[currentBidIndex - 4]) / 2;
        return ((recentAvg - oldAvg) / oldAvg) * 100;
    }

    private double simulateEconomicConditions() {
        return (Math.random() - 0.5) * 50;
    }

    @Override
    public String getRecommendation() {
        int metric = getMetric();
        StringBuilder signal = new StringBuilder();
        
        if (metric > 50) signal.append("STRONG BUY [++]");
        else if (metric > 20) signal.append("BUY [+]");
        else if (metric < -50) signal.append("STRONG SELL [--]");
        else if (metric < -20) signal.append("SELL [-]");
        else signal.append("HOLD [=]");
        
        return signal.toString();
    }

    @Override
    public String getMarketSentiment() {
        if (getMetric() > 30) {
            return "Strong financial sector performance [++]";
        } else if (getMetric() < -30) {
            return "Economic concerns affecting banking sector [-]";
        } else if (calculateVolatility() > 3) {
            return "Market adjusting to Federal Reserve policy changes [~]";
        }
        return "Stable banking sector performance [=]";
    }

    @Override
    public String getMarketPhase() {
        if (currentBidIndex < 2) return MarketFormatting.PHASE_ACCUMULATION;
        String basePhase = MarketIndicators.determineMarketPhase(bidHistory, volumeHistory);
        double volatility = calculateVolatility();
        if (volatility > 4) {
            return basePhase + " (High Volatility)";
        }
        return basePhase;
    }
}