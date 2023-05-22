const searchInput = document.getElementById('searchBar')

searchInput.addEventListener('input', () => {
    const keyword = searchInput.value.trim();
    updateTableContent(keyword);
});

function updateTableContent(keyword) {
    $.ajax({
        url: '/api/admin/clients',
        type: 'GET',
        data: {
            keyword: keyword
        },
        success: function(response) {
            console.log(response)
            const tbody = document.querySelector('.table tbody');
            tbody.innerHTML = '';

            response.forEach(user => {
                const tr = document.createElement('tr');
                tr.innerHTML = `
          <td><i class="fab fa-bootstrap fa-lg text-primary"></i> <strong>${user.nom}</strong></td>
          <td><i class="fab fa-bootstrap fa-lg text-primary"></i> <strong>${user.prenom}</strong></td>
          <td><i class="fab fa-bootstrap fa-lg text-primary">${user.email}</i></td>
          <td><span class="badge bg-label-warning me-1">${user.role}</span></td>
          <td>
            <ul class="list-unstyled users-list m-0 avatar-group d-flex align-items-center">
              <li
                data-bs-toggle="tooltip"
                data-popup="tooltip-custom"
                data-bs-placement="top"
                class="avatar avatar-xs pull-up"
                title="Avatar"
              >
                <img src="${user.imageUrl}" alt="Avatar" class="rounded-circle" />
              </li>
            </ul>
          </td>
          <td>
            <div class="dropdown">
              <button type="button" class="btn p-0 dropdown-toggle hide-arrow" data-bs-toggle="dropdown">
                <i class="bx bx-dots-vertical-rounded"></i>
              </button>
              <div class="dropdown-menu">
                <a class="dropdown-item" href="/clients/${user.id}"><i class="bx bx-edit-alt me-2"></i> Edit</a>
                <a class="dropdown-item" href="javascript:void(0);"><i class="bx bx-trash me-2"></i> Delete</a>
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
