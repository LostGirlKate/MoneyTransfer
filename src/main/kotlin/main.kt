enum class CardType {
    MASTERCARD, MAESTRO, MIR, VISA, VK_PAY
}

const val DAY_LIMIT = 150_000
const val DAY_LIMIT_VK_PAY = 15_000
const val MONTH_LIMIT = 600_000
const val MONTH_LIMIT_VK_PAY = 40_000
const val MONTH_LIMIT_NO_COMMISSION = 75_000
const val PERCENT_WITH_LIMIT = 0.6
const val ADD_COMMISSION = 20
const val PERCENT_NO_LIMIT = 0.75
const val MIN_COMMISSION = 35

var totalMonthTransferMastercard = 0
var totalMonthTransferVisa = 0
var totalMonthTransferMir = 0
var totalMonthTransferMaestro = 0
var totalMonthTransferVKPay = 0
var totalDayTransferMastercard = 0
var totalDayTransferVisa = 0
var totalDayTransferMir = 0
var totalDayTransferMaestro = 0
var totalDayTransferVKPay = 0

fun main() {
    transferByCardType(CardType.MASTERCARD, 1_000)
    transferByCardType(CardType.MASTERCARD, 76_000)
    transferByCardType(CardType.MASTERCARD, 100_000)

    transferByCardType(CardType.MAESTRO, 150_000)
    transferByCardType(CardType.MAESTRO, 76_000)
    transferByCardType(CardType.MAESTRO, 100_000)

    transferByCardType(CardType.VISA, 100_000)
    transferByCardType(CardType.VISA, 50_000)
    transferByCardType(CardType.VISA, 100_000)

    transferByCardType(amount = 100_000)
    transferByCardType(amount = 50_000)
    transferByCardType(amount = 50_000)


    transferByCardType(CardType.VK_PAY, 15_000)
    transferByCardType(CardType.VK_PAY, 10_000)
    clearDayTotal()
    transferByCardType(CardType.VK_PAY, 15_000)
    clearDayTotal()
    transferByCardType(CardType.VK_PAY, 15_000)
}

fun transferByCardType(cardType: CardType = CardType.MIR, amount: Int): Boolean {
    val totalMonthTransfer = getTotalMonthTransfer(cardType)
    val totalDayTransfer = getTotalDayTransfer(cardType)
    val dayLimit = when (cardType) {
        CardType.VK_PAY -> DAY_LIMIT_VK_PAY
        else -> DAY_LIMIT
    }
    val monthLimit = when (cardType) {
        CardType.VK_PAY -> MONTH_LIMIT_VK_PAY
        else -> MONTH_LIMIT
    }
    if (totalDayTransfer + amount > dayLimit) {
        println("Дневной лимит по карте ${cardType.name} исчерпан, вы не можете осуществить перевод на сумму $amount руб!\n")
        return false
    } else if (totalMonthTransfer + amount > monthLimit) {
        println("Месячный лимит по карте ${cardType.name} исчерпан, вы не можете осуществить перевод на сумму $amount руб.!\n")
        return false
    } else {
        val commission = getCommission(cardType, amount, getTotalMonthTransfer(cardType))
        println("Размер комиссии по карте ${cardType.name} - $commission руб.")
        applyTransfer(cardType, amount)
        println("Перевод осуществлен!\n")
        return true
    }
}

fun getCommission(cardType: CardType = CardType.MIR, amount: Int, totalMonthTransfer: Int = 0): Int {
    val commission = when (cardType) {
        CardType.MASTERCARD, CardType.MAESTRO -> if (totalMonthTransfer + amount > MONTH_LIMIT_NO_COMMISSION) Math.round(
            (amount - 0.coerceAtLeast(MONTH_LIMIT_NO_COMMISSION - totalMonthTransfer)) * (PERCENT_WITH_LIMIT / 100) + ADD_COMMISSION
        ).toInt() else 0

        CardType.VISA, CardType.MIR -> Integer.max(MIN_COMMISSION, Math.round(amount * (PERCENT_NO_LIMIT / 100)).toInt())
        CardType.VK_PAY -> 0
    }
    return commission
}

fun getTotalDayTransfer(cardType: CardType): Int {
    return when (cardType) {
        CardType.MASTERCARD -> totalDayTransferMastercard
        CardType.VISA -> totalDayTransferVisa
        CardType.MIR -> totalDayTransferMir
        CardType.MAESTRO -> totalDayTransferMaestro
        CardType.VK_PAY -> totalDayTransferVKPay
    }
}

fun getTotalMonthTransfer(cardType: CardType): Int {
    return when (cardType) {
        CardType.MASTERCARD -> totalMonthTransferMastercard
        CardType.VISA -> totalMonthTransferVisa
        CardType.MIR -> totalMonthTransferMir
        CardType.MAESTRO -> totalMonthTransferMaestro
        CardType.VK_PAY -> totalMonthTransferVKPay
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

        CardType.MAESTRO -> {
            totalMonthTransferMaestro += amount
            totalDayTransferMaestro += amount
        }

        CardType.VK_PAY -> {
            totalMonthTransferVKPay += amount
            totalDayTransferVKPay += amount
        }
    }
}

fun clearDayTotal() {
    totalDayTransferMastercard = 0
    totalDayTransferVisa = 0
    totalDayTransferMir = 0
    totalDayTransferMaestro = 0
    totalDayTransferVKPay = 0
}

fun clearMonthTotal() {
    totalMonthTransferMastercard = 0
    totalMonthTransferVisa = 0
    totalMonthTransferMir = 0
    totalMonthTransferMaestro = 0
    totalMonthTransferVKPay = 0
}
