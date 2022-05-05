class StockBuySellPrice {
    // Problem Question:
    // https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
    public int maxProfit(int[] prices) {
        /**
         * Approach:
         * 
         * Max profit will come when I buy at the minimum price, before this current
         * price.
         * Meaning, I will track the minimum till this current price, and once I find
         * that, I will get the profit as (current price - minimum bought price)
         * 
         * And we have to just track the maximum of those profits.
         */
        int pricesLen = prices.length;

        if (pricesLen == 0) {
            return 0;
        }

        int minimumBuyPrice = prices[0], maxProfit = 0;

        for (int i = 1; i < pricesLen; i++) {
            if (prices[i] < minimumBuyPrice) {
                minimumBuyPrice = prices[i];
            }

            maxProfit = Math.max(maxProfit, prices[i] - minimumBuyPrice);
        }

        return maxProfit;
    }
}