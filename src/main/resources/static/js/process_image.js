// Function to handle group selection change
function handleGroupSelectChange(event) {
    const groupSelect = document.getElementById("groupSelect");
    const childSelect = document.getElementById("childSelect");

    // Clear previous children
    childSelect.innerHTML = "";

    // Get the selected group
    const selectedGroup = groupSelect.value;

    if (selectedGroup) {
        // Show the child select
        childSelect.style.display = "block";

        // Populate the child select with options
        groupChildrenMap[selectedGroup].forEach(child => {
            const option = document.createElement("option");
            option.value = child.value;
            option.text = child.text;
            childSelect.appendChild(option);
        });
    } else {
        // Hide the child select if no group is selected
        childSelect.style.display = "none";
    }
}

// Function to handle child selection change
function handleChildSelectChange(event) {
    const childSelect = document.getElementById("childSelect");
    const selectedOptions = Array.from(childSelect.selectedOptions).map(option => option.value);

    // Process each selected option
    selectedOptions.forEach(option => {
        if (option) {
            processImage(option);
        }
    });
}

// Attach event listeners
document.getElementById("groupSelect").addEventListener("change", handleGroupSelectChange);
document.getElementById("childSelect").addEventListener("change", handleChildSelectChange);

// Existing processImage function
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














// function handleSelectChange(event) {
//     const selectedOption = event.target.value;
//     if (selectedOption) {
//         processImage(selectedOption);
//     }
// }

// function processImage(endpoint) {
//     let formData = new FormData();
//     let fileInput = document.getElementById("fileInput").files[0];
//     let progressBar = document.getElementById("progressBar");
//     let progressContainer = document.getElementById("progressContainer");

//     if (!fileInput) {
//         alert("Please select a file first.");
//         return;
//     }

//     formData.append("file", fileInput);
//     progressContainer.classList.remove("hidden");
//     progressBar.style.width = "0%";

//     let progressInterval = setInterval(() => {
//         let width = parseInt(progressBar.style.width);
//         if (width < 90) {
//             progressBar.style.width = (width + 10) + "%";
//         }
//     }, 500);

//     fetch(endpoint, {
//         method: "POST",
//         body: formData
//     })
//         .then(response => response.text())
//         .then(imagePath => {
//             clearInterval(progressInterval);
//             progressBar.style.width = "100%";
//             setTimeout(() => {
//                 progressContainer.classList.add("hidden");
//             }, 500);

//             if (imagePath.startsWith("/outputs/")) { // Ensure correct response
//                 let fullPath = window.location.origin + imagePath;
//                 document.getElementById("outputImage").src = fullPath;
//                 document.getElementById("downloadLink").href = fullPath;
//                 document.getElementById("result").classList.remove("hidden");
//             } else {
//                 console.log("Error processing image: " + imagePath);
//             }
//         })
//         .catch(error => {
//             clearInterval(progressInterval);
//             progressContainer.classList.add("hidden");
//             console.error("Error:", error);
//         });
// }