function deleteUser(id){
    if(confirm('Est-vous sûr ?')){
        fetch('/api/clients/'+id, {
            method: 'DELETE',
        })
            .then(response => {
                if (response.ok) {
                    const keyword = document.getElementById('searchBar').value;
                    console.log('Deleted successfully !');
                    updateTableContent(keyword);
                } else {
                    console.error('failed.');
                }
            })
            .catch(error => {
                console.error('Error:', error);
            });
    }

}