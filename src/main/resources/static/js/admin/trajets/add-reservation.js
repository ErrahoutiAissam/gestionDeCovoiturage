function saveData() {
    var selectedIds = [];
    var checkboxes = document.getElementsByName("selectedContacts");

    for (var i = 0; i < checkboxes.length; i++) {
        if (checkboxes[i].checked) {
            selectedIds.push(checkboxes[i].value);
        }
    }

    const id = document.getElementById('id');

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
                window.location.href = '/admin/trajets/'+id.value;
            } else {
                console.error("Error:", response.status);
            }
        })
        .catch(error => {
            console.error("Error:", error);
        });
}