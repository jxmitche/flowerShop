package org.johnm.flower.shop.reporting;

import java.util.List;

import org.johnm.flower.shop.orders.OrderLine;

public interface Reporter {
	void report(final List<OrderLine> orderLines);
}
