import cv2
import numpy as np

def pyramidal_filter(image_path, output_path):
    # Load image in grayscale
    image = cv2.imread(image_path, cv2.IMREAD_GRAYSCALE)
    
    if image is None:
        print("Error: Could not load image.")
        return
    
    # Define the filter (H)
    H = (1/81) * np.array([[1, 2, 3, 2, 1],
                            [2, 4, 6, 4, 2],
                            [3, 6, 9, 6, 3],
                            [2, 4, 6, 4, 2],
                            [1, 2, 3, 2, 1]])
    
    # Apply the filter using convolution
    filtered_image = cv2.filter2D(image, -1, H)
    
    # Save the output image
    cv2.imwrite(output_path, filtered_image)
    

if __name__ == "__main__":
    import sys
    if len(sys.argv) != 3:
        print("Usage: python script.py <input_image> <output_image>")
    else:
        image_path = sys.argv[1]
        output_path = sys.argv[2]
        pyramidal_filter(image_path, output_path)
