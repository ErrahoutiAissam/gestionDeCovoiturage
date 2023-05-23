const searchInput = document.getElementById('searchBar')

searchInput.addEventListener('input', () => {
    const keyword = searchInput.value.trim();
    updateTableContent(keyword);
});

function updateTableContent(keyword) {
    $.ajax({
        url: '/api/client/trajets',
        type: 'GET',
        data: {
            keyword: keyword
        },
        success: function(response) {
            console.log(response)
            const tbody = document.querySelector('.table tbody');
            tbody.innerHTML = '';

            response.forEach(trajet => {
                const tr = document.createElement('tr');
                tr.innerHTML = `
          <td><i class="fab fa-bootstrap fa-lg text-primary"></i> <strong>${trajet.villeDepart}</strong></td>
          <td><i class="fab fa-bootstrap fa-lg text-primary"></i> <strong>${trajet.villeArrive}</strong></td>
          <td><i class="fab fa-bootstrap fa-lg text-primary">${trajet.dateDepart}</i></td>
          <td><i class="fab fa-bootstrap fa-lg text-primary">${trajet.nbrPlacesDisponibles}</i></td>
          <td><i class="fab fa-bootstrap fa-lg text-primary">${trajet.prixParPersonne}</i></td>
       
          <td>
            <div class="dropdown">
              <button type="button" class="btn p-0 dropdown-toggle hide-arrow" data-bs-toggle="dropdown">
                <i class="bx bx-dots-vertical-rounded"></i>
              </button>
              <div class="dropdown-menu">
                <a class="dropdown-item" href="/clients/${trajet.id}"><i class="bx bx-edit-alt me-2"></i> Edit</a>
                <a class="dropdown-item" onclick="deleteUser(${trajet.id})"><i class="bx bx-trash me-2"></i> Delete</a>
              </div>
            </div>
          </td>
        `;
                tbody.appendChild(tr);
            });
        },
        error: function(error) {
            console.log("ERROR", error)

        }
    });
}
