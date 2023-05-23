const searchForm = document.getElementById('searchForm');

searchForm.addEventListener('submit', (event) => {
    event.preventDefault();
    const selectedIds = [];
    const checkboxes = document.getElementsByName("selectedContacts");

    for (let i = 0; i < checkboxes.length; i++) {
        if (checkboxes[i].checked) {
            selectedIds.push(checkboxes[i].value);
        }
    }

    const id = document.getElementById('id_trajet');

    fetch("/api/admin/trajets/"+id.value+"/add-reservation", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(selectedIds)
    })
        .then(response => {
            if (response.ok) {
                console.log("OK");

            } else {
                console.error("Error:", response.status);
            }
        })
        .catch(error => {
            console.error("Error:", error);
        });
})

function reload() {
    const id = document.getElementById('id_trajet');
    window.location.href = '/admin/trajets/'+id.value;
}

function confirmState(trajetId, resId) {
    fetch("/api/admin/trajets/"+trajetId+"/reservations"+resId, {
        method: "PUT",
    })
        .then(response => {
            if (response.ok) {
                console.log("OK");

            } else {
                console.error("Error:", response.status);
            }
        })
        .catch(error => {
            console.error("Error:", error);
        });
}