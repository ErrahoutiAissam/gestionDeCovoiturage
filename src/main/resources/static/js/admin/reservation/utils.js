const updateForm = document.getElementById("updateReservationForm");
const createForm = document.getElementById("addReservationForm")


document.getElementById("clear").addEventListener("click",() =>{

    updateForm.reset()
    createForm.reset()
    const radioButtons = document.querySelectorAll('input[type="radio"]');
    radioButtons.forEach(radio => {
        radio.checked = false;
    });
})



