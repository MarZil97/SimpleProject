package lt.codeacademy.bussines.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import lt.codeacademy.bussines.entities.Uzsakymai;
import lt.codeacademy.bussines.entities.Purchase;
import lt.codeacademy.bussines.services.MainService;

@Controller
public class MainController {

	@Autowired
	MainService ms;
	
	@GetMapping
	public String redirect() {
		return "redirect:/index";
	}
	
	@GetMapping("/index")
	public String showIndexPage() {
		return "index";
	}
	
	@GetMapping("/purchases")
	public String showPurchasesPage(Model model) {
		model.addAttribute("purchases", ms.getPurchaseList());
		return "purchases";
	}
	
	@GetMapping("/orders")
	public String showOrdersPage(Model model) {
		model.addAttribute("orders", ms.getOrderList());
		return "orders";
	}
	
	@GetMapping("/signpurchase")
	public String showPurchaseSignForm(Purchase purchase) {
		return "add-purchase";
	}
	
	@GetMapping("/signorder")
	public String showOrderSignForm(Uzsakymai order) {
		return "add-order";
	}
	
	@PostMapping("/addpurchase")
	public String addPurchase(Purchase purchase, BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			return "add-purchase";
		}
		ms.savePurchase(purchase);
		return "redirect:/purchases";
	}
	
	@PostMapping("/addorder")
	public String addOrder(Uzsakymai order, BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			return "add-order";
		}
		ms.saveOrder(order);
		return "redirect:/orders";
	}
	
	@GetMapping("/editpurchase/{id}")
	public String showPurchaseUpdateForm(@PathVariable("id") long id, Model model) {
		Purchase purchase = ms.purchaseFindById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		
		model.addAttribute("purchase", purchase);
		return "update-purchase";
	}
	
	@GetMapping("/editorder/{id}")
	public String showOrderUpdateForm(@PathVariable("id") long id, Model model) {
		Uzsakymai order = ms.orderFindById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		
		model.addAttribute("order", order);
		return "update-order";
	}
	
	@PostMapping("/updatepurchase/{id}")
	public String updatePurchase(@PathVariable("id") long id, Purchase purchase, BindingResult result, Model model) {
		if(result.hasErrors()) {
			purchase.setId(id);
			return "update-purchase";
		}
		
		ms.savePurchase(purchase);
		return "redirect:/purchases";
	}
	
	@PostMapping("/updateorder/{id}")
	public String updateOrder(@PathVariable("id") long id, Uzsakymai order, BindingResult result, Model model) {
		if(result.hasErrors()) {
			order.setId(id);
			return "update-order";
		}
		
		ms.saveOrder(order);
		return "redirect:/orders";
	}
	
	@GetMapping("/deletepurchase/{id}")
	public String deletePurchase(@PathVariable("id") long id, Model model) {
		Purchase purchase = ms.purchaseFindById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		ms.deletePurchase(purchase);
		return "redirect:/purchases";
	}
	
	@GetMapping("/deleteorder/{id}")
	public String deleteOrder(@PathVariable("id") long id, Model model) {
		Uzsakymai order = ms.orderFindById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		ms.deleteOrder(order);
		return "redirect:/orders";
	}
	@GetMapping("/earnings")
	public String earnings(Model model) {
		model.addAttribute("findspents", ms.findSpents());
		model.addAttribute("findgros", ms.findGrosEarns());
		model.addAttribute("earn", ms.findEarn());
		
		return "earnings";
	}
	
	@GetMapping("/adresas")
	public List<Uzsakymai> adresas(){
		return ms.findUzsakymai();
	}
	
}
