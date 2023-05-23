const searchInput = document.getElementById('search-bar')

searchInput.addEventListener('input', () => {
    const input = searchInput.value.trim().toUpperCase();
    updateTableContent(input);
});

function updateTableContent(keyword) {
    $.ajax({
        url: '/api/reservation',
        type: 'GET',
        data: {
            keyword: keyword
        },
        success: function(response) {
            console.log(response)
            const tbody = document.querySelector('.table tbody');
            tbody.innerHTML = '';
            response.forEach(reservation => {
                const tr = document.createElement('tr');
                tr.innerHTML = `
          <td><i class="fab fa-bootstrap fa-lg text-primary"></i> <strong>${reservation.etat}</strong></td>
         
     
          </td>
          <td>
            <div class="dropdown">
              <button type="button" class="btn p-0 dropdown-toggle hide-arrow" data-bs-toggle="dropdown">
                <i class="bx bx-dots-vertical-rounded"></i>
              </button>
              <div class="dropdown-menu">
                <a class="dropdown-item" href="/reservation/${reservation.id}"><i class="bx bx-edit-alt me-2"></i> Edit</a>
                <a class="dropdown-item" onclick="deleteReservation(${reservation.id})"><i class="bx bx-trash me-2"></i> Delete</a>
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
