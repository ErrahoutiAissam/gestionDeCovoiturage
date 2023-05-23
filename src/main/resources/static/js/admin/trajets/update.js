const form = document.getElementById('trajetForm');

form.addEventListener('submit', (event) => {
    event.preventDefault();

    const formData = new FormData(form);

    const trajetDTO = {
        id: formData.get('id'),
        villeDepart: formData.get('villeDepart'),
        villeArrive: formData.get('villeArrive'),
        dateDepart: formData.get('dateDepart'),
        nbrPlacesDisponibles: formData.get('nbrPlacesDisponibles'),
        prixParPersonne: formData.get('prixParPersonne')
    };

    fetch('/api/admin/trajets/update', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(trajetDTO)
    })
        .then(response => {
            if (response.ok) {
                console.log('trajet modifié avec succès !');
                form.reset();
            } else {
                console.error('modification de trajet est échoué.');
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });
});
