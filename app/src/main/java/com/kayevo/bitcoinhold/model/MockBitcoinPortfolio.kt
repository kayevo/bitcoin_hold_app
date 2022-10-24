class MockBitcoinPortfolio {
    var amount: Double = 0.0 // Should be BigDecimal = BigDecimal("0.0")
        private set
    var averagePrice: Double = 0.0
        private set

    fun addFunds(funds: Double, payedPrice: Double){
        amount += funds
        averagePrice = (averagePrice + payedPrice) / amount
    }

    /* Javascript
    (averagePrice + payedPrice) / amountInBitcoin = averagePrice
    (100000 + 100000) / 2 = 100000

    (averagePrice + payedPrice) / amountInSatoshi = averagePrice
    (100000 + 100000) / 200000000 = 0,001

    (averagePrice + payedPrice) / amountInSatoshi = averagePrice
    parseBitcoinToSatoshi( (100000 + 100000) / 200000000 ) = 100000
    */

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