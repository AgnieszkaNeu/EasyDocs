document.getElementById("signin").addEventListener("click", function(e) {
    e.preventDefault();
    const email = document.getElementById("email").value.trim();
    const password = document.getElementById("password").value.trim();
    document.getElementById("invalid-credentials").hidden = true;
    document.getElementById("empty-email").hidden = true;
    document.getElementById("empty-password").hidden = true;
    document.getElementById("email-label").hidden = false;
    document.getElementById("password-label").hidden = false;

    console.log(password)
    console.log(email)

    let login = true
    if (email === "") {
        document.getElementById("empty-email").removeAttribute("hidden");
        document.getElementById("email-label").hidden = true;
        login = false;
    }
    if (password === "") {
        document.getElementById("empty-password").removeAttribute("hidden");
        document.getElementById("password-label").hidden = true;
        login = false;
    }

    if (login) {
        fetch('http://localhost:9090/login', {
            method: "POST",
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            credentials: 'include',
            body: new URLSearchParams({
                username: email,
                password: password
              })
        }).then(response => {
            if(response.ok){
                window.location.href = "dashboard.html";
            }
            else{
                document.getElementById("invalid-credentials").removeAttribute("hidden");
                //document.getElementById("password-div").classList.replace("mb-4", "mb-1");
                //document.getElementById("password").classList.add("is-invalid");
                //document.getElementById("username").classList.add("is-invalid");
            }
        }).catch(error => console.error("Błąd: " + error));
    }

});