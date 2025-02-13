import cv2
import sys

def median_filter(input_path, output_path, size=3):
    image = cv2.imread(input_path, cv2.IMREAD_GRAYSCALE)
    if image is None:
        print("Error: Unable to load image.")
        sys.exit(1)

    filtered_image = cv2.medianBlur(image, size)
    cv2.imwrite(output_path, filtered_image)


if __name__ == "__main__":
    if len(sys.argv) != 3:
        print("Usage: python add_noise.py <input_path> <output_path>")
        sys.exit(1)

    input_path = sys.argv[1]
    output_path = sys.argv[2]
    median_filter(input_path, output_path)