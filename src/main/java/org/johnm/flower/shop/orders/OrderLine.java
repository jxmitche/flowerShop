package org.johnm.flower.shop.orders;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.johnm.flower.shop.flowers.Bundle;
import org.johnm.flower.shop.flowers.FlowerProduct;
import org.johnm.flower.shop.money.AussieDollars;
import org.johnm.flower.shop.validation.NullParamValidator;

public class OrderLine {
	private boolean isValidOrderLine;
	private boolean cantMatchNumberOrderedToBundleSizes;
	private long numberOrdered;
	private FlowerProduct flowerProduct;
	private Map<Bundle, Long> howManyOfEachBundle = new HashMap<Bundle, Long>();
	private AussieDollars totalPrice;
	private NullParamValidator nullValidator = new NullParamValidator();
	
	public OrderLine(final long numberOrdered, final FlowerProduct flowerProduct, final boolean isValid) {
		nullValidator.checkNotNull(flowerProduct, "flowerProduct");
		
		this.numberOrdered = numberOrdered;
		this.flowerProduct = flowerProduct;
		this.isValidOrderLine = isValid;
	}

	public boolean isValidOrderLine() {
		return isValidOrderLine;
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
		if (isValidOrderLine) {
			final List<Bundle> bundles = flowerProduct.getCopyOfBundles();
			int originalNumberOfBundles = bundles.size();
			long flowersLeftToBeAllocatedToABundle = 0;
					
			for (int i=0; i< originalNumberOfBundles; i++) {
				flowersLeftToBeAllocatedToABundle = allocateFlowersToBundles(bundles);
				
				if (flowersLeftToBeAllocatedToABundle == 0) {
					break;
				} 

				howManyOfEachBundle.put(bundles.get(0), 0l);
				bundles.remove(0);
			}
			
			if (flowersLeftToBeAllocatedToABundle != 0) {
				cantMatchNumberOrderedToBundleSizes = true;
			}			
		}
	}

	long allocateFlowersToBundles(List<Bundle> bundles) {
		final long flowersLeftToBeAllocatedToABundle = loopThruBundlesAllocatingFlowers(bundles, numberOrdered);
		
		return flowersLeftToBeAllocatedToABundle;
	}

	long loopThruBundlesAllocatingFlowers(final List<Bundle> bundles, final long numberOfFlowersOrdered) {
		long flowersLeftToBeAllocated = numberOfFlowersOrdered;
		
		for (Bundle bundle : bundles) {
			final long flowersInThisBundle = 
					calculateFlowersInThisBundle(flowersLeftToBeAllocated, bundle.getNumberInBundle());
			howManyOfEachBundle.put(bundle, flowersInThisBundle / bundle.getNumberInBundle());
			flowersLeftToBeAllocated = flowersLeftToBeAllocated - flowersInThisBundle;
		}
		
		return flowersLeftToBeAllocated;
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
