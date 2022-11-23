package com.kayevo.bitcoinhold.helper

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.util.*
import kotlin.math.pow

fun Long.parseSatoshiToBitcoin(): String {
    return "%.${8}f".format(Locale.ENGLISH, (this / 10.0.pow(8.0)))
}

fun Double.parseSatoshiToBitcoin(): String {
    return "%.${8}f".format(Locale.ENGLISH, (this / 10.0.pow(8.0)))
}

fun Double.parseBitcoinPriceToSatoshiPrice(): Double{
    return (this * 100.0).toLong().parseSatoshiToBitcoin().toDouble() / 100.0
}

fun String.parseBitcoinToSatoshi(): Long {
    return (this.toDouble() * 10.0.pow(8.0)).toLong()
}

fun String.parseCurrencyToDouble(): Double {
    return this.replace(",", "").toDoubleOrNull() ?: 0.0
}

fun Double.parseToCurrency(): String {
    val formatCurrency: NumberFormat = NumberFormat.getCurrencyInstance(Locale.ENGLISH)
    formatCurrency.maximumFractionDigits = 2
    formatCurrency.currency = Currency.getInstance("USD")

    return formatCurrency.format(this).replace("\$", "")
}

fun Double.parseToPercentage(): String {
    val percentageSymbol = if(this >= 0) "+" else ""

    return "$percentageSymbol ${this.parseToCurrency()} %"
}

fun Double.parseToBRLCurrency(): String {
    val formatCurrency: NumberFormat = NumberFormat.getCurrencyInstance()
    formatCurrency.maximumFractionDigits = 2
    formatCurrency.currency = Currency.getInstance("BRL")

    return formatCurrency.format(this).replace(" ", "")
}

