import cv2
import numpy as np

def apply_additive_shift(image_path, output_path):
    image = cv2.imread(image_path)
    
    if image is None:
        print("Error: Could not load image.")
        return
    
    L = 100
    image = np.float64(image)
    
    v = np.clip(image + L, 0, 255)
    v = np.uint8(v)
    
    cv2.imwrite(output_path, v)


if __name__ == "__main__":
    import sys
    if len(sys.argv) != 3:
        print("Usage: python script.py <input_image> <output_image>")
    else:
        image_path = sys.argv[1]
        output_path = sys.argv[2]
        apply_additive_shift(image_path, output_path)
