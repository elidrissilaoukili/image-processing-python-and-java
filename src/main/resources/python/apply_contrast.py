import cv2
import numpy as np

def apply_contrast(image_path, output_path):
    image = cv2.imread(image_path)
    
    if image is None:
        print("Error: Could not load image.")
        return
    
    image = np.float64(image)
    A = np.min(image)
    B = np.max(image)
    
    P = 255 / (B - A)
    L = -P * A
    
    v = np.clip(image * P + L, 0, 255)
    v = np.uint8(v)
    
    cv2.imwrite(output_path, v)


if __name__ == "__main__":
    import sys
    if len(sys.argv) != 3:
        print("Usage: python script.py <input_image> <output_image>")
    else:
        image_path = sys.argv[1]
        output_path = sys.argv[2]
        apply_contrast(image_path, output_path)