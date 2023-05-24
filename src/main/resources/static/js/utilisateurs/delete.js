function deleteUser(element){

    const id = $(element).data("user-id");

    if (confirm('Est-vous sûr de supprimer cet utilisateur ?')) {

        $.ajax({
            url: '/api/admin/clients/'+id,
            type: "DELETE",
            success: function () {
                alert("supprimé avec succès");
                $(element).closest("tr").remove();
            },
            error: function (error) {
                console.log("Error deleting user:", error);
                alert("Une erreur est survenue");
            }
        });
    }

}