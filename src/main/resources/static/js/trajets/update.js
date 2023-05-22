const form = document.getElementById('Updateform');

form.addEventListener('submit', (event) => {
    event.preventDefault();

    const formData = new FormData(form);
    const registerRequest = {
        villeDepart: formData.get('villeDepart'),
        villeArrivee: formData.get('villeArrivee'),
        nbrPlacesDisponibles: formData.get('nbrPlacesDisponibles'),
        PrixParPersonne: formData.get('PrixParPersonne'),
    };

    fetch('/api/trajets/update', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(registerRequest)
    })
        .then(response => {
            if (response.ok) {
                console.log('trajet mis à jour avec succès !');
            } else {
                console.error('mise à jour échouée.');
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });
});
