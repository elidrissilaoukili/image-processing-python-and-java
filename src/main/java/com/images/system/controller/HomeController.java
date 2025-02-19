package com.images.system.controller;

import java.io.IOException;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.ui.Model;

import java.nio.file.*;
import java.util.List;

@Controller
public class HomeController {

    @GetMapping({ "/" })
    public String home() {
        return "bgremover";
    }

    @GetMapping("/view-image")
    public String viewImage(Model model) {
        List<String> uploadImages = getImagesFromDirectory("src/main/resources/static/uploads");
        List<String> outputImages = getImagesFromDirectory("src/main/resources/static/outputs");

        model.addAttribute("uploadImages", uploadImages);
        model.addAttribute("outputImages", outputImages);
        return "view-image";
    }

    private List<String> getImagesFromDirectory(String dirPath) {
        try {
            Path path = Paths.get(dirPath);
            if (Files.exists(path)) {
                return Files.list(path)
                        .filter(Files::isRegularFile)
                        .map(p -> path.getFileName().toString() + "/" + p.getFileName().toString())
                        .collect(Collectors.toList());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return List.of(); // Return empty list if error occurs
    }

}
