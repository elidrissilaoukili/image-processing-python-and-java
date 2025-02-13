import sys
import cv2
import numpy as np

def roberts_edge_detection(input_path, output_path):
    # Read the image
    image = cv2.imread(input_path)
    if image is None:
        print("Error: Unable to read the image.")
        return

    # Convert to grayscale
    gray = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)

    # Apply Roberts edge detection
    roberts = np.zeros_like(gray)
    m, n = gray.shape
    for x in range(m - 1):
        for y in range(n - 1):
            roberts[x, y] = abs(gray[x, y] - gray[x + 1, y + 1]) + abs(gray[x, y + 1] - gray[x + 1, y])

    # Threshold the result
    roberts[roberts < 25] = 0

    # Save the result
    cv2.imwrite(output_path, np.uint8(roberts))


if __name__ == "__main__":
    if len(sys.argv) != 4:
        print("Usage: python edge_detection.py <input_path> <output_path> <method>")
        sys.exit(1)

    input_path = sys.argv[1]
    output_path = sys.argv[2]

    roberts_edge_detection(input_path, output_path)

