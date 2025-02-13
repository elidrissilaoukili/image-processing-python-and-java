import cv2
import numpy as np
import sys

def gaussian_filter(input_path, output_path, size=3):
    image = cv2.imread(input_path, cv2.IMREAD_GRAYSCALE)
    if image is None:
        print("Error: Unable to load image.")
        sys.exit(1)

    if size == 3:
        kernel = (1/16) * np.array([[1, 2, 1], [2, 4, 2], [1, 2, 1]])
    elif size == 5:
        kernel = (1/256) * np.array([[1, 4, 6, 4, 1], [4, 16, 24, 16, 4], 
                                     [6, 24, 36, 24, 6], [4, 16, 24, 16, 4], [1, 4, 6, 4, 1]])
    else:
        print("Error: Unsupported Gaussian filter size.")
        sys.exit(1)

    filtered_image = cv2.filter2D(image, -1, kernel)
    cv2.imwrite(output_path, filtered_image)


if __name__ == "__main__":
    if len(sys.argv) != 3:
        print("Usage: python add_noise.py <input_path> <output_path>")
        sys.exit(1)

    input_path = sys.argv[1]
    output_path = sys.argv[2]
    gaussian_filter(input_path, output_path)