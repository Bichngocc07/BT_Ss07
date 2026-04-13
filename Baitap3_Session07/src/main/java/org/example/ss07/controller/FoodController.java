package org.example.ss07.controller;

import org.example.ss07.model.Food;
import org.example.ss07.repository.FoodRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Controller
public class FoodController {

    private static final String UPLOAD_DIR = "C:/RikkeiFood_Temp/";

    @GetMapping("/add")
    public String showForm() {
        return "add-food";
    }

    @PostMapping("/add")
    public String addFood(
            @RequestParam String name,
            @RequestParam String category,
            @RequestParam double price,
            @RequestParam("image") MultipartFile file,
            Model model
    ) {

        if (file.isEmpty()) {
            model.addAttribute("error", "Chưa chọn file");
            return "add-food";
        }

        String filename = file.getOriginalFilename();

        if (!filename.endsWith(".jpg") && !filename.endsWith(".png") && !filename.endsWith(".jpeg")) {
            model.addAttribute("error", "Sai định dạng");
            return "add-food";
        }

        if (price < 0) {
            model.addAttribute("error", "Giá phải >= 0");
            return "add-food";
        }

        try {
            File dir = new File(UPLOAD_DIR);
            if (!dir.exists()) dir.mkdirs();

            File dest = new File(UPLOAD_DIR + filename);
            file.transferTo(dest);

            Food food = new Food();
            food.setName(name);
            food.setCategory(category);
            food.setPrice(price);
            food.setImagePath(dest.getAbsolutePath());

            FoodRepository.list.add(food);

            System.out.println("Tổng: " + FoodRepository.list.size());

            model.addAttribute("success", "OK");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "add-food";
    }
}