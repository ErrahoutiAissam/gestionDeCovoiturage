const form = document.getElementById('updateReservationForm');

form.addEventListener('submit', (e) => {
    e.preventDefault();

    const trajetSelect = document.getElementById("trajet");
    const trajetId = trajetSelect.value;

    const stateSelected = document.querySelector('input[name="etat"]:checked').value.toUpperCase()
    const reservationId = document.getElementById("id").value;

    const userSelected = document.getElementById("user");
    const userId = userSelected.value;

    // const userId = document.getElementById("idUser");


    const registerRequest = {
        idTrajet: trajetId,
        etat: stateSelected,
        idUser: userId

    };
 
    console.log(JSON.stringify(registerRequest))

    console.log(reservationId)

    fetch('/api/admin/reservation/' + reservationId, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(registerRequest)
    })
        .then(response => {
            if (response.ok) {
                console.log('Reservation updated successfully!');
            } else {
                console.error('Reservation update failed.');
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });
});

