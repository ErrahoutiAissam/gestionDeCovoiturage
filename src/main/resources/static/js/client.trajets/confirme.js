function confirmState(trajetId, resId) {
    fetch(`/api/client/trajets/${trajetId}/confirm?resId=${resId}`, {
        method: "PUT",
    })
        .then(response => {
            if (response.ok) {
                console.log("OK");
                alert("confirmé avec success")

            } else {
                console.error("Error:", response.status);
            }
        })
        .catch(error => {
            console.error("Error:", error);
        });
}