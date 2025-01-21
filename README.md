# Stock Market Trading Simulation

## Student Information
- **Name**: Rushabh Ukani 
- **NUID**:  002922246
- **Email**: ukani.r@northeastern.edu
- **Course**: CSYE 7374 Design Patterns
- **Assignment**: 1 - Stock API Implementation

## Project Description
This project implements a stock trading simulation system that demonstrates object-oriented design principles and real-world market behaviors. The implementation includes:

- Abstract `StockAPI` class with core stock functionality
- `Tradable` interface defining trading operations
- Two concrete implementations:
  - `TechStock`: Simulates technology sector stocks with innovation metrics
  - `BankStock`: Simulates banking sector stocks with interest rate sensitivity

### Key Features
1. Real-time market simulation
2. Sector-specific performance metrics
3. Market sentiment analysis
4. Trading recommendations
5. Volume and price tracking
6. Day high/low monitoring

## How to Run

### Prerequisites
- Java JDK 11 or higher
- Git

### Build and Run Instructions
1. Clone the repository:
   ```bash
   git clone [repository-url]
   cd [repository-name]
   ```

2. Compile the Java files:
   ```bash
   javac edu/neu/csye7374/*.java
   ```

3. Run the program:
   ```bash
   java edu.neu.csye7374.Driver
   ```

## Sample Output
```
============Market Simulation Start===================

📱 Simulating Meta stock during earnings season:
Initial: Meta Platforms Inc[META] $480.00 | High: $480.00 Low: $480.00 | Vol: 0

Market Update:
Meta Platforms Inc[META] $485.50 | High: $485.50 Low: $480.00 | Vol: 5234
Signal: BUY ⬆️
Analysis: Strong positive momentum. Innovation driving growth 💡

[... more trading updates ...]

🏦 Simulating Goldman Sachs during Fed announcement:
Initial: Goldman Sachs Group[GS] $385.00 | High: $385.00 Low: $385.00 | Vol: 0

[... more trading updates ...]

============Market Simulation End===================
```

## Class Structure
1. **Tradable (Interface)**
   - Defines core trading operations
   - Methods for bid processing and metric calculation

2. **StockAPI (Abstract Class)**
   - Implements base stock functionality
   - Manages price history and market data
   - Provides common utilities for stock analysis

3. **TechStock (Concrete Class)**
   - Specialized for technology sector
   - Includes innovation score metrics
   - Volatile price movement simulation

4. **BankStock (Concrete Class)**
   - Specialized for banking sector
   - Interest rate sensitivity
   - Economic condition simulation

## Design Patterns Used
1. **Template Method Pattern**
   - Base functionality in StockAPI
   - Specialized behavior in concrete classes

2. **Strategy Pattern**
   - Different metric calculation strategies
   - Sector-specific market analysis

## Future Enhancements
- Add more stock sectors (Healthcare, Energy, etc.)
- Implement real-time data fetching
- Add technical analysis indicators
- Include market news sentiment analysis

## References
1. Stock Market Basics - []
2. Java Design Patterns - []
3. Financial Markets - []