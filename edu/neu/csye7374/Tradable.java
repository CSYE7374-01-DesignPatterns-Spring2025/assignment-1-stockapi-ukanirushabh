package edu.neu.csye7374;

public interface Tradable {
    /**
     * Place a bid to buy a stock
     * @param bid price offered
     */
    void setBid(String bid);

    /**
     * Get performance metric (-100 to +100)
     * @return performance metric
     */
    int getMetric();
    
    /**
     * Get trading recommendation
     * @return trading recommendation
     */
    String getRecommendation();
    
    /**
     * Get market sentiment
     * @return market sentiment description
     */
    String getMarketSentiment();
    
    /**
     * Get current market phase
     * @return current market phase
     */
    String getMarketPhase();
    
    /**
     * Get technical analysis summary
     * @return technical analysis
     */
    default String getTechnicalAnalysis() {
        return "Technical analysis not available";
    }
}