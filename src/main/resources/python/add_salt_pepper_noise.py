import sys
import cv2
import numpy as np

def add_salt_pepper_noise(input_path, output_path, amount=0.02):
    image = cv2.imread(input_path, cv2.IMREAD_GRAYSCALE)
    if image is None:
        print("Error: Unable to load image.")
        sys.exit(1)
    
    noisy_image = image.copy()
    total_pixels = image.size
    num_salt = int(amount * total_pixels / 2)
    num_pepper = int(amount * total_pixels / 2)
    
    # Salt noise (white pixels)
    coords = [np.random.randint(0, i - 1, num_salt) for i in image.shape[:2]]
    noisy_image[coords[0], coords[1]] = 255
    
    # Pepper noise (black pixels)
    coords = [np.random.randint(0, i - 1, num_pepper) for i in image.shape[:2]]
    noisy_image[coords[0], coords[1]] = 0
    
    cv2.imwrite(output_path, noisy_image)


# input_path = "s.png"
# output_path = "output.png"
if __name__ == "__main__":
    if len(sys.argv) != 3:
        print("Usage: python add_noise.py <input_path> <output_path>")
        sys.exit(1)

    input_path = sys.argv[1]
    output_path = sys.argv[2]
    add_salt_pepper_noise(input_path, output_path)
