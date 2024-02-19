fun main() {
    val amount = 10540
    val minCommission = 35
    val commissionPercent = 0.75
    val commission = Integer.max(minCommission, Math.round(amount * (commissionPercent / 100)).toInt())
    println("Размер комиссии $commission руб.")
}