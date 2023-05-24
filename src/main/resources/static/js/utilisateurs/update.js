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
                alert("enregistré avec succès");
            } else {
                console.error('failed.');
                alert("Une erreur est survenue");
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert("Une erreur est survenue");
        });
});


const fileInput = document.getElementById('upload');

fileInput.addEventListener('change', (event) => {
    const file = event.target.files[0];
    const id = document.getElementById('id').value;

    const formData = new FormData();
    formData.append('file', file);
    const uploadedAvatar = document.getElementById('uploadedAvatar');
    fetch(`/api/clients/${id}/image`, {
        method: 'PUT',
        body: formData
    })
        .then(response => {
            if (response.ok) {
                console.log('Image updated successfully!');
                uploadedAvatar.src = URL.createObjectURL(file);
            } else {
                console.error('Error updating image:', response.statusText);
                alert("Une erreur est survenue");
            }
        })
        .catch(error => {
            console.error('Request failed:', error);
            alert("Une erreur est survenue");
        });
});


