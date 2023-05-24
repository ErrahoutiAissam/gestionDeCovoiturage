const form = document.getElementById('addReservationForm');

form.addEventListener('submit', (e) => {
    e.preventDefault();

    const trajetSelect = document.getElementById("trajet");
    const trajetId = trajetSelect.value;

    const userSelected = document.getElementById("user");
    const userId = userSelected.value;


    const stateSelected = document.querySelector('input[name="etat"]:checked').value.toUpperCase()

    const registerRequest = {
        idTrajet: trajetId,
        etat: stateSelected,
        idUser: userId

    };
    console.log(trajetId)
    console.log(JSON.stringify(registerRequest))

    fetch('/api/admin/reservation/create', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(registerRequest)
    })
        .then(response => {
            if (response.ok) {
                console.log('Reservation added successfully!');
                alert("enregistré avec succès");
                form.reset();
            } else {
                console.error('Reservation adding failed.');
                alert("Une erreur est survenue");
                console.log(response.headers)
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert("Une erreur est survenue");
        });
});
