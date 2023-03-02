package com.shopme.order;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.shopme.common.entity.Customer;
import com.shopme.common.entity.order.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
	
	@Query("SELECT DISTINCT o FROM Order o JOIN o.orderDetails od JOIN od.product p "
			+ "WHERE o.customer.id = ?2 "
			+ "AND (p.name LIKE %?1% OR o.status LIKE %?1%)")
	public Page<Order> findAll(String keyword, Integer customerId, Pageable pageable);
	
	@Query("SELECT o FROM Order o WHERE o.customer.id = ?1")
	public Page<Order> findAll(Integer customerId, Pageable pageable);
	
	public Order findByIdAndCustomer(Integer id, Customer customer);
	
	@Query("select SUM(od.quantity) FROM Order o,OrderDetail od "
			+"WHERE o.id=od.order.id AND od.product.id=?1 AND  o.customer.id = ?2 AND"
			+ " o.orderTime < ?3 AND o.orderTime > ?4")			
	public Integer sumofpreviouspurchaseditems(Integer productId,Integer customerid, Date d1, Date d2);
}