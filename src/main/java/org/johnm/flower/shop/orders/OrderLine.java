package org.johnm.flower.shop.orders;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.johnm.flower.shop.flowers.Bundle;
import org.johnm.flower.shop.flowers.FlowerProduct;
import org.johnm.flower.shop.money.AussieDollars;
import org.johnm.flower.shop.validation.NullParamValidator;

public class OrderLine {
	private boolean isValid;
	private boolean cantMatchNumberOrderedToBundleSizes;
	private long numberOrdered;
	private FlowerProduct flowerProduct;
	private Map<Bundle, Long> howManyOfEachBundle = new HashMap<Bundle, Long>();
	private AussieDollars totalPrice;
	private NullParamValidator nullValidator;
	
	public OrderLine(final long numberOrdered, final FlowerProduct flowerProduct, final boolean isValid) {
		nullValidator = new NullParamValidator();
		nullValidator.checkNotNull(flowerProduct, "flowerProduct");
		
		this.numberOrdered = numberOrdered;
		this.flowerProduct = flowerProduct;
		this.isValid = isValid;
	}

	public boolean isValid() {
		return isValid;
	}

	public boolean isCantMatchNumberOrderedToBundleSizes() {
		return cantMatchNumberOrderedToBundleSizes;
	}

	public long getNumberOrdered() {
		return numberOrdered;
	}

	public FlowerProduct getFlowerProduct() {
		return flowerProduct;
	}
	
	public Map<Bundle, Long> getHowManyOfEachBundle() {
		return howManyOfEachBundle;
	}

	public AussieDollars getTotalPrice() {
		return totalPrice;
	}

	void calculateBundlesToFillOrder() {
		if (isValid) {
			final List<Bundle> bundles = flowerProduct.getBundles();
			long flowersLeftToBeAllocatedToABundle = numberOrdered;
			
			for (Bundle bundle : bundles) {
				final long flowersInThisBundle = 
						calculateFlowersInThisBundle(flowersLeftToBeAllocatedToABundle, bundle.getNumberInBundle());
				howManyOfEachBundle.put(bundle, flowersInThisBundle / bundle.getNumberInBundle());
				flowersLeftToBeAllocatedToABundle = flowersLeftToBeAllocatedToABundle - flowersInThisBundle;
			}
			
			if (flowersLeftToBeAllocatedToABundle != 0) {
				cantMatchNumberOrderedToBundleSizes = true;
			}
		}
	}
	
	long calculateFlowersInThisBundle(final long flowersLeftToBeAllocatedToABundle, final long numberInBundle) {
		final long multiplier = flowersLeftToBeAllocatedToABundle / numberInBundle;
		final long flowersInThisBundle = multiplier * numberInBundle;
		
		return flowersInThisBundle;
	}
	
	void calculateTotalPrice() {
		totalPrice = new AussieDollars(0);
		
		for (Bundle bundle : howManyOfEachBundle.keySet()) {
			final long howMany = howManyOfEachBundle.get(bundle);
			final AussieDollars bundleTotal = bundle.getPricePerBundle().multiply(howMany);
			totalPrice = totalPrice.add(bundleTotal);
		}
	}
}
