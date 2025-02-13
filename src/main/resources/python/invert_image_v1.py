import sys
import cv2
import numpy as np

def invert_image(input_path, output_path):
    # Read the image
    image = cv2.imread(input_path, cv2.IMREAD_GRAYSCALE)  # Ensure grayscale
    if image is None:
        print("Error: Unable to read the image.")
        return

    # Invert the image
    inverted_image = 255 - image

    # Save the inverted image
    cv2.imwrite(output_path, inverted_image)
    print(f"Inverted image saved as {output_path}")


if __name__ == "__main__":
    if len(sys.argv) != 3:
        print("Usage: python removebg.py <input_path> <output_path>")
        sys.exit(1)

    input_path = sys.argv[1]
    output_path = sys.argv[2]
    invert_image(input_path, output_path)

