package com.images.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.images.system.service.ImageProcessingService;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.Instant;

@RestController
public class ImageController {

    @Autowired
    private ImageProcessingService imageProcessingService;

    private String imageControllerHelper(MultipartFile file, String version) {
        if (file.isEmpty()) {
            return "No file uploaded.";
        }

        try {
            // Define external directories (outside JAR)
            String baseDir = System.getProperty("user.dir"); // Root of project
            String uploadDir = baseDir + "/src/main/resources/static/uploads/";
            String outputDir = baseDir + "/src/main/resources/static/outputs/";

            // Ensure directories exist
            new File(uploadDir).mkdirs();
            new File(outputDir).mkdirs();

            // Save uploaded file
            String originalFilename = file.getOriginalFilename();
            String uploadedFilePath = Paths.get(uploadDir, originalFilename).toString();
            file.transferTo(new File(uploadedFilePath));

            // Generate output filename
            String outputFileName = Instant.now().toEpochMilli() + "-output.png";
            String outputPath = Paths.get(outputDir, outputFileName).toString();

            // Process image (background removal)
            if ("removeBackgroundV1".equals(version)) {
                imageProcessingService.removeBackgroundV1(uploadedFilePath, outputPath);
            } else if ("removeBackgroundV2".equals(version)) {
                imageProcessingService.removeBackgroundV2(uploadedFilePath, outputPath);
            } else if ("imageEnhancerV1".equals(version)) {
                imageProcessingService.imageEnhancerV1(uploadedFilePath, outputPath);
            } else if ("invertImageV1".equals(version)) {
                imageProcessingService.invertImageV1(uploadedFilePath, outputPath);
            } else if ("pyramidalFilter".equals(version)) {
                imageProcessingService.pyramidalFilter(uploadedFilePath, outputPath);
            } else if ("laplacianFilter".equals(version)) {
                imageProcessingService.laplacianFilter(uploadedFilePath, outputPath);
            } else if ("makeupFilter".equals(version)) {
                imageProcessingService.makeupFilter(uploadedFilePath, outputPath);
            } else if ("scaleImageSize".equals(version)) {
                imageProcessingService.scaleImageSize(uploadedFilePath, outputPath);
            } else if ("applyAdditiveShift".equals(version)) {
                imageProcessingService.applyAdditiveShift(uploadedFilePath, outputPath);
            } else if ("applyContrast".equals(version)) {
                imageProcessingService.applyContrast(uploadedFilePath, outputPath);
            } else if ("applyGaussianNoise".equals(version)) {
                imageProcessingService.applyGaussianNoise(uploadedFilePath, outputPath);
            } else if ("applyInversion".equals(version)) {
                imageProcessingService.applyInversion(uploadedFilePath, outputPath);
            } else if ("applyMultiplicativeScaling".equals(version)) {
                imageProcessingService.applyMultiplicativeScaling(uploadedFilePath, outputPath);
            } else if ("addSaltPepperNoise".equals(version)) {
                imageProcessingService.addSaltPepperNoise(uploadedFilePath, outputPath);
            } else if ("meanFilter".equals(version)) {
                imageProcessingService.meanFilter(uploadedFilePath, outputPath);
            } else if ("gaussianFilter".equals(version)) {
                imageProcessingService.gaussianFilter(uploadedFilePath, outputPath);
            } else if ("pyramidalFilterV2".equals(version)) {
                imageProcessingService.pyramidalFilterV2(uploadedFilePath, outputPath);
            } else if ("conicalFilter".equals(version)) {
                imageProcessingService.conicalFilter(uploadedFilePath, outputPath);
            } else if ("medianFilter".equals(version)) {
                imageProcessingService.medianFilter(uploadedFilePath, outputPath);
            } else if ("gradientEdgeDetection".equals(version)) {
                imageProcessingService.gradientEdgeDetection(uploadedFilePath, outputPath);
            } else if ("sobelEdgeDetection".equals(version)) {
                imageProcessingService.sobelEdgeDetection(uploadedFilePath, outputPath);
            } else if ("prewittEdgeDetection".equals(version)) {
                imageProcessingService.prewittEdgeDetection(uploadedFilePath, outputPath);
            } else if ("robertsEdgeDetection".equals(version)) {
                imageProcessingService.robertsEdgeDetection(uploadedFilePath, outputPath);
            } else if ("laplacianEdgeDetection".equals(version)) {
                imageProcessingService.laplacianEdgeDetection(uploadedFilePath, outputPath);
            }

            // Return output image path
            return "/outputs/" + outputFileName;
        } catch (IOException e) {
            return "Error processing file: " + e.getMessage();
        }
    }

    @PostMapping("/remove-background-v1")
    public String removeBackgroundV1(@RequestParam("file") MultipartFile file) {
        return imageControllerHelper(file, "removeBackgroundV1");
    }

    @PostMapping("/remove-background-v2")
    public String removeBackgroundV2(@RequestParam("file") MultipartFile file) {
        return imageControllerHelper(file, "removeBackgroundV2");
    }

    @PostMapping("/image-enhancer-v1")
    public String imageEnhancerV1(@RequestParam("file") MultipartFile file) {
        return imageControllerHelper(file, "imageEnhancerV1");
    }

    @PostMapping("/invert-image-v1")
    public String invertImageV1(@RequestParam("file") MultipartFile file) {
        return imageControllerHelper(file, "invertImageV1");
    }

    @PostMapping("/pyramidal-filter")
    public String pyramidalFilter(@RequestParam("file") MultipartFile file) {
        return imageControllerHelper(file, "pyramidalFilter");
    }

    @PostMapping("/laplacian-filter")
    public String laplacianFilter(@RequestParam("file") MultipartFile file) {
        return imageControllerHelper(file, "laplacianFilter");
    }

    @PostMapping("/makeup-filter")
    public String makeupFilter(@RequestParam("file") MultipartFile file) {
        return imageControllerHelper(file, "makeupFilter");
    }

    @PostMapping("/scale-image-size")
    public String scaleImageSize(@RequestParam("file") MultipartFile file) {
        return imageControllerHelper(file, "scaleImageSize");
    }

    @PostMapping("/apply-additive-shift")
    public String applyAdditiveShift(@RequestParam("file") MultipartFile file) {
        return imageControllerHelper(file, "applyAdditiveShift");
    }

    @PostMapping("/apply-contrast")
    public String applyContrast(@RequestParam("file") MultipartFile file) {
        return imageControllerHelper(file, "applyContrast");
    }

    @PostMapping("/apply-gaussian-noise")
    public String applyGaussianNoise(@RequestParam("file") MultipartFile file) {
        return imageControllerHelper(file, "applyGaussianNoise");
    }

    @PostMapping("/apply-inversion")
    public String applyInversion(@RequestParam("file") MultipartFile file) {
        return imageControllerHelper(file, "applyInversion");
    }

    @PostMapping("/apply-multiplicative-scaling")
    public String applyMultiplicativeScaling(@RequestParam("file") MultipartFile file) {
        return imageControllerHelper(file, "applyMultiplicativeScaling");
    }

    @PostMapping("/add-salt-pepper-noise")
    public String addSaltPepperNoise(@RequestParam("file") MultipartFile file) {
        return imageControllerHelper(file, "addSaltPepperNoise");
    }

    @PostMapping("/mean-filter")
    public String meanFilter(@RequestParam("file") MultipartFile file) {
        return imageControllerHelper(file, "meanFilter");
    }

    @PostMapping("/gaussian-filter")
    public String gaussianFilter(@RequestParam("file") MultipartFile file) {
        return imageControllerHelper(file, "gaussianFilter");
    }

    @PostMapping("/pyramidal-filter-v2")
    public String pyramidalFilterV2(@RequestParam("file") MultipartFile file) {
        return imageControllerHelper(file, "pyramidalFilterV2");
    }

    @PostMapping("/conical-filter")
    public String conicalFilter(@RequestParam("file") MultipartFile file) {
        return imageControllerHelper(file, "conicalFilter");
    }

    @PostMapping("/median-filter")
    public String medianFilter(@RequestParam("file") MultipartFile file) {
        return imageControllerHelper(file, "medianFilter");
    }

    @PostMapping("/gradient-edge-detection")
    public String gradientEdgeDetection(@RequestParam("file") MultipartFile file) {
        return imageControllerHelper(file, "gradientEdgeDetection");
    }

    @PostMapping("/sobel-edge-detection")
    public String sobelEdgeDetection(@RequestParam("file") MultipartFile file) {
        return imageControllerHelper(file, "sobelEdgeDetection");
    }

    @PostMapping("/prewitt-edge-detection")
    public String prewittEdgeDetection(@RequestParam("file") MultipartFile file) {
        return imageControllerHelper(file, "prewittEdgeDetection");
    }

    @PostMapping("/roberts-edge-detection")
    public String robertsEdgeDetection(@RequestParam("file") MultipartFile file) {
        return imageControllerHelper(file, "robertsEdgeDetection");
    }

    @PostMapping("/laplacian-edge-detection")
    public String laplacianEdgeDetection(@RequestParam("file") MultipartFile file) {
        return imageControllerHelper(file, "laplacianEdgeDetection");
    }
}