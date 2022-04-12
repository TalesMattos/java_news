package java08;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalAndInteger {

	/**
	 
	 The enum RoundingMode provides eight rounding modes:
			
			CEILING – rounds towards positive infinity
			FLOOR – rounds towards negative infinity
			UP – rounds away from zero
			DOWN – rounds towards zero
			HALF_UP – rounds towards “nearest neighbor” unless both neighbors are equidistant, in which case rounds up
			HALF_DOWN – rounds towards “nearest neighbor” unless both neighbors are equidistant, in which case rounds down
			HALF_EVEN – rounds towards the “nearest neighbor” unless both neighbors are equidistant, in which case, rounds towards the even neighbor
			UNNECESSARY – no rounding is necessary and ArithmeticException is thrown if no exact result is possible
			
			***	HALF_EVEN rounding mode minimizes the bias due to rounding operations. It is frequently used. It is also known as the banker's rounding.
	 
	 */
	
	public static void main(String[] args) {
		System.out.println(new BigDecimal("2.445").setScale(2, RoundingMode.HALF_EVEN));
	}
	
	public static BigDecimal calculateTotalAmount(BigDecimal quantity,
	    BigDecimal unitPrice, BigDecimal discountRate, BigDecimal taxRate) { 
	    BigDecimal amount = quantity.multiply(unitPrice);
	    BigDecimal discount = amount.multiply(discountRate);
	    BigDecimal discountedAmount = amount.subtract(discount);
	    BigDecimal tax = discountedAmount.multiply(taxRate);
	    BigDecimal total = discountedAmount.add(tax);
	    BigDecimal totalhalf = discountedAmount.add(tax).divide(BigDecimal.valueOf(2));

	    // round to 2 decimal places using HALF_EVEN
	    BigDecimal roundedTotal = total.setScale(2, RoundingMode.HALF_EVEN);
	        
	    return roundedTotal;
	}
}
