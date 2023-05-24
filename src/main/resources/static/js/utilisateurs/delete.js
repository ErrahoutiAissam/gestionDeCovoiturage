function deleteUser(id){
    if(confirm('Est-vous sûr ?')){
        fetch('/api/clients/'+id, {
            method: 'DELETE',
        })
            .then(response => {
                if (response.ok) {
                    const keyword = document.getElementById('searchBar').value;
                    console.log('Deleted successfully !');
                    alert("supprimé avec succès");
                    updateTableContent(keyword);
                } else {
                    console.error('failed.');
                    alert("Une erreur est survenue");
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert("Une erreur est survenue");
            });
    }

}