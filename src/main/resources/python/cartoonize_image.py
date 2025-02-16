import sys
import cv2
import numpy as np
import os

def cartoonize_image(input_path, output_path):
    # Read the image in color mode (default)
    image = cv2.imread(input_path)
    if image is None:
        print("Error: Unable to load image.")
        sys.exit(1)

    # Convert to grayscale
    gray = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)

    # Apply median blur
    gray = cv2.medianBlur(gray, 5)

    # Detect edges using adaptive thresholding
    edges = cv2.adaptiveThreshold(gray, 255, cv2.ADAPTIVE_THRESH_MEAN_C, 
                                  cv2.THRESH_BINARY, 9, 9)

    # Apply bilateral filter to smooth colors while keeping edges sharp
    color = cv2.bilateralFilter(image, 9, 250, 250)

    # Convert edges to 3 channels
    edges = cv2.cvtColor(edges, cv2.COLOR_GRAY2BGR)

    # Combine edges with the color image
    cartoon = cv2.bitwise_and(color, edges)

    # Save the output image
    cv2.imwrite(output_path, cartoon)



if __name__ == "__main__":
    if len(sys.argv) != 3:
        print("Usage: python cartoonize_image.py <input_path> <output_path>")
        sys.exit(1)

    input_path = sys.argv[1]
    output_path = sys.argv[2]

    cartoonize_image(input_path, output_path)
