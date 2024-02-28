import org.junit.Test
import org.junit.Assert.*

class MainKtTest {


    @Test
    fun getTotalMonthAndDayTransferMastercard() {
        clearDayTotal()
        clearMonthTotal()
        val cardType = CardType.MASTERCARD
        var monthResult = getTotalMonthTransfer(cardType)
        assertEquals(0, monthResult)
        var dayResult = getTotalDayTransfer(cardType)
        assertEquals(0, dayResult)
        transferByCardType(cardType, 100)
        monthResult = getTotalMonthTransfer(cardType)
        assertEquals(100, monthResult)
        dayResult = getTotalDayTransfer(cardType)
        assertEquals(100, dayResult)
        clearDayTotal()
        clearMonthTotal()
        monthResult = getTotalMonthTransfer(cardType)
        assertEquals(0, monthResult)
        dayResult = getTotalDayTransfer(cardType)
        assertEquals(0, dayResult)
    }

    @Test
    fun getTotalMonthAndDayTransferVisa() {
        clearDayTotal()
        clearMonthTotal()
        val cardType = CardType.VISA
        var monthResult = getTotalMonthTransfer(cardType)
        assertEquals(0, monthResult)
        var dayResult = getTotalDayTransfer(cardType)
        assertEquals(0, dayResult)
        transferByCardType(cardType, 100)
        monthResult = getTotalMonthTransfer(cardType)
        assertEquals(100, monthResult)
        dayResult = getTotalDayTransfer(cardType)
        assertEquals(100, dayResult)
        clearDayTotal()
        clearMonthTotal()
        monthResult = getTotalMonthTransfer(cardType)
        assertEquals(0, monthResult)
        dayResult = getTotalDayTransfer(cardType)
        assertEquals(0, dayResult)
    }

    @Test
    fun getTotalMonthAndDayTransferMir() {
        clearDayTotal()
        clearMonthTotal()
        val cardType = CardType.MIR
        var monthResult = getTotalMonthTransfer(cardType)
        assertEquals(0, monthResult)
        var dayResult = getTotalDayTransfer(cardType)
        assertEquals(0, dayResult)
        transferByCardType(cardType, 100)
        monthResult = getTotalMonthTransfer(cardType)
        assertEquals(100, monthResult)
        dayResult = getTotalDayTransfer(cardType)
        assertEquals(100, dayResult)
        clearDayTotal()
        clearMonthTotal()
        monthResult = getTotalMonthTransfer(cardType)
        assertEquals(0, monthResult)
        dayResult = getTotalDayTransfer(cardType)
        assertEquals(0, dayResult)
    }


    @Test
    fun getTotalMonthAndDayTransferMaestro() {
        clearDayTotal()
        clearMonthTotal()
        val cardType = CardType.MAESTRO
        var monthResult = getTotalMonthTransfer(cardType)
        assertEquals(0, monthResult)
        var dayResult = getTotalDayTransfer(cardType)
        assertEquals(0, dayResult)
        transferByCardType(cardType, 100)
        monthResult = getTotalMonthTransfer(cardType)
        assertEquals(100, monthResult)
        dayResult = getTotalDayTransfer(cardType)
        assertEquals(100, dayResult)
        clearDayTotal()
        clearMonthTotal()
        monthResult = getTotalMonthTransfer(cardType)
        assertEquals(0, monthResult)
        dayResult = getTotalDayTransfer(cardType)
        assertEquals(0, dayResult)
    }


    @Test
    fun getTotalMonthAndDayTransferVKPay() {
        clearDayTotal()
        clearMonthTotal()
        val cardType = CardType.VK_PAY
        var monthResult = getTotalMonthTransfer(cardType)
        assertEquals(0, monthResult)
        var dayResult = getTotalDayTransfer(cardType)
        assertEquals(0, dayResult)
        transferByCardType(cardType, 100)
        monthResult = getTotalMonthTransfer(cardType)
        assertEquals(100, monthResult)
        dayResult = getTotalDayTransfer(cardType)
        assertEquals(100, dayResult)
        clearDayTotal()
        clearMonthTotal()
        monthResult = getTotalMonthTransfer(cardType)
        assertEquals(0, monthResult)
        dayResult = getTotalDayTransfer(cardType)
        assertEquals(0, dayResult)
    }

    @Test
    fun transferByCardTypeTest() {
        clearDayTotal()
        clearMonthTotal()
        var result = transferByCardType(CardType.VK_PAY, 100_000)
        assertEquals(false, result)

        result = transferByCardType( amount = 150_000)
        assertEquals(true, result)
        result = transferByCardType(CardType.MASTERCARD, 150_000)
        assertEquals(true, result)
        result = transferByCardType(CardType.VISA, 150_000)
        assertEquals(true, result)
        result = transferByCardType(CardType.MAESTRO, 150_000)
        assertEquals(true, result)
        result = transferByCardType( amount = 100_000)
        assertEquals(false, result)
        result = transferByCardType(CardType.MASTERCARD, 150_000)
        assertEquals(false, result)
        result = transferByCardType(CardType.MAESTRO, 150_000)
        assertEquals(false, result)
        result = transferByCardType(CardType.VISA, 150_000)
        assertEquals(false, result)
        result = transferByCardType(CardType.VK_PAY, 10_000)
        assertEquals(true, result)
        result = transferByCardType(CardType.VK_PAY, 15_000)
        assertEquals(false, result)
        clearDayTotal()
        result = transferByCardType( amount = 150_000)
        assertEquals(true, result)
        result = transferByCardType(CardType.MASTERCARD, 150_000)
        assertEquals(true, result)
        result = transferByCardType(CardType.VISA, 150_000)
        assertEquals(true, result)
        result = transferByCardType(CardType.MAESTRO, 150_000)
        assertEquals(true, result)
        result = transferByCardType(CardType.VK_PAY, 10_000)
        assertEquals(true, result)
        clearDayTotal()
        result = transferByCardType( amount = 150_000)
        assertEquals(true, result)
        result = transferByCardType(CardType.MASTERCARD, 150_000)
        assertEquals(true, result)
        result = transferByCardType(CardType.VISA, 150_000)
        assertEquals(true, result)
        result = transferByCardType(CardType.MAESTRO, 150_000)
        assertEquals(true, result)
        result = transferByCardType(CardType.VK_PAY, 10_000)
        assertEquals(true, result)
        clearDayTotal()
        result = transferByCardType( amount = 150_000)
        assertEquals(true, result)
        result = transferByCardType(CardType.MASTERCARD, 150_000)
        assertEquals(true, result)
        result = transferByCardType(CardType.VISA, 150_000)
        assertEquals(true, result)
        result = transferByCardType(CardType.MAESTRO, 150_000)
        assertEquals(true, result)
        result = transferByCardType(CardType.VK_PAY, 10_000)
        assertEquals(true, result)
        result = transferByCardType( amount = 100_000)
        assertEquals(false, result)
        result = transferByCardType(CardType.MASTERCARD, 100_000)
        assertEquals(false, result)
        result = transferByCardType(CardType.VISA, 100_000)
        assertEquals(false, result)
        result = transferByCardType(CardType.MAESTRO, 100_000)
        assertEquals(false, result)
        result = transferByCardType(CardType.VK_PAY, 10_000)
        assertEquals(false, result)
        clearDayTotal()
        result = transferByCardType( amount = 100_000)
        assertEquals(false, result)
        result = transferByCardType(CardType.MASTERCARD, 100_000)
        assertEquals(false, result)
        result = transferByCardType(CardType.VISA, 100_000)
        assertEquals(false, result)
        result = transferByCardType(CardType.MAESTRO, 100_000)
        assertEquals(false, result)
        result = transferByCardType(CardType.VK_PAY, 10_000)
        assertEquals(false, result)
    }

    @Test
    fun getCommissionTest() {
        clearDayTotal()
        clearMonthTotal()
        var result = getCommission(CardType.MASTERCARD, 100)
        assertEquals( 0,result)
        result = getCommission(CardType.VK_PAY, 100)
        assertEquals( 0,result)
        result = getCommission(CardType.MAESTRO, 100)
        assertEquals( 0,result)
        result = getCommission(CardType.MIR, 100)
        assertEquals( 35,result)
        result = getCommission(CardType.VISA, 100)
        assertEquals( 35,result)

        result = getCommission(CardType.MASTERCARD, 400_000)
        assertEquals( 1970,result)
        result = getCommission(CardType.VK_PAY, 400_000)
        assertEquals( 0,result)
        result = getCommission(CardType.MAESTRO, 400_000)
        assertEquals( 1970,result)
        result = getCommission(CardType.MIR, 400_000)
        assertEquals( 3000,result)
        result = getCommission(CardType.VISA, 400_000)
        assertEquals( 3000,result)

        result = getCommission(amount = 400_000)
        assertEquals( 3000,result)


    }

}