class MockBitcoinPortfolio {
    var amount: Double = 0.0 // Should be BigDecimal = BigDecimal("0.0")
        private set
    var averagePrice: Double = 0.0
        private set

    fun addFunds(funds: Double, payedPrice: Double){
        amount += funds
        averagePrice = (averagePrice + payedPrice) / amount
    }

    fun removeFunds(funds: Double){
        amount -= funds
        if (amount == 0.0){
            restartAveragePrice()
        }
    }

    private fun restartAveragePrice(){
        averagePrice = 0.0
    }
}