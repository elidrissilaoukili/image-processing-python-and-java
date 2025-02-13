import cv2
import numpy as np

def apply_makeup_filter(image_path, output_path):
    # Load image in color
    image = cv2.imread(image_path)
    
    if image is None:
        print("Error: Could not load image.")
        return
    
    # Convert image to HSV color space
    hsv = cv2.cvtColor(image, cv2.COLOR_BGR2HSV)
    
    # Enhance red and pink tones for lipstick effect
    hsv[:, :, 1] = np.clip(hsv[:, :, 1] * 1.5, 0, 255)
    
    # Convert back to BGR color space
    filtered_image = cv2.cvtColor(hsv, cv2.COLOR_HSV2BGR)
    
    # Save the output image
    cv2.imwrite(output_path, filtered_image)
    

if __name__ == "__main__":
    import sys
    if len(sys.argv) != 3:
        print("Usage: python script.py <input_image> <output_image>")
    else:
        image_path = sys.argv[1]
        output_path = sys.argv[2]
        apply_makeup_filter(image_path, output_path)
