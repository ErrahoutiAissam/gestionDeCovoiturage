function deleteTrajet(element) {

    const trajetId = $(element).data("trajet-id");
    console.log(trajetId)

    if (confirm('Est-vous sûr de supprimer ce Trajet')) {

    $.ajax({
        url: "/api/admin/trajets/" + trajetId,
        type: "DELETE",
        success: function () {
            // Remove the deleted trajet from the table
            $(element).closest("tr").remove();
        },
        error: function (error) {
            console.log("Error deleting Trajet:", error);
        }
    });
    }
}