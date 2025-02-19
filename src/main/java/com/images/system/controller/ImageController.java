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
            } else if ("lowPassFilter".equals(version)) {
                imageProcessingService.lowPassFilter(uploadedFilePath, outputPath);
            } else if ("butterworthLowPassFilter".equals(version)) {
                imageProcessingService.butterworthLowPassFilter(uploadedFilePath, outputPath);
            } else if ("highPassFilter".equals(version)) {
                imageProcessingService.highPassFilter(uploadedFilePath, outputPath);
            } else if ("butterworthHighPassFilter".equals(version)) {
                imageProcessingService.butterworthHighPassFilter(uploadedFilePath, outputPath);
            } else if ("cartoonizeImage".equals(version)) {
                imageProcessingService.cartoonizeImage(uploadedFilePath, outputPath);
            } else if ("detectHarrisCorners".equals(version)) {
                imageProcessingService.detectHarrisCorners(uploadedFilePath, outputPath);
            } else if ("susanCornerDetection".equals(version)) {
                imageProcessingService.susanCornerDetection(uploadedFilePath, outputPath);
            }

            
            // Return output image path
            return "/outputs/" + outputFileName;

        } catch (IOException e) {
            return "Error processing file: " + e.getMessage();
        }
    }

    @PostMapping("/remove_background_v1")
    public String removeBackgroundV1(@RequestParam("file") MultipartFile file) {
        return imageControllerHelper(file, "removeBackgroundV1");
    }

    @PostMapping("/remove_background_v2")
    public String removeBackgroundV2(@RequestParam("file") MultipartFile file) {
        return imageControllerHelper(file, "removeBackgroundV2");
    }

    @PostMapping("/image_enhancer_v1")
    public String imageEnhancerV1(@RequestParam("file") MultipartFile file) {
        return imageControllerHelper(file, "imageEnhancerV1");
    }

    @PostMapping("/invert_image_v1")
    public String invertImageV1(@RequestParam("file") MultipartFile file) {
        return imageControllerHelper(file, "invertImageV1");
    }

    @PostMapping("/pyramidal_filter")
    public String pyramidalFilter(@RequestParam("file") MultipartFile file) {
        return imageControllerHelper(file, "pyramidalFilter");
    }

    @PostMapping("/laplacian_filter")
    public String laplacianFilter(@RequestParam("file") MultipartFile file) {
        return imageControllerHelper(file, "laplacianFilter");
    }

    @PostMapping("/makeup_filter")
    public String makeupFilter(@RequestParam("file") MultipartFile file) {
        return imageControllerHelper(file, "makeupFilter");
    }

    @PostMapping("/scale_image_size")
    public String scaleImageSize(@RequestParam("file") MultipartFile file) {
        return imageControllerHelper(file, "scaleImageSize");
    }

    @PostMapping("/apply_additive_shift")
    public String applyAdditiveShift(@RequestParam("file") MultipartFile file) {
        return imageControllerHelper(file, "applyAdditiveShift");
    }

    @PostMapping("/apply_contrast")
    public String applyContrast(@RequestParam("file") MultipartFile file) {
        return imageControllerHelper(file, "applyContrast");
    }

    @PostMapping("/apply_gaussian_noise")
    public String applyGaussianNoise(@RequestParam("file") MultipartFile file) {
        return imageControllerHelper(file, "applyGaussianNoise");
    }

    @PostMapping("/apply_inversion")
    public String applyInversion(@RequestParam("file") MultipartFile file) {
        return imageControllerHelper(file, "applyInversion");
    }

    @PostMapping("/apply_multiplicative_scaling")
    public String applyMultiplicativeScaling(@RequestParam("file") MultipartFile file) {
        return imageControllerHelper(file, "applyMultiplicativeScaling");
    }

    @PostMapping("/add_salt_pepper_noise")
    public String addSaltPepperNoise(@RequestParam("file") MultipartFile file) {
        return imageControllerHelper(file, "addSaltPepperNoise");
    }

    @PostMapping("/mean_filter")
    public String meanFilter(@RequestParam("file") MultipartFile file) {
        return imageControllerHelper(file, "meanFilter");
    }

    @PostMapping("/gaussian_filter")
    public String gaussianFilter(@RequestParam("file") MultipartFile file) {
        return imageControllerHelper(file, "gaussianFilter");
    }

    @PostMapping("/pyramidal_filter_v2")
    public String pyramidalFilterV2(@RequestParam("file") MultipartFile file) {
        return imageControllerHelper(file, "pyramidalFilterV2");
    }

    @PostMapping("/conical_filter")
    public String conicalFilter(@RequestParam("file") MultipartFile file) {
        return imageControllerHelper(file, "conicalFilter");
    }

    @PostMapping("/median_filter")
    public String medianFilter(@RequestParam("file") MultipartFile file) {
        return imageControllerHelper(file, "medianFilter");
    }

    @PostMapping("/gradient_edge_detection")
    public String gradientEdgeDetection(@RequestParam("file") MultipartFile file) {
        return imageControllerHelper(file, "gradientEdgeDetection");
    }

    @PostMapping("/sobel_edge_detection")
    public String sobelEdgeDetection(@RequestParam("file") MultipartFile file) {
        return imageControllerHelper(file, "sobelEdgeDetection");
    }

    @PostMapping("/prewitt_edge_detection")
    public String prewittEdgeDetection(@RequestParam("file") MultipartFile file) {
        return imageControllerHelper(file, "prewittEdgeDetection");
    }

    @PostMapping("/roberts_edge_detection")
    public String robertsEdgeDetection(@RequestParam("file") MultipartFile file) {
        return imageControllerHelper(file, "robertsEdgeDetection");
    }

    @PostMapping("/laplacian_edge_detection")
    public String laplacianEdgeDetection(@RequestParam("file") MultipartFile file) {
        return imageControllerHelper(file, "laplacianEdgeDetection");
    }

    @PostMapping("/low_pass_filter")
    public String lowPassFilter(@RequestParam("file") MultipartFile file) {
        return imageControllerHelper(file, "lowPassFilter");
    }

    @PostMapping("/butterworth_low_pass_filter")
    public String butterworthLowPassFilter(@RequestParam("file") MultipartFile file) {
        return imageControllerHelper(file, "butterworthLowPassFilter");
    }

    @PostMapping("/high_pass_filter")
    public String highPassFilter(@RequestParam("file") MultipartFile file) {
        return imageControllerHelper(file, "highPassFilter");
    }

    @PostMapping("/butterworth_high_pass_filter")
    public String butterworthHighPassFilter(@RequestParam("file") MultipartFile file) {
        return imageControllerHelper(file, "butterworthHighPassFilter");
    }

    @PostMapping("/cartoonize_image")
    public String cartoonizeImage(@RequestParam("file") MultipartFile file) {
        return imageControllerHelper(file, "cartoonizeImage");
    }

    @PostMapping("/detect_harris_corners")
    public String detectHarrisCorners(@RequestParam("file") MultipartFile file) {
        return imageControllerHelper(file, "detectHarrisCorners");
    }

    @PostMapping("/susan_corner_detection")
    public String susanCornerDetection(@RequestParam("file") MultipartFile file) {
        return imageControllerHelper(file, "susanCornerDetection");
    }

    
}