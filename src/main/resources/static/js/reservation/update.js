const form = document.getElementById('updateReservationForm');

form.addEventListener('submit', (e) => {
    e.preventDefault();

    const trajetSelect = document.getElementById("trajet");
    const trajetId = trajetSelect.value;

    const stateSelected = document.querySelector('input[name="etat"]:checked').value.toUpperCase()
    const reservationId = document.getElementById("id").value;

    // const userSelected = document.getElementById("user");
    // const userId = userSelected.value;

    // const userId = document.getElementById("idUser");


    const registerRequest = {
        idTrajet: trajetId,
        etat: stateSelected,

    };
 
    console.log(JSON.stringify(registerRequest))

    console.log(reservationId)

    fetch('/api/client/reservation/' + reservationId, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(registerRequest)
    })
        .then(response => {
            if (response.ok) {
                console.log('Reservation updated successfully!');
                alert("enregistré avec succès");
            } else {
                console.error('Reservation update failed.');
                alert("Une erreur est survenue");
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert("Une erreur est survenue");
        });
});

