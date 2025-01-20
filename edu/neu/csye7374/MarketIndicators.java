package edu.neu.csye7374;

public class MarketIndicators {
    public static String determineMarketPhase(double[] prices, double[] volumes) {
        if (prices.length < 2) return MarketFormatting.PHASE_ACCUMULATION + " [=]";
        
        double priceChange = ((prices[prices.length-1] - prices[0]) / prices[0]) * 100;
        double averageVolume = calculateAverageVolume(volumes);
        double volatility = calculateVolatility(prices);
        
        // More detailed phase analysis
        StringBuilder phase = new StringBuilder();
        
        if (priceChange > 5 && volumes[volumes.length-1] > averageVolume) {
            phase.append(MarketFormatting.PHASE_MARKUP);
            if (volatility > 3) {
                phase.append(" [++]");
            } else {
                phase.append(" [+]");
            }
        } else if (priceChange < -5 && volumes[volumes.length-1] > averageVolume) {
            phase.append(MarketFormatting.PHASE_DISTRIBUTION);
            if (volatility > 3) {
                phase.append(" [--]");
            } else {
                phase.append(" [-]");
            }
        } else if (Math.abs(priceChange) <= 5) {
            phase.append(MarketFormatting.PHASE_CONSOLIDATION);
            phase.append(" [=]");
        } else {
            phase.append(MarketFormatting.PHASE_ACCUMULATION);
            phase.append(" [*]");
        }
        
        return phase.toString();
    }
    
    private static double calculateVolatility(double[] prices) {
        if (prices.length < 2) return 0;
        double sum = 0;
        for (int i = 1; i < prices.length; i++) {
            double change = ((prices[i] - prices[i-1]) / prices[i-1]) * 100;
            sum += Math.abs(change);
        }
        return sum / (prices.length - 1);
    }
    
    public static String getVolumeStatus(int currentVolume, double averageVolume) {
        double ratio = currentVolume / averageVolume;
        if (ratio > 1.5) return "HIGH [+]";
        if (ratio < 0.5) return "LOW [-]";
        return "NORMAL [=]";
    }
    
    private static double calculateAverageVolume(double[] volumes) {
        if (volumes == null || volumes.length == 0) return 0;
        double sum = 0;
        int count = 0;
        for (double volume : volumes) {
            if (volume > 0) {
                sum += volume;
                count++;
            }
        }
        return count > 0 ? sum / count : 0;
    }
    
    public static double calculateRSI(double[] prices) {
        if (prices.length < 2) return 50.0;
        double gainSum = 0, lossSum = 0;
        for (int i = 1; i < prices.length; i++) {
            double diff = prices[i] - prices[i-1];
            if (diff > 0) gainSum += diff;
            else lossSum -= diff;
        }
        if (lossSum == 0) return 100;
        double rs = gainSum / lossSum;
        return 100.0 - (100.0 / (1.0 + rs));
    }
    
    public static double calculateMACD(double[] prices) {
        if (prices.length < 2) return 0;
        double fastEMA = calculateEMA(prices, 12);
        double slowEMA = calculateEMA(prices, 26);
        return fastEMA - slowEMA;
    }
    
    private static double calculateEMA(double[] prices, int period) {
        double multiplier = 2.0 / (period + 1.0);
        double ema = prices[0];
        
        for (int i = 1; i < prices.length; i++) {
            ema = (prices[i] - ema) * multiplier + ema;
        }
        return ema;
    }
}