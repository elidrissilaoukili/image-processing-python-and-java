import sys
import cv2
import numpy as np

def laplacian_edge_detection(input_path, output_path):
    # Read the image
    image = cv2.imread(input_path)
    if image is None:
        print("Error: Unable to read the image.")
        return

    # Convert to grayscale
    gray = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)

    # Apply Laplacian edge detection
    laplacian = cv2.Laplacian(gray, cv2.CV_64F)
    laplacian = np.uint8(np.absolute(laplacian))

    cv2.imwrite(output_path, laplacian)

if __name__ == "__main__":
    if len(sys.argv) != 4:
        print("Usage: python edge_detection.py <input_path> <output_path> <method>")
        sys.exit(1)

    input_path = sys.argv[1]
    output_path = sys.argv[2]

    laplacian_edge_detection(input_path, output_path)

