import sys
import cv2
import numpy as np


def sobel_edge_detection(input_path, output_path):
    # Read the image
    image = cv2.imread(input_path)
    if image is None:
        print("Error: Unable to read the image.")
        return

    # Convert to grayscale
    gray = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)

    # Apply Sobel edge detection
    sobel_x = cv2.Sobel(gray, cv2.CV_64F, 1, 0, ksize=3)
    sobel_y = cv2.Sobel(gray, cv2.CV_64F, 0, 1, ksize=3)
    sobel = cv2.magnitude(sobel_x, sobel_y)

    # Save the result
    cv2.imwrite(output_path, np.uint8(sobel))



if __name__ == "__main__":
    if len(sys.argv) != 4:
        print("Usage: python edge_detection.py <input_path> <output_path> <method>")
        sys.exit(1)

    input_path = sys.argv[1]
    output_path = sys.argv[2]

    sobel_edge_detection(input_path, output_path)

