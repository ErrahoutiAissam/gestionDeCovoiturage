const confirmeBtns = document.querySelectorAll("#confirme-res");

confirmeBtns.forEach(confirmeBtn => {
    confirmeBtn.addEventListener("click", () => {
        const trajetId = confirmeBtn.parentElement.querySelector('input[name="trajet_id"]').value;
        const resId = confirmeBtn.parentElement.querySelector('input[name="res_id"]').value;

        console.log(trajetId);
        console.log(resId);

        fetch(trajetId + '/reservations/' + resId, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
        })
            .then(response => {
                if (response.ok) {
                    console.log('Success!');
                    // Perform any further actions if needed
                } else {
                    console.error('Request failed.');
                    // Handle the error case if needed
                }
            })
            .catch(error => {
                console.error('Error:', error);
                // Handle any network or other errors
            });
    });
});
