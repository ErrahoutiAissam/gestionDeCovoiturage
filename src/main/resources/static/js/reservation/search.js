const searchInput = document.getElementById('searchBar')

searchInput.addEventListener('input', () => {
    const keyword = searchInput.value.trim();
    updateTableContent(keyword);
});

function updateTableContent(keyword) {
    $.ajax({
        url: '/api/reservation', type: 'GET', data: {
            keyword: keyword
        }, success: function (response) {
            console.log(response)
            const tbody = document.querySelector('.table tbody');
            tbody.innerHTML = '';

            response.forEach(reservation => {
                const tr = document.createElement('tr');
                tr.innerHTML = `<tr>
  
                                <td>
                                        <i class="fab fa-bootstrap fa-lg text-primary"></i> <strong th:text="${reservation.utilisateur.nom}"></strong>
                                </td>
                                <td>
                                    <i class="fab fa-bootstrap fa-lg text-primary"></i>
                                    <span>Date Depart:</span>
                                    <strong th:text="${reservation.trajet.getDateDepart()}"></strong>
                                    <br/>
                                    <span>Ville Depart:</span>
                                    <strong th:text="${reservation.trajet.getVilleDepart()}"></strong>
                                    <br/>
                                    <span>Ville D'arrive:</span>
                                    <strong th:text="${reservation.trajet.getVilleArrive()}"></strong>
                                </td>
                                <td>
                                    <i class="fab fa-bootstrap fa-lg text-primary" th:text="${reservation.etat}"></i>
                                </td>
                                <td>
                                    <div class="dropdown" >
                                        <button type="button" class="btn p-0 dropdown-toggle hide-arrow" data-bs-toggle="dropdown">
                                            <i class="bx bx-dots-vertical-rounded"></i>
                                        </button>
                                        <div class="dropdown-menu">
                                            <a class="dropdown-item" th:href="@{/client/reservations/{id}(id=${reservation.getId()})}"><i class="bx bx-edit-alt me-2"></i> Edit</a
                                            >
                                            <a class="dropdown-item"  th:data-reservation-id="${reservation.id}" onclick="deleteReservation(this)" >
                                            <i class="bx bx-trash me-2"></i> Delete</a
                                            >
                                        </div>
                                    </div>
                                </td>
                                </tr>
        `;
                tbody.appendChild(tr);
            });
        }, error: function (error) {
            console.log("ERROR", error)

        }
    });
}
