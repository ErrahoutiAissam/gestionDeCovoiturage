const form = document.getElementById('registerForm');

form.addEventListener('submit', (event) => {
    event.preventDefault();

    const formData = new FormData(form);
    const registerRequest = {
        nom: formData.get('nom'),
        prenom: formData.get('prenom'),
        email: formData.get('email'),
        password: formData.get('password'),
        role: formData.get('typesUser')
    };

    fetch('/api/admin/clients/create', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(registerRequest)
    })
        .then(response => {
            if (response.ok) {
                console.log('User registered successfully!');
                form.reset();
            } else {
                console.error('User registration failed.');
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });
});
