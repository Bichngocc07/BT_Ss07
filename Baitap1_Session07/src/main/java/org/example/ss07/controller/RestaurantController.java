package org.example.ss07.controller;

import org.example.ss07.model.RestaurantProfile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RestaurantController {

    // 1. Hiển thị trang cập nhật hồ sơ
    @GetMapping("/merchant/update-profile")
    public String showProfileForm(Model model) {
        // Khởi tạo đối tượng rỗng để Thymeleaf bind vào form
        model.addAttribute("restaurantProfile", new RestaurantProfile());
        return "profile";
    }

    // 2. Xử lý dữ liệu khi người dùng bấm Lưu
    @PostMapping("/merchant/update-profile")
    public String updateProfile(@ModelAttribute("restaurantProfile") RestaurantProfile profile) {
        // Kiểm tra log để xem Data Binding đã thành công chưa
        System.out.println("Tên quán: " + profile.getName());
        System.out.println("Số điện thoại: " + profile.getPhone());
        System.out.println("Trạng thái: " + (profile.isActive() ? "Đang mở" : "Đóng cửa"));

        // Trả về trang thông báo thành công hoặc redirect
        return "success";
    }
}