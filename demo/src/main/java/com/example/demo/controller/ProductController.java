package com.example.demo.controller;

import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.model.Status;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    // Hiển thị danh sách sản phẩm
    @GetMapping
    public ModelAndView showProductList() {
        List<Product> products = productService.getAllProducts();
        return new ModelAndView("product/list", "products", products);
    }

    // Hiển thị form thêm sản phẩm
    @GetMapping("/add")
    public ModelAndView showAddForm() {
        ModelAndView modelAndView = new ModelAndView("product/add");
        modelAndView.addObject("product", new Product());
        modelAndView.addObject("categories", categoryService.getAllCategories());
        modelAndView.addObject("statuses", Status.values());
        return modelAndView;
    }

    // Lưu sản phẩm mới
    @PostMapping("/add")
    public String saveNewProduct(@Valid @ModelAttribute("product") Product product,
                                 BindingResult bindingResult,
                                 Model model,
                                 RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryService.getAllCategories());
            model.addAttribute("statuses", Status.values());
            return "product/add";
        }
        productService.saveProduct(product);
        redirectAttributes.addFlashAttribute("msg", "Thêm sản phẩm thành công!");
        return "redirect:/products";
    }

    // Hiển thị form chỉnh sửa sản phẩm
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        if (product == null) {
            return "error/404"; // Trang lỗi nếu không tìm thấy sản phẩm
        }
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("statuses", Status.values());
        return "product/edit";
    }

    // Cập nhật sản phẩm
    @PostMapping("/edit")
    public String updateProduct(@Valid @ModelAttribute("product") Product product,
                                BindingResult bindingResult,
                                Model model,
                                RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryService.getAllCategories());
            model.addAttribute("statuses", Status.values());
            return "product/edit";
        }
        productService.saveProduct(product);
        redirectAttributes.addFlashAttribute("msg", "Cập nhật sản phẩm thành công!");
        return "redirect:/products";
    }

    // Hiển thị xác nhận xóa sản phẩm
    @GetMapping("/delete/{id}")
    public String showDeleteForm(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        if (product == null) {
            return "error/404"; // Trang lỗi nếu không tìm thấy sản phẩm
        }
        model.addAttribute("product", product);
        return "product/delete";
    }

    // Xóa sản phẩm
    @PostMapping("/delete")
    public String deleteProduct(@RequestParam Long id,
                                @RequestParam String submit,
                                RedirectAttributes redirectAttributes) {
        if ("Delete".equals(submit)) {
            productService.deleteProducts(List.of(id));
            redirectAttributes.addFlashAttribute("msg", "Xóa sản phẩm thành công!");
        }
        return "redirect:/products";
    }
}
