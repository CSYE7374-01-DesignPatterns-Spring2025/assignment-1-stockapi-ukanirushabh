package edu.neu.csye7374;

public class MarketFormatting {
    // Market Indicators
    public static final String ARROW_UP = "↑";
    public static final String ARROW_DOWN = "↓";
    public static final String ARROW_SIDE = "→";
    public static final String STRONG_UP = "⇈";
    public static final String STRONG_DOWN = "⇊";
    
    // Volume Indicators
    public static final String VOLUME_HIGH = "HIGH";
    public static final String VOLUME_NORMAL = "NORMAL";
    public static final String VOLUME_LOW = "LOW";
    
    // Market Phases
    public static final String PHASE_ACCUMULATION = "ACCUMULATION";
    public static final String PHASE_MARKUP = "MARKUP";
    public static final String PHASE_DISTRIBUTION = "DISTRIBUTION";
    public static final String PHASE_CONSOLIDATION = "CONSOLIDATION";
    
    // Trading Signals
    public static String formatSignal(String signal) {
        return String.format("%-15s", signal);
    }
    
    // Price Formatting
    public static String formatPrice(double price) {
        return String.format("$%.2f", price);
    }
    
    // Volume Formatting
    public static String formatVolume(int volume) {
        return String.format("%,d", volume);
    }
    
    // Technical Indicators
    public static String formatTechnicals(double rsi, double macd) {
        return String.format("RSI: %.2f | MACD: %.2f", rsi, macd);
    }
}