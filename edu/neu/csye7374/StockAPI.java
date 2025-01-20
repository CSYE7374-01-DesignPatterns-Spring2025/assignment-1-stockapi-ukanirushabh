package edu.neu.csye7374;

public abstract class StockAPI implements Tradable {
    protected String id;
    protected double price;
    protected String description;
    protected double[] bidHistory;
    protected double[] volumeHistory;
    protected int currentBidIndex;
    protected double dayHigh;
    protected double dayLow;
    protected int volume;
    protected double averageVolume = 10000; // Base average volume
    
    public StockAPI(String id, double price, String description) {
        this.id = id;
        this.price = price;
        this.description = description;
        this.bidHistory = new double[6];
        this.volumeHistory = new double[6];
        this.currentBidIndex = 0;
        this.dayHigh = price;
        this.dayLow = price;
        this.volume = 0;
    }

    @Override
    public void setBid(String bid) {
        try {
            double bidValue = Double.parseDouble(bid);
            if (currentBidIndex < 6) {
                bidHistory[currentBidIndex] = bidValue;
                volumeHistory[currentBidIndex] = generateVolume();
                this.price = bidValue;
                updateDayHighLow(bidValue);
                this.volume = (int)volumeHistory[currentBidIndex];
                currentBidIndex++;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid bid format: " + bid);
        }
    }

    private void updateDayHighLow(double newPrice) {
        if (newPrice > dayHigh) dayHigh = newPrice;
        if (newPrice < dayLow) dayLow = newPrice;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s[%s] %s | ", 
            description, 
            id, 
            MarketFormatting.formatPrice(price)));
        
        sb.append(String.format("H: %s L: %s | ", 
            MarketFormatting.formatPrice(dayHigh), 
            MarketFormatting.formatPrice(dayLow)));
        
        sb.append(String.format("Vol: %s", 
            MarketFormatting.formatVolume(volume)));
        
        if (currentBidIndex >= 2) {
            double rsi = MarketIndicators.calculateRSI(bidHistory);
            double macd = MarketIndicators.calculateMACD(bidHistory);
            sb.append("\n").append(MarketFormatting.formatTechnicals(rsi, macd));
            sb.append(" | Volume: ").append(MarketIndicators.getVolumeStatus(volume, averageVolume));
        }
        
        return sb.toString();
    }

    private double generateVolume() {
        double multiplier = 0.5 + Math.random();
        return averageVolume * multiplier;
    }

    protected double calculateVolatility() {
        if (currentBidIndex < 2) return 0;
        double sum = 0;
        for (int i = 1; i < currentBidIndex; i++) {
            double change = ((bidHistory[i] - bidHistory[i-1]) / bidHistory[i-1]) * 100;
            sum += Math.abs(change);
        }
        return sum / (currentBidIndex - 1);
    }

    public abstract String getRecommendation();
    public abstract String getMarketSentiment();
    public abstract String getMarketPhase();
}