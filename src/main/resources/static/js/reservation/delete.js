function deleteReservation(element) {

    var reservationId = $(element).data("reservation-id");
    console.log(reservationId)

    if (confirm('Est-vous sûr de supprimer cette reservation')) {

    $.ajax({
        url: "/api/reservation/" + reservationId,
        type: "DELETE",
        success: function () {
            // Remove the deleted reservation from the table
            $(element).closest("tr").remove();
        },
        error: function (error) {
            console.log("Error deleting reservation:", error);
        }
    });
    }
}