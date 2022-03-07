package com.bd.tpfinal.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bd.tpfinal.model.HistoricalProductPrice;
import com.bd.tpfinal.model.Product;
import com.bd.tpfinal.services.ProductService;

@RestController
@RequestMapping(value = "/api/product")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
public class ProductController {
	@Autowired ProductService productService;
	
	@PostMapping("/create")
    public long createProduct( @RequestBody Product product) {
		try {
			product = productService.createNewProduct(product);
			return (product != null) ? product.getId() : -1;
		}
		catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
    }
	
	@PutMapping("/changePrice")
	public boolean changeProductPrice(@RequestParam(name = "idProduct") long idProduct, @RequestParam(name = "newPrice") float newPrice) {
		try {
			return productService.changeProductPrice(idProduct, newPrice);
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@GetMapping("/list")
    public List<Product> getProductList() {
		try {
			return productService.getProductList();
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }
	
	@GetMapping("/getHistoricalPrices")
    public List<HistoricalProductPrice> getHistoricalPricesFromProduct( @RequestParam(name = "idProduct") long idProduct ) {
		try {
			return productService.getHistoricalPricesFromProduct(idProduct);
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }
}
