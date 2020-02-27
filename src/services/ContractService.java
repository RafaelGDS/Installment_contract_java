package services;

import java.util.Calendar;
import java.util.Date;

import entities.Contract;
import entities.Installment;

public class ContractService {

	private OnlinePayment onlinePayment;

	public ContractService(OnlinePayment onlinePayment) {
		this.onlinePayment = onlinePayment;
	}
	
	public void processContract(Contract contract, int months) {
		double basicQuota = contract.getTotalValue() / months;
		for(int i=1; i<=months; i++) {
			Date date = addMonths(contract.getDate(), i);
			double updateQuota = basicQuota + onlinePayment.interest(basicQuota, i);
			double fullQuota = updateQuota + onlinePayment.paymentFee(updateQuota);
			contract.addInstallment(new Installment(date, fullQuota));
		}
	}
	
	private Date addMonths(Date date, int n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, n);
		return cal.getTime();
	}
}
