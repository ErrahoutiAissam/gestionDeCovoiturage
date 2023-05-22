const form = document.getElementById('form');

form.addEventListener('submit', (event) => {
    event.preventDefault();

    const formData = new FormData(form);
    const registerRequest = {
        id: formData.get('id'),
        nom: formData.get('nom'),
        prenom: formData.get('prenom'),
    };

    fetch('/api/clients/update', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(registerRequest)
    })
        .then(response => {
            if (response.ok) {
                console.log('Updated successfully !');
            } else {
                console.error('failed.');
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });
});
