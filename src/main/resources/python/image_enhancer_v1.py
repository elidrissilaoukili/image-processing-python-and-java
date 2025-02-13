import sys
import cv2
import numpy as np

def enhance_image(input_path, output_path):
    # Read the image
    image = cv2.imread(input_path)
    if image is None:
        print("Error: Unable to read the image.")
        return

    # Convert to LAB color space
    lab = cv2.cvtColor(image, cv2.COLOR_BGR2LAB)

    # Apply CLAHE to the L channel (contrast enhancement)
    clahe = cv2.createCLAHE(clipLimit=3.0, tileGridSize=(8, 8))
    lab[:, :, 0] = clahe.apply(lab[:, :, 0])

    # Convert back to BGR color space
    enhanced = cv2.cvtColor(lab, cv2.COLOR_LAB2BGR)

    # Sharpening kernel
    kernel = np.array([[0, -1, 0], 
                       [-1, 5, -1], 
                       [0, -1, 0]])

    # Apply sharpening filter
    sharpened = cv2.filter2D(enhanced, -1, kernel)

    # Save the enhanced image
    cv2.imwrite(output_path, sharpened)
    print(f"Enhanced image saved as {output_path}")


if __name__ == "__main__":
    if len(sys.argv) != 3:
        print("Usage: python removebg.py <input_path> <output_path>")
        sys.exit(1)

    input_path = sys.argv[1]
    output_path = sys.argv[2]
    enhance_image(input_path, output_path)

