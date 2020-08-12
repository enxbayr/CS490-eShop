package edu.miu.eshop.bankapi.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.miu.eshop.bankapi.Repository.TransactionHistRepository;
import edu.miu.eshop.bankapi.domain.Transaction;
import edu.miu.eshop.bankapi.domain.TransactionHist;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class TransactionHistServiceImpl implements TransactionHistService {

	@Autowired
	private TransactionHistRepository repository;

	@Override
	public void saveTransactionHist(Transaction transaction, boolean response, LocalDate date, LocalTime time) {
		TransactionHist transactionhist = new TransactionHist(transaction, response, date, time);
		repository.save(transactionhist);

	}
	
	
	@Override
	public List<TransactionHist> getAllTransactionHist() {
		return repository.findAll();
	}
	

	@Override
	public double getTotalTransaction(boolean response) {
		List<TransactionHist> transaction = getAllTransactionHist();
		double totalAmount = 0.0;
		for (TransactionHist trans : transaction) {
			if (trans.getResponse() == response) {
				totalAmount += trans.getTransaction().getAmount();
			}
		}
		return totalAmount;
	}

}
