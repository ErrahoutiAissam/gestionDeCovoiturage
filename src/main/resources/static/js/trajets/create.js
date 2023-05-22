const form = document.getElementById('trajetForm');

form.addEventListener('submit', (event) => {
    event.preventDefault();

    const formData = new FormData(form);
    const registerRequest = {
        villeDepart: formData.get('villeDepart'),
        villeArrivee: formData.get('villeArrivee'),
        dateDepart: formData.get('dateDepart'),
        nbrPlacesDisponibles: formData.get('nbrPlacesDisponibles'),
        prixParPersonne: formData.get('prixParPersonne')
    };

    fetch('/api/trajets/create', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(registerRequest)
    })
        .then(response => {
            if (response.ok) {
                console.log('trajet ajouté avec succès !');
                form.reset();
            } else {
                console.error('ajout de trajet est échoué.');
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });
});
