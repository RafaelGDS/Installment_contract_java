package services;

public class PaypalService implements OnlinePayment{
	
	private static final double FEE_PORCENTAGE = 0.01;
	private static final double MONTHLY_INTEREST = 0.02;

	public double paymentFee(double amount) {
		return amount * FEE_PORCENTAGE;
	}

	public double interest(double amount, int months) {
		return amount * MONTHLY_INTEREST * months;
	}
}
