package org.example.ss07.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/merchant/dish")
public class DishController {

    /**
     * Phương thức này được đánh dấu là @ModelAttribute ở cấp độ Method.
     * Spring sẽ tự động chạy hàm này và đưa "categories" vào Model
     * trước khi bất kỳ hàm @GetMapping nào bên dưới được thực hiện.
     */
    @ModelAttribute("categories")
    public List<String> getCategories() {
        System.out.println("--- Tự động lấy danh sách categories ---");
        return Arrays.asList("Món chính", "Đồ uống", "Tráng miệng", "Topping");
    }

    // API 1: Mở form thêm mới
    @GetMapping("/add")
    public String showAddForm(Model model) {
        // Không cần thêm categories vào model nữa, Spring đã tự làm
        // model.addAttribute("dish", new Dish()); // Giả sử có class Dish
        return "dish-add";
    }

    // API 2: Mở form chỉnh sửa
    @GetMapping("/edit")
    public String showEditForm(Model model) {
        // Categories đã tự động có sẵn trong Model
        return "dish-edit";
    }

    // API 3: Mở trang tìm kiếm
    @GetMapping("/search")
    public String showSearchPage() {
        // Categories đã tự động có sẵn trong Model
        return "dish-search";
    }
}