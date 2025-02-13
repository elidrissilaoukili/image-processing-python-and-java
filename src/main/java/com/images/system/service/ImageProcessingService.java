package com.images.system.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.core.io.ClassPathResource;

import java.io.File;

@Service
public class ImageProcessingService {

    private String python_exe = "C:\\Users\\elidrissi\\AppData\\Local\\Programs\\Python\\Python311\\python.exe";

    public void executePythonScript(String scriptName, String imagePath, String outputPath) {
        try {
            ClassPathResource resource = new ClassPathResource(scriptName);
            File pythonScript = resource.getFile();

            ProcessBuilder processBuilder = new ProcessBuilder(python_exe, pythonScript.getAbsolutePath(), imagePath,
                    outputPath);
            processBuilder.redirectErrorStream(true);

            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            int exitCode = process.waitFor();
            System.out.println("Exited with code: " + exitCode);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void removeBackgroundV1(String imagePath, String outputPath) {
        executePythonScript("python/removebg_v1.py", imagePath, outputPath);
    }

    public void removeBackgroundV2(String imagePath, String outputPath) {
        executePythonScript("python/removebg_v2.py", imagePath, outputPath);
    }

    public void imageEnhancerV1(String imagePath, String outputPath) {
        executePythonScript("python/image_enhancer_v1.py", imagePath, outputPath);
    }

    public void invertImageV1(String imagePath, String outputPath) {
        executePythonScript("python/invert_image_v1.py", imagePath, outputPath);
    }

    public void pyramidalFilter(String imagePath, String outputPath) {
        executePythonScript("python/pyramidal_filter.py", imagePath, outputPath);
    }

    public void laplacianFilter(String imagePath, String outputPath) {
        executePythonScript("python/laplacian_filter.py", imagePath, outputPath);
    }

    public void makeupFilter(String imagePath, String outputPath) {
        executePythonScript("python/makeup_filter.py", imagePath, outputPath);
    }

    public void scaleImageSize(String imagePath, String outputPath) {
        executePythonScript("python/scale_image_size.py", imagePath, outputPath);
    }

    public void applyAdditiveShift(String imagePath, String outputPath) {
        executePythonScript("python/apply_additive_shift.py", imagePath, outputPath);
    }

    public void applyContrast(String imagePath, String outputPath) {
        executePythonScript("python/apply_contrast.py", imagePath, outputPath);
    }

    public void applyGaussianNoise(String imagePath, String outputPath) {
        executePythonScript("python/apply_gaussian_noise.py", imagePath, outputPath);
    }

    public void applyInversion(String imagePath, String outputPath) {
        executePythonScript("python/apply_inversion.py", imagePath, outputPath);
    }

    public void applyMultiplicativeScaling(String imagePath, String outputPath) {
        executePythonScript("python/apply_multiplicative_scaling.py", imagePath, outputPath);
    }

    public void addSaltPepperNoise(String imagePath, String outputPath) {
        executePythonScript("python/add_salt_pepper_noise.py", imagePath, outputPath);
    }

    public void meanFilter(String imagePath, String outputPath) {
        executePythonScript("python/mean_filter.py", imagePath, outputPath);
    }

    public void gaussianFilter(String imagePath, String outputPath) {
        executePythonScript("python/gaussian_filter.py", imagePath, outputPath);
    }

    public void pyramidalFilterV2(String imagePath, String outputPath) {
        executePythonScript("python/pyramidal_filterV2.py", imagePath, outputPath);
    }

    public void conicalFilter(String imagePath, String outputPath) {
        executePythonScript("python/conical_filter.py", imagePath, outputPath);
    }

    public void medianFilter(String imagePath, String outputPath) {
        executePythonScript("python/median_filter.py", imagePath, outputPath);
    }

    public void gradientEdgeDetection(String imagePath, String outputPath) {
        executePythonScript("python/median_filter.py", imagePath, outputPath);
    }

    public void sobelEdgeDetection(String imagePath, String outputPath) {
        executePythonScript("python/median_filter.py", imagePath, outputPath);
    }

    public void prewittEdgeDetection(String imagePath, String outputPath) {
        executePythonScript("python/median_filter.py", imagePath, outputPath);
    }

    public void robertsEdgeDetection(String imagePath, String outputPath) {
        executePythonScript("python/median_filter.py", imagePath, outputPath);
    }

    public void laplacianEdgeDetection(String imagePath, String outputPath) {
        executePythonScript("python/median_filter.py", imagePath, outputPath);
    }
}