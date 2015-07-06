package org.johnm.flower.shop.flowers;

import java.util.ArrayList;
import java.util.List;

import org.johnm.flower.shop.validation.NullParamValidator;

public class FlowerProductFactory {
	private NullParamValidator nullChecker = new NullParamValidator();
	private FlowerProduct roses;
	private FlowerProduct lilies;
	private FlowerProduct tulips;
	private FlowerProduct unknown;
	
	public FlowerProductFactory() {
		roses = createRoses();
		lilies = createLilies();
		tulips = createTulips();
		unknown = createUnknown();
	}
	
	public FlowerProduct getFlowerProductForCode(final String code) {
		nullChecker.checkNotNull(code, "code");
		
		if (FlowerType.ROSE.getCode().equals(code)) {
			return roses;
		} else if (FlowerType.LILY.getCode().equals(code)) {
			return lilies;
		} if (FlowerType.TULIP.getCode().equals(code)) {
			return tulips;
		}
		
		return unknown;
	}
	
	public FlowerProduct getUnknown() {
		return unknown;
	}

	FlowerProduct createRoses() {
		final List<Bundle> bundles = new ArrayList<Bundle>();
		
		bundles.add(new Bundle(10, 1299));
		bundles.add(new Bundle(5, 699));
		
		return new FlowerProduct(FlowerType.ROSE, bundles);
	}
	
	FlowerProduct createLilies() {
		final List<Bundle> bundles = new ArrayList<Bundle>();
		
		bundles.add(new Bundle(9, 2495));
		bundles.add(new Bundle(6, 1695));
		bundles.add(new Bundle(3, 995));
		
		return new FlowerProduct(FlowerType.LILY, bundles);
	}
	
	FlowerProduct createTulips() {
		final List<Bundle> bundles = new ArrayList<Bundle>();
		
		bundles.add(new Bundle(9, 1699));
		bundles.add(new Bundle(5, 995));
		bundles.add(new Bundle(3, 595));
		
		return new FlowerProduct(FlowerType.TULIP, bundles);
	}
	
	FlowerProduct createUnknown() {
		final List<Bundle> bundles = new ArrayList<Bundle>();
		
		return new FlowerProduct(FlowerType.UNKNOWN, bundles);
	}
}
