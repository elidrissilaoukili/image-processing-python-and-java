import cv2
import numpy as np

def apply_laplacian(image_path, output_path):
    # Load image in grayscale
    image = cv2.imread(image_path, cv2.IMREAD_GRAYSCALE)
    
    if image is None:
        print("Error: Could not load image.")
        return
    
    # Define the Laplacian filter (M1)
    M1 = np.array([[-1, -1, -1],
                   [-1,  8, -1],
                   [-1, -1, -1]])
    
    # Apply the filter using convolution
    filtered_image = cv2.filter2D(image, -1, M1)
    
    # Save the output image
    cv2.imwrite(output_path, filtered_image)



if __name__ == "__main__":
    import sys
    if len(sys.argv) != 3:
        print("Usage: python script.py <input_image> <output_image>")
    else:
        image_path = sys.argv[1]
        output_path = sys.argv[2]
        apply_laplacian(image_path, output_path)
    # apply_laplacian(image_path, output_path)
