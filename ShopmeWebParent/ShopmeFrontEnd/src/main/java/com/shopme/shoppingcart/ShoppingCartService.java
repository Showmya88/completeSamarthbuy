package com.shopme.shoppingcart;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopme.common.entity.CartItem;
import com.shopme.common.entity.Customer;
import com.shopme.common.entity.product.Product;
import com.shopme.order.OrderRepository;
import com.shopme.product.ProductRepository;

@Service
@Transactional
public class ShoppingCartService {

	@Autowired private CartItemRepository cartRepo;
	@Autowired private ProductRepository productRepo;
	@Autowired private OrderRepository orderRepo;
	
	public Integer addProduct(Integer productId, Integer quantity, Customer customer) 
			throws ShoppingCartException {
		Integer updatedQuantity = quantity;
		Product product = new Product(productId);
		
		int sumquantity = 0;
		try {
			sumquantity = fairusetypevalidation(productId,customer);
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		
		CartItem cartItem = cartRepo.findByCustomerAndProduct(customer, product);
		
		if (cartItem != null) {
			updatedQuantity = cartItem.getQuantity() + quantity;
			checkfairandperorderlimit(updatedQuantity,sumquantity,productId,customer);
	
		} else {
			checkfairandperorderlimit(updatedQuantity,sumquantity,productId,customer);
			cartItem = new CartItem();
			cartItem.setCustomer(customer);
			cartItem.setProduct(product);
		}
		
		cartItem.setQuantity(updatedQuantity);
		
		cartRepo.save(cartItem);
		
		return updatedQuantity;
	}
	
	private void checkfairandperorderlimit(Integer updatedQuantity, int sumquantity, Integer productId,
			Customer customer) throws ShoppingCartException {
		Product productDetails = productRepo.findById(productId).get();
		
		int fairuselimit = productDetails.getFairUseLimit();
		int perorderlimit = productDetails.getPerOrderLimit();
		String fairtype = productDetails.getFairUseType();
		
		if(sumquantity<=fairuselimit) {
			
			int newquantity=sumquantity+updatedQuantity;
			if((updatedQuantity>perorderlimit) || (newquantity>fairuselimit)) {
				throw new ShoppingCartException("As per policy Per Order Limit "
						+ "or Fair Use Limit is reached for this Product.");
			}
		}else {
			throw new ShoppingCartException("As per policy Fair Use Limit "
					+ "is reached for this - \t"+fairtype);
		}
		
	}

	private int fairusetypevalidation(Integer productId, Customer customer) throws ParseException {
		
		Product productDetails = productRepo.findById(productId).get();
		String fairtype = productDetails.getFairUseType();
		
		int customerid=customer.getId();
		int previouspurchasedquantity;
	
		LocalDate date = LocalDate.now();
		LocalDate newdate = date;
		
		switch(fairtype) {
		case "DAY":
			 newdate = date;
			 break;
		case "WEEK":
			 newdate = date.minusDays(7);
			 break;
		case "MONTH":
			 newdate = date.minusDays(30);
			 break;
		case "YEAR":
			 newdate = date.minusDays(365);
			 break;
		}
	   	    
		Date todaydate= new Date();
		Date validationdate = new SimpleDateFormat("yyyy-MM-dd").parse(newdate.toString());
	    						
		Integer sumofpreviouspurchasedquantity 
		=orderRepo.sumofpreviouspurchaseditems(productId,customerid,todaydate,validationdate);
		
		if (sumofpreviouspurchasedquantity == null){
			
			previouspurchasedquantity=0;
		} else {
			
			previouspurchasedquantity = sumofpreviouspurchasedquantity;
		 	
		}
		return previouspurchasedquantity;	
	}

	public List<CartItem> listCartItems(Customer customer) {
		return cartRepo.findByCustomer(customer);
	}
	
	public float updateQuantity(Integer productId, Integer quantity, Customer customer) 
			throws ShoppingCartException, ParseException {
		try {
			fairusepolicycheckandupdatequan(productId, quantity, customer);
			cartRepo.updateQuantity(quantity, customer.getId(), productId);
			Product product = productRepo.findById(productId).get();
			float subtotal = product.getDiscountPrice() * quantity;
			return subtotal;
		} catch (ShoppingCartException e) {
			throw e;
		} catch (ParseException e) {
			throw e;
		}		
	}
	
	private void fairusepolicycheckandupdatequan(Integer productId, Integer quantity, Customer customer) 
			throws ShoppingCartException,ParseException {
		
		int sumquantity = 0;
		try {
			sumquantity = fairusetypevalidation(productId,customer);
		}catch(ParseException e) {
			throw e;
		}
		
		try {
			checkfairandperorderlimit(quantity,sumquantity,productId,customer);
		} catch (ShoppingCartException e) {
			throw e;
		}
		
	}

	public void removeProduct(Integer productId, Customer customer) {
		cartRepo.deleteByCustomerAndProduct(customer.getId(), productId);
	}
	
	public void deleteByCustomer(Customer customer) {
		cartRepo.deleteByCustomer(customer.getId());
	}
}