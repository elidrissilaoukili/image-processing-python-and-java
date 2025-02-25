import sys
import cv2
import numpy as np

def apply_dilation(input_path, output_path):
    image = cv2.imread(input_path, cv2.IMREAD_GRAYSCALE)
    if image is None:
        print("Error: Unable to load image.")
        sys.exit(1)

    kernel = cv2.getStructuringElement(cv2.MORPH_ELLIPSE, (4, 4))
    dilated_image = cv2.dilate(image, kernel)

    cv2.imwrite(output_path, dilated_image)

if __name__ == "__main__":
    if len(sys.argv) != 3:
        print("Usage: python dilation.py <input_path> <output_path>")
        sys.exit(1)

    input_path = sys.argv[1]
    output_path = sys.argv[2]
    apply_dilation(input_path, output_path)
