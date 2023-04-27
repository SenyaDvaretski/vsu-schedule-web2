
loginWithLocalStorage();
const descr = document.querySelector('.white__block_descr'),
      email = document.querySelector('.white__block_email'),
      password = document.querySelector('.white__block_password'),
      btn = document.querySelector('.white__block_button');


function loginWithLocalStorage(){
    var head = new Headers();
           head.append("Content-Type", "text/html");
           if(localStorage.getItem("token") === null) return;
           var token = localStorage.getItem("token")
                          console.log(token)
                          head.append("Authorization", "Bearer "+token);
                           var requestOptions = {
                            method: 'GET',
                            headers: head,
                            redirect: 'follow'
                          };
                          fetch("http://127.0.0.1:5000/vsuAdmin", requestOptions)
                            .then(response => response.text())
                            .then(data => {
                                    window.location.href = "/vsuAdmin"

                                }
                                )

                            .catch(error => console.log('error', error));
                        }

btn.onclick = function() {
    var myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");

    var raw = JSON.stringify({
      "password": password.value + "",
      "login": email.value + ""
    });

    var requestOptions = {
      method: 'POST',
      headers: myHeaders,
      body: raw,
      redirect: 'follow'
    };



    fetch("http://127.0.0.1:5000/rest/auth/authenticate", requestOptions)
      .then(response => response.json())
      .then(data => {
            localStorage.setItem("token",data.token)
        }

      )
      .catch(error => console.log('error', error));
        loginWithLocalStorage()
}
