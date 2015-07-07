package org.johnm.flower.shop.money;

import java.math.BigDecimal;

import org.johnm.flower.shop.validation.NullParamValidator;

public class AussieDollars {
	private BigDecimal amount;
	private NullParamValidator nullValidator = new NullParamValidator();
	
	public AussieDollars(final long amount) {
		final BigDecimal value = BigDecimal.valueOf(amount, 2);
		this.amount = value;
	}
	
	private AussieDollars(final BigDecimal amount) {
		nullValidator.checkNotNull(amount, "amount");
		
		this.amount = amount;
	}

	BigDecimal getAmount() {
		return amount;
	}
	
	public AussieDollars multiply(final long multiplier) {
		final BigDecimal multi = BigDecimal.valueOf(multiplier, 0);
		final BigDecimal result = amount.multiply(multi);
		final AussieDollars dollarResult = new AussieDollars(result);
		
		return dollarResult;
	}
	
	public AussieDollars add(final AussieDollars leftHandSide) {
		nullValidator.checkNotNull(leftHandSide, "leftHandSide");
		
		final BigDecimal result = amount.add(leftHandSide.getAmount());
		final AussieDollars dollarResult = new AussieDollars(result);
		
		return dollarResult;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AussieDollars other = (AussieDollars) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "$" + amount.toString();
	}

}
