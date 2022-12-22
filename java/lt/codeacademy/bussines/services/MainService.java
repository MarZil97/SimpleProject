package lt.codeacademy.bussines.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lt.codeacademy.bussines.entities.Uzsakymai;
import lt.codeacademy.bussines.entities.Purchase;
import lt.codeacademy.bussines.repositories.OrderRepository;
import lt.codeacademy.bussines.repositories.PurchaseRepository;

@Service
public class MainService {

	@Autowired
	PurchaseRepository pRep;
	
	@Autowired
	OrderRepository oRep;
	
	public List<Purchase> getPurchaseList(){
		return pRep.findAll();
	}
	
	public List<Uzsakymai> getOrderList(){
		return oRep.findAll();
	}
	
	public  Optional<Uzsakymai> orderFindById(long id) {
		return oRep.findById(id);
	}
	
	public Optional<Purchase> purchaseFindById(long id) {
		return pRep.findById(id);
	}
	
	public void savePurchase(Purchase purchase) {
		pRep.save(purchase);
	}
	
	public void saveOrder(Uzsakymai order) {
		oRep.save(order);
	}
	
	public void deletePurchase(Purchase purchase) {
		pRep.delete(purchase);
	}
	public void deleteOrder(Uzsakymai order) {
		oRep.delete(order);
	}
	
	public double findSpents() {
		ArrayList<Purchase> list = (ArrayList<Purchase>) getPurchaseList();
		double sum = 0;
		for(Purchase e : list) {
			sum = sum + e.getPrice();
		}
		return sum;
	}
	
	public double findGrosEarns() {
		ArrayList<Uzsakymai> list = (ArrayList<Uzsakymai>) getOrderList();
		double sum = 0;
		for(Uzsakymai e : list) {
			if(e.isStatus()) {
				sum = sum + e.getPrice();
			}
		}
		return sum;
	}
	
	public double findEarn() {
		return findGrosEarns() - findSpents();
	}
	
	public List<Uzsakymai> findUzsakymai(){
		return oRep.findAll();
	}
}
