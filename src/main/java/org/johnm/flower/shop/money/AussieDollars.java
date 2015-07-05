package org.johnm.flower.shop.money;

import java.math.BigDecimal;

public class AussieDollars {
	private BigDecimal amount;
	
	public AussieDollars(final long amount) {
		final BigDecimal value = BigDecimal.valueOf(amount, 2);
		this.amount = value;
	}

	BigDecimal getAmount() {
		return amount;
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

}
