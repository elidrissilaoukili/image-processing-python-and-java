import cv2
import numpy as np
import sys

def detect_harris_corners(input_path, output_path, sigma=1, k=0.04, threshold=50, radius=6):
    # Load image
    image = cv2.imread(input_path)
    if image is None:
        print("Error: Unable to load image.")
        sys.exit(1)
    
    # Convert to grayscale
    gray = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)
    gray = np.float32(gray)
    
    # Compute gradients using Sobel
    Ix = cv2.Sobel(gray, cv2.CV_64F, 1, 0, ksize=3)
    Iy = cv2.Sobel(gray, cv2.CV_64F, 0, 1, ksize=3)
    
    # Compute second-order gradients
    Ix2 = cv2.GaussianBlur(Ix ** 2, (0, 0), sigma)
    Iy2 = cv2.GaussianBlur(Iy ** 2, (0, 0), sigma)
    Ixy = cv2.GaussianBlur(Ix * Iy, (0, 0), sigma)
    
    # Compute Harris response
    detM = Ix2 * Iy2 - Ixy ** 2
    traceM = Ix2 + Iy2
    R = detM - k * (traceM ** 2)
    
    # Normalize and threshold
    R_norm = cv2.normalize(R, None, 0, 255, cv2.NORM_MINMAX)
    R_norm = np.uint8(R_norm)
    
    keypoints = np.argwhere(R_norm > threshold)
    
    # Apply non-maximum suppression
    for x, y in keypoints:
        if R_norm[x, y] == np.max(R_norm[max(0, x - radius):x + radius + 1, max(0, y - radius):y + radius + 1]):
            cv2.circle(image, (y, x), 3, (0, 0, 255), -1)
    
    # Save output
    cv2.imwrite(output_path, image)

if __name__ == "__main__":
    if len(sys.argv) != 3:
        print("Usage: python harris_corners.py <input_path> <output_path>")
        sys.exit(1)
    
    input_path = sys.argv[1]
    output_path = sys.argv[2]
    detect_harris_corners(input_path, output_path)
