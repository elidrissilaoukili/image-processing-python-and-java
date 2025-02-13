import sys
from PIL import Image

def remove_background(image_path, output_path):
    # Example background removal logic (this is just a placeholder)
    img = Image.open(image_path)
    # Perform background removal (this is a dummy operation)
    img = img.convert("RGBA")
    datas = img.getdata()
    new_data = []
    for item in datas:
        # Change all white (also shades of whites) pixels to transparent
        if item[0] > 200 and item[1] > 200 and item[2] > 200:
            new_data.append((255, 255, 255, 0))
        else:
            new_data.append(item)
    img.putdata(new_data)
    img.save(output_path, "PNG")


if __name__ == "__main__":
    image_path = sys.argv[1]
    output_path = sys.argv[2]
    remove_background(image_path, output_path)