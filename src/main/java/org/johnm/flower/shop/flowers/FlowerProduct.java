package org.johnm.flower.shop.flowers;

import java.util.ArrayList;
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
	
	public List<Bundle> getCopyOfBundles() {
		final List<Bundle> copyOfBundles = new ArrayList<Bundle>();
		
		for (Bundle bundle : bundles) {
			copyOfBundles.add(bundle);
		}
		
		return copyOfBundles;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bundles == null) ? 0 : bundles.hashCode());
		result = prime * result
				+ ((flowerType == null) ? 0 : flowerType.hashCode());
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
		FlowerProduct other = (FlowerProduct) obj;
		if (bundles == null) {
			if (other.bundles != null)
				return false;
		} else if (!bundles.equals(other.bundles))
			return false;
		if (flowerType != other.flowerType)
			return false;
		return true;
	}

}
