import sys
import cv2
import numpy as np

def butterworth_low_pass_filter(input_path, output_path, D0=1, n=3):
    image = cv2.imread(input_path, cv2.IMREAD_GRAYSCALE)
    if image is None:
        print("Error: Unable to load image.")
        sys.exit(1)
    
    F = np.fft.fftshift(np.fft.fft2(image))
    M, N = F.shape
    H = np.zeros((M, N))
    M2, N2 = M // 2, N // 2
    
    for i in range(M):
        for j in range(N):
            D = np.sqrt((i - M2) ** 2 + (j - N2) ** 2)
            H[i, j] = 1 / (1 + (D / D0) ** (2 * n))
    
    G = F * H
    g = np.fft.ifft2(G).real
    cv2.imwrite(output_path, np.uint8(np.clip(g, 0, 255)))


if __name__ == "__main__":
    import sys
    if len(sys.argv) != 3:
        print("Usage: python script.py <input_image> <output_image>")
    else:
        input_path = sys.argv[1]
        output_path = sys.argv[2]
    
        butterworth_low_pass_filter(input_path, output_path)
