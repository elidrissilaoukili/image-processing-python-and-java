import cv2
import numpy as np
import sys

def susan_corner_detection(input_path, output_path, radius=2, alpha=50):
    # Load image
    image = cv2.imread(input_path, cv2.IMREAD_GRAYSCALE)
    if image is None:
        print("Error: Unable to load image.")
        sys.exit(1)
    
    image = image.astype(np.float64)
    n, m = image.shape
    alpha = alpha / 100.0
    
    # Create SUSAN mask
    mask = np.zeros((2 * radius + 1, 2 * radius + 1))
    b = np.ones((radius + 1, radius + 1))
    
    for i in range(radius + 1):
        for j in range(radius + 1):
            if radius == 1 and j > i:
                b[i, j] = 0
            elif radius > 1 and j > i + 1:
                b[i, j] = 0
    
    mask[:radius + 1, radius:] = b[:, :radius+1]
    mask[:radius + 1, :radius + 1] = np.rot90(b)
    mask = mask + np.flipud(mask)
    mask[radius, :] -= 1
    max_response = np.sum(mask)
    
    f = np.zeros((n, m))
    for i in range(radius, n - radius):
        for j in range(radius, m - radius):
            region = image[i - radius:i + radius + 1, j - radius:j + radius + 1]
            region_masked = region * mask
            intensity_center = region_masked[radius, radius]
            s = np.exp(-((region_masked - intensity_center) / max_response) ** 6)
            sum_s = np.sum(s)
            
            if intensity_center == 0:
                sum_s -= np.count_nonzero(mask == 0)
            
            f[i, j] = sum_s
    
    f_crop = f[radius:n - radius, radius:m - radius]
    min_f = np.min(f_crop)
    max_f = np.max(f)
    
    d = 2 * radius + 1
    temp1 = n // d
    temp2 = m // d
    fff = f.copy()
    fff[n:temp1 * d + d, m:temp2 * d + d] = 0
    
    for i in range(radius, temp1 * d + d, d):
        for j in range(radius, temp2 * d + d, d):
            window = fff[i - radius:i + radius + 1, j - radius:j + radius + 1]
            min_window = np.min(window[window > 0]) if np.any(window > 0) else 0
            
            y, x = np.where((window != min_window) & (window <= min_f + alpha * (max_f - min_f)) & (window > 0))
            u, v = np.where(window == min_window)
            
            for l in range(1, len(u)):
                fff[i - radius + u[l], j - radius + v[l]] = 0
            
            for l in range(len(x)):
                fff[i - radius + y[l], j - radius + x[l]] = 0
    
    threshold = min_f + alpha * (max_f - min_f)
    keypoints = np.column_stack(np.where((min_f <= fff) & (fff <= threshold)))
    
    # Draw detected points
    color_image = cv2.imread(input_path)
    for y, x in keypoints:
        cv2.circle(color_image, (x, y), 2, (0, 0, 255), -1)
    
    cv2.imwrite(output_path, color_image)
    print("Number of detected keypoints:", len(keypoints))

if __name__ == "__main__":
    if len(sys.argv) != 3:
        print("Usage: python susan_corners.py <input_path> <output_path>")
        sys.exit(1)
    
    input_path = sys.argv[1]
    output_path = sys.argv[2]
    susan_corner_detection(input_path, output_path)
