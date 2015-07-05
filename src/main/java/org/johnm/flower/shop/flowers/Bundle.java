package org.johnm.flower.shop.flowers;

import org.johnm.flower.shop.money.AussieDollars;

public class Bundle {
	private long numberInBundle;
	private AussieDollars pricePerBundle;
	
	public Bundle(final long nbrInBundle, final long pricePerBundle) {
		final AussieDollars dollars = new AussieDollars(pricePerBundle);
		
		this.numberInBundle = nbrInBundle;
		this.pricePerBundle = dollars;
	}

	long getNumberInBundle() {
		return numberInBundle;
	}

	AussieDollars getPricePerBundle() {
		return pricePerBundle;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ (int) (numberInBundle ^ (numberInBundle >>> 32));
		result = prime * result
				+ ((pricePerBundle == null) ? 0 : pricePerBundle.hashCode());
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
		Bundle other = (Bundle) obj;
		if (numberInBundle != other.numberInBundle)
			return false;
		if (pricePerBundle == null) {
			if (other.pricePerBundle != null)
				return false;
		} else if (!pricePerBundle.equals(other.pricePerBundle))
			return false;
		return true;
	}
	
}
