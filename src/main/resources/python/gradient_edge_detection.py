import sys
import cv2
import numpy as np

def gradient_edge_detection(input_path, output_path):
    image = cv2.imread(input_path)
    if image is None:
        print("Error: Unable to read the image.")
        return

    gray = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)

    gradient = np.zeros_like(gray)
    gradienthor = np.zeros_like(gray)
    gradientver = np.zeros_like(gray)

    maskhor = np.array([[0, 0, 0], [-1, 0, 1], [0, 0, 0]])
    maskver = np.array([[0, -1, 0], [0, 0, 0], [0, 1, 0]])

    m, n = gray.shape
    for i in range(3, m - 3):
        for j in range(3, n - 3):
            for k in range(3):
                for l in range(3):
                    gradienthor[i, j] += gray[i - k, j - l] * maskhor[k, l]
                    gradientver[i, j] += gray[i - k, j - l] * maskver[k, l]

    for i in range(3, m - 3):
        for j in range(3, n - 3):
            gradient[i, j] = np.sqrt(gradienthor[i, j]**2 + gradientver[i, j]**2)

    cv2.imwrite(output_path, np.uint8(gradient))


if __name__ == "__main__":
    if len(sys.argv) != 4:
        print("Usage: python edge_detection.py <input_path> <output_path> <method>")
        sys.exit(1)

    input_path = sys.argv[1]
    output_path = sys.argv[2]

    gradient_edge_detection(input_path, output_path)

