package edu.miu.eshop.bankapi.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import edu.miu.eshop.bankapi.domain.Transaction;
import edu.miu.eshop.bankapi.domain.TransactionHist;

public interface TransactionHistService {

	public void saveTransactionHist(Transaction transaction, boolean response, LocalDate date, LocalTime time);
	
	public List<TransactionHist> getAllTransactionHist();
	public double getTotalTransaction(boolean response);
}
