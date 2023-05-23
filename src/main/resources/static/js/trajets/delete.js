function deleteTrajet(id){
    if(confirm('Est-vous sûr ?')){
        fetch('/api/trajets/'+id, {
            method: 'DELETE',
        })
            .then(response => {
                if (response.ok) {
                    console.log('Deleted successfully !');
                } else {
                    console.error('failed.');
                }
            })
            .catch(error => {
                console.error('Error:', error);
            });
    }

}