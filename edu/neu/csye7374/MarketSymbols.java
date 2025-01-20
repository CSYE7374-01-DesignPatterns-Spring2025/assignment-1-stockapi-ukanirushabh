package edu.neu.csye7374;

public class MarketSymbols {
    // Unicode symbols for market indicators
    public static final String ARROW_UP = "\u2191";      // ↑
    public static final String ARROW_DOWN = "\u2193";    // ↓
    public static final String ARROW_RIGHT = "\u2192";   // →
    public static final String DOUBLE_UP = "\u21C8";     // ⇈
    public static final String DOUBLE_DOWN = "\u21CA";   // ⇊
    
    // Market status indicators
    public static final String BULLISH = "[" + ARROW_UP + "]";
    public static final String BEARISH = "[" + ARROW_DOWN + "]";
    public static final String NEUTRAL = "[" + ARROW_RIGHT + "]";
    public static final String STRONG_BULLISH = "[" + DOUBLE_UP + "]";
    public static final String STRONG_BEARISH = "[" + DOUBLE_DOWN + "]";
    
    public static String getPriceChangeSymbol(double change) {
        if (change > 2.0) return STRONG_BULLISH;
        if (change < -2.0) return STRONG_BEARISH;
        if (change > 0) return BULLISH;
        if (change < 0) return BEARISH;
        return NEUTRAL;
    }
    
    public static String getVolumeSymbol(double volumeRatio) {
        if (volumeRatio > 1.5) return "[++]";
        if (volumeRatio < 0.5) return "[--]";
        return "[==]";
    }
    
    public static String getTrendSymbol(String trend) {
        switch(trend.toUpperCase()) {
            case "UP": return BULLISH;
            case "DOWN": return BEARISH;
            case "STRONG_UP": return STRONG_BULLISH;
            case "STRONG_DOWN": return STRONG_BEARISH;
            default: return NEUTRAL;
        }
    }
    
    public static String formatWithSymbol(double value, String description) {
        String symbol = getPriceChangeSymbol(value);
        return String.format("%.2f%% %s", value, symbol);
    }
}