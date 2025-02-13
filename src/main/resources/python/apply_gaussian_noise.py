import cv2
import numpy as np

def apply_gaussian_noise(image_path, output_path):
    # Load image in color
    image = cv2.imread(image_path)
    
    if image is None:
        print("Error: Could not load image.")
        return
    
    # Add Gaussian noise
    row, col, ch = image.shape
    mean = 0
    var = 0.01
    sigma = var**0.5
    gauss = np.random.normal(mean, sigma, (row, col, ch))
    noisy_image = np.add(image, gauss * 255)
    
    # Clip values to valid range
    noisy_image = np.clip(noisy_image, 0, 255)
    
    # Convert to uint8
    noisy_image = np.uint8(noisy_image)
    
    # Save the output image
    cv2.imwrite(output_path, noisy_image)


if __name__ == "__main__":
    import sys
    if len(sys.argv) != 3:
        print("Usage: python script.py <input_image> <output_image>")
    else:
        image_path = sys.argv[1]
        output_path = sys.argv[2]
        apply_gaussian_noise(image_path, output_path)
