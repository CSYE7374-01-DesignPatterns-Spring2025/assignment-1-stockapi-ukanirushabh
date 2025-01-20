package edu.neu.csye7374;

public class MarketNews {
    private static final String[][] TECH_NEWS = {
        {"Product Launch Success", "+2.5", "Strong consumer demand"},
        {"Earnings Beat", "+3.0", "Revenue exceeds expectations"},
        {"Innovation Award", "+1.5", "Industry recognition"},
        {"Partnership Announcement", "+2.0", "Strategic alliance formed"},
        {"Market Share Growth", "+1.8", "Competitive position improves"}
    };

    private static final String[][] BANK_NEWS = {
        {"Interest Rate Update", "-1.5", "Fed policy impact"},
        {"Regulatory Approval", "+2.0", "New service launch"},
        {"Economic Report", "+1.8", "Strong financial metrics"},
        {"Merger Complete", "+2.5", "Synergy benefits expected"},
        {"Analyst Upgrade", "+1.2", "Positive outlook"}
    };

    public static MarketEvent generateNews(String sector) {
        String[][] newsSource = sector.equals("TECH") ? TECH_NEWS : BANK_NEWS;
        int index = (int)(Math.random() * newsSource.length);
        return new MarketEvent(
            newsSource[index][0],
            Double.parseDouble(newsSource[index][1]),
            newsSource[index][2]
        );
    }

    public static class MarketEvent {
        private String headline;
        private double impact;
        private String details;

        public MarketEvent(String headline, double impact, String details) {
            this.headline = headline;
            this.impact = impact;
            this.details = details;
        }

        public String getHeadline() { return headline; }
        public double getImpact() { return impact; }
        public String getDetails() { return details; }

        @Override
        public String toString() {
            String direction = impact >= 0 ? "[+]" : "[-]";
            return String.format("MARKET NEWS %s: %s\nImpact: %.1f%% - %s", 
                direction, headline, impact, details);
        }
    }
}