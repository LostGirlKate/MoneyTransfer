enum class CardType {
    MASTERCARD, MIR, VISA
}

const val DAY_LIMIT = 150_000
const val MONTH_LIMIT = 600_000
const val MONTH_LIMIT_MASTERCARD_NO_COMMISSION = 75_000
const val PERCENT_MASTERCARD = 0.6
const val PERCENT_VISA = 0.75
const val MASTERCARD_ADD_COMMISSION = 20
const val VISA_MIN_COMMISSION = 35
var totalMonthTransferMastercard = 0
var totalMonthTransferVisa = 0
var totalMonthTransferMir = 0
var totalDayTransferMastercard = 0
var totalDayTransferVisa = 0
var totalDayTransferMir = 0

fun main() {
    transferByCardType(CardType.MASTERCARD, 1_000)
    transferByCardType(CardType.MASTERCARD, 76_000)
    transferByCardType(CardType.MASTERCARD, 100_000)

    transferByCardType(CardType.VISA, 100_000)
    transferByCardType(CardType.VISA, 50_000)
    transferByCardType(CardType.VISA, 100_000)

    transferByCardType(amount = 100_000)
    transferByCardType(amount = 50_000)
    transferByCardType(amount = 50_000)
}

fun transferByCardType(cardType: CardType = CardType.MIR, amount: Int) {
    val totalMonthTransfer = getTotalMonthTransfer(cardType)
    val totalDayTransfer = getTotalDayTransfer(cardType)
    if (totalDayTransfer + amount > DAY_LIMIT) {
        println("Дневной лимит по карте ${cardType.name} исчерпан, вы не можете осуществить перевод на сумму $amount руб!")
    } else if (totalMonthTransfer + amount > MONTH_LIMIT) {
        println("Месячный лимит по карте ${cardType.name} исчерпан, вы не можете осуществить перевод на сумму $amount руб.!")
    } else {
        val commission = getCommission(cardType, amount, getTotalMonthTransfer(cardType))
        println("Размер комиссии по карте ${cardType.name} - $commission руб.")
        applyTransfer(cardType, amount)
        println("Перевод осуществлен!")
    }
    println()
}

fun getCommission(cardType: CardType = CardType.MIR, amount: Int, totalMonthTransfer: Int = 0): Int {
    val commission = when (cardType) {
        CardType.MASTERCARD -> if (totalMonthTransfer + amount > MONTH_LIMIT_MASTERCARD_NO_COMMISSION) Math.round(
            (amount - 0.coerceAtLeast(MONTH_LIMIT_MASTERCARD_NO_COMMISSION - totalMonthTransfer)) * (PERCENT_MASTERCARD / 100) + MASTERCARD_ADD_COMMISSION
        ).toInt() else 0

        CardType.VISA -> Integer.max(VISA_MIN_COMMISSION, Math.round(amount * (PERCENT_VISA / 100)).toInt())
        CardType.MIR -> 0
    }
    return commission
}

fun getTotalDayTransfer(cardType: CardType): Int {
    return when (cardType) {
        CardType.MASTERCARD -> totalDayTransferMastercard
        CardType.VISA -> totalDayTransferVisa
        CardType.MIR -> totalDayTransferMir
    }
}

fun getTotalMonthTransfer(cardType: CardType): Int {
    return when (cardType) {
        CardType.MASTERCARD -> totalMonthTransferMastercard
        CardType.VISA -> totalMonthTransferVisa
        CardType.MIR -> totalMonthTransferMir
    }
}

fun applyTransfer(cardType: CardType, amount: Int) {
    when (cardType) {
        CardType.MASTERCARD -> {
            totalMonthTransferMastercard += amount
            totalDayTransferMastercard += amount
        }

        CardType.VISA -> {
            totalMonthTransferVisa += amount
            totalDayTransferVisa += amount
        }

        CardType.MIR -> {
            totalMonthTransferMir += amount
            totalDayTransferMir += amount
        }
    }
}

fun clearDayTotal() {
    totalDayTransferMastercard = 0
    totalDayTransferVisa = 0
    totalDayTransferMir = 0
}

fun clearMonthTotal() {
    totalMonthTransferMastercard = 0
    totalMonthTransferVisa = 0
    totalMonthTransferMir = 0
}
