import cv2
import numpy as np

def enlarge_image(image_path, output_path, scale=1.5):
    # Load image in color
    image = cv2.imread(image_path)
    
    if image is None:
        print("Error: Could not load image.")
        return
    
    # Get original dimensions
    height, width = image.shape[:2]
    
    # Resize image with given scale
    new_size = (int(width * scale), int(height * scale))
    enlarged_image = cv2.resize(image, new_size, interpolation=cv2.INTER_CUBIC)
    
    # Save the output image
    cv2.imwrite(output_path, enlarged_image)
    

# image_path = "s-cut.png"
# output_path = "output.png"
if __name__ == "__main__":
    import sys
    if len(sys.argv) < 3:
        print("Usage: python script.py <input_image> <output_image> [scale]")
    else:
        image_path = sys.argv[1]
        output_path = sys.argv[2]
        scale = float(sys.argv[3]) if len(sys.argv) > 3 else 1.5
        enlarge_image(image_path, output_path, scale)
