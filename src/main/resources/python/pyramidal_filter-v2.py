import cv2
import numpy as np
import sys

def pyramidal_filter(input_path, output_path):
    image = cv2.imread(input_path, cv2.IMREAD_GRAYSCALE)
    if image is None:
        print("Error: Unable to load image.")
        sys.exit(1)

    kernel = (1/81) * np.array([[1, 2, 3, 2, 1], [2, 4, 6, 4, 2], [3, 6, 9, 6, 3], 
                                [2, 4, 6, 4, 2], [1, 2, 3, 2, 1]])
    filtered_image = cv2.filter2D(image, -1, kernel)
    cv2.imwrite(output_path, filtered_image)


if __name__ == "__main__":
    if len(sys.argv) != 3:
        print("Usage: python add_noise.py <input_path> <output_path>")
        sys.exit(1)

    input_path = sys.argv[1]
    output_path = sys.argv[2]
    pyramidal_filter(input_path, output_path)