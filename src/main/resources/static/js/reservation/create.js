const form = document.getElementById('addReservationForm');

form.addEventListener('submit', (e) => {
    e.preventDefault();

    const trajetSelect = document.getElementById("trajet");
    const trajetId = trajetSelect.value;

    const registerRequest = {
        idTrajet: trajetId,
    };
    console.log(trajetId)
    console.log(JSON.stringify(registerRequest))

    fetch('/api/reservation/create', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(registerRequest)
    })
        .then(response => {
            if (response.ok) {
                console.log('Reservation added successfully!');
                form.reset();
            } else {
                console.error('Reservation adding failed.');
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });
});
