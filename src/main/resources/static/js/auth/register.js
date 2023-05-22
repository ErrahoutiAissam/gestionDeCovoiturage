const form = document.getElementById('registerForm');

form.addEventListener('submit', function(event) {
    event.preventDefault();
    const nom = document.getElementById('nom').value;
    const prenom = document.getElementById('prenom').value;
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;

    console.log(nom + prenom + email + password)
    const registerRequest = {
        nom: nom,
        prenom: prenom,
        email: email,
        password: password
    };

    fetch('/api/auth/register', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(registerRequest)
    }).then(response => {
        if(response.ok) {
            console.log("registered successfully !!")
        }else {
            console.error(response);
        }
    }).catch((error) => {
        console.error("CATCH", error);
    })
});
