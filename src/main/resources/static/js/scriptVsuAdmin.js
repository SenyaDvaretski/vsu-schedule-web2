const body = document.querySelector("body"),
      modeToggle = body.querySelector(".mode-toggle");
      sidebar = body.querySelector("nav");
      sidebarToggle = body.querySelector(".sidebar-toggle");
      logoutBtn = document.getElementsByClassName("logoutBtn")[0];


logoutBtn.onclick = function () {
    token = localStorage.getItem("token");
    var myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");
    myHeaders.append("Authorization", "Bearer " + token);
    myHeaders.append("Cookie", "JSESSIONID=C9A5B398E23E08EBC2392F912302C741");



    var requestOptions = {
      method: 'GET',
      headers: myHeaders,
      redirect: 'follow'
    };

    fetch("http://127.0.0.1:5000/rest/auth/logout", requestOptions)
      .then(response => response.text())
      .then(result => {
            localStorage.removeItem("token");
            window.location.href = "/login";


            })

      .catch(error => console.log('error', error));
}


let mode = localStorage.getItem("mode");
if(mode ==="dark"){
    body.classList.toggle("dark");
}

let getStatus = localStorage.getItem("status");
if(getStatus && getStatus ==="close"){
    sidebar.classList.toggle("close");
}

modeToggle.addEventListener("click", () =>{
    body.classList.toggle("dark");
    if(body.classList.contains("dark")){
        localStorage.setItem("mode", "dark");
    }else{
        localStorage.setItem("mode", "light");
    }
});

//sidebarToggle.addEventListener("click", () => {
//    sidebar.classList.toggle("close");
//    if(sidebar.classList.contains("close")){
//        localStorage.setItem("status", "close");
//    }else{
//        localStorage.setItem("status", "open");
//    }
//});
$('#file-input').focus(function() {
    $('label').addClass('focus');
})
.focusout(function() {
    $('label').removeClass('focus');
});
var dropZone = $('#upload-container');
dropZone.on('drag dragstart dragend dragover dragenter dragleave drop', function(){
    return false;
});
dropZone.on('dragover dragenter', function() {
    dropZone.addClass('dragover');
});

dropZone.on('dragleave', function(e) {
    dropZone.removeClass('dragover');
});
dropZone.on('dragleave', function(e) {
    let dx = e.pageX - dropZone.offset().left;
    let dy = e.pageY - dropZone.offset().top;
    if ((dx < 0) || (dx > dropZone.width()) || (dy < 0) || (dy > dropZone.height())) {
         dropZone.removeClass('dragover');
    };
});
dropZone.on('drop', function(e) {
    dropZone.removeClass('dragover');
    let files = e.originalEvent.dataTransfer.files;
    sendFiles(files);
});
$('#file-input').change(function() {
    let files = this.files;
    sendFiles(files);
});
function sendFiles(files) {
    let maxFileSize = 5242880;
    let Data = new FormData();
    $(files).each(function(index, file) {
         if (file.size <= maxFileSize) {
              Data.append('images[]', file);
         }
    });
};
$.ajax({
    url: "/rest/uploadFile",
    type: "POST",
    data: Data,
    contentType: "multipart/form-data",
    dataType: 'json',
    processData: false,
    success: function(data) {
         alert('Файлы были успешно загружены');
    }
});

let descr = querySelector('.descr');
let src = querySelector('.descr_src');
let button = querySelector('.switch');
let container = querySelector('.container ');

