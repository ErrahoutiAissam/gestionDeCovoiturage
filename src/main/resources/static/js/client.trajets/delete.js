function deleteTrajet(element) {

    const trajetId = $(element).data("trajet-id");
    console.log(trajetId)

    if (confirm('Est-vous sûr de supprimer ce Trajet')) {


        $.ajax({
            url: "/api/admin/trajets/" + trajetId,
            type: "DELETE",
            success: function () {
                alert("supprimé avec succès");
                $(element).closest("tr").remove();
            },
            error: function (error) {
                console.log("Error deleting Trajet:", error);
                alert("Une erreur est survenue");
            }
        });
    }
}