package com.example.ecommerce.Controller;

import com.example.ecommerce.Entity.Products;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ProductController {

    private List<Products> productsList = Arrays.asList(new Products(001L,"Iphone 14 pro", 180000.00,"C:\\Users\\aaaa\\Pictures\\graffiti\\Adhesive poster Minion Bomb Box Graffiti - WALL STICKERS.jpg", "Available"));
}
