import cv2
import numpy as np
import sys

def mean_filter(input_path, output_path, size=3):
    image = cv2.imread(input_path, cv2.IMREAD_GRAYSCALE)
    if image is None:
        print("Error: Unable to load image.")
        sys.exit(1)

    kernel = np.ones((size, size), np.float32) / (size * size)
    filtered_image = cv2.filter2D(image, -1, kernel)
    cv2.imwrite(output_path, filtered_image)



if __name__ == "__main__":
    if len(sys.argv) != 3:
        print("Usage: python add_noise.py <input_path> <output_path>")
        sys.exit(1)

    input_path = sys.argv[1]
    output_path = sys.argv[2]
    mean_filter(input_path, output_path)