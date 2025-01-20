package edu.neu.csye7374;

public class TechStock extends StockAPI {
    private double innovationScore;

    public TechStock(String id, double price, String description) {
        super(id, price, description);
        this.innovationScore = Math.random() * 100;
    }

    @Override
    public int getMetric() {
        if (currentBidIndex < 2) return 0;
        
        double momentum = calculateMomentum();
        double volatility = calculateVolatility();
        double innovationImpact = (innovationScore - 50) / 2;
        
        int metric = (int) (momentum * 0.5 + innovationImpact * 0.3 - volatility * 0.2);
        return Math.min(Math.max(metric, -100), 100);
    }

    private double calculateMomentum() {
        double lastPrice = bidHistory[currentBidIndex - 1];
        double previousPrice = bidHistory[currentBidIndex - 2];
        return ((lastPrice - previousPrice) / previousPrice) * 100;
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
        double volatility = calculateVolatility();
        if (volatility > 5) {
            return "High tech sector volatility [!]";
        } else if (getMetric() > 30) {
            return "Strong innovation momentum [+]";
        } else if (getMetric() < -30) {
            return "Tech sector valuation concerns [-]";
        }
        return "Stable tech performance [=]";
    }

    @Override
    public String getMarketPhase() {
        if (currentBidIndex < 2) return MarketFormatting.PHASE_ACCUMULATION;
        String basePhase = MarketIndicators.determineMarketPhase(bidHistory, volumeHistory);
        
        // Add tech-specific context
        if (getMetric() > 30) {
            return basePhase + " (Growth Phase)";
        } else if (getMetric() < -30) {
            return basePhase + " (Correction Phase)";
        }
        return basePhase;
    }
}