package com.example.film.controller;

import com.example.film.utils.ImageUpload;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author 陈乐
 * @version 1.0
 * @date 2020/3/11 16:55
 */
@Controller
public class TestController {

    @GetMapping(value = "/index")
    public String file() {
        return "HelloWord";
    }

    @PostMapping(value = "/fileUpload")
    public String fileUpload(@RequestParam(value = "file") MultipartFile file, Model model, HttpServletRequest request) {
        String filename = ImageUpload.upload(file);
        model.addAttribute("filename", filename);
        return "HelloWord";
    }

}
