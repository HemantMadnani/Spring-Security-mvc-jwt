package co.in.springsecwithhib.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import co.in.springsecwithhib.model.Transaction;

@RestController
public class TransactionController extends BaseController {

	@GetMapping("/home/transaction/fetch")
	public ResponseEntity<List<Transaction>> getAllTransaction() {

		return ResponseEntity.ok().body(getServiceRegistry().getTransactionService().getAllTransactions());
	}

}
