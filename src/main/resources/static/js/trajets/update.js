const form = document.getElementById('UpdateForm');

form.addEventListener('submit', (event) => {
    event.preventDefault();

    const formData = new FormData(form);
    const registerRequest = {
        id:formData.get('id'),
        villeDepart: formData.get('villeDepart'),
        villeArrive: formData.get('villeArrive'),
        dateDepart:formData.get('dateDepart'),
        nbrPlacesDisponibles: formData.get('nbrPlacesDisponibles'),
        prixParPersonne: formData.get('prixParPersonne'),
    };
console.log(formData);
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
