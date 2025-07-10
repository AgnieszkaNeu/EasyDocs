import * as pdfjsLib from 'https://cdnjs.cloudflare.com/ajax/libs/pdf.js/5.3.93/pdf.min.mjs';
import Dropzone from "dropzone";

handleDropzone();
const data = await fetchAllDocuments();
displayFiles(data);

async function fetchAllDocuments() {
  const response = await fetch('http://localhost:9090/document', {
    method: "GET",
    credentials: 'include'
  });

  if (response.status === 401) {
    window.location.href = "login.html";
    return; 
  }

  if (!response.ok) {
    throw new Error("Error fetching documents");
  }

  return await response.json();
}

function displayFiles(data){
    const mainDiv = document.getElementById('fileDashboardContainer');

    for (const file of data) {

      /*
      Create card for each file
      */
      const card = document.createElement('div');
      card.classList.add("card", "bg-white/15", "backdrop-blur-md", "hover:shadow-2xl", "hover:bg-white/30");

      const figure = document.createElement('figure');
      const canvas = document.createElement('canvas');
      
      const cardBody = document.createElement('div');
      cardBody.classList.add('card-body');

      const title = document.createElement('h2');
      title.classList.add('card-title');
      title.textContent = file.name;

      const description = document.createElement('p');
      description.textContent = file.description;
      
      getPdfContent(file.doc_id, canvas);

      figure.appendChild(canvas);
      card.appendChild(figure);
      cardBody.appendChild(title);
      cardBody.appendChild(description);
      card.appendChild(cardBody);
      mainDiv.appendChild(card);
    }
}

function getPdfContent(resource_id, canvas){
  fetch(`http://localhost:9090/document/resource/${resource_id}`, {
    method: "GET",
    credentials: 'include'
  })
    .then(response => {
      if (response.status === 401) {
          window.location.href = "login.html";
      } else if (!response.ok) {
          throw new Error("Cannot load file content.");
      }
      return response.arrayBuffer();
    })
    .then(arrayBuffer => {
      pdfjsLib.GlobalWorkerOptions.workerSrc = 'https://cdnjs.cloudflare.com/ajax/libs/pdf.js/5.3.93/pdf.worker.min.mjs';

      const loadingTask = pdfjsLib.getDocument({ data: arrayBuffer });
      return loadingTask.promise;
    })
    .then(pdf => {
      return pdf.getPage(1);
    })
    .then(page => {
      const scale = 0.85;
      const viewport = page.getViewport({ scale });

      const context = canvas.getContext('2d');
      canvas.height = 200;
      canvas.width = 380;

      return page.render({ canvasContext: context, viewport }).promise;
    })
  .catch(error => {
    console.error("Wystąpił błąd:", error);
  });
}

function handleDropzone() {
  window.addEventListener("DOMContentLoaded", () => {
    const confirmForm = document.getElementById("uploadConfirmModal");
    const fileName = document.getElementById("fileName");
    const confirmBtn = document.getElementById("confirmUploadBtn");
    const cancelBtn = document.getElementById("cancelUploadBtn");

    let pendingFile = null;

    const myDropzone = new Dropzone("#dropFile", {
      url: "http://localhost:9090/document",
      withCredentials: true,
      paramName: "file",
      maxFilesize: 5,

      accept(file, done) {
        pendingFile = file;
        confirmForm.removeAttribute("hidden");
        fileName.setAttribute("placeholder", file.name);

        confirmBtn.onclick = () => {
          done(); 
          confirmForm.setAttribute("hidden", "true");
          pendingFile = null;
          window.location.reload();
        };

        cancelBtn.onclick = () => {
          if (pendingFile) {
            myDropzone.removeFile(file);
          }
          confirmForm.setAttribute("hidden", "true");
          pendingFile = null;
        };
      }
    });

    myDropzone.on("error", (file, errorMessage) => {
      if (errorMessage.includes("too big")) {
        showToast(`File is too big. Max size: ${myDropzone.options.maxFilesize} MB`);
        myDropzone.removeFile(file);
      }
    });
    
  });
}

function showToast(message, duration = 6000) {
  const toast = document.createElement("div");
  toast.className = "toast";
  toast.textContent = message;
  document.body.appendChild(toast);

  setTimeout(() => toast.classList.add("show"), 100);

  setTimeout(() => {
    toast.classList.remove("show");
    setTimeout(() => document.body.removeChild(toast), 300);
  }, duration);
}
