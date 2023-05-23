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