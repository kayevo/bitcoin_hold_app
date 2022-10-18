package com.kayevo.bitcoinhold.data.helper

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.util.*
import kotlin.math.pow

fun Long.parseSatoshiToBitcoin(): String {
    return "%.${8}f".format((this / 10.0.pow(8.0)))
}

fun Double.parseToCurrency(): String {
    val numFormat = NumberFormat.getCurrencyInstance() as DecimalFormat
    val formatSymbols: DecimalFormatSymbols = numFormat.decimalFormatSymbols

    formatSymbols.currencySymbol = ""
    numFormat.decimalFormatSymbols = formatSymbols

    return numFormat.format(this).trim()
}

fun Double.parseToBRLCurrency(): String {
    val formatCurrency: NumberFormat = NumberFormat.getCurrencyInstance()
    formatCurrency.maximumFractionDigits = 2
    formatCurrency.currency = Currency.getInstance("BRL")

    return formatCurrency.format(this).replace(" ", "")
}

