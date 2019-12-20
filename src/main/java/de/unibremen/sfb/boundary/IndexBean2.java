package de.unibremen.sfb.boundary;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.unibremen.sfb.control.CustomerService;
import de.unibremen.sfb.control.OrderService;
import de.unibremen.sfb.entity.Customer;
import de.unibremen.sfb.entity.Order;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

@Named
@RequestScoped
public class IndexBean2 {

	@Inject
	private CustomerService customerService;

	@Inject
	private OrderService orderService;

	private BarChartModel customerBarModel;

    private List<Order> orders;

	@PostConstruct
	public void init() {
		this.orders = orderService.getOrders();
		createCustomerRevenueBarChart();
	}

	private void createCustomerRevenueBarChart() {

		BarChartModel model = new BarChartModel();

		ChartSeries revenue = new ChartSeries();
		revenue.setLabel("Revenue");

		List<Customer> customers = customerService.getCustomers();

        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 0; i < alphabet.length(); i++) {
			String letter = alphabet.substring(i, i + 1);

			long summedRevenueByLetter = customers.stream()
					.filter(c -> c.getFirstName().substring(0, 1).equalsIgnoreCase(letter))
					.mapToLong(Customer::getBilledRevenue).sum();

			revenue.set(letter, summedRevenueByLetter);
		}

		model.addSeries(revenue);
		model.setTitle("Customer revenue (grouped by first name starting-letter)");
		model.setLegendPosition("ne");
		model.setSeriesColors("007ad9");

		Axis xAxis = model.getAxis(AxisType.X);
		xAxis.setLabel("First name starting-letter");

		Axis yAxis = model.getAxis(AxisType.Y);
		yAxis.setLabel("Revenue");

		customerBarModel = model;
	}

	public BarChartModel getCustomerBarModel() {
		return customerBarModel;
	}

	public List<Order> getOrders() {
		return orders;
	}

}
