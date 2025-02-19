function handleSelectChange(event) {
    const selectedOption = event.target.value;
    if (selectedOption) {
        processImage(selectedOption);
    }
}

function processImage(endpoint) {
    let formData = new FormData();
    let fileInput = document.getElementById("fileInput").files[0];
    let progressBar = document.getElementById("progressBar");
    let progressContainer = document.getElementById("progressContainer");

    if (!fileInput) {
        alert("Please select a file first.");
        return;
    }

    formData.append("file", fileInput);
    progressContainer.classList.remove("hidden");
    progressBar.style.width = "0%";

    let progressInterval = setInterval(() => {
        let width = parseInt(progressBar.style.width);
        if (width < 90) {
            progressBar.style.width = (width + 10) + "%";
        }
    }, 500);

    fetch(endpoint, {
        method: "POST",
        body: formData
    })
        .then(response => response.text())
        .then(imagePath => {
            clearInterval(progressInterval);
            progressBar.style.width = "100%";
            setTimeout(() => {
                progressContainer.classList.add("hidden");
            }, 500);

            if (imagePath.startsWith("/outputs/")) { // Ensure correct response
                let fullPath = window.location.origin + imagePath;
                document.getElementById("outputImage").src = fullPath;
                document.getElementById("downloadLink").href = fullPath;
                document.getElementById("result").classList.remove("hidden");
            } else {
                console.log("Error processing image: " + imagePath);
            }
        })
        .catch(error => {
            clearInterval(progressInterval);
            progressContainer.classList.add("hidden");
            console.error("Error:", error);
        });
}