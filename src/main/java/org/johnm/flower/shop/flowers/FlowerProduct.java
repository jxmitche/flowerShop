package org.johnm.flower.shop.flowers;

import java.util.List;

import org.johnm.flower.shop.validation.NullParamValidator;

public class FlowerProduct {
	private NullParamValidator nullChecker = new NullParamValidator();
	private FlowerType flowerType;
	private List<Bundle> bundles;
	
	public FlowerProduct(final FlowerType flowerType, final List<Bundle> bundles) {
		nullChecker.checkNotNull(flowerType, "flowerType");
		nullChecker.checkNotNull(bundles, "bundles");
		
		this.flowerType = flowerType;
		this.bundles = bundles;
	}
	
	public boolean isUnknown() {
		return FlowerType.UNKNOWN.equals(flowerType);
	}

	public FlowerType getFlowerType() {
		return flowerType;
	}

	public List<Bundle> getBundles() {
		return bundles;
	}

}
