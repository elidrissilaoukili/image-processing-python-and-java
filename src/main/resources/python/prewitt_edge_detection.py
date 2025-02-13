import sys
import cv2
import numpy as np


def prewitt_edge_detection(input_path, output_path):
    # Read the image
    image = cv2.imread(input_path)
    if image is None:
        print("Error: Unable to read the image.")
        return

    # Convert to grayscale
    gray = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)

    # Prewitt filters
    prewitt_x = np.array([[-1, 0, 1], [-1, 0, 1], [-1, 0, 1]])
    prewitt_y = np.array([[-1, -1, -1], [0, 0, 0], [1, 1, 1]])

    # Apply Prewitt edge detection
    prewitt_x_filtered = cv2.filter2D(gray, -1, prewitt_x)
    prewitt_y_filtered = cv2.filter2D(gray, -1, prewitt_y)
    prewitt = np.sqrt(prewitt_x_filtered**2 + prewitt_y_filtered**2)

    # Save the result
    cv2.imwrite(output_path, np.uint8(prewitt))


if __name__ == "__main__":
    if len(sys.argv) != 4:
        print("Usage: python edge_detection.py <input_path> <output_path> <method>")
        sys.exit(1)

    input_path = sys.argv[1]
    output_path = sys.argv[2]

    prewitt_edge_detection(input_path, output_path)

