package com.shopme;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.shopme.category.CategoryService;
import com.shopme.common.entity.Category;
import com.shopme.common.entity.product.Product;
import com.shopme.common.exception.CategoryNotFoundException;
import com.shopme.product.ProductService;
import com.shopme.review.ReviewService;
import com.shopme.review.vote.ReviewVoteService;

@Controller
public class MainController {

	
	@Autowired private ProductService productService;
	@Autowired private CategoryService categoryService;
	
	
	@GetMapping("")
	public String viewHomePage(Model model) {
		List<Category> listCategories = categoryService.listNoChildrenCategories();
		model.addAttribute("listCategories", listCategories);
		
		return "index";
	}
	
	@GetMapping("/terms")
	public String viewterms()
	{
		return "terms";
	}
	@GetMapping("/browserpolicy")
	public String viewbrowswerpolicy()
	{
		return "browserpolicy";
	}
	
	@GetMapping("/privacypolicy")
	public String viewprivacypolicy()
	{
		return "privacypolicy";
	}
	
	
	@GetMapping("/login")
	public String viewLoginPage() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
			return "login";
		}
		
		return "redirect:/";
	}
	
	@GetMapping("/bags")
	public String subcat(Model model)
	{
		List<Category> listCategories = categoryService.listNoChildrenCategories();
		model.addAttribute("listCategories", listCategories);
		return "bags";
	}
	@GetMapping("/electronics")
	public String electronics(Model model)
	{
		List<Category> listCategories = categoryService.listNoChildrenCategories();
		model.addAttribute("listCategories", listCategories);
		return "electronics";
	}
	
	@GetMapping("/women")
	public String women(Model model)
	{
		List<Category> listCategories = categoryService.listNoChildrenCategories();
		model.addAttribute("listCategories", listCategories);
		return "women";
	}
	
	@GetMapping("/kids")
	public String kids(Model model)
	{
		List<Category> listCategories = categoryService.listNoChildrenCategories();
		model.addAttribute("listCategories", listCategories);
		return "kids";
	}
	
	@GetMapping("/brand")
	public String brand(Model model)
	{
		List<Category> listCategories = categoryService.listNoChildrenCategories();
		model.addAttribute("listCategories", listCategories);
		return "brand";
	}
	
	  

	
	
}
