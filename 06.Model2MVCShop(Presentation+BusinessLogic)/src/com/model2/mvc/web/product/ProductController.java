package com.model2.mvc.web.product;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;

@Controller
public class ProductController {
	
	///Field
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	// setter Method 구현 않음
	
	public ProductController() {
		System.out.println(this.getClass());
	}
	
	@Value("#{commonProperties['pageUnit']}")
	// @Value("#{commonProperties['pageUnit'] ? : 3}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	// @Value("#{commonProperties['pageSize'] ? : 2}")
	int pageSize;
	
	@RequestMapping("/addProductView.do") // 등록
	public String addProductView() throws Exception {
	
		System.out.println("/addProduct.do");
		
		
		return "forward:/product/addProductView.jsp";
	
	}
	
	@RequestMapping("/addProduct.do") // 추가등록
	 public String addProduct(@ModelAttribute("product") Product product) throws Exception {
	
		System.out.println("/getProduct.do");
		
		String[] temp = product.getManuDate().split("-");
		product.setManuDate(temp[0]+temp[1]+temp[2]);
		
		productService.addProduct(product);
		
		
		return "forward:/product/addProduct.jsp";

	}
	
	@RequestMapping("/getProduct.do")
	public String getProduct(@RequestParam("prodNo") int prodNo, @RequestParam("menu") String menu, Model model,
			HttpServletRequest request , HttpServletResponse response) throws Exception {
	
		System.out.println("/getProduct.do");
		
		Product product = productService.getProduct(prodNo);
		
		Cookie[] cookies = request.getCookies();
		Cookie cookie = null;
		String str = "";
		
		if(cookies != null && cookies.length > 0) {
			for(int i=0; i<cookies.length; i++) {
				Cookie temp = cookies[i];
				
				if(temp.getName().equals("history")) {
					System.out.println("쿠키쿠키 : "+temp.getValue());
					
					str += cookies[i].getValue()+",";
				}
			}
			str += prodNo;
		}
		cookie = new Cookie("history", str);
		cookie.setMaxAge(-1);
		response.addCookie(cookie);
		
		model.addAttribute("product", product);
		
	
		
		
		if(menu.equals("manage")) {
			return "forward:/product/updateProductView.jsp";
		}else {
			return "forward:/product/getProduct.jsp";
		}
		
		
	
	}
	
	@RequestMapping("/updateProductView.do") // 수정
	public String updateProductView(@RequestParam("prodNo") int prodNo, Model model) throws Exception {
	
		System.out.println("/updateProductView.do");
		
		Product product = productService.getProduct(prodNo);
		
		model.addAttribute("product", product);
		
		
		return "forward:/product/updateProductView.jsp";
	
	}
	
	@RequestMapping("/updateProduct.do")
	public String updateProduct(@ModelAttribute("product") Product product, Model model, HttpSession session) throws Exception {
	
		System.out.println("/updateProduct.do");
		
		productService.updateProduct(product);
		
		model.addAttribute("product", product);
		
		
	return "forward:/product/getProduct.jsp";

	}
	
	@RequestMapping("/listProduct.do") //목록
	public String listProduct(@ModelAttribute("search") Search search, Model model, HttpServletRequest request) throws Exception {

		System.out.println("/listProduct.do");
		
		if(search.getCurrentPage() == 0) {
			search.setCurrentPage(1);
		}
		
		if(search.getPageSize() == 0) {
			search.setPageSize(pageSize);
		}
		
		Map<String, Object> map = productService.getProductList(search);
		
		Page resultPage = new Page(search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, search.getPageSize());
		System.out.println(resultPage);
		
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		
		
		return "forward:/product/listProduct.jsp";

	}
	
}